package umbrella.lambda.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HumanTest {

    /**
     * java8 之前的用法
     */
    @Test
    public void collectionsSortingBeforeJava8() {

        List<Human> humans = Arrays.asList(
                new Human("sweetyun", 24),
                new Human("cloudsen", 23),
                new Human("cloudsen", 21)
        );

        /*
        使用Collections的sort方法,自定义姓名字母顺序排序
        需要传入一个实现了Comparator接口的对象
        这里使用匿名内部类
        适用于排序算法临时在这里使用一次
         */
        System.out.println("=".repeat(40));
        System.out.println("before name ascending sorting...");
        humans.forEach(System.out::println);
        Collections.sort(humans, new Comparator<Human>() {
            // ascending
            @Override
            public int compare(Human o1, Human o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println("after name ascending sorting...");
        humans.forEach(System.out::println);

        /*
        使用Collections的sort方法,根据名字和年龄倒序排序
        需要传入一个实现了Comparator接口的对象
        这里使用自定义的，实现了Comparator接口的对象
        适用于排序算法在多处使用时，减少代码量
         */
        System.out.println("=".repeat(40));
        System.out.println("before name and age descending sorting...");
        humans.forEach(System.out::println);
        Collections.sort(humans, new Human.SortByNameThenAge());
        System.out.println("after name and age descending sorting...");
        humans.forEach(System.out::println);

    }

    /**
     * java8 collections的lambda版本
     */
    @Test
    public void collectionsSortingViaLambda() {
        List<Human> humans = Arrays.asList(
                new Human("sweetyun", 23),
                new Human("cloudsen", 40),
                new Human("cloudsen", 19)
        );

        /*
        使用Collections的sort方法,自定义年龄倒序排序
        需要传入一个实现了Comparator接口的对象
        这里将匿名内部类转换为lambda function
         */
        System.out.println("=".repeat(40));
        System.out.println("before age descending sorting...");
        humans.forEach(System.out::println);
        Collections.sort(humans, (o1, o2) -> o2.getAge() - o1.getAge());
        System.out.println("after age descending sorting...");
        humans.forEach(System.out::println);


        /*
        使用Collections的sort方法,根据名字和年龄倒序排序
        需要传入一个实现了Comparator接口的对象
        这里可以传入静态方法
         */
        System.out.println("=".repeat(40));
        System.out.println("before name and age ascending sorting...");
        humans.forEach(System.out::println);
        Collections.sort(humans, Human::sortByNameThenAge);
        System.out.println("after name and age ascending sorting...");
        humans.forEach(System.out::println);
    }

    /**
     * java8 List.sort()
     */
    @Test
    public void listSortViaLambda() {

        List<Human> humans = Arrays.asList(
                new Human("sweetyun", 23),
                new Human("cloudsen", 40),
                new Human("cloudsen", 19)
        );

        /*
        使用1.8 List的默认方法sort,单条件排序
        这里和Collections.sort(humans, (o1, o2) -> o2.getAge() - o1.getAge());是等价的
         */
        humans.sort((o1, o2) -> o2.getAge() - o1.getAge());

        /*
        使用1.8 List的默认方法sort
        这里可以传入静态方法
        这里和Collections.sort(humans, Human::sortByNameThenAge);是等价的
         */
        humans.sort(Human::sortByNameThenAge);


        /*
        使用1.8的lambda来进行多条件排序。
         */
        humans.sort((o1, o2) -> {
            if (o1.getName().equals(o2.getName())) {
                return o1.getAge() - o2.getAge();
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        });

        /*
        使用1.8给Comparator接口新增的comparing静态方法
        它接收一个函数
        通过接收的函数返回一个可用的Comparator对象
         */
        humans.sort(Comparator.comparing(Human::getName));

        /*
        使用1.8给Comparator接口新增的comparing静态方法
        这里可以链式的进行多条件排序
         */
        humans.sort(Comparator.comparing(Human::getName).thenComparing(Human::getAge));

        /*
        Comparator 还有reversed()默认方法
        用于反转排序结果
         */
        System.out.println("=".repeat(40));
        System.out.println("before reversing sorting via name and age...");
        humans.forEach(System.out::println);
        humans.sort(Comparator.comparing(Human::getName).thenComparing(Human::getAge).reversed());
        System.out.println("after reversing sorting via name and age...");
        humans.forEach(System.out::println);
    }
}