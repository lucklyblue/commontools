package com.utils.RS232;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanchen.chen
 * @ClassName SerialUtils
 * @Despriction: 串口工具类 需要导入RXTXcomm.jar，rxtxParallel.dll，rxtxSerial.dll到jre目录与项目中
 *              项目中也可以用maven
 * @date 2019/10/23 14:18
 * @Version 1.0
 */
public class SerialUtils {


    /**
    * @author wanchen.chen
    * @Description 将数据写入单个串口
    * @Date 14:36 2019/10/23
    * @Param []
    * @return void
    **/
    public  String writeData(String comName,String data){
        //1.定义变量
        CommPortIdentifier com = null;//用于记录本地串口
        SerialPort serialCom = null;//用于标识打开的串口
        try {
            com = CommPortIdentifier.getPortIdentifier(comName);//获取串口
//            System.out.println(com.isCurrentlyOwned()+"=============");
            serialCom = (SerialPort) com.open(comName+"write",1000);//打开串口
            OutputStream outputStream = serialCom.getOutputStream();//获取串口输出流对象
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();
            serialCom.close();
        } catch (NoSuchPortException e) {
            System.err.println("不是串口！");
            e.printStackTrace();
        } catch (PortInUseException e) {
            System.err.println("串口正在使用！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "发送成功！";
    }

    /**
    * @author wanchen.chen
    * @Description 获取串口数据
    * @Date 14:41 2019/10/23
    * @Param [comName] 监听的串口
    * @return void
    **/
    public void readData(String comName){
        List<String> portNameList = new ArrayList<String>();
        portNameList.add(comName);
        //传入当前空闲可用的端口，进行监听
        RxtxBuilder.init(portNameList);
        CommUtil comm = RxtxBuilder.comms.get(0);
        try {
            while (true) {
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            comm.ClosePort();
        }
    }

    /**
    * @author wanchen.chen
    * @Description 数据追加到本地
    * @Date 17:05 2019/10/23
    * @Param [data]
    * @return java.lang.String
    **/
    public String OutToFile(String inpath,String data) {
        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f=new File(inpath);
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(data);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "追加成功！";
    }
}
