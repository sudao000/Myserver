package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by ASD on 2016/10/22.
 */
@WebServlet(name = "TestServlet")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        downloadChineseFileByOutputStream(response);
    }

    private void downloadChineseFileByOutputStream(HttpServletResponse response)
            throws FileNotFoundException, IOException {
        response.setContentType("text/plain");
        String realPath = "/opt/tomact9/download/yangji.apk";//获取要下载的文件的绝对路径
        //String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);//获取要下载的文件名
        //设置content-disposition响应头控制浏览器以下载的形式打开文件，中文文件名要使用URLEncoder.encode方法进行编码，否则会出现文件名乱码
        response.setHeader("Location", "yang_ji.apk");
        response.setHeader("content-disposition", "attachment;filename=yangji.apk");
        File f=new File(realPath);
        long slengh=f.length();
        System.out.println(slengh);
        InputStream in = new FileInputStream(f);//获取文件输入流
        int len = 0;
        byte[] buffer = new byte[1024];
        //byte[] buffer=FileUtils.readBytes4file(file);
        OutputStream out = response.getOutputStream();
        response.addHeader("Content-Length", "" + slengh);
        long speed = 1024 * 100L;//限制下载速度为100k/s
        long startTime = System.currentTimeMillis();
        int i = -1;
        while ((i = in.read(buffer)) != -1) {
            len = len + i;
            out.write(buffer);
            /*if (len > speed) {
                startPause(startTime + 1000);
                len = 0;
                startTime = System.currentTimeMillis();
            }*/
        }
        out.flush();
        out.close();
        in.close();


    }

    private void startPause(long time) {
        while (true) {
            if (System.currentTimeMillis() > time) {
                break;
            }
        }
    }
}
