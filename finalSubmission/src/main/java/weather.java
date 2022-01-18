public class weather {

    String id;
    String fxDate;
    double tempMax;
    double tempMin;
    String textDay;

    public weather() {
    }

    public weather(String id, String fxDate, double tempMax, double tempMin, String textDay) {
        this.id = id;
        this.fxDate = fxDate;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.textDay = textDay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFxDate() {
        return fxDate;
    }

    public void setFxDate(String fxDate) {
        this.fxDate = fxDate;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }
}
