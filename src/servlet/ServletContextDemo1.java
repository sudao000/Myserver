package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;

/**
 * Created by ASD on 2016/8/19.
 */
public class ServletContextDemo1 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //客户端是以UTF-8编码传输数据到服务器端的，所以需要设置服务器端以UTF-8的编码进行接收，否则对于中文数据就会产生乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("username");
        response.getWriter().print("您已提交用户名："+userName);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     * 设置图片的背景色
     *
     * @param g
     */

    private void setBackGround(Graphics g) {
        // 设置颜色
        g.setColor(Color.WHITE);
        // 填充区域
        g.fillRect(0, 0, 120, 30);
    }

    /**
     * 设置图片的边框
     *
     * @param g
     */
    private void setBorder(Graphics g) {
        // 设置边框颜色
        g.setColor(Color.BLUE);
        // 边框区域
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
    }

    /**
     * 在图片上画随机线条
     *
     * @param g
     */
    private void drawRandomLine(Graphics g) {
        // 设置颜色
        g.setColor(Color.GREEN);
        // 设置线条个数并画线
        for (int i = 0; i < 5; i++) {
            int x1 = new Random().nextInt(120);
            int y1 = new Random().nextInt(30);
            int x2 = new Random().nextInt(120);
            int y2 = new Random().nextInt(30);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 画随机字符
     *
     * @param g
     * @param createTypeFlag
     * @return String... createTypeFlag是可变参数，
     * Java1.5增加了新特性：可变参数：适用于参数个数不确定，类型确定的情况，java把可变参数当做数组处理。注意：可变参数必须位于最后一项
     */
    private String drawRandomNum(Graphics2D g, String... createTypeFlag) {
        // 设置颜色
        g.setColor(Color.RED);
        // 设置字体
        g.setFont(new Font("宋体", Font.BOLD, 20));
        //常用的中国汉字
        String baseChineseChar = "\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9" +
                "\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c\u90a3\u8981\u4e0b\u770b" +
                "\u6e05\u9633\u7167\u529e\u53f2\u6539\u5386\u8f6c\u753b\u9020\u5634\u6b64\u6cbb\u5317";
        //数字和字母的组合
        String baseNumLetter = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";
        //纯数字
        String baseNum = "0123456789";
        //纯字母
        String baseLetter = "ABCDEFGHJKLMNOPQRSTUVWXYZ";
        //createTypeFlag[0]==null表示没有传递参数
        if (createTypeFlag.length > 0 && null != createTypeFlag[0]) {
            if (createTypeFlag[0].equals("ch")) {
                // 截取汉字
                return createRandomChar(g, baseChineseChar);
            } else if (createTypeFlag[0].equals("nl")) {
                // 截取数字和字母的组合
                return createRandomChar(g, baseNumLetter);
            } else if (createTypeFlag[0].equals("n")) {
                // 截取数字
                return createRandomChar(g, baseNum);
            } else if (createTypeFlag[0].equals("l")) {
                // 截取字母
                return createRandomChar(g, baseLetter);
            }
        } else {
            // 默认截取数字和字母的组合
            return createRandomChar(g, baseNumLetter);
            // return createRandomChar(g, baseChineseChar);
        }

        return "";
    }

    /**
     * 创建随机字符
     *
     * @param g
     * @param baseChar
     * @return 随机字符
     */
    private String createRandomChar(Graphics2D g, String baseChar) {
        StringBuffer sb = new StringBuffer();
        int x = 5;
        String ch = "";
        // 控制字数
        for (int i = 0; i < 4; i++) {
            // 设置字体旋转角度
            int degree = new Random().nextInt() % 30;
            ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
            sb.append(ch);
            // 正向角度
            g.rotate(degree * Math.PI / 180, x, 20);
            g.drawString(ch, x, 20);
            // 反向角度
            g.rotate(-degree * Math.PI / 180, x, 20);
            x += 30;
        }
        return sb.toString();
    }


}
