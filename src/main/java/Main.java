import java.util.List;

public class Main {

    public static void main(String[] args) {
        TrainData trainData = TrainData.fromCSVResource("BritishTrainFatalities.csv");
        if (trainData == null) {
            System.out.println("Couldn't load CSV file");
            return;
        }

        DecadeStats decadeStats = new DecadeStats(trainData);

        List<YearStats> sumStats = decadeStats.total();
        System.out.println("Total per decade ====================");
        for (YearStats decade: sumStats) {
            System.out.println(decade);
        }

        List<YearStats> avgStats = decadeStats.average();
        System.out.println("\nAverage per decade ====================");
        for (YearStats decade: avgStats) {
            System.out.println(decade);
        }
    }
}
