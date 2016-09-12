package msocket;

import mysql.Comysql;
import mysql.dao.StateDao;
import mysql.dao.TnowDao;
import mysql.dao.WarnDao;
import mysql.dao.WarnPDao;
import mysql.tab.State;
import mysql.tab.Warnp;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
                WarnDao warnDao=new WarnDao(conn, name);

                try {
                    String[] state = tnowDao.getTnow();
                    State st = new State(Comysql.getCount(name+"_s"), state[1], s[1], s[0]);
                    System.out.println("write:" + state[1]+"  "+s[1]);
                    stateDao.add(st);
                    wrd(state[0],state[1]);


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
    public  void  wrd(String s1,String s2)throws SQLException {
        int[] ints1 = StoInt(s1);
        float[] floats2 = StoFloat(s2);
        int a=0;
        WarnPDao warnPDao=new WarnPDao(conn, name);
        List<Warnp> lwp=warnPDao.getWarnp();
        WarnDao warnDao=new WarnDao(conn, name);
        int lwp_size=lwp.size();
        for(int i=0;i<lwp_size;i++){
            Warnp warnp=lwp.get(i);
            String s=warnp.term;
            String m[]=s.split(",");
            float wt=Float.parseFloat(m[3]);
            int it=Integer.parseInt(m[3]);
            int index=Integer.parseInt(m[1]);
            if(s.contains("a")){
                if(s.contains(">")){
                    if(floats2[index]>wt){
                        System.out.println(i);
                        warnDao.add(i);
                    }
                }
                else if(s.contains("<")){
                    if(floats2[index]<wt){
                        warnDao.add(i);
                        System.out.println(i);
                    }
                }

            }
            else {
                if(ints1[index]==it){
                    warnDao.add(i);
                    System.out.println(i);
                }

            }
        }

    }
    public  int[] StoInt(String s) {
        if (s.contains(",")) {
            String m[] = s.split(",");
            int m_size = m.length;
            int[] a = new int[m_size];
            for (int q = 0; q < m_size; q++) {
                a[q] = Integer.parseInt(m[q]);
            }
            return a;


        } else {
            System.err.println("格式有误!");
            return null;
        }

    }

    public  float[] StoFloat(String s) {
        if (s.contains(",")) {
            String m[] = s.split(",");
            int m_size = m.length;
            float[] a = new float[m_size];
            for (int q = 0; q < m_size; q++) {
                a[q] = Float.parseFloat(m[q]);
            }
            return a;

        } else {
            System.err.println("格式有误!");
            return null;
        }

    }
}
