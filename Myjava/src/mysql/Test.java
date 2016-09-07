package mysql;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;

/**
 * Created by ASD on 2016/9/1.
 */
public class Test {
    public static void main(String args[]) throws IOException, SQLException {
        /*ServerSocket serivce = new ServerSocket(6000);
        Connection con=Comysql.getConnection();
        while (true) {
            //等待客户端连接
            Socket socket = serivce.accept();
            new Thread(new ReciveRunable(socket,con)).start();
            new Thread(new WriteData(con)).start();
        }*/
        Connection conn = Comysql.getConnection();
        String sql = "select * from sclyj01001_s where date='2016-09-05'";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        try {
            String s=resultSetToJson(rs);
            System.out.println(s);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public  static String resultSetToJson(ResultSet rs) throws SQLException, JSONException {
        JSONArray array = new JSONArray();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            JSONObject jsonobj = new JSONObject();
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
