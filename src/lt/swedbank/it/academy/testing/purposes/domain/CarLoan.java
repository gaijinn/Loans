package lt.swedbank.it.academy.testing.purposes.domain;

public class CarLoan extends VehicleLoan implements Comparable<CarLoan> {

    private float enginePower;

    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
    }

    @Override
    public int compareTo(CarLoan o) {
        int compareResult = this.getPrice().compareTo(o.getPrice());
        int compareLoan = this.getInterestRate().compareTo(o.getInterestRate());
        if (compareResult > 0)
            return -1;
        if (compareResult < 0)
            return 1;
        if (this.getEnginePower() > o.getEnginePower())
            return -1;
        if (this.getEnginePower() < o.getEnginePower()) {
            return 1;
        }
        return compareLoan;
    }
}
