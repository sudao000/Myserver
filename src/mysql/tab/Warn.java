package mysql.tab;

/**
 * Created by ASD on 2016/9/5.
 */
public class Warn{
    public int id,type;
    public String time,date;

    public Warn(int id, int type, String time, String date) {
        this.id = id;
        this.type = type;
        this.time = time;
        this.date = date;
    }
}
