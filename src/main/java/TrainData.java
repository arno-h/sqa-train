import java.io.InputStream;
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
            yearStats.ridesMkm = scanner.nextInt();
            yearStats.collAcc = scanner.nextInt();
            yearStats.collFatal = scanner.nextInt();
            yearStats.roadAcc = scanner.nextInt();
            yearStats.roadFatal = scanner.nextInt();
            yearStats.moveAcc = scanner.nextInt();
            yearStats.moveFatal = scanner.nextInt();
            scanner.nextLine();
            yearStats.calcDecade();
            data.add(yearStats);
        }
    }
}
