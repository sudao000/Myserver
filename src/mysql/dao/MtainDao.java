package mysql.dao;

import mysql.tab.Mtain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASD on 2016/9/2.
 */
public class MtainDao {
    Connection conn;
    String fname;

    public MtainDao(Connection conn, String fname) {
        this.conn = conn;
        this.fname = fname+"_m";
    }
    public int add(Mtain mtain)throws SQLException{
        String sql="insert into "+fname+" values(?,?,?,?,?,?)";
        PreparedStatement pres = conn.prepareStatement(sql);
        pres.setInt(1, mtain.id);
        pres.setString(2, mtain.que);
        pres.setString(3, mtain.way);
        pres.setString(4, mtain.person);
        pres.setString(5, mtain.time);
        pres.setString(6, mtain.date);
        int n = pres.executeUpdate();
        pres.close();
        return n;
    }
    public List<Mtain> getMtain(String date)throws SQLException{
        String sql="select * from "+fname+" where date="+"'" + date + "'";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        List<Mtain> list=new ArrayList<>();
        while (rs.next()){
            int id=rs.getInt("id");
            String que=rs.getString("que");
            String way=rs.getString("way");
            String person=rs.getString("person");
            String time=rs.getString("time");
            String mdate=rs.getString("date");
            Mtain ma=new Mtain(id,que,way,person,time,mdate);
            list.add(ma);
        }
        rs.close();
        st.close();
        return list;
    }


}
