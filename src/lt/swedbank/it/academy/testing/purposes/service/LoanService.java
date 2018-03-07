package lt.swedbank.it.academy.testing.purposes.service;

import lt.swedbank.it.academy.testing.purposes.domain.*;
import lt.swedbank.it.academy.testing.purposes.utility.LoanUtil;

import java.math.BigDecimal;
import java.util.*;

public class LoanService implements LoanServiceInterface {
    private Loan[] loan;
    private BigDecimal averageLoanCost;
    private List<Loan> highRiskLoans = new ArrayList<>();
    private BigDecimal averageLoanCostOfHighRiskLoan;
    private BigDecimal averageLoanCostOfNormalRiskLoan;
    private BigDecimal averageLoanCostOfLowRiskLoan;
    private BigDecimal maximumPriceOfNonExpiredLoans;
    private List<Loan> normalRiskVehicleLoans;
    private int maximumAgeOfLowRiskLoanedVehicles;
    private List<Loan> personalRealEstateLoans;
    private List<Loan> expiredHighRiskVehicleLoansOfHighestDuration;
    private List<Loan> lowRiskHarvesterLoans;
    private List<Loan> expiredLandLoansInReservation;
    private List<Loan> loansOfHigherThanAverageDepreciation;


    public LoanService(Loan[] loan) {
        this.loan = loan;
        this.averageLoanCost = new BigDecimal(0);
        this.averageLoanCostOfHighRiskLoan = new BigDecimal(0);
        this.averageLoanCostOfNormalRiskLoan = new BigDecimal(0);
        this.averageLoanCostOfLowRiskLoan = new BigDecimal(0);
    }

    public List<Loan> findAllLoansHigherThanAverageDepreciation() {
        List<Loan> loans = new ArrayList<>();
        //TODO ask about this how to calculate! Also mistake in getExpiredLandLoansInReservation
        //TODO l.getClass().equals(HarvesterLoan.class) instead of instanceof ???? 
        return loans;
    }

    private BigDecimal calculateAverageDepreciation() {
        BigDecimal averageDeprecation = new BigDecimal(0);
        for (Loan l : loan) {
            if (l.getClass().equals(VehicleLoan.class)) {
                averageDeprecation = averageDeprecation.add(LoanUtil.calculateVehicleDeprecation((VehicleLoan) l));
            }
        }
        return averageDeprecation.divide(new BigDecimal(loan.length));
    }

    public List<Loan> findAllExpiredLandLoansInReservation() {
        List<Loan> loans = new ArrayList<>();
        for (Loan l : loan) {
            if (l instanceof LandLoan && ((LandLoan) l).isInReservation() && !l.isValid()) {
                loans.add(l);
            }
        }
        return loans;
    }

    public List<Loan> findAllLowRiskHarvesterLoans() {
        List<Loan> loans = new ArrayList<>();
        for (Loan l : loan) {
            if (l.getClass().equals(HarvesterLoan.class) && l.getRiskType().equals(LoanRiskType.LOW_RISK)) {
                loans.add(l);
            }
        }
        return loans;
    }

    @Override
    public List<Loan> getLowRiskHarvesterLoans() {
        return lowRiskHarvesterLoans;
    }

    @Override
    public List<Loan> getExpiredLandLoansInReservation() {
        return expiredLandLoansInReservation;
    }

    @Override
    public List<Loan> getLoansOfHigherThanAverageDepreciation() {
        return loansOfHigherThanAverageDepreciation;
    }

    @Override
    public List<Loan> findAllExpiredHighRiskVehicleLoansOfHighestDuration() {
        List<Loan> list = new ArrayList<>();
        for (Loan l : loan) {
            if (l instanceof VehicleLoan && !l.isValid() && l.getRiskType().equals(LoanRiskType.HIGH_RISK)) {
                list.add(l);
            }
        }
        return list;
    }

    @Override
    public List<Loan> findAllPersonalRealEstateLoans() {
        List<Loan> loans = new ArrayList<>();
        for (Loan l : loan) {
            if (l instanceof RealEstateLoan && ((RealEstateLoan) l).getPurpose().equals(RealEstatePurpose.PERSONAL)) {
                loans.add(l);
            }
        }
        return loans;
    }

    @Override
    public int calculateMaximumAgeOfLowRiskLoanedVehicles() {
        int maximumAge = 0;
        for (Loan l : loan) {
            if (l instanceof VehicleLoan && l.getRiskType().equals(LoanRiskType.LOW_RISK)) {
                maximumAge = ((VehicleLoan) l).getMaximumAge();
            }
        }
        return maximumAge;
    }

    @Override
    public List<Loan> findAllNormalRiskVehicleLoans() {
        List<Loan> vehicleLoans = new ArrayList<>();
        for (Loan l : loan) {
            if (l instanceof VehicleLoan && l.getRiskType().equals(LoanRiskType.NORMAL_RISK)) {
                vehicleLoans.add(l);
            }
        }
        return vehicleLoans;
    }

    @Override
    public List<Loan> calculateHighRiskLoans() {
        for (Loan l : loan) {
            if (l.getRiskType() == LoanRiskType.HIGH_RISK) {
                highRiskLoans.add(l);
            }
        }
        return highRiskLoans;
    }

