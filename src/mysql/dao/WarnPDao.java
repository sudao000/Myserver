package mysql.dao;

import mysql.Comysql;
import mysql.tab.Warnp;
import org.json.JSONException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASD on 2016/9/2.
 */
public class WarnPDao {
    Connection conn;
    String fname;

    public WarnPDao(Connection conn, String fname) {
        this.conn = conn;
        this.fname = fname+"_wp";
    }
    public List<Warnp> getWarnp()throws SQLException{
       String sql="select * from "+fname;
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        List<Warnp> list=new ArrayList<>();
        while (rs.next()){
            int id=rs.getInt("id");
            String sumary=rs.getString("sumary");
            String term=rs.getString("term");
            Warnp warnp=new Warnp(id,sumary,term);
            list.add(warnp);
        }
        return list;
    }
    public String getJSonwp()throws  SQLException,JSONException{
        String sql="select * from "+fname;
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        String result= Comysql.resultSetToJson(rs);
        rs.close();
        st.close();
        return result;
    }

}
