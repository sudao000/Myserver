package msocket;

import mysql.dao.TnowDao;
import mysql.tab.Tnow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by ASD on 2016/9/5.
 */
public class ReciveRunable implements Runnable {
    public static String fname;

    Socket socket = null;
    Connection conn;

    public ReciveRunable(Socket socket, Connection con) {
        this.socket = socket;
        this.conn = con;
    }

    @Override
    public void run() {
        boolean f = true;
        InputStream input;
        String line = null;
        try {

            input = socket.getInputStream();
            BufferedReader bff = new BufferedReader(
                    new InputStreamReader(input, "gbk"));

            while ((line = bff.readLine()) != null) {
                String s[] = new String[3];
                System.out.println("receive:"+line);
                s = line.split(";");
                if (f) {
                    fname = s[0];
                    f = false;
                }
                TnowDao tnowDao = new TnowDao(conn, s[0]);
                Tnow tnow = new Tnow(1, s[1], s[2]);
                try {
                    tnowDao.update(tnow);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
