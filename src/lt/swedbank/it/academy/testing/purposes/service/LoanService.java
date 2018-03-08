package lt.swedbank.it.academy.testing.purposes.service;

import lt.swedbank.it.academy.testing.purposes.domain.*;
import lt.swedbank.it.academy.testing.purposes.utility.DateUtil;
import lt.swedbank.it.academy.testing.purposes.utility.LoanUtil;

import java.math.BigDecimal;
import java.util.*;

public class LoanService implements LoanServiceInterface {
    private Loan[] loans;

    public LoanService(Loan[] loans) {
        this.loans = loans;
    }

    public List<Loan> findAllLoansHigherThanAverageDepreciation() {
        List<Loan> loans = new ArrayList<>();
        //TODO ask about this how to calculate! Also mistake in getExpiredLandLoansInReservation
        return loans;
    }

    private BigDecimal calculateAverageDepreciation() {
        BigDecimal averageDeprecation = new BigDecimal(0);
        for (Loan l : loans) {
            if (l.getClass().equals(VehicleLoan.class)) {
                averageDeprecation = averageDeprecation.add(LoanUtil.calculateVehicleDeprecation((VehicleLoan) l));
            }
        }
        return averageDeprecation.divide(new BigDecimal(loans.length));
    }

    public List<Loan> findAllExpiredLandLoansInReservation() {
        List<Loan> loans = new ArrayList<>();
        for (Loan l : this.loans) {
            if (l instanceof LandLoan && ((LandLoan) l).isInReservation() && !findIfValid(l)) {
                loans.add(l);
            }
        }
        return loans;
    }

    public List<Loan> findAllLowRiskHarvesterLoans() {
        List<Loan> loans = new ArrayList<>();
        for (Loan l : this.loans) {
            if (l.getClass().equals(HarvesterLoan.class) && l.getRiskType().equals(LoanRiskType.LOW_RISK)) {
                loans.add(l);
            }
        }
        return loans;
    }

    @Override
    public List<Loan> findAllExpiredHighRiskVehicleLoansOfHighestDuration() {
        List<Loan> list = new ArrayList<>();
        for (Loan l : loans) {
            if (l instanceof VehicleLoan && !findIfValid(l) && l.getRiskType().equals(LoanRiskType.HIGH_RISK)) {
                list.add(l);
            }
        }
        return list;
    }

    @Override
    public List<Loan> findAllPersonalRealEstateLoans() {
        List<Loan> loans = new ArrayList<>();
        for (Loan l : this.loans) {
            if (l instanceof RealEstateLoan && ((RealEstateLoan) l).getPurpose().equals(RealEstatePurpose.PERSONAL)) {
                loans.add(l);
            }
        }
        return loans;
    }

    @Override
    public int calculateMaximumAgeOfLowRiskLoanedVehicles() {
        int maximumAge = 0;
        for (Loan l : loans) {
            if (l instanceof VehicleLoan && l.getRiskType().equals(LoanRiskType.LOW_RISK)) {
                maximumAge = ((VehicleLoan) l).getMaximumAge();
            }
        }
        return maximumAge;
    }

    @Override
    public List<Loan> findAllNormalRiskVehicleLoans() {
        List<Loan> vehicleLoans = new ArrayList<>();
        for (Loan l : loans) {
            if (l instanceof VehicleLoan && l.getRiskType().equals(LoanRiskType.NORMAL_RISK)) {
                vehicleLoans.add(l);
            }
        }
        return vehicleLoans;
    }

    @Override
    public List<Loan> calculateHighRiskLoans() {
        List<Loan> highRiskLoans = new ArrayList<>();
        for (Loan l : loans) {
            if (l.getRiskType() == LoanRiskType.HIGH_RISK) {
                highRiskLoans.add(l);
            }
        }
        return highRiskLoans;
    }

    @Override
    public BigDecimal calculateAverageLoanCost() {
        BigDecimal averageLoanCost = new BigDecimal(0);
        for (Loan l : loans) {
            averageLoanCost = averageLoanCost.add(calculateTotalLoanCost(l));
        }
        return averageLoanCost = averageLoanCost.divide(new BigDecimal(loans.length));
    }

    @Override
    public BigDecimal calculateAverageLoanCostByRiskGroups(LoanRiskType loanRiskType) {
        BigDecimal temp = new BigDecimal(0);
        int iter = 0;
        for (Loan l : loans) {
            if (l.getRiskType() == loanRiskType) {
                iter++;
                temp = temp.add(calculateInterest(l));
                temp = temp.add(l.getPrice());
            }
        }
        return temp.divide(new BigDecimal(iter));
    }

    @Override
    public BigDecimal calculateMaximumPriceOfNonExpiredLoan() {
        BigDecimal temp = new BigDecimal(0);

        for (Loan l : loans) {
            if (findIfValid(l) && l.getPrice().compareTo(temp) > 0)
                temp = l.getPrice();
        }
        return temp;
    }

    @Override
    public Set<String> findVehicleModels() {
        Set<String> vehicles = new HashSet<>();
        for (Loan l : loans) {
            if (l instanceof VehicleLoan) {
                vehicles.add(((VehicleLoan) l).getModel());
            }
        }
        return vehicles;
    }

    @Override
    public Map<LoanRiskType, Collection<Loan>> groupLoansByRiskType() {
        Map<LoanRiskType, Collection<Loan>> loans = new TreeMap<>();
        for (Loan l : this.loans) {
            if (!loans.containsKey(l.getRiskType())) {
                loans.put(l.getRiskType(), new ArrayList<>());
                loans.get(l.getRiskType()).add(l);
            } else {
                loans.get(l.getRiskType()).add(l);
            }
        }
        return loans;
    }

    private boolean findIfValid(Loan loan) {
        Date today = DateUtil.getDateFromString("2018-03-07");
        Date expirationDate = DateUtil.addYears(loan.getCreationDate(), loan.getTermInYears());
        return DateUtil.differenceInDays(expirationDate, today) > 0;
    }

    private BigDecimal calculateTotalLoanCost(BigDecimal price, BigDecimal interestRate) {
        BigDecimal totalLoan = new BigDecimal(0);
        totalLoan = totalLoan.add(calculateInterest(price,interestRate));
        totalLoan = totalLoan.add(price);
        return totalLoan;
    }

    private BigDecimal calculateInterest(BigDecimal price, BigDecimal interestRate) {
        return price.multiply((interestRate.divide(new BigDecimal(100))));
    }


    public Set<Loan> prioritizeLoans() {
        Set<Loan> loanSet = new TreeSet<>(new PrioritizeLoansComparator());
        for (Loan l : loans) {
            l.setTotalLoanCost(calculateTotalLoanCost(l.getPrice(),l.getInterestRate()));
            loanSet.add(l);
            System.out.println(loanSet.size());
        }
        return loanSet;
    }

    public List<Loan> assignTotalCosts(List<Loan> loans){
        List<Loan> loanList = new ArrayList<>();
        for (Loan loan: loans) {
            loanList.add(loan.setTotalLoanCost(calculateTotalLoanCost(loan.getPrice(), loan.getInterestRate())));
        }

    }


}
