package mysql.tab;

/**
 * Created by ASD on 2016/9/5.
 */
public class User {
    public int id;
    public String name, psd, company, phonenum, email, shen, shi, address;
    public User(){}

    public User(int id, String name, String psd, String company,
                String phonenum, String email, String shen, String shi, String address) {
        this.id = id;
        this.name = name;
        this.psd = psd;
        this.company = company;
        this.phonenum = phonenum;
        this.email = email;
        this.shen = shen;
        this.shi = shi;
        this.address = address;
    }
}
