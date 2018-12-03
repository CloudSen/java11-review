package umbrella.lambda.sort;

import java.util.Comparator;

public class Human {

    private String name;
    private int age;

    public Human(){

    }

    public Human(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Human setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Human setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static int sortByNameThenAge(Human o1, Human o2) {
        if (o1.getName().equals(o2.getName())) {
            return o1.getAge() - o2.getAge();
        } else {
            return o1.getName().compareTo(o2.getName());
        }
    }

    static class SortByNameThenAge implements Comparator<Human> {
        @Override
        public int compare(Human o1, Human o2) {
            if (o1.getName().equals(o2.getName())) {
                return o2.getAge() - o1.getAge();
            } else {
                return o2.getName().compareTo(o1.getName());
            }
        }
    }
}
