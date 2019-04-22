package cn.fuser.amap;

public class POI {
    private String id = null;
    private String name = null;
    private String type = null;
    private String typecode = null;
    private String bizType = null;
    private String address = null;
    private String location = null;
    private String distance = null;
    private String tel = null;
    private String pcode = null;
    private String pname = null;
    private String citycode = null;
    private String cityname = null;
    private Photo[] photos = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname;
    }

    public String getEntrLocation() {
        return entrLocation;
    }

    public void setEntrLocation(String entrLocation) {
        this.entrLocation = entrLocation;
    }

    public String getExitLocation() {
        return exitLocation;
    }

    public void setExitLocation(String exitLocation) {
        this.exitLocation = exitLocation;
    }

    public String getNaviPoiid() {
        return naviPoiid;
    }

    public void setNaviPoiid(String naviPoiid) {
        this.naviPoiid = naviPoiid;
    }

    public String getGridcode() {
        return gridcode;
    }

    public void setGridcode(String gridcode) {
        this.gridcode = gridcode;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getIndoorMap() {
        return indoorMap;
    }

    public void setIndoorMap(int indoorMap) {
        this.indoorMap = indoorMap;
    }

    public IndoorData getIndoorData() {
        return indoorData;
    }

    public void setIndoorData(IndoorData indoorData) {
        this.indoorData = indoorData;
    }

    public int getGroupbuyNum() {
        return groupbuyNum;
    }

    public void setGroupbuyNum(int groupbuyNum) {
        this.groupbuyNum = groupbuyNum;
    }

    public Photo[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photo[] photos) {
        this.photos = photos;
    }

    private String adcode = null;
    private String adname = null;
    private String entrLocation = null;
    private String exitLocation = null;
    private String naviPoiid = null;
    private String gridcode = null;
    private String alias = null;
    private String businessArea = null;
    private String parkingType = null;
    private String tag = null;
    private int indoorMap = 0;
    private IndoorData indoorData = null;
    private int groupbuyNum = 0;
}
