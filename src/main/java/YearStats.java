public class YearStats {
    Integer year = 0;
    Integer ridesMkm = 0;
    Integer collAcc = 0;
    Integer collFatal = 0;
    Integer roadAcc = 0;
    Integer roadFatal = 0;
    Integer moveAcc = 0;
    Integer moveFatal = 0;

    public Integer getDecade() {
        return (year - 1900) / 10;
    }
}