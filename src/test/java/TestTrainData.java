import org.junit.Test;

import java.io.ByteArrayInputStream;
import static org.junit.Assert.*;

public class TestTrainData {
    @Test
    public void testReadCSVOneLine() {
        ByteArrayInputStream csv = new ByteArrayInputStream("first line\n1993,100,200,300,40,50,6,7\n".getBytes());
        TrainData trainData = new TrainData();
        trainData.readCSV(csv);
        assertEquals(1993, (int)trainData.getRow(0).year);
        assertEquals(100, (int)trainData.getRow(0).ridesMkm);
        assertEquals(200, (int)trainData.getRow(0).collAcc);
        assertEquals(300, (int)trainData.getRow(0).collFatal);
        assertEquals(40, (int)trainData.getRow(0).roadAcc);
        assertEquals(50, (int)trainData.getRow(0).roadFatal);
        assertEquals(6, (int)trainData.getRow(0).moveAcc);
        assertEquals(7, (int)trainData.getRow(0).moveFatal);
    }

    @Test
    public void testReadCSVTwoLines() {
        ByteArrayInputStream csv = new ByteArrayInputStream("first line\n1993,100,200,300,40,50,6,7\n1994,11,21,31,41,51,61,71\n".getBytes());
        TrainData trainData = new TrainData();
        trainData.readCSV(csv);
        assertEquals(1994, (int)trainData.getRow(1).year);
        assertEquals(11, (int)trainData.getRow(1).ridesMkm);
        assertEquals(21, (int)trainData.getRow(1).collAcc);
        assertEquals(31, (int)trainData.getRow(1).collFatal);
        assertEquals(41, (int)trainData.getRow(1).roadAcc);
        assertEquals(51, (int)trainData.getRow(1).roadFatal);
        assertEquals(61, (int)trainData.getRow(1).moveAcc);
        assertEquals(71, (int)trainData.getRow(1).moveFatal);
    }
}
