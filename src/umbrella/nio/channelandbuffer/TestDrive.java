package umbrella.nio.channelandbuffer;

public class TestDrive {
    public static void main(String[] args) {
        ChannelAndBufferTest ct = new ChannelAndBufferTest();
        try {
            //ct.readAndWriteFileViaChannelAndBuffer();
            ct.testBufferEquals();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
