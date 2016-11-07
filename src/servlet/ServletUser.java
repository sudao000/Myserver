package servlet;


import mysql.Comysql;
import mysql.dao.UserDao;
import mysql.tab.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASD on 2016/8/19.
 */
public class ServletUser extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doPost(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type","text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String name =request.getParameter("name");
        String psd=request.getParameter("psd");
        UserDao userDao=new UserDao(Comysql.getConnection());
        PrintWriter out=response.getWriter();
        if(name!=null){
            if(psd==null){
                try {
                    boolean f1=userDao.check(name);
                    if(f1){
                        out.print("存在：true");
                        out.flush();
                        out.close();
                    }else {
                        out.print("不存在：false");
                        out.flush();
                        out.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    boolean f2=userDao.proving(name,psd);
                    if(f2){
                        User user=userDao.query(name);
                        StringBuffer machine=userDao.getMachine(user.id);
                        Map map=new HashMap();
                        map.put("name",user.name);
                        map.put("company",user.company);
                        map.put("phonenum",user.phonenum);
                        map.put("email",user.email);
                        map.put("shen",user.shen);
                        map.put("shi",user.shi);
                        map.put("adress",user.address);
                        map.put("machine",machine);
                        System.out.println(map.toString());
                        JSONObject json=new JSONObject(map);
                        out.print(json);
                        out.flush();
                        out.close();
                    }else {
                        out.print("用户名或密码错误：false");
                        out.flush();
                        out.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}