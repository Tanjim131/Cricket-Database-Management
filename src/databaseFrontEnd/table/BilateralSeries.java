package databaseFrontEnd.table;

public class BilateralSeries {
    private String seriesName;
    private String year;
    private String format;

    public BilateralSeries(String seriesName,String year,String format){
        this.seriesName = seriesName;
        this.year = year;
        this.format = format;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "BilateralSeries{" +
                "seriesName='" + seriesName + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
