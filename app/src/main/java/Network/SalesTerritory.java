package Network;

public class SalesTerritory {
    private int territoryId;
    private String name;
    private String countryRegionCode;
    private String groupTerritory;

    public int getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(int territoryId) {
        this.territoryId = territoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryRegionCode() {
        return countryRegionCode;
    }

    public void setCountryRegionCode(String countryRegionCode) {
        this.countryRegionCode = countryRegionCode;
    }

    public String getGroup() {
        return groupTerritory;
    }

    public void setGroup(String groupTerritory) {
        this.groupTerritory = groupTerritory;
    }
}
