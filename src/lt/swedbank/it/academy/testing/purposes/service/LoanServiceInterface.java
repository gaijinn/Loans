package lt.swedbank.it.academy.testing.purposes.service;

import lt.swedbank.it.academy.testing.purposes.domain.Loan;
import lt.swedbank.it.academy.testing.purposes.domain.LoanRiskType;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LoanServiceInterface {

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

}
