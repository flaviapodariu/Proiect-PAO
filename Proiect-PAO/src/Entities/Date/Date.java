package Entities.Date;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(){}

    public Date(int d, int m, int y){
        this.day = d;
        this.month = m;
        this.year = y;
    }

    public static Date parser(String date){
        String[] list = date.split("-", 0);

        return new Date(Integer.parseInt(list[0]),
                               Integer.parseInt(list[1]),
                               Integer.parseInt(list[2]));

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isGreaterOrEqual(Date date2){
        if(this.year > date2.year)
            return true;

        if(this.year < date2.year)
            return false;

        if(this.month > date2.month)
            return true;

        if(this.month < date2.month)
            return false;

        return this.day >= date2.day;
    }

    public boolean isGreater(Date date2) {
        if(this.year > date2.year)
            return true;

        if(this.year < date2.year)
            return false;

        if(this.month > date2.month)
            return true;

        if(this.month < date2.month)
            return false;

        return this.day > date2.day;
    }

    public boolean isBetween(Date date1, Date date2){
        return this.isGreaterOrEqual(date1) && !this.isGreater(date2);
    }

    @Override
    public String toString() {
        return this.day + "-" + this.month + "-" + this.year;
    }
}
