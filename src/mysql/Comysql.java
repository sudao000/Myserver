package mysql;

import java.sql.*;

/**
 * Created by ASD on 2016/8/30.
 */
public class Comysql {
    Connection connection;
    Statement statement;
    ResultSet rSet;

    //����һ�����ض����ݿ������
    public static Connection getConnection() {
        Connection connection=null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("�޷��ҵ�������");
            }
//����URLΪ jdbc:mysql//��������ַ/���ݿ��� �������2�������ֱ��ǵ�½�û���������
             connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager?" +
                            "useUnicode=true&characterEncoding=utf8&DBMSendcoding=utf8&serverTimezone=GMT&useSSL=false",
                    "root", "0000");//����3��url����Ϊ�˽���������⣬ʱ�������Լ�SSL����
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
}

