package cn.fuser.amap;

public class Suggestion {
    private String keywords = null;
    private City[] cities = null;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public City[] getCities() {
        return cities;
    }

    public void setCities(City[] cities) {
        this.cities = cities;
    }
}
