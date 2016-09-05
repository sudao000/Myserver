package mysql.tab;

/**
 * Created by ASD on 2016/9/5.
 */
public class Machine {
    public int id, usenum;
    public String num, name, summary, aparm, dparm, date;

    public Machine(int id, String num, String name, int usenum, String summary, String aparm, String dparm, String date) {
        this.id = id;
        this.usenum = usenum;
        this.num = num;
        this.name = name;
        this.summary = summary;
        this.aparm = aparm;
        this.dparm = dparm;
        this.date = date;
    }
}
