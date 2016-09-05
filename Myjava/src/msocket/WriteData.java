package msocket;

import mysql.Comysql;
import mysql.dao.StateDao;
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
    String name,state;


    public WriteData(Connection conn, String name,String state) {
        this.conn = conn;
        this.name= name;
        this.state=state;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String []s=getTime();
        StateDao stateDao=new StateDao(conn,name);
        try {
            State st=new State(Comysql.getCount(name),state,s[1],s[0]);
            stateDao.add(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public String[] getTime(){
        Date date=new Date();
        DateFormat f1=new SimpleDateFormat("yyy-MM-dd");
        DateFormat f2=new SimpleDateFormat("HH:mm:ss");
        String s[]=new String[2];
        s[0]=f1.format(date);
        s[1]=f2.format(date);
        return  s;
    }
}
