package lt.swedbank.it.academy.testing.purposes.service;

import lt.swedbank.it.academy.testing.purposes.domain.Loan;
import lt.swedbank.it.academy.testing.purposes.domain.LoanRiskType;

import java.util.Comparator;

public class PrioritizeLoansComparator implements Comparator<Loan> {
    @Override
    public int compare(Loan o1, Loan o2) {
        if (o1.getRiskType().equals(LoanRiskType.HIGH_RISK))
            return -1;
        if(o2.getRiskType().equals(LoanRiskType.HIGH_RISK))
            return 1;
        if (o1.getTotalLoanCost().compareTo(o2.getTotalLoanCost()) > 0)
            return -1;
        if (o1.getTotalLoanCost().compareTo(o2.getTotalLoanCost()) < 0)
            return 1;
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }
}
