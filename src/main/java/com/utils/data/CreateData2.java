package com.utils.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author cwc
 * @Despriction: 生产数据
 * @date 2018/10/26 9:02
 */
public class CreateData2 {
    static long fileSize = 10240;


        public static void main(String args[]) {
            String s = "D:/tmp/TXT/a.txt";
            File file =new File(s);
            getFile(file);
//            makeDir(s, 30, 500);

        }

    /**
     * 创建多个子目录
     * @param path
     * @param num1
     * @param num2
     */
    public static void makeDir(String path, int num1, int num2) {
                for (int i = 0; i < num1; i++) {
                    File f = new File(path, getFileName());
                    f.mkdir();
                    for (int j = 0; j < num2; j++){
                        File fs =new File(f.getPath(),(getFileName()+".txt"));//创建文件名后缀自己定义
                        try {
                            fs.createNewFile();
                            getFile(fs);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
        }
    /**
     * 随机名字
     * @return
     */
    public static String getFileName() {
            UUID uuid = UUID.randomUUID();
            String str1 = uuid.toString().substring(0, 10);
            return str1;
        }

    /**
     * 写入随机内容
     * @param myFile
     * @return
     */
    public static String getFile(File myFile){
    //下面把数据写入创建的文件，首先新建文件名为参数创建FileWriter对象
        FileWriter resultFile= null;
        try {
            //把该对象包装进PrinterWriter对象
            resultFile = new FileWriter(myFile);
            PrintWriter myNewFile=new PrintWriter(resultFile);
            //再通过PrinterWriter对象的println()方法把字符串数据写入新建文件
            long size = fileSize;
            while (size -- > 0){
                myNewFile.print(randomChar());
                if (size % 1024 == 0){
                    myNewFile.flush();
                }
            }
            myNewFile.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭文件写入流
            try {
                resultFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

     static ThreadLocalRandom random = ThreadLocalRandom.current();
    public static char randomChar(){
        return (char)(random.nextInt(122-65)+65);//有些字符是不占空间的，需要筛选出A-Z的数据
    }
}
