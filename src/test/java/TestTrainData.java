import org.junit.Test;

import java.io.ByteArrayInputStream;
import static org.junit.Assert.*;

public class TestTrainData {
    @Test
    public void testReadCSVOneLine() {
        ByteArrayInputStream csv = new ByteArrayInputStream("first line\n1993,100,200,300,40,50,6,7\n".getBytes());
        TrainData trainData = new TrainData();
        trainData.readCSV(csv);
        assertEquals(1993, (int)trainData.year.get(0));
        assertEquals(100, (int)trainData.rides_mkm.get(0));
        assertEquals(200, (int)trainData.coll_acc.get(0));
        assertEquals(300, (int)trainData.coll_fatal.get(0));
        assertEquals(40, (int)trainData.road_acc.get(0));
        assertEquals(50, (int)trainData.road_fatal.get(0));
        assertEquals(6, (int)trainData.move_acc.get(0));
        assertEquals(7, (int)trainData.move_fatal.get(0));
    }

    @Test
    public void testReadCSVTwoLines() {
        ByteArrayInputStream csv = new ByteArrayInputStream("first line\n1993,100,200,300,40,50,6,7\n1994,11,21,31,41,51,61,71\n".getBytes());
        TrainData trainData = new TrainData();
        trainData.readCSV(csv);
        assertEquals(1994, (int)trainData.year.get(1));
        assertEquals(11, (int)trainData.rides_mkm.get(1));
        assertEquals(21, (int)trainData.coll_acc.get(1));
        assertEquals(31, (int)trainData.coll_fatal.get(1));
        assertEquals(41, (int)trainData.road_acc.get(1));
        assertEquals(51, (int)trainData.road_fatal.get(1));
        assertEquals(61, (int)trainData.move_acc.get(1));
        assertEquals(71, (int)trainData.move_fatal.get(1));
    }
}
