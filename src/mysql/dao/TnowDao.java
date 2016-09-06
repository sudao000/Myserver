package mysql.dao;

import mysql.tab.Tnow;

import java.sql.*;

/**
 * Created by ASD on 2016/9/5.
 */
public class TnowDao {
    Connection conn;
    String fname;

    public TnowDao(Connection conn, String fname) {
        this.conn = conn;
        this.fname = fname+"_t";
    }
    public int update(Tnow tnow)throws SQLException {
        String sql="UPDATE "+fname+" SET dstate=?,astate=? WHERE id=1";
        PreparedStatement pres = conn.prepareStatement(sql);
        pres.setString(1, tnow.ds);
        pres.setString(2, tnow.as);
        int n = pres.executeUpdate();
        pres.close();
        return n;
    }
    public String[] getTnow()throws SQLException{
        String sql="SELECT * FROM "+fname;
        Statement st=conn.createStatement();
        String s[]=new String[2];
        ResultSet rs=st.executeQuery(sql);
        if(rs.next()){
        s[0]=rs.getString("dstate");
        s[1]=rs.getString("astate");}
        rs.close();
        st.close();
        return s;

    }


}
