package servlet;

/**
 * Created by ASD on 2016/8/19.
 */

import mysql.Comysql;
import mysql.dao.UserDao;
import mysql.tab.User;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;

public class ServletRegister extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //response.getWriter().print("dsdsdas");
        this.doPost(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String acceptjson = "";
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        (ServletInputStream) request.getInputStream(), "utf-8"
                ));
        StringBuffer bf = new StringBuffer("");
        String temp;
        while ((temp = br.readLine()) != null) {
            bf.append(temp);
        }
        br.close();
        acceptjson = bf.toString();
        boolean f = false;
        OutputStream os = response.getOutputStream();
        if (acceptjson != "") {
            JSONObject jo = JSONObject.fromObject(acceptjson);
            System.out.println(jo);
            String name=jo.getString("name");
            String psd=jo.getString("psd");
            String company=jo.getString("company");
            String phone=jo.getString("phone");
            String email=jo.getString("email");
            String sheng=jo.getString("sheng");
            String shi=jo.getString("sheng");
            String address=jo.getString("address");
            try {
               int id=Comysql.getMax("userinfo")+1;
                User user=new User(id,name,psd,company,phone,email,sheng,shi,address);
                UserDao userDao=new UserDao(Comysql.getConnection());
                userDao.add(user);
                System.out.println("Ö´ÐÐsql");
                f=true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (f) {
                os.write("×¢²á³É¹¦".getBytes());
                System.out.println("×¢²á³É¹¦");
                os.flush();
                os.close();
            } else {
                os.write("×¢²áÊ§°Ü".getBytes());
                System.out.println("×¢²áÊ§°Ü");
                os.flush();
                os.close();
            }
        }
    }
    public void Writedata(JSONObject jo)throws SQLException{

        UserDao userDao = new UserDao(Comysql.getConnection());
        User user = new User(
                Comysql.getMax("userinfo")+1,
                jo.getString("name"),
                jo.getString("psd"),
                jo.getString("company"),
                jo.getString("phone"),
                jo.getString("email"),
                jo.getString("sheng"),
                jo.getString("shi"),
                jo.getString("adress")
        );
        System.out.println(user.name);
        userDao.add(user);

    }
}