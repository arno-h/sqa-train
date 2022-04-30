import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainData {
    private final ArrayList<YearStats> data = new ArrayList<>();

    public YearStats getRow(int row) {
        return data.get(row);
    }

    public int numRows() {
        return data.size();
    }

    public void readCSV(InputStream csv) {
        Scanner scanner = new Scanner(csv);
        scanner.nextLine(); // ignore first line
        scanner.useDelimiter(", *|\n");
        while (scanner.hasNext()) {
            YearStats yearStats = new YearStats();
            yearStats.year = scanner.nextInt();
            yearStats.ridesMkm = scanner.nextFloat();
            yearStats.collAcc = scanner.nextFloat();
            yearStats.collFatal = scanner.nextFloat();
            yearStats.roadAcc = scanner.nextFloat();
            yearStats.roadFatal = scanner.nextFloat();
            yearStats.moveAcc = scanner.nextFloat();
            yearStats.moveFatal = scanner.nextFloat();
            scanner.nextLine();
            data.add(yearStats);
        }
    }


    public static TrainData fromCSVResource(String resourceName) {
        URL pathURL = TrainData.class.getResource(resourceName);
        if (pathURL == null) {
            return null;
        }
        try {
            File csv = new File(pathURL.toURI());
            TrainData trainData = new TrainData();
            trainData.readCSV(new FileInputStream(csv));
            return trainData;
        } catch(URISyntaxException | FileNotFoundException e) {
            return null;
        }
    }

    public static TrainData fromString(String csv) {
        ByteArrayInputStream csvStream = new ByteArrayInputStream(csv.getBytes());
        TrainData trainData = new TrainData();
        trainData.readCSV(csvStream);
        return trainData;
    }
}
