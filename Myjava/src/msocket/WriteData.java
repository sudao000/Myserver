package msocket;

import mysql.Comysql;
import mysql.dao.StateDao;
import mysql.dao.TnowDao;
import mysql.tab.State;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ASD on 2016/9/5.
 */
public class WriteData implements Runnable {
    Connection conn;

    public void setName(String name) {
        this.name = name;
    }

    String name;


    public WriteData(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        while (true) {
            setName(ReciveRunable.fname);
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (name!=null) {
                String[] s = getTime();
                StateDao stateDao = new StateDao(conn, name);
                TnowDao tnowDao = new TnowDao(conn, name);

                try {
                    String[] state = tnowDao.getTnow();
                    State st = new State(Comysql.getCount(name+"_s"), state[1], s[1], s[0]);
                    System.out.println("write:" + state[1]+"  "+s[1]);
                    stateDao.add(st);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public String[] getTime() {
        Date date = new Date();
        DateFormat f1 = new SimpleDateFormat("yyy-MM-dd");
        DateFormat f2 = new SimpleDateFormat("HH:mm:ss");
        String s[] = new String[2];
        s[0] = f1.format(date);
        s[1] = f2.format(date);
        return s;
    }
}
