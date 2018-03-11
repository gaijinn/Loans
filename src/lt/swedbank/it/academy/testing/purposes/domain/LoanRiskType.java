package lt.swedbank.it.academy.testing.purposes.domain;

public enum LoanRiskType {
    HIGH_RISK(3),
    NORMAL_RISK(2),
    LOW_RISK(1);

    final int value;
    LoanRiskType(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
