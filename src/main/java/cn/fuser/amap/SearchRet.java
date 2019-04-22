package cn.fuser.amap;

public class SearchRet {
    private int status = 0;
    private String info = null;
    private int count = 0;
    private Suggestion suggestion = null;
    private POI[] pois = null;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    public POI[] getPois() {
        return pois;
    }

    public void setPois(POI[] pois) {
        this.pois = pois;
    }
}
