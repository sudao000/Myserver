package mysql;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ASD on 2016/9/1.
 */
public class Test {
    public static void main(String args[]) {
        /*try {
            s = td.getTnow();
            System.out.println(s[0] + "\n" + s[1] + ";");
            td.update(tnow);
            s = td.getTnow();
            System.out.println(s[0] + "\n" + s[1] + ";");


        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        Date date=new Date();
        DateFormat f1=new SimpleDateFormat("yyy-MM-dd");
        DateFormat f2=new SimpleDateFormat("HH:mm:ss");
        String s[]=new String[2];
        s[0]=f1.format(date);
        s[1]=f2.format(date);
        System.out.println(s[0]+" "+s[1]);


    }
}
