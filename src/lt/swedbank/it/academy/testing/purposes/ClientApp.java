package lt.swedbank.it.academy.testing.purposes;

import lt.swedbank.it.academy.testing.purposes.domain.Loan;
import lt.swedbank.it.academy.testing.purposes.service.LoanService;
import lt.swedbank.it.academy.testing.purposes.service.LoanServiceInterface;

public class ClientApp {

    public static void main(String[] args) {

        Loan[] loans = getInitializer().initializeLoans();
        LoanService service = new LoanService(loans);

        service.setLowRiskHarvesterLoans(service.findAllLowRiskHarvesterLoans());

        System.out.println("There are " + service.getLowRiskHarvesterLoans().size());

        service.setExpiredLandLoansInReservation(service.findAllExpiredLandLoansInReservation());

        System.out.println("There is " + service.getExpiredLandLoansInReservation().size());


        service.setExpiredHighRiskVehicleLoansOfHighestDuration(service.findAllExpiredHighRiskVehicleLoansOfHighestDuration());
        System.out.println(service.getExpiredHighRiskVehicleLoansOfHighestDuration().size() + " " + service.getExpiredHighRiskVehicleLoansOfHighestDuration().get(0).getTermInYears());





    }


    private static DomainInitializer getInitializer() {
        return new Task3DomainInitializer();
    }

}
