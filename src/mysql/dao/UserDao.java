package mysql.dao;

import mysql.tab.User;

import java.sql.*;

/**
 * Created by ASD on 2016/9/1.
 */
public class UserDao {
    Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public int add(User user) throws SQLException {
        String sql = "insert into userinfo values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pres = conn.prepareStatement(sql);
        pres.setInt(1, user.id);
        pres.setString(2, user.name);
        pres.setString(3, user.psd);
        pres.setString(4, user.company);
        pres.setString(5, user.phonenum);
        pres.setString(6, user.email);
        pres.setString(7, user.shen);
        pres.setString(8, user.shi);
        pres.setString(9, user.address);
        int n = pres.executeUpdate();
        pres.close();
        return n;
    }

    public int delete(String s) throws SQLException {
        String sql = "delete from userinfo where name=" + "'+" + s + "'";
        Statement statement = conn.createStatement();
        int n = statement.executeUpdate(sql);
        statement.close();
        return n;
    }

    public int changepsd(int id, String psd) throws SQLException {
        String sql = "update userinfo set psd=" + "'" + psd + "'" + " where id=" + id;
        Statement statement = conn.createStatement();
        int n = statement.executeUpdate(sql);
        statement.close();
        return n;
    }

    public User query(String name) throws SQLException {
        String sql = "select * from userinfo where name=" + "'" + name + "'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        User user = getUSerFromResultSet(rs);
        rs.close();
        st.close();
        return user;

    }

    public boolean check(String name) throws SQLException {
        String sql = "select * from userinfo where name=?";
        PreparedStatement pres = conn.prepareStatement(sql);
        pres.setString(1, name);
        ResultSet rs = pres.executeQuery();
        if (rs.next()) {
            rs.close();
            pres.close();
            return true;
        } else {
            rs.close();
            pres.close();
            return false;
        }


    }

    public boolean proving(String name, String psd) throws SQLException {
        String sql = "select * from userinfo where name=? and psd=?";
        PreparedStatement pres = conn.prepareStatement(sql);
        pres.setString(1, name);
        pres.setString(2, psd);
        ResultSet rs = pres.executeQuery();
        if (rs.next()) {
            rs.close();
            pres.close();
            return true;
        } else {
            rs.close();
            pres.close();
            return false;
        }
    }

    public StringBuffer getMachine(int id) throws SQLException {
        String sql = "select num from machineinfo where usenum=" + id;
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        StringBuffer s=new StringBuffer("");
        while (rs.next()) {
            s.append(rs.getString("num")+",");
        }
        rs.close();
        st.close();
        return s;
    }

    static User getUSerFromResultSet(ResultSet set) throws SQLException {
        if (set.next()) {
            int id = set.getInt("id");
            String name = set.getString("name");
            String psd = set.getString("psd");
            String company = set.getString("company");
            String phonenum = set.getString("phonenum");
            String email = set.getString("email");
            String shen = set.getString("shen");
            String shi = set.getString("shi");
            String adress = set.getString("adress");
            User user = new User(id, name, psd, company, phonenum, email, shen, shi, adress);
            return user;
        } else return null;
    }
}

