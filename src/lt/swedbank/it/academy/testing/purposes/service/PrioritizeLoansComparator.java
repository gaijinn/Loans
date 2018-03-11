package lt.swedbank.it.academy.testing.purposes.service;

import lt.swedbank.it.academy.testing.purposes.domain.Loan;

import java.util.Comparator;

public class PrioritizeLoansComparator implements Comparator<Loan> {
    @Override
    public int compare(Loan object1, Loan object2) {

        if (object1.getRiskType().getValue() > object2.getRiskType().getValue())
            return -1;
        if (object1.getRiskType().getValue() < object2.getRiskType().getValue())
            return 1;
        if (object1.getTotalLoanCost().compareTo(object2.getTotalLoanCost()) > 0)
            return -1;
        if (object1.getTotalLoanCost().compareTo(object2.getTotalLoanCost()) < 0) {
            return 1;
        }
        if (object1.getCreationDate().compareTo(object2.getCreationDate()) > 0){
            return 1;
        }
        return -1;
    }
}
