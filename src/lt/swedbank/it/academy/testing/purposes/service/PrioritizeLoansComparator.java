package lt.swedbank.it.academy.testing.purposes.service;

import lt.swedbank.it.academy.testing.purposes.domain.Loan;

import java.util.Comparator;

public class PrioritizeLoansComparator implements Comparator<Loan> {
    @Override
    public int compare(Loan o1, Loan o2) {

        if (o1.getRiskType().getValue() > o2.getRiskType().getValue())
            return 1;
        if (o1.getRiskType().ordinal() < o2.getRiskType().ordinal())
            return -1;
        if (o1.getTotalLoanCost().compareTo(o2.getTotalLoanCost()) > 0)
            return -1;
        if (o1.getTotalLoanCost().compareTo(o2.getTotalLoanCost()) < 0) {
            return 1;
        }
        return 1;
    }
}
