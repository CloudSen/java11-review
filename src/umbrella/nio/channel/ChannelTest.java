package umbrella.nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTest {
    public void fileChannelTest() throws Exception {
        RandomAccessFile aFile = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data.txt", "rw");
        // 创建一个文件通道
        FileChannel readChannel = aFile.getChannel();
        // 创建内存空间大小为5的字节缓存区
        ByteBuffer buf = ByteBuffer.allocate(5);
        // 通过文件通道，读取文件中的数据，写到缓存区
        int bytesRead = readChannel.read(buf);

        while (bytesRead != -1) {
            System.out.println("Read: " + bytesRead);
            // 缓存区写模式转为读模式，设置limit等于当前的位置position，然后position置为0，capacity不变
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.println((char) buf.get());
            }
            // 缓冲区读模式转换为写模式，在当前的位置，将position设为0，limit设置为与capacity相同
            buf.clear();
            bytesRead = readChannel.read(buf);
        }
        aFile.close();
    }
}
