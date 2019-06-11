package com.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈万琛
 * @date 2017/12/15 17:52
 * 获取TXT文件里的内容
 */
public  class GetFile {
    public static void main(String[] args) {

        System.out.println( readTxtFile("D:\\IDEAKU\\bigData\\spark_01\\src\\main\\resources\\workOne.txt"));
    }
    public static String readTxtFile(String filePath){
        List<String> list=new ArrayList<>();
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    list.add(lineTxt);
//                    System.out.println(lineTxt);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list.toString();
    }
}
