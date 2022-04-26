public class YearStats {
    Integer year = 0;
    Integer ridesMkm = 0;
    Integer collAcc = 0;
    Integer collFatal = 0;
    Integer roadAcc = 0;
    Integer roadFatal = 0;
    Integer moveAcc = 0;
    Integer moveFatal = 0;

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

    private Integer zeroForNull(Integer num) {
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

    private Integer asCount(Integer num) {
        return num == null ? 0 : 1;
    }
}