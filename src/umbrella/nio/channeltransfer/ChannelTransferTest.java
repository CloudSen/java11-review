package umbrella.nio.channeltransfer;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class ChannelTransferTest {

    public void transferFromTest() throws Exception {
        try (
                FileChannel fromChannel = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data.txt",
                        "rw").getChannel();
                FileChannel toChannel = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data-w.txt",
                        "rw").getChannel()
        ) {
            long position = 0;
            long count = fromChannel.size();
            toChannel.transferFrom(fromChannel, position, count);
        }
    }

    public void transferToTest() throws Exception {
        try (
                FileChannel channel = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data.txt",
                        "rw").getChannel();
                FileChannel toChannel = new RandomAccessFile("/home/cloudsen/work/java/learning/java11-review/src/umbrella/nio/nio-data-w.txt",
                        "rw").getChannel()
        ) {
            long position = 0;
            long count = channel.size();
            channel.transferTo(position, count, toChannel);
        }
    }
}
