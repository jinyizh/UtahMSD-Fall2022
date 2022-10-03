public class RainData {
    String month;
    int year;
    float rainfall;
    public RainData() {
        this.month = "";
        this.year = 0;
        this.rainfall = 0;
    }
    public RainData(String month, int year, float rainfall) {
        this.month = month;
        this.year = year;
        this.rainfall = rainfall;
    }
    public String getMonth() {
        return this.month;
    }
    public float getRainfall() {
        return this.rainfall;
    }
}
