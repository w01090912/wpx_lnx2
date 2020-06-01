package com.wpx;

import org.junit.Test;

import java.io.Console;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: wpx
 * @Date: 2020/4/16 14:23
 * @Version: V_1.0.0
 */
public class OneTest {

    public static void main(String[] args) {
        //one();
        //two（）；
        three("D:\\1.桌面","二手车原文件.docx");
        //six();
    }

    public static void six() {
        String str = "main-action-holder";
        String[] splits = str.split("-");
        for(int i=0;i<splits.length;i++){
            str=str+splits[i];
        }
        System.out.println(str);
    }

    /**
     * @param path
     * @Author: wpx
     * @Description: 使用Java代码或伪代码列出一个目录下所有的文件，包括所有子目录下的文件，并分别打印出所有文件和目录的数量
     * @Date: 2020/4/16
     */
    public static void three(String path,String fileName) {
        //获取 path路径文件
        File yuan =new File(fileName);
        File file = new File(path);

        ArrayList<File> folders = new ArrayList<File>();//存放所有的文件夹对象
        ArrayList<File> files = new ArrayList<File>();//存放所有的文件对象

        if (file.exists()) { //判断该文件是否存在
            if (file.isDirectory()) {
                folders.add(file);
                for (File folder : folders) { //遍历文件夹集合并获取其下所有的子文件  //判断file是否是文件夹  file.isDirectory()
                    File[] listFiles = file.listFiles();//获取文件下的子文件
                    for (File f : listFiles) {
                        if (f.isDirectory()) {//判断file是否是文件夹
                            System.out.println("文件夹：" + f.getName());
                            String[] childFileNameList = f.list();//文件夹就继续遍历下的子文件
                            for (String name : childFileNameList) {
                                System.out.println(name);
                            }
                        } else if (f.isFile()) {
                            System.out.println("文件：" + f.getName());
                        } else {
                            System.err.println("出现错误！");
                        }
                    }
                }
            }else{
                //读取该文件的内容

                //输出到新的文件中


            }
        }
    }

    /**
     * @param
     * @Author: wpx
     * @Description: 给定长度为500的数组，随机放入值为1-50中间的任意整数，请编写程序找出其中的偶数数字，并按照该数字在数组中出现次数从多到少排序输出
     * @Date: 2020/4/16
     */
    public static void two() {
        Integer[] arr = new Integer[500];
        Random rn = new Random();
        for (int i = 0; i <= 500 - 1; i++) {
            arr[i] = rn.nextInt(49) + 1;

        }
        //数组存入list
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(arr));

        for (int i = 0; i <= arr.length; i++) {
            if (arr[i] % 2 != 0) {
                list.remove(arr[i]);
            }

        }
        Integer[] newarr = new Integer[list.size()];
        list.toArray(newarr);


    }

    /**
     * @param
     * @Author: wpx
     * @Description: 写一个验证掷骰子概率的程序，同时投掷2颗6面骰子n次，计算得到各数字的概率。
     * @Date: 2020/4/16
     */
    public static void one() {

        Scanner scan = new Scanner(System.in);
        System.out.print("请输入次数：");
        Integer read = Integer.valueOf(scan.nextLine());
        while (read >= 0) {
            try {
                if (read == 0) {
                    break;
                }
                Random r = new Random();
                BigDecimal total = new BigDecimal(read);
                Integer temp = 0;
                Map<Integer, Object> hashMap = new HashMap<>();
                for (int i = 0; i < read; i++) {// 6次
                    for (int j = 0; j < 2; j++) {// 2 颗
                        int key = r.nextInt(6) + 1;
                        temp = +key;
                        //map存储数据出现的次数  存在value+1  不存在则存进去value=1
                        if (hashMap.containsKey(key) && j == 1) {
                            hashMap.put(temp, Integer.valueOf(hashMap.get(temp).toString()) + 1);
                            temp = 0;
                        } else if (j == 1) {
                            hashMap.put(temp, 1);
                        }
                    }
                }
                if (hashMap != null && hashMap.size() > 0) {
                    for (int i = 2; i < 13; i++) {
                        System.out.println(hashMap.containsKey(i));
                        if (hashMap.get(i) != null) {
                            System.err.println(String.format("数字为%s的概率=%s", i,
                                    (new BigDecimal(Integer.valueOf(hashMap.get(i).toString()))
                                            .divide(total, 2, BigDecimal.ROUND_HALF_UP).setScale(2))
                                            .multiply(new BigDecimal(100))
                                            + "%"));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("数据有误!");
            }
            read--;
        }
        scan.close();
    }
}
