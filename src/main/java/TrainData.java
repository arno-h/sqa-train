import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainData {
    private final ArrayList<YearStats> data = new ArrayList<>();
    private final Scanner scanner;

    public TrainData(InputStream csv) {
        this.scanner = new Scanner(csv);
        scanner.useDelimiter(", *|\n");
    }

    public YearStats getRow(int row)  {
        return data.get(row);
    }

    public int numRows() {
        return data.size();
    }

    private void readCSV() {
        scanner.nextLine(); // ignore first line
        while (scanner.hasNext()) {
            YearStats yearStats = new YearStats();
            yearStats.year = scanner.nextInt();
            yearStats.ridesMkm = readFloat();
            yearStats.collAcc = readFloat();
            yearStats.collFatal = readFloat();
            yearStats.roadAcc = readFloat();
            yearStats.roadFatal = readFloat();
            yearStats.moveAcc = readFloat();
            yearStats.moveFatal = readFloat();
            data.add(yearStats);
        }
    }

    private Float readFloat() {
        if (scanner.hasNext("NA")) {
            scanner.next("NA");
            return null;
        }
        return scanner.nextFloat();
    }

    public static TrainData fromCSVResource(String resourceName) {
        URL pathURL = TrainData.class.getResource(resourceName);
        if (pathURL == null) {
            return null;
        }
        try {
            File csv = new File(pathURL.toURI());
            TrainData trainData = new TrainData(new FileInputStream(csv));
            trainData.readCSV();
            return trainData;
        } catch(URISyntaxException | FileNotFoundException e) {
            return null;
        }
    }

    public static TrainData fromString(String csv) {
        ByteArrayInputStream csvStream = new ByteArrayInputStream(csv.getBytes());
        TrainData trainData = new TrainData(csvStream);
        trainData.readCSV();
        return trainData;
    }
}
