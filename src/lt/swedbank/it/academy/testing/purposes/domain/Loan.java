package lt.swedbank.it.academy.testing.purposes.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;


public class Loan {

    private Date creationDate;
    private int termInYears;
    private String name;
    private LoanRiskType riskType;
    private BigDecimal price;
    private BigDecimal interestRate;
    private BigDecimal totalLoanCost;
    private boolean isValid;

    public boolean isValid() {
        return this.isValid;
    }

    public void setIsValid(boolean set) {
        this.isValid = set;
    }

    public BigDecimal getTotalLoanCost() {
        return totalLoanCost;
    }

    public void setTotalLoanCost(BigDecimal totalLoanCost) {
        this.totalLoanCost = totalLoanCost;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getTermInYears() {
        return termInYears;
    }

    public void setTermInYears(int termInYears) {
        this.termInYears = termInYears;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LoanRiskType getRiskType() {
        return riskType;
    }

    public void setRiskType(LoanRiskType riskType) {
        this.riskType = riskType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Loan)) return false;
        Loan loan = (Loan) o;
        return termInYears == loan.termInYears &&
                Objects.equals(creationDate, loan.creationDate) &&
                Objects.equals(name, loan.name) &&
                riskType == loan.riskType &&
                Objects.equals(price, loan.price) &&
                Objects.equals(interestRate, loan.interestRate) &&
                Objects.equals(totalLoanCost, loan.totalLoanCost);
    }

    @Override
    public int hashCode() {

        return Objects.hash(creationDate, termInYears, name, riskType, price, interestRate, totalLoanCost);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "creationDate=" + creationDate +
                ", termInYears=" + termInYears +
                ", name='" + name + '\'' +
                ", riskType=" + riskType +
                ", price=" + price +
                ", interestRate=" + interestRate +
                ", totalLoanCost=" + totalLoanCost +
                ", isValid=" + isValid +
                '}';
    }
}
