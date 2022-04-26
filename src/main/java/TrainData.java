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
            yearStats.year += scanner.nextInt();
            yearStats.rides_mkm += scanner.nextInt();
            yearStats.coll_acc += scanner.nextInt();
            yearStats.coll_fatal += scanner.nextInt();
            yearStats.road_acc += scanner.nextInt();
            yearStats.road_fatal += scanner.nextInt();
            try {
                yearStats.move_acc += scanner.nextInt();
            } catch (InputMismatchException e) {
                if (scanner.hasNext("NA")) {
                    yearStats.move_acc = null;
                }
            }
            try {
                yearStats.move_fatal += scanner.nextInt();
            } catch (InputMismatchException e) {
                if (scanner.hasNext("NA")) {
                    yearStats.move_fatal = null;
                }
            }
            yearStats.calcDecade();
            data.add(yearStats);
            scanner.nextLine();
        }
    }
}
