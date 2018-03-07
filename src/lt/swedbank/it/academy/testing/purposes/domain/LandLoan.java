package lt.swedbank.it.academy.testing.purposes.domain;

public class LandLoan extends RealEstateLoan {

    private boolean isInReservation;

    public boolean isInReservation() {
        return isInReservation;
    }

    public void setInReservation(boolean inReservation) {
        isInReservation = inReservation;
    }
}
