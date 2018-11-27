package umbrella.nio.scatterandgather;

public class TestDrive {
    public static void main(String[] args) {
        ScatterAndGather sg = new ScatterAndGather();
        try {
            //sg.scatteringReads();
            sg.gatheringWrites();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
