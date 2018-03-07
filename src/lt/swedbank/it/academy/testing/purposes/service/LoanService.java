package lt.swedbank.it.academy.testing.purposes.service;

import lt.swedbank.it.academy.testing.purposes.domain.Loan;
import lt.swedbank.it.academy.testing.purposes.domain.LoanRiskType;
import lt.swedbank.it.academy.testing.purposes.domain.RealEstateLoan;
import lt.swedbank.it.academy.testing.purposes.domain.VehicleLoan;

import java.math.BigDecimal;
import java.util.*;

public class LoanService {
    private Loan[] loan;
    private BigDecimal averageLoanCost;
    private List<Loan> highRiskLoans = new ArrayList<>();
    private BigDecimal averageLoanCostOfHighRiskLoan;
    private BigDecimal averageLoanCostOfNormalRiskLoan;
    private BigDecimal averageLoanCostOfLowRiskLoan;
    private BigDecimal maximumPriceOfNonExpiredLoans;
    private List<VehicleLoan> normalRiskVehicleLoans;
    private int maximumAgeOfLowRiskLoanedVehicles;
    private List<RealEstateLoan> personalRealEstateLoans;
    private List<VehicleLoan> expiredHighRiskVehicleLoansOfHighestDuration;


    public LoanService(Loan[] loan) {
        this.loan = loan;
        this.averageLoanCost = new BigDecimal(0);
        this.averageLoanCostOfHighRiskLoan = new BigDecimal(0);
        this.averageLoanCostOfNormalRiskLoan = new BigDecimal(0);
        this.averageLoanCostOfLowRiskLoan = new BigDecimal(0);
    }

    public List<Loan> getHighRiskLoans() {
        return highRiskLoans;
    }

    public List<Loan> calculateHighRiskLoans() {
        for (Loan l : loan) {
            if (l.getRiskType() == LoanRiskType.HIGH_RISK) {
                highRiskLoans.add(l);
            }
        }
        return highRiskLoans;
    }

    public BigDecimal getAverageLoanCost() {
        return averageLoanCost;
    }

    public BigDecimal calculateAverageLoanCost() {
        for (Loan l : loan) {
//            averageLoanCost = averageLoanCost.add(l.calculateInterest());
//            averageLoanCost = averageLoanCost.add(l.getPrice());
            averageLoanCost = averageLoanCost.add(l.calculateTotalLoanCost());
        }
        return averageLoanCost = averageLoanCost.divide(new BigDecimal(loan.length));
    }

    public BigDecimal calculateAverageLoanCostByRiskGroups(LoanRiskType loanRiskType) {
        BigDecimal temp = new BigDecimal(0);
        int iter = 0;
        for (Loan l : loan) {
            if (l.getRiskType() == loanRiskType) {
                iter++;
                temp = temp.add(l.calculateInterest());
                temp = temp.add(l.getPrice());
            }
        }
        return temp.divide(new BigDecimal(iter));
    }

    public BigDecimal getMaximumPriceOfNonExpiredLoans() {
        return maximumPriceOfNonExpiredLoans;
    }

    public void setMaximumPriceOfNonExpiredLoans(BigDecimal maximumPriceOfNonExpiredLoans) {
        this.maximumPriceOfNonExpiredLoans = maximumPriceOfNonExpiredLoans;
    }

    public BigDecimal calculateMaximumPriceOfNonExpiredLoan() {
        BigDecimal temp = new BigDecimal(0);

        for (Loan l : loan) {
            if (l.isValid() && l.getPrice().compareTo(temp) > 0)
                temp = l.getPrice();
        }
        return temp;
    }

    public void setAverageLoanCost(BigDecimal averageLoanCost) {
        this.averageLoanCost = averageLoanCost;
    }

    public void setHighRiskLoans(List<Loan> highRiskLoans) {
        this.highRiskLoans = highRiskLoans;
    }

    public void setAverageLoanCostOfHighRiskLoan(BigDecimal averageLoanCostOfHighRiskLoan) {
        this.averageLoanCostOfHighRiskLoan = averageLoanCostOfHighRiskLoan;
    }

    public void setAverageLoanCostOfNormalRiskLoan(BigDecimal averageLoanCostOfNormalRiskLoan) {
        this.averageLoanCostOfNormalRiskLoan = averageLoanCostOfNormalRiskLoan;
    }

    public void setAverageLoanCostOfLowRiskLoan(BigDecimal averageLoanCostOfLowRiskLoan) {
        this.averageLoanCostOfLowRiskLoan = averageLoanCostOfLowRiskLoan;
    }

    public BigDecimal getAverageLoanCostOfHighRiskLoan() {
        return averageLoanCostOfHighRiskLoan;
    }

    public BigDecimal getAverageLoanCostOfNormalRiskLoan() {
        return averageLoanCostOfNormalRiskLoan;
    }

    public BigDecimal getAverageLoanCostOfLowRiskLoan() {
        return averageLoanCostOfLowRiskLoan;
    }

    public List<VehicleLoan> getNormalRiskVehicleLoans() {
        return normalRiskVehicleLoans;
    }

    public void setNormalRiskVehicleLoans(List<VehicleLoan> normalRiskVehicleLoans) {
        this.normalRiskVehicleLoans = normalRiskVehicleLoans;
    }

    public int getMaximumAgeOfLowRiskLoanedVehicles() {
        return maximumAgeOfLowRiskLoanedVehicles;
    }

    public void setMaximumAgeOfLowRiskLoanedVehicles(int maximumAgeOfLowRiskLoanedVehicles) {
        this.maximumAgeOfLowRiskLoanedVehicles = maximumAgeOfLowRiskLoanedVehicles;
    }

    public List<RealEstateLoan> getPersonalRealEstateLoans() {
        return personalRealEstateLoans;
    }

    public void setPersonalRealEstateLoans(List<RealEstateLoan> personalRealEstateLoans) {
        this.personalRealEstateLoans = personalRealEstateLoans;
    }

    public List<VehicleLoan> getExpiredHighRiskVehicleLoansOfHighestDuration() {
        return expiredHighRiskVehicleLoansOfHighestDuration;
    }

    public void setExpiredHighRiskVehicleLoansOfHighestDuration(List<VehicleLoan> expiredHighRiskVehicleLoansOfHighestDuration) {
        this.expiredHighRiskVehicleLoansOfHighestDuration = expiredHighRiskVehicleLoansOfHighestDuration;
    }

    public Set<String> findVehicleModels() {
        Set<String> vehicles = new HashSet<>();
        for (Loan l : loan) {
            if (l instanceof VehicleLoan) {
                vehicles.add(((VehicleLoan) l).getModel());
            }
        }
        return vehicles;
    }

    public Map<LoanRiskType, Collection<Loan>> groupLoansByRiskType() {
        Map<LoanRiskType, Collection<Loan>> loans = new TreeMap<>();
        for (Loan l : loan) {
            if (!loans.containsKey(l.getRiskType())) {
                loans.put(l.getRiskType(), new ArrayList<>());
                loans.get(l.getRiskType()).add(l);
            } else {
                loans.get(l.getRiskType()).add(l);
            }
        }
        return loans;
    }
}
