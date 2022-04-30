import java.util.List;

public class Main {

    public static void main(String[] args) {
        TrainData trainData = TrainData.fromCSVResource("BritishTrainFatalities.csv");
        if (trainData == null) {
            System.out.println("Couldn't load CSV file");
            return;
        }

        DecadeStats decadeStats = new DecadeStats(trainData);

        List<YearStats> stats = decadeStats.total();
        printStats(stats, "Total per decade ====================");
        stats = decadeStats.average();
        printStats(stats, "\nAverage per decade ====================");
    }

    private static void printStats(List<YearStats> stats, String header) {
        System.out.println(header);
        for(YearStats year: stats) {
            System.out.println(year);
        }
    }
}
