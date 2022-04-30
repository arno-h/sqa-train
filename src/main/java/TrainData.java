import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TrainData {
    private final ArrayList<YearStats> data = new ArrayList<>();

    public YearStats getRow(int row)  {
        return data.get(row);
    }

    public int numRows() {
        return data.size();
    }

    void readCSV(InputStream csv) {
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
            try {
                yearStats.moveAcc += scanner.nextFloat();
            } catch (InputMismatchException e) {
                if (scanner.hasNext("NA")) {
                    yearStats.moveAcc = null;
                }
            }
            try {
                yearStats.moveFatal += scanner.nextFloat();
            } catch (InputMismatchException e) {
                if (scanner.hasNext("NA")) {
                    yearStats.moveFatal = null;
                }
            }
            data.add(yearStats);
            scanner.nextLine();
        }
    }
}
