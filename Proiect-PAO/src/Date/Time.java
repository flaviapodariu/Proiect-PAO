package Date;

public class Time {
    private int hour;
    private int minutes;

    public Time(){}

    public Time(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public static Time parser(String time){
        String[] list = time.split(":", 0);
        return new Time(Integer.parseInt(list[0]), Integer.parseInt(list[1]));
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public String toString() {
        return this.hour + ":" + this.minutes;
    }
}
