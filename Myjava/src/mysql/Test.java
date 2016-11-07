package mysql;


import msocket.ReciveRunable;
import msocket.WriteData;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ASD on 2016/9/1.
 */
public class Test {
    public static void main(String args[]) throws IOException, SQLException {
        ServerSocket serivce = new ServerSocket(6000);
        Connection con=Comysql.getConnection();
        while (true) {
            //等待客户端连接
            Socket socket = serivce.accept();
            new Thread(new ReciveRunable(socket,con)).start();
            new Thread(new WriteData(con)).start();
        }



    }



}

