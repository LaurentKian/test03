class city {
    Double lon;
    double lat;
    int id;
    String name;

    public city() {
    }

    public city(Double lon, double lat, int id, String name) {
        this.lon = lon;
        this.lat = lat;
        this.id = id;
        this.name = name;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

