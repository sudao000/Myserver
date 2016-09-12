package mysql;

import mysql.dao.WarnDao;
import mysql.dao.WarnPDao;
import mysql.tab.Warnp;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ASD on 2016/9/12.
 */
public class Fztest {

    public  void  wrd()throws SQLException {
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
            float wt=Float.parseFloat(m[3]);
            int it=Integer.parseInt(m[3]);
            int index=Integer.parseInt(m[1]);
            if(s.contains("a")){
                if(s.contains(">")){
                    if(floats2[index]>wt){
                        System.out.println(i);
                        warnDao.add(i);
                    }
                }
                else if(s.contains("<")){
                    if(floats2[index]<wt){
                        warnDao.add(i);
                        System.out.println(i);
                    }
                }

            }
            else {
                if(ints1[index]==it){
                    warnDao.add(i);
                    System.out.println(i);
                }

            }
        }

    }
    public  int[] StoInt(String s) {
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

    public  float[] StoFloat(String s) {
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


}
