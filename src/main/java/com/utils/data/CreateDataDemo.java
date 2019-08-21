package com.utils.data;

import java.io.*;
import java.util.Random;
import java.util.UUID;

/**
 * @author wanchen.chen
 * @ClassName CreateDataDemo
 * @Despriction:
 * @date 2019/8/20 14:25
 * @Version 1.0
 */
public class CreateDataDemo {

    //内容
    static String[] index ={"有车一族","有房一族","有女友一族","单身狗","高富帅","白富美","有矿一族","月光一族"};
    //存储地址
    static String filePath = "D:\\tmp\\TXT\\demo.txt";

    public static void main(String[] args) {
        Random random =new Random();
        int rd=0;
        StringBuffer sb =new StringBuffer();
        for (int i=0;i<50;i++){
            StringBuffer in =new StringBuffer();
            rd= random.nextInt(8);
            for (int j=0;j<rd+1;j++){
                if(j==(rd)){
                    in.append(index[j]);
                }else{
                    in.append(index[j]+",");
                }
            }
            sb.append(UUID.randomUUID().toString().replaceAll("-", "").substring(0,10)+"\t"+in.toString()+"\n");
        }
        System.out.println(sb);
        string2File(sb.toString(),filePath,"UTF-8");
    }

    /**
    * @author wanchen.chen
    * @Description 写到本地
    * @Date 15:23 2019/8/20
    * @Param [srcStr, filePath, encoding]
    * @return void
    **/
    public static void string2File(String srcStr, String filePath, String encoding){
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new StringReader(srcStr));
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), encoding));
            char[] buf = new char[4096];
            int len;
            while ((len = reader.read(buf)) != -1) {
                writer.write(buf, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
