package lt.swedbank.it.academy.testing.purposes;

import lt.swedbank.it.academy.testing.purposes.domain.Loan;
import lt.swedbank.it.academy.testing.purposes.domain.LoanRiskType;
import lt.swedbank.it.academy.testing.purposes.service.LoanService;

public class ClientApp {

    public static void main(String[] args) {

        Loan[] loans = getInitializer().initializeLoans();
        LoanService service = new LoanService(loans);

        System.out.println(service.calculateAverageDepreciation());
    }


    private static DomainInitializer getInitializer() {
        return new Task3DomainInitializer();
    }

}
