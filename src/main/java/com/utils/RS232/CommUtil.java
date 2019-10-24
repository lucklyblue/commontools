package com.utils.RS232;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

/**
 * @author wanchen.chen
 * @ClassName CommUtil
 * @Despriction: 实体类，监控读取类，当有数据会自动触发读取数据操作
 * @date 2019/10/22 15:40
 * @Version 1.0
 */
public class CommUtil implements SerialPortEventListener {

    InputStream inputStream; // 从串口来的输入流
    OutputStream outputStream;// 向串口输出的流
    SerialPort serialPort; // 串口的引用
    CommPortIdentifier portId;
    SerialUtils su =new SerialUtils();
    int count = 0;

    public CommUtil(CommPortIdentifier temp , String name) {
        try {
            portId = temp;
            serialPort = (SerialPort) portId.open("My" + name, 2000);
        } catch (PortInUseException e) {

        }
        try {
            inputStream = serialPort.getInputStream();
            outputStream = serialPort.getOutputStream();
        } catch (IOException e) {
        }
        try {
            serialPort.addEventListener(this); // 给当前串口添加一个监听器
        } catch (TooManyListenersException e) {
        }
        serialPort.notifyOnDataAvailable(true); // 当有数据时通知
        try {
            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, // 设置串口读写参数
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {
        }
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;

            case SerialPortEvent.DATA_AVAILABLE:// 当有可用数据时读取数据,并且给串口返回数据
                byte[] readBuffer = new byte[1024];
                try {
                    if(inputStream.available() > 0){
                        int numBytes = inputStream.read(readBuffer);
//                        String req = new String(readBuffer,"UTF-8");
                        String req = new String(readBuffer,"GBK");
                        //这里对读取的数据进行处理，可以保存到本地txt格式或者做别的操作，下面注释的方式是使用追加的方式将数据写入文本中
                        su.OutToFile("D:\\logs\\dd.txt",req);
                        System.out.println(Thread.currentThread()+"---"+req);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void send(String content) {
        try {
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ClosePort() {
        if (serialPort != null) {
            serialPort.close();
        }
    }

}
