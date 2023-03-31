public class YearStats {
    int year = 0;
    float ridesMkm = 0;
    float collAcc = 0;
    float collFatal = 0;
    float roadAcc = 0;
    float roadFatal = 0;
    float moveAcc = 0;
    float moveFatal = 0;

    public int getDecade() {
        return (year / 10) * 10;
    }

    public void addStats(YearStats other) {
        year = other.year;
        ridesMkm += other.ridesMkm;
        collAcc += other.collAcc;
        collFatal += other.collFatal;
        roadAcc += other.roadAcc;
        roadFatal += other.roadFatal;
        moveAcc += other.moveAcc;
        moveFatal += other.moveFatal;
    }

    public void divideBy(int number) {
        ridesMkm /= number;
        collAcc /= number;
        collFatal /= number;
        roadAcc /= number;
        roadFatal /= number;
        moveAcc /= number;
        moveFatal /= number;
    }

    public String toString() {
        return String.format("Decade %d: " + "rides_mkm=%.2f, coll_acc=%.2f, " +
                        "coll_fatal=%.2f, road_acc=%.2f, " +
                        "road_fatal=%.2f, move_acc=%.2f, " +
                        "move_fatal=%.2f",
                getDecade(), ridesMkm, collAcc, collFatal,
                roadAcc, roadFatal, moveAcc, moveFatal);
    }
}