package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ASD on 2016/8/25.
 */
public class Getindex {
    private static ArrayList<String> filelist=new ArrayList<String>();
    public static  void main(String[] args){

        File f=new File("F:\\Java\\javascript学习总结\\index.html");
        try {
            f.createNewFile() ;	// 根据给定的路径创建新文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filepath="F:\\Java\\javascript学习总结";
        getFiles(filepath);

    }
    static void getFiles( String filePath )
    {   String r="—.+?-";
        File root = new File( filePath );
        File[] files = root.listFiles();

        for ( File file : files )
        {

            String s=file.getAbsolutePath().replaceAll(r,"dsd");
            if ( !file.isDirectory() )
            {   System.out.println(file.getAbsolutePath());
                System.out.println(s);
            }
        }
    }


}
