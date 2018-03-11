package lt.swedbank.it.academy.testing.purposes.utility;

import lt.swedbank.it.academy.testing.purposes.domain.VehicleLoan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class LoanUtil {

    public static BigDecimal calculateVehicleDeprecation(VehicleLoan loan){
        BigDecimal vehicleDeprecation;
        Date today = DateUtil.getDateFromString("2018-03-11");
        long yearsInUse;
        yearsInUse = DateUtil.differenceInDays(today, loan.getManufactured());
        yearsInUse = yearsInUse / 365;
        vehicleDeprecation = loan.getPrice().multiply(new BigDecimal(yearsInUse).divide(new BigDecimal(loan.getMaximumAge()),RoundingMode.HALF_UP));
        return vehicleDeprecation;
    }
}
