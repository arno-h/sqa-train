import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
        FileInputStream csvStream = new FileInputStream(csv);

        TrainData trainData = new TrainData();
        trainData.readCSV(csvStream);

        PrintWriter out = new PrintWriter(System.out, true);
        DecadeStats decadeStats = new DecadeStats(trainData, out);
        decadeStats.total();
        decadeStats.average();
    }
}
