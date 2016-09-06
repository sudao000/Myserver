package mysql.tab;

/**
 * Created by ASD on 2016/9/5.
 */
public class State{
    public int id;
    public String state,time,date;

    public State(int id, String state, String time, String date) {
        this.id = id;
        this.state = state;
        this.time = time;
        this.date = date;
    }
}
