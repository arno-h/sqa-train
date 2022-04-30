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
            yearStats.ridesMkm = readFloat(scanner);
            yearStats.collAcc = readFloat(scanner);
            yearStats.collFatal = readFloat(scanner);
            yearStats.roadAcc = readFloat(scanner);
            yearStats.roadFatal = readFloat(scanner);
            yearStats.moveAcc = readFloat(scanner);
            yearStats.moveFatal = readFloat(scanner);
            data.add(yearStats);
        }
    }

    Float readFloat(Scanner scanner) {
        if (scanner.hasNext("NA")) {
            scanner.next("NA");
            return null;
        }
        return scanner.nextFloat();
    }
}