    @Override
    public BigDecimal calculateAverageLoanCost() {
        for (Loan l : loan) {
            averageLoanCost = averageLoanCost.add(l.calculateTotalLoanCost());
        }
        return averageLoanCost = averageLoanCost.divide(new BigDecimal(loan.length));
    }

    @Override
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

    @Override
    public BigDecimal calculateMaximumPriceOfNonExpiredLoan() {
        BigDecimal temp = new BigDecimal(0);

        for (Loan l : loan) {
            if (l.isValid() && l.getPrice().compareTo(temp) > 0)
                temp = l.getPrice();
        }
        return temp;
    }

    @Override
    public Set<String> findVehicleModels() {
        Set<String> vehicles = new HashSet<>();
        for (Loan l : loan) {
            if (l instanceof VehicleLoan) {
                vehicles.add(((VehicleLoan) l).getModel());
            }
        }
        return vehicles;
    }

    @Override
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

    @Override
    public List<Loan> getHighRiskLoans() {
        return highRiskLoans;
    }

    @Override
    public BigDecimal getMaximumPriceOfNonExpiredLoans() {
        return maximumPriceOfNonExpiredLoans;
    }

    @Override
    public BigDecimal getAverageLoanCost() {
        return averageLoanCost;
    }

    @Override
    public void setMaximumPriceOfNonExpiredLoans(BigDecimal maximumPriceOfNonExpiredLoans) {
        this.maximumPriceOfNonExpiredLoans = maximumPriceOfNonExpiredLoans;
    }

    @Override
    public void setAverageLoanCost(BigDecimal averageLoanCost) {
        this.averageLoanCost = averageLoanCost;
    }

    @Override
    public void setHighRiskLoans(List<Loan> highRiskLoans) {
        this.highRiskLoans = highRiskLoans;
    }

    @Override
    public void setAverageLoanCostOfHighRiskLoan(BigDecimal averageLoanCostOfHighRiskLoan) {
        this.averageLoanCostOfHighRiskLoan = averageLoanCostOfHighRiskLoan;
    }

    @Override
    public void setAverageLoanCostOfNormalRiskLoan(BigDecimal averageLoanCostOfNormalRiskLoan) {
        this.averageLoanCostOfNormalRiskLoan = averageLoanCostOfNormalRiskLoan;
    }

    @Override
    public void setAverageLoanCostOfLowRiskLoan(BigDecimal averageLoanCostOfLowRiskLoan) {
        this.averageLoanCostOfLowRiskLoan = averageLoanCostOfLowRiskLoan;
    }

    @Override
    public BigDecimal getAverageLoanCostOfHighRiskLoan() {
        return averageLoanCostOfHighRiskLoan;
    }

    @Override
    public BigDecimal getAverageLoanCostOfNormalRiskLoan() {
        return averageLoanCostOfNormalRiskLoan;
    }

    @Override
    public BigDecimal getAverageLoanCostOfLowRiskLoan() {
        return averageLoanCostOfLowRiskLoan;
    }

    @Override
    public List<Loan> getNormalRiskVehicleLoans() {
        return normalRiskVehicleLoans;
    }

    @Override
    public void setNormalRiskVehicleLoans(List<Loan> normalRiskVehicleLoans) {
        this.normalRiskVehicleLoans = normalRiskVehicleLoans;
    }

    @Override
    public int getMaximumAgeOfLowRiskLoanedVehicles() {
        return maximumAgeOfLowRiskLoanedVehicles;
    }

    @Override
    public void setMaximumAgeOfLowRiskLoanedVehicles(int maximumAgeOfLowRiskLoanedVehicles) {
        this.maximumAgeOfLowRiskLoanedVehicles = maximumAgeOfLowRiskLoanedVehicles;
    }

    @Override
    public List<Loan> getPersonalRealEstateLoans() {
        return personalRealEstateLoans;
    }

    @Override
    public void setPersonalRealEstateLoans(List<Loan> personalRealEstateLoans) {
        this.personalRealEstateLoans = personalRealEstateLoans;
    }

    @Override
    public List<Loan> getExpiredHighRiskVehicleLoansOfHighestDuration() {
        return expiredHighRiskVehicleLoansOfHighestDuration;
    }

    @Override
    public void setExpiredHighRiskVehicleLoansOfHighestDuration(List<Loan> expiredHighRiskVehicleLoansOfHighestDuration) {
        this.expiredHighRiskVehicleLoansOfHighestDuration = expiredHighRiskVehicleLoansOfHighestDuration;
    }


    public void setLowRiskHarvesterLoans(List<Loan> lowRiskHarvesterLoans) {
        this.lowRiskHarvesterLoans = lowRiskHarvesterLoans;
    }

    public void setExpiredLandLoansInReservation(List<Loan> expiredLandLoansInReservation) {
        this.expiredLandLoansInReservation = expiredLandLoansInReservation;
    }

    public void setLoansOfHigherThanAverageDepreciation(List<Loan> loansOfHigherThanAverageDepreciation) {
        this.loansOfHigherThanAverageDepreciation = loansOfHigherThanAverageDepreciation;
    }
}
