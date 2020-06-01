package com.wpx.test;

import com.wpx.WpxLnx2Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * @Author: wpx
 * @Date: 2020/3/30 21:51
 * @Version: V_1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WpxLnx2Application.class)
public class FileIOTest {



    /**
     * @Author: wpx
     * @Description: 创建一个新的文件
     * @Date: 2020/3/30
     * @param
     */
    @Test
    public void file(){
        String PATH="D:\\1.桌面\\王培旭\\实习前复习的内容\\io\\代码生成的文件";
        String PATH_NAME="我爱你.txt";
        File file = new File( PATH,"新建文件夹.txt");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            int b;
            while ((b=bufferedInputStream.read())!=-1){
                System.out.println(b);
            }
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
