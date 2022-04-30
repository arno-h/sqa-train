import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestTrainData {
    @Test
    public void testReadCSVOneLine() {
        ByteArrayInputStream csv = new ByteArrayInputStream("first line\n1993,100,200,300,40,50,6,7\n".getBytes());
        TrainData trainData = new TrainData();
        trainData.readCSV(csv);
        assertEquals(1993, (int) trainData.getRow(0).year);
        assertEquals(100, trainData.getRow(0).ridesMkm, 0);
        assertEquals(200, trainData.getRow(0).collAcc, 0);
        assertEquals(300, trainData.getRow(0).collFatal, 0);
        assertEquals(40, trainData.getRow(0).roadAcc, 0);
        assertEquals(50, trainData.getRow(0).roadFatal, 0);
        assertEquals(6, trainData.getRow(0).moveAcc, 0);
        assertEquals(7, trainData.getRow(0).moveFatal, 0);
    }

    @Test
    public void testReadCSVTwoLines() {
        ByteArrayInputStream csv = new ByteArrayInputStream(
                "first line\n1993,100,200,300,40,50,6,7\n1994,11,21,31,41,51,61,71\n".getBytes());
        TrainData trainData = new TrainData();
        trainData.readCSV(csv);
        assertEquals(1994, (int) trainData.getRow(1).year);
        assertEquals(11, trainData.getRow(1).ridesMkm, 0);
        assertEquals(21, trainData.getRow(1).collAcc, 0);
        assertEquals(31, trainData.getRow(1).collFatal, 0);
        assertEquals(41, trainData.getRow(1).roadAcc, 0);
        assertEquals(51, trainData.getRow(1).roadFatal, 0);
        assertEquals(61, trainData.getRow(1).moveAcc, 0);
        assertEquals(71, trainData.getRow(1).moveFatal, 0);
    }

    @Test
    public void testReadNASingleLine() {
        ByteArrayInputStream csv = new ByteArrayInputStream("first line\n1993,NA,200,NA,40,NA,6,NA".getBytes());
        TrainData trainData = new TrainData();
        trainData.readCSV(csv);
        assertEquals(1993, (int) trainData.getRow(0).year);
        assertNull(trainData.getRow(0).ridesMkm);
        assertEquals(200, trainData.getRow(0).collAcc, 0);
        assertNull(trainData.getRow(0).collFatal);
        assertEquals(40, trainData.getRow(0).roadAcc, 0);
        assertNull(trainData.getRow(0).roadFatal);
        assertEquals(6, trainData.getRow(0).moveAcc, 0);
        assertNull(trainData.getRow(0).moveFatal);
    }

    @Test
    public void testReadNAMultipleLines() {
        ByteArrayInputStream csv = new ByteArrayInputStream(
                "first line\n1993,NA,200,NA,40,NA,6,NA\n1994,11,NA,31,NA,51,NA,71\n".getBytes());
        TrainData trainData = new TrainData();
        trainData.readCSV(csv);
        assertNull(trainData.getRow(0).moveFatal);
        assertEquals(1994, (int) trainData.getRow(1).year);
        assertNull(trainData.getRow(1).moveAcc);
    }
}
