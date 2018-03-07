package lt.swedbank.it.academy.testing.purposes.domain;

import java.util.Date;

public class HouseLoan extends RealEstateLoan {

    private int floorCount;
    private Date constructionDate;

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public Date getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(Date constructionDate) {
        this.constructionDate = constructionDate;
    }
}
