package mysql;

import dao.Myjson;
import org.json.JSONArray;
import org.json.JSONException;

import java.sql.*;

/**
 * Created by ASD on 2016/8/30.
 */
public class Comysql {
    Connection connection;
    Statement statement;
    ResultSet rSet;

    //返回一个与特定数据库的连接
    public static Connection getConnection() {
        Connection connection=null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
               // Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("无法找到驱动类");
            }
//连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码
           /*  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager?" +
                            "useUnicode=true&characterEncoding=utf8&DBMSendcoding=utf8&serverTimezone=GMT&useSSL=false",
                    "root", "0000");//新增3个url参数为了解决编码问题，时区错乱以及SSL错误。*/
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager?" +
                            "useUnicode=true&characterEncoding=utf8",
                    "root", "0000");//新增3个url参数为了解决编码问题，时区错乱以及SSL错误。
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static int getCount(String form)throws  SQLException{
        Connection conn=getConnection();
        String sql="select * from "+form;
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        rs.last();
        int n=rs.getRow();
        return n;

    }
    public static int getMax(String form)throws  SQLException{
        Connection conn=getConnection();
        String sql="select MAX(id) from "+form;
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        if(rs.next())
        {int n=rs.getInt("max(id)");
            return n;}
        else return -1;
    }
    public  static String resultSetToJson(ResultSet rs) throws SQLException, JSONException {
        JSONArray array = new JSONArray();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            Myjson jsonobj=new Myjson();
            for(int i=1;i<=columnCount;i++){
                String columnName=metaData.getColumnLabel(i);
                String value=rs.getString(columnName);
                jsonobj.put(columnName,value);

            }
            array.put(jsonobj);
        }
        return array.toString();
    }
}

