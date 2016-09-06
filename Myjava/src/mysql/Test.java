package mysql;

import java.io.IOException;

/**
 * Created by ASD on 2016/9/1.
 */
public class Test {
    public static void main(String args[]) throws IOException {
        /*ServerSocket serivce = new ServerSocket(6000);
        Connection con=Comysql.getConnection();
        while (true) {
            //等待客户端连接
            Socket socket = serivce.accept();
            new Thread(new ReciveRunable(socket,con)).start();
            new Thread(new WriteData(con)).start();
        }*/
        String s[] = {"001", "002", "003", "004"};
        StringBuffer a = new StringBuffer("");
        for (int i = 0; i < s.length; i++) {

                a.append(s[i] + ",");

        }
        String s1=a.toString();
        s=s1.split(",");
        for(int i=0;i<s.length;i++)
        { System.out.println(i);}

    }
}
