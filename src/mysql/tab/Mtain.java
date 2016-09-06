package mysql.tab;

/**
 * Created by ASD on 2016/9/5.
 */
public class Mtain{
    public int id;
    public String que,way,person,time,date;

    public Mtain(int id, String que, String way, String person, String time, String date) {
        this.id = id;
        this.que = que;
        this.way = way;
        this.person = person;
        this.time = time;
        this.date = date;
    }
}
