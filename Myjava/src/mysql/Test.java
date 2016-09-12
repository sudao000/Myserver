package mysql;


import mysql.dao.WarnDao;
import mysql.dao.WarnPDao;
import mysql.tab.Warnp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
        /*String s1 = "1,0,1,0,0,0,1";
        String s2 = "20,36,58,37,20";
        String a1 = "1,4,5";
        String a2 = "0,1,0";
        String b1 = "1,2,4";
        String b2 = "50,-40,15";

        int[] ints1 = StoInt(s1);
        int[] inta1 = StoInt(a1);
        float[] floats2 = StoFloat(s2);
        float[] floatb2 = StoFloat(b2);
        int[] inta2 = StoInt(a2);
        int[] intb1 = StoInt(b1);
        int a1size = inta1.length;
        int b1size = intb1.length;
        for (int z = 0; z < a1size; z++) {
            if (ints1[inta1[z]] == inta2[z]) {
                System.out.println(z + "：相等");
            } else {
                System.out.println(z + ":不相等");
            }
        }
        for (int x = 0; x < b1size; x++) {
            if (floatb2[x] > 0) {
                if (floatb2[x] - floats2[intb1[x]] > 0) {
                    System.out.println(a1size + x + ":小于上限");
                } else {
                    System.out.println(a1size + x + ":大于上限");
                }
            } else {
                if (floatb2[x] + floats2[intb1[x]] > 0) {
                    System.out.println(a1size + x + ":大于下限");
                } else {
                    System.out.println(a1size + x + ":小于下限");
                }
            }
        }*/
        Fztest test=new Fztest();
        test.wrd();
    }


    public static int[] StoInt(String s) {
        if (s.contains(",")) {
            String m[] = s.split(",");
            int m_size = m.length;
            int[] a = new int[m_size];
            for (int q = 0; q < m_size; q++) {
                a[q] = Integer.parseInt(m[q]);
            }
            return a;


        } else {
            System.err.println("格式有误!");
            return null;
        }

    }

    public static float[] StoFloat(String s) {
        if (s.contains(",")) {
            String m[] = s.split(",");
            int m_size = m.length;
            float[] a = new float[m_size];
            for (int q = 0; q < m_size; q++) {
                a[q] = Float.parseFloat(m[q]);
            }
            return a;

        } else {
            System.err.println("格式有误!");
            return null;
        }

    }
    public static void  wrd()throws SQLException{
        String s1 = "1,0,1,0,0,0,1";
        String s2 = "20,36,58,37,20";
        int[] ints1 = StoInt(s1);
        float[] floats2 = StoFloat(s2);
        int a=0;
        WarnPDao warnPDao=new WarnPDao(Comysql.getConnection(),"sclyj01001");
        List<Warnp> lwp=warnPDao.getWarnp();
        WarnDao warnDao=new WarnDao(Comysql.getConnection(),"sclyj01001");

        int lwp_size=lwp.size();
        for(int i=0;i<lwp_size;i++){
            Warnp warnp=lwp.get(i);
            String s=warnp.term;
            String m[]=s.split(",");
          /*  System.out.println(s);
            System.out.println(m[0]);
            System.out.println(m[1]);
            System.out.println(m[2]);
            System.out.println(m[3]);*/
            float wt=Float.parseFloat(m[3]);
            int it=Integer.parseInt(m[3]);
            int index=Integer.parseInt(m[1]);
            if(s.contains("a")){
                if(s.contains(">")){
                    if(floats2[index]>wt){
                        System.out.println(i);
                    }
                }
                else if(s.contains("<")){
                    if(floats2[index]<wt){
                        System.out.println(i);
                    }
                }

            }
            else {
                if(ints1[index]==it){
                    System.out.println(i);
                }

            }
        }

    }


}
