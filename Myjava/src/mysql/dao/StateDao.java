package mysql.dao;

import mysql.Comysql;
import mysql.tab.State;
import org.json.JSONException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASD on 2016/9/2.
 */
public class StateDao {
    Connection conn;
    String fname;

    public StateDao(Connection conn, String fname) {
        this.conn = conn;
        this.fname = fname+"_s";
    }
    public int add(State state) throws SQLException{
      String sql="insert into "+fname+" values(?,?,?,?)";
        PreparedStatement pres = conn.prepareStatement(sql);
        pres.setInt(1, state.id);
        pres.setString(2, state.state);
        pres.setString(3, state.time);
        pres.setString(4, state.date);
        int n = pres.executeUpdate();
        pres.close();
        return n;
    }
    public List<State> getState(String date)throws SQLException{
        String sql="select * from "+fname+" where date="+"'" + date + "'";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        List<State> list_state=new ArrayList<>();
        while (rs.next()){
            int id=rs.getInt("id");
            String stss=rs.getString("state");
            String mtime=rs.getString("time");
            String mdate=rs.getString("date");
            State s=new State(id,stss,mtime,mdate);
            list_state.add(s);
        }
        rs.close();
        st.close();
        return list_state;


    }
    public String getJSonState(String date)throws  SQLException,JSONException{
        String sql="select * from "+fname+" where date="+"'" + date + "'";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        String result= Comysql.resultSetToJson(rs);
        rs.close();
        st.close();
        return  result;
    }


}
