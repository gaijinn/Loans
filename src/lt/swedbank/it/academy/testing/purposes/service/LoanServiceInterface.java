package lt.swedbank.it.academy.testing.purposes.service;

import lt.swedbank.it.academy.testing.purposes.domain.Loan;
import lt.swedbank.it.academy.testing.purposes.domain.LoanRiskType;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LoanServiceInterface {
    List<Loan> getLowRiskHarvesterLoans();

    List<Loan> getExpiredLandLoansInReservation();

    List<Loan> getLoansOfHigherThanAverageDeprecation();

    List<Loan> findAllExpiredHighRiskVehicleLoansOfHighestDuration();

    List<Loan> findAllPersonalRealEstateLoans();

    int calculateMaximumAgeOfLowRiskLoanedVehicles();

    List<Loan> findAllNormalRiskVehicleLoans();

    List<Loan> calculateHighRiskLoans();

    BigDecimal calculateAverageLoanCost();

    BigDecimal calculateAverageLoanCostByRiskGroups(LoanRiskType loanRiskType);

    BigDecimal calculateMaximumPriceOfNonExpiredLoan();

    Set<String> findVehicleModels();

    Map<LoanRiskType, Collection<Loan>> groupLoansByRiskType();

    List<Loan> getHighRiskLoans();

    BigDecimal getMaximumPriceOfNonExpiredLoans();

    BigDecimal getAverageLoanCost();

    void setMaximumPriceOfNonExpiredLoans(BigDecimal maximumPriceOfNonExpiredLoans);

    void setAverageLoanCost(BigDecimal averageLoanCost);

    void setHighRiskLoans(List<Loan> highRiskLoans);

    void setAverageLoanCostOfHighRiskLoan(BigDecimal averageLoanCostOfHighRiskLoan);

    void setAverageLoanCostOfNormalRiskLoan(BigDecimal averageLoanCostOfNormalRiskLoan);

    void setAverageLoanCostOfLowRiskLoan(BigDecimal averageLoanCostOfLowRiskLoan);

    BigDecimal getAverageLoanCostOfHighRiskLoan();

    BigDecimal getAverageLoanCostOfNormalRiskLoan();

    BigDecimal getAverageLoanCostOfLowRiskLoan();

    List<Loan> getNormalRiskVehicleLoans();

    void setNormalRiskVehicleLoans(List<Loan> normalRiskVehicleLoans);

    int getMaximumAgeOfLowRiskLoanedVehicles();

    void setMaximumAgeOfLowRiskLoanedVehicles(int maximumAgeOfLowRiskLoanedVehicles);

    List<Loan> getPersonalRealEstateLoans();

    void setPersonalRealEstateLoans(List<Loan> personalRealEstateLoans);

    List<Loan> getExpiredHighRiskVehicleLoansOfHighestDuration();

    void setExpiredHighRiskVehicleLoansOfHighestDuration(List<Loan> expiredHighRiskVehicleLoansOfHighestDuration);
}
