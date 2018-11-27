package umbrella.nio.channelandbuffer;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelAndBufferTest {
    public void readAndWriteFileViaChannelAndBuffer() throws Exception {
        try (
                // 创建文件通道
                FileChannel readChannel = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data.txt",
                        "rw").getChannel();
                FileChannel writeChannel = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data-w.txt",
                        "rw").getChannel();
        ) {
            // 创建内存空间大小为5的字节缓存区
            ByteBuffer buf = ByteBuffer.allocate(5);
            // 通过文件通道，读取文件中的数据，写到缓存区
            int bytesRead = readChannel.read(buf);

            while (bytesRead != -1) {
                System.out.println("Read: " + bytesRead);
                // 缓存区写模式转为读模式，设置limit等于当前的位置position，然后position置为0，capacity不变
                buf.flip();
                // 读取buffer数据，通过文件通道将数据写入文件
                writeChannel.write(buf);
                // 将postion清零，limit不变
                buf.rewind();
                // 直接从buffer中读取数据，并打印
                while (buf.hasRemaining()) {
                    System.out.println((char) buf.get());
                }
                // 清除缓冲区，缓冲区读模式转换为写模式，将position设为0，limit设置为与capacity相同
                buf.clear();
                bytesRead = readChannel.read(buf);
            }
        }

    }

    public void testBufferEquals() throws Exception {
        try (
                // 创建文件通道
                FileChannel readChannel = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data.txt",
                        "rw").getChannel();
                FileChannel readChannel2 = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data.txt",
                        "rw").getChannel();
                FileChannel readChannel3 = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data.txt",
                        "rw").getChannel();
                FileChannel readChannel4 = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data.txt",
                        "rw").getChannel()
        ) {
            // 创建三个内存空间大小为5的字节缓存区
            ByteBuffer buf5 = ByteBuffer.allocate(5);
            ByteBuffer buf5a = ByteBuffer.allocate(5);
            ByteBuffer buf5b = ByteBuffer.allocate(5);
            // 创建内存空间大小为10的字节缓存区
            ByteBuffer buf10 = ByteBuffer.allocate(10);

            System.out.println("=".repeat(20));
            // true: position到limit之间元素数量相同
            System.out.println("buf5 equals to buf5a? " + buf5.equals(buf5a));
            // false：position到limit之间元素数量不相同
            System.out.println("buf5 equals to buf10? " + buf5.equals(buf10));
            // false
            System.out.println("buf5a equals to buf10? " + buf5a.equals(buf10));

            // 使用相同的通道，装满buffer
            readChannel.read(buf5);
            readChannel.read(buf5a);
            readChannel.read(buf10);

            // 读取数据后，position和limit在同一元素，没有剩余元素。
            System.out.println("=".repeat(20));
            // true
            System.out.println("buf5 equals to buf5a? " + buf5.equals(buf5a));
            // true
            System.out.println("buf5 equals to buf10? " + buf5.equals(buf10));
            // true
            System.out.println("buf5a equals to buf10? " + buf5a.equals(buf10));

            /*
            buf5 content:
            abcde
            buf5a content:
            fghij
            buf10 content:
            klmnopqrst
             */
            System.out.println("=".repeat(20));
            System.out.println("buf5 content:");
            this.printBuffer(buf5);
            System.out.println("buf5a content:");
            this.printBuffer(buf5a);
            System.out.println("buf10 content:");
            this.printBuffer(buf10);

            // 重置position
            buf5.rewind();
            buf5a.rewind();
            buf10.rewind();

            System.out.println("=".repeat(20));
            // false 剩余元素数量相同但是内容不同
            System.out.println("buf5 equals to buf5a? " + buf5.equals(buf5a));
            // false 剩余元素数量不相同
            System.out.println("buf5 equals to buf10? " + buf5.equals(buf10));
            // false 剩余元素数量不相同
            System.out.println("buf5a equals to buf10? " + buf5a.equals(buf10));

            // 清空buffer，通过不同channel读取相同的内容
            buf5.clear();
            buf5a.clear();
            buf10.clear();
            readChannel2.read(buf5);
            readChannel3.read(buf5a);
            readChannel3.read(buf5b);
            readChannel4.read(buf10);

            /*
            buf5 content:
            abcde
            buf5a content:
            abcde
            buf5b content:
            fghij
            buf10 content:
            abcdefghij
             */
            System.out.println("=".repeat(20));
            System.out.println("buf5 content:");
            this.printBuffer(buf5);
            System.out.println("buf5a content:");
            this.printBuffer(buf5a);
            System.out.println("buf5b content:");
            this.printBuffer(buf5b);
            System.out.println("buf10 content:");
            this.printBuffer(buf10);


            // 重置position
            buf5.rewind();
            buf5a.rewind();
            buf10.rewind();

            System.out.println("=".repeat(20));
            // true 剩余元素数量相同, 且内容也相同都是abcde
            System.out.println("buf5 equals to buf5a? " + buf5.equals(buf5a));
            // false 剩余元素数量不相同
            System.out.println("buf5 equals to buf10? " + buf5.equals(buf10));
            // false 剩余元素数量相同, 但内容不同，一个是abcde一个是fghij
            System.out.println("buf5a equals to buf5b? " + buf5a.equals(buf5b));
            // false 剩余元素数量不相同
            System.out.println("buf5a equals to buf10? " + buf5a.equals(buf10));
        }
    }

    private void printBuffer(ByteBuffer byteBuffer) {
        StringBuilder stringBuilder = new StringBuilder();
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            stringBuilder.append((char) byteBuffer.get());
        }
        System.out.println(stringBuilder.toString());
    }
}
