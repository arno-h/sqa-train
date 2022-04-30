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
        TrainData trainData = new TrainData();
        trainData.readCSV(new FileInputStream(csv));

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
