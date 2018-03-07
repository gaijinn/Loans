package lt.swedbank.it.academy.testing.purposes.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import lt.swedbank.it.academy.testing.purposes.DateUtil;
import lt.swedbank.it.academy.testing.purposes.DomainInitializer;


public class Loan implements DomainInitializer {

    private Date creationDate;
    private int termInYears;
    private String name;
    private LoanRiskType riskType;
    private BigDecimal price;
    private BigDecimal interestRate;
    private BigDecimal totalLoanCost;

    public boolean isValid() {
        Date today = DateUtil.getDateFromString("2018-03-07");
        Date expirationDate = DateUtil.addYears(creationDate, termInYears);
        return DateUtil.differenceInDays(expirationDate, today) > 0;
    }

    public BigDecimal getTotalLoanCost() {
        return totalLoanCost;
    }

    public void setTotalLoanCost(BigDecimal totalLoanCost) {
        this.totalLoanCost = totalLoanCost;
    }

    public BigDecimal calculateTotalLoanCost() {
        BigDecimal totalLoan = new BigDecimal(0);
        totalLoan = totalLoan.add(calculateInterest());
        totalLoan = totalLoan.add(this.price);
        return totalLoan;
    }

    public BigDecimal calculateInterest(){
        return this.price.multiply((this.interestRate.divide(new BigDecimal(100))));
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
    public Loan[] initializeLoans() {
        return new Loan[0];
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
}
