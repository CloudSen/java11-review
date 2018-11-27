package umbrella.nio.channel;

public class TestDrive {
    public static void main(String[] args) {
        ChannelTest ct = new ChannelTest();
        try {
            ct.fileChannelTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
