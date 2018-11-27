package umbrella.nio.scatterandgather;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatterAndGather {

    public void scatteringReads() throws Exception {
        try (
                // 创建文件通道
                FileChannel readChannel = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data.txt",
                        "rw").getChannel()
        ) {
            // 创建两个字节缓存区
            ByteBuffer buf1 = ByteBuffer.allocate(5);
            ByteBuffer buf2 = ByteBuffer.allocate(5);
            ByteBuffer bufArray[] = {buf1, buf2};

            // Scattering Reads
            readChannel.read(bufArray);
            printBuffer(buf1);
            printBuffer(buf2);

        }
    }

    public void gatheringWrites() throws Exception {
        try (
                // 创建文件通道
                FileChannel writeChannel = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data-w.txt",
                        "rw").getChannel()
        ) {
            // 创建两个字节缓冲区
            ByteBuffer buf1 = ByteBuffer.allocate(2);
            ByteBuffer buf2 = ByteBuffer.allocate(2);
            ByteBuffer bufArray[] = {buf1, buf2};

            String strArray[] = {"a", "b", "c", "d"};
            //写简单数据到缓冲区
            for (int i = 0; i < strArray.length; i++) {
                if (i < 2) {
                    buf1.put(strArray[i].getBytes());
                } else {
                    buf2.put(strArray[i].getBytes());
                }
            }

            // Gathering Writes
            buf1.flip();
            buf2.flip();
            writeChannel.write(bufArray);
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
