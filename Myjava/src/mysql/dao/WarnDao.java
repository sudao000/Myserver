package mysql.dao;

import mysql.Comysql;
import mysql.tab.Warn;
import org.json.JSONException;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Created by ASD on 2016/9/2.
 */
public class WarnDao {
    Connection conn;
    String fname;

    public WarnDao(Connection conn, String fname) {
        this.conn = conn;
        this.fname = fname+"_w";
    }
    public int add(Warn warn) throws SQLException {
        String sql="insert into "+fname+" values(?,?,?,?)";
        PreparedStatement pres = conn.prepareStatement(sql);
        pres.setInt(1, warn.id);
        pres.setInt(2, warn.type);
        pres.setString(3, warn.time);
        pres.setString(4, warn.date);
        int n = pres.executeUpdate();
        pres.close();
        return n;
    }
    public int add(int i)throws SQLException{
        String sql="insert into "+fname+" values(?,?,?,?)";
        PreparedStatement pres = conn.prepareStatement(sql);
        String[] time=getTime();
        pres.setInt(1, Comysql.getCount(fname));
        pres.setInt(2, i);
        pres.setString(3, time[1]);
        pres.setString(4, time[0]);
        int n = pres.executeUpdate();
        pres.close();
        return n;
    }
    public List<Warn> getState(String date)throws SQLException{
        String sql="select * from "+fname+" where date="+"'" + date + "'";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        List<Warn> list_warn=new ArrayList<>();
        while (rs.next()){
            int id=rs.getInt("id");
            int type=rs.getInt("type");
            String mtime=rs.getString("time");
            String mdate=rs.getString("date");
            Warn warn=new Warn(id,type,mtime,mdate);
            list_warn.add(warn);
        }
        rs.close();
        st.close();
        return list_warn;

    }
    public String getJSonwarn(String date)throws  SQLException,JSONException {
        String sql="select * from "+fname+" where date="+"'" + date + "'";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        String result= Comysql.resultSetToJson(rs);
        rs.close();
        st.close();
        return result;
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
