package test;
import java.io.*;
import java.net.Socket;  
  
public class AndroidRunable implements Runnable {  
  
    Socket socket = null;  
  
    public AndroidRunable(Socket socket) {  
        this.socket = socket;
        System.out.println("����һ���߳�");
    }  
  
    @Override  
    public void run() {  
        // ��android�ͻ������hello worild  
        String line = null;  
        InputStream input;  
        OutputStream output;  
        String str = "��ã�";
        try {  
            //��ͻ��˷�����Ϣ  
            output = socket.getOutputStream();  
            input = socket.getInputStream();  
            BufferedReader bff = new BufferedReader(  
                    new InputStreamReader(input,"gbk"));
            output.write(str.getBytes("gbk"));  
            output.flush();  
            //��ر�socket    

            //��ȡ�ͻ��˵���Ϣ  
            while ((line = bff.readLine()) != null) {
                System.out.print(line);  
            }  
            //�ر����������  
            /*output.close();
            bff.close();  
            input.close();  
            socket.close();  */
 
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
}  
