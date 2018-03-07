package lt.swedbank.it.academy.testing.purposes;

import lt.swedbank.it.academy.testing.purposes.domain.Loan;
import lt.swedbank.it.academy.testing.purposes.domain.LoanRiskType;
import lt.swedbank.it.academy.testing.purposes.service.LoanService;

public class ClientApp {

    public static void main(String[] args) {

        Loan[] loans = getInitializer().initializeLoans();
        LoanService service = new LoanService(loans);

        service.setHighRiskLoans(service.calculateHighRiskLoans());

        System.out.println("There are " + service.getHighRiskLoans().size());

        service.setAverageLoanCost(service.calculateAverageLoanCost());

        System.out.println(service.getAverageLoanCost());

        service.setAverageLoanCostOfNormalRiskLoan(service.calculateAverageLoanCostByRiskGroups(LoanRiskType.NORMAL_RISK));
        System.out.println(LoanRiskType.NORMAL_RISK + ": " + service.getAverageLoanCostOfNormalRiskLoan());

        service.setAverageLoanCostOfHighRiskLoan(service.calculateAverageLoanCostByRiskGroups(LoanRiskType.HIGH_RISK));
        System.out.println(LoanRiskType.HIGH_RISK + ": " + service.getAverageLoanCostOfHighRiskLoan());

        service.setAverageLoanCostOfLowRiskLoan(service.calculateAverageLoanCostByRiskGroups(LoanRiskType.LOW_RISK));
        System.out.println(LoanRiskType.LOW_RISK + ": " + service.getAverageLoanCostOfLowRiskLoan());

        service.setMaximumPriceOfNonExpiredLoans(service.calculateMaximumPriceOfNonExpiredLoan());
        System.out.println(service.getMaximumPriceOfNonExpiredLoans());





    }


    public static DomainInitializer getInitializer() {
        return new Task2DomainInitializer();
    }

}
