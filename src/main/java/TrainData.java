import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainData {
    final ArrayList<Integer> year = new ArrayList<>();
    final ArrayList<Integer> rides_mkm = new ArrayList<>();
    final ArrayList<Integer> coll_acc = new ArrayList<>();
    final ArrayList<Integer> coll_fatal = new ArrayList<>();
    final ArrayList<Integer> road_acc = new ArrayList<>();
    final ArrayList<Integer> road_fatal = new ArrayList<>();
    final ArrayList<Integer> move_acc = new ArrayList<>();
    final ArrayList<Integer> move_fatal = new ArrayList<>();
    final ArrayList<Integer> decade = new ArrayList<>();

    public void readCSV(InputStream csv) {
        Scanner scanner = new Scanner(csv);
        scanner.nextLine(); // ignore first line
        scanner.useDelimiter(", *|\n");
        while (scanner.hasNext()) {
            year.add(scanner.nextInt());
            rides_mkm.add(scanner.nextInt());
            coll_acc.add(scanner.nextInt());
            coll_fatal.add(scanner.nextInt());
            road_acc.add(scanner.nextInt());
            road_fatal.add(scanner.nextInt());
            move_acc.add(scanner.nextInt());
            move_fatal.add(scanner.nextInt());
            scanner.nextLine();
        }
    }

    public void calcDecade() {
        for (Integer currentYear : year) {
            decade.add((currentYear - 1900) / 10);
        }
    }
}
