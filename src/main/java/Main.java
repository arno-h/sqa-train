import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        URL pathURL = Main.class.getResource("BritishTrainFatalities.csv");
        if (pathURL == null) {
            System.out.println("Couldn't load CSV file");
            return;
        }
        File csv = new File(pathURL.toURI());
        FileInputStream csvStream = new FileInputStream(csv);

        TrainData trainData = new TrainData();
        trainData.readCSV(csvStream);

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
