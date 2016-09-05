package mysql.dao;

import mysql.tab.Machine;

import java.sql.*;

/**
 * Created by ASD on 2016/9/2.
 */
public class MachineDao {
    Connection conn;

    public MachineDao(Connection conn) {
        this.conn = conn;
    }

    public int add(Machine machine) throws SQLException {
        String sql = "insert into machineinfo values(?,?,?,?,?,?,?,?)";
        PreparedStatement pres = conn.prepareStatement(sql);
        pres.setInt(1, machine.id);
        pres.setString(2, machine.num);
        pres.setString(3, machine.name);
        pres.setInt(4, machine.usenum);
        pres.setString(5, machine.summary);
        pres.setString(6, machine.aparm);
        pres.setString(7,machine.dparm);
        pres.setString(8, machine.date);
        int n = pres.executeUpdate();
        pres.close();
        return n;
    }

    public Machine getInfo(String num) throws SQLException {
        String sql = "select * from machineinfo where num=" + "'" + num + "'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        Machine machine = getMachinefromresult(rs);
        rs.close();
        st.close();
        return machine;

    }

    private Machine getMachinefromresult(ResultSet set) throws SQLException {
        if (set.next()) {
            int id = set.getInt("id");
            String num = set.getString("num");
            String name = set.getString("name");
            int usenum = set.getInt("usenum");
            String summary = set.getString("summary");
            String aparm = set.getString("aparm");
            String dparm = set.getString("dparm");
            String date = set.getString("date");
            Machine machine = new Machine(id, num, name, usenum, summary, aparm,dparm, date);
            return machine;
        } else return null;
    }


}

