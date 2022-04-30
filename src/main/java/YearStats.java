public class YearStats {
    Integer year = 0;
    Float ridesMkm = 0f;
    Float collAcc = 0f;
    Float collFatal = 0f;
    Float roadAcc = 0f;
    Float roadFatal = 0f;
    Float moveAcc = 0f;
    Float moveFatal = 0f;

    Integer getDecade() {
        return (year - 1900) / 10;
    }

    public void addStats(YearStats other) {
        year = other.year;
        ridesMkm += zeroForNull(other.ridesMkm);
        collAcc += zeroForNull(other.collAcc);
        collFatal += zeroForNull(other.collFatal);
        roadAcc += zeroForNull(other.roadAcc);
        roadFatal += zeroForNull(other.roadFatal);
        moveAcc += zeroForNull(other.moveAcc);
        moveFatal += zeroForNull(other.moveFatal);
    }

    private Float zeroForNull(Float num) {
        return num == null ? 0 : num;
    }

    public void countStats(YearStats other) {
        year = other.year;
        ridesMkm += asCount(other.ridesMkm);
        collAcc += asCount(other.collAcc);
        collFatal += asCount(other.collFatal);
        roadAcc += asCount(other.roadAcc);
        roadFatal += asCount(other.roadFatal);
        moveAcc += asCount(other.moveAcc);
        moveFatal += asCount(other.moveFatal);
    }

    private Float asCount(Float num) {
        return num == null ? 0f : 1f;
    }

    public String toString() {
        return String.format("Decade %d: rides_mkm=%.2f, coll_acc=%.2f, coll_fatal=%.2f, " +
                "road_acc=%.2f, road_fatal=%.2f, move_acc=%.2f, move_fatal=%.2f",
                getDecade() * 10 + 1900, ridesMkm, collAcc, collFatal, roadAcc, roadFatal, moveAcc, moveFatal);
    }
}