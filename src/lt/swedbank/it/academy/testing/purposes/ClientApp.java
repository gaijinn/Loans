package lt.swedbank.it.academy.testing.purposes;

import lt.swedbank.it.academy.testing.purposes.domain.Loan;
import lt.swedbank.it.academy.testing.purposes.domain.LoanRiskType;
import lt.swedbank.it.academy.testing.purposes.domain.RealEstatePurpose;
import lt.swedbank.it.academy.testing.purposes.service.LoanService;

import javax.xml.crypto.KeySelector;

public class ClientApp {

    public static void main(String[] args) {

        Loan[] loans = getInitializer().initializeLoans();
        LoanService service = new LoanService(loans);

        service.setNormalRiskVehicleLoans(service.findAllNormalRiskVehicleLoans());

        System.out.println("There are " + service.getNormalRiskVehicleLoans().size());

        service.setMaximumAgeOfLowRiskLoanedVehicles(service.calculateMaximumAgeOfLowRiskLoanedVehicles());

        System.out.println(service.getMaximumAgeOfLowRiskLoanedVehicles());

        service.setPersonalRealEstateLoans(service.findAllPersonalRealEstateLoans());
        System.out.println("There are: " + service.getPersonalRealEstateLoans().size());

        service.setExpiredHighRiskVehicleLoansOfHighestDuration(service.findAllExpiredHighRiskVehicleLoansOfHighestDuration());
        System.out.println(service.getExpiredHighRiskVehicleLoansOfHighestDuration().size() + " " + service.getExpiredHighRiskVehicleLoansOfHighestDuration().get(0).getTermInYears());





    }


    private static DomainInitializer getInitializer() {
        return new Task3DomainInitializer();
    }

}
