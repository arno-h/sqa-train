import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TrainData {
    ArrayList<Integer> year = new ArrayList<>();
    ArrayList<Integer> rides_mkm = new ArrayList<>();
    ArrayList<Integer> coll_acc = new ArrayList<>();
    ArrayList<Integer> coll_fatal = new ArrayList<>();
    ArrayList<Integer> road_acc = new ArrayList<>();
    ArrayList<Integer> road_fatal = new ArrayList<>();
    ArrayList<Integer> move_acc = new ArrayList<>();
    ArrayList<Integer> move_fatal = new ArrayList<>();
    ArrayList<Integer> decade = new ArrayList<>();

    void calcDecade() {
        for (int i = 0; i < year.size(); i++) {
            decade.add((year.get(i) - 1900) / 10);
        }
    }

    void readCSV(InputStream csv) {
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
            try {
                move_acc.add(scanner.nextInt());
            } catch (InputMismatchException e) {
                if (scanner.hasNext("NA")) {
                    move_acc.add(-1);
                }
            }
            try {
                move_fatal.add(scanner.nextInt());
            } catch (InputMismatchException e) {
                if (scanner.hasNext("NA")) {
                    move_fatal.add(-1);
                }
            }
            scanner.nextLine();
        }
    }
}
