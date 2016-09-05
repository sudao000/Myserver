package test;
import java.io.*;
import java.net.Socket;  
  
public class AndroidRunable implements Runnable {  
  
    Socket socket = null;  
  
    public AndroidRunable(Socket socket) {  
        this.socket = socket;
        System.out.println("创建一个线程");
    }  
  
    @Override  
    public void run() {  
        // 向android客户端输出hello worild  
        String line = null;  
        InputStream input;  
        OutputStream output;  
        String str = "你好！";
        try {  
            //向客户端发送信息  
            output = socket.getOutputStream();  
            input = socket.getInputStream();  
            BufferedReader bff = new BufferedReader(  
                    new InputStreamReader(input,"gbk"));
            output.write(str.getBytes("gbk"));  
            output.flush();  
            //半关闭socket    

            //获取客户端的信息  
            while ((line = bff.readLine()) != null) {
                System.out.print(line);  
            }  
            //关闭输入输出流  
            /*output.close();
            bff.close();  
            input.close();  
            socket.close();  */
 
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
}  
