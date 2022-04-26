import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

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
        decadeStats.total();
        decadeStats.average();
    }
}
