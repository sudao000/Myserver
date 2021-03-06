package servlet;

import mysql.Comysql;
import mysql.dao.*;
import org.json.JSONArray;
import org.json.JSONException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

/**
 * Created by ASD on 2016/8/18.
 */

public class DataServelet extends javax.servlet.http.HttpServlet {

    private String form,num,para;
    private Connection conn;

    protected void doPost(javax.servlet.http.HttpServletRequest request, HttpServletResponse
            response) throws javax.servlet.ServletException, IOException {

       this.doGet(request,response);
    }
    protected void doGet(javax.servlet.http.HttpServletRequest request, HttpServletResponse
            response) throws javax.servlet.ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type","text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        form=request.getParameter("form");
        num=request.getParameter("num");
        para=request.getParameter("para");
        conn= Comysql.getConnection();
        int i=Integer.parseInt(form);
        OutputStream os = response.getOutputStream();
        String result="";
        System.out.println("i:"+i);
        //os.write(("i:"+i).getBytes());
        if(form!=null&&num!=null&&para!=null){
            try{
                switch (i){
                    case 1:
                        MachineDao machineDao=new MachineDao(conn);
                        result=machineDao.getJsonMachine(num);
                       // System.out.println("1:"+result);
                        break;
                    case 2:
                        MtainDao mtainDao=new MtainDao(conn,num);
                        result=mtainDao.getJson(para);
                        //System.out.println("2:"+result);
                        break;
                    case 3:
                        StateDao stateDao=new StateDao(conn,num);
                        result=stateDao.getJSonState(para);
                        //System.out.println("3:"+result);

                        break;
                    case 4:
                        TnowDao tnowDao=new TnowDao(conn,num);
                        result=tnowDao.getJSonTnow();
                        //System.out.println("4:"+result);

                        break;
                    case 5:
                        UserDao userDao=new UserDao(conn);
                        result=userDao.getJSonUser(para);
                        //System.out.println("5:"+result);

                        break;
                    case 6:
                        WarnDao warnDao=new WarnDao(conn,num);
                        result=warnDao.getJSonwarn(para);
                        //System.out.println("6:"+result);
                        break;
                    case 7:
                        WarnPDao warnPDao=new WarnPDao(conn,num);
                        result=warnPDao.getJSonwp();
                        //System.out.println("7:"+result);
                        break;
                    default:
                        result="false";


                }
            }catch (Exception e) {
                e.printStackTrace();
            }



        }
        else {
            result="参数有误，请重新输入！";
        }
        try {
            JSONArray json=new JSONArray(result);
            int size=json.length();
            for(int q=0;q<size;q++)
            {
                //System.out.println("shifoujinru2");
                os.write(json.get(q).toString().getBytes("UTF-8"));
            }
            os.flush();
            os.close();
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }




}
