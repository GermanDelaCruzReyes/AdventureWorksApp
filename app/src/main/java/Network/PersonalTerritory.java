package Network;
import com.google.gson.annotations.SerializedName;
public class PersonalTerritory {
    @SerializedName("businessEntityId")
    private int businessEntityId;
    private int territoryId;
    private Double salesQuota;
    private Double bonus;
    private Double commissionPct;

    public int getBusinessEntityId() {
        return businessEntityId;
    }

    public void setBusinessEntityId(int businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public int getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(int territoryId) {
        this.territoryId = territoryId;
    }

    public Double getSalesQuota() {
        return salesQuota;
    }

    public void setSalesQuota(Double salesQuota) {
        this.salesQuota = salesQuota;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Double commissionPct) {
        this.commissionPct = commissionPct;
    }
}
