package mysql.dao;

import mysql.tab.Warn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
}
