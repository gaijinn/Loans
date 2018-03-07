package lt.swedbank.it.academy.testing.purposes.utility;

import lt.swedbank.it.academy.testing.purposes.domain.VehicleLoan;

import java.math.BigDecimal;
import java.util.Date;

public class LoanUtil {

    public static BigDecimal calculateVehicleDeprecation(VehicleLoan loan){
        BigDecimal vehicleDeprecation = new BigDecimal(0);
        Date today = DateUtil.getDateFromString("2018-03-07");
        long yearsInUse;
        yearsInUse = DateUtil.differenceInDays(today, loan.getManufactured());
        yearsInUse = yearsInUse / 365;
        return vehicleDeprecation.multiply(new BigDecimal(yearsInUse).divide(new BigDecimal(loan.getMaximumAge())));
    }
}
