package com.utils.RS232;

import gnu.io.CommPortIdentifier;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author wanchen.chen
 * @ClassName RxtxBuilder
 * @Despriction:
 * @date 2019/10/22 15:39
 * @Version 1.0
 */
public class RxtxBuilder {
    static List<CommUtil> comms = new ArrayList<CommUtil>();

    static void init(List<String> names) {
        Enumeration portList = CommPortIdentifier.getPortIdentifiers(); // 得到当前连接上的端口
        while (portList.hasMoreElements()) {
            CommPortIdentifier temp = (CommPortIdentifier) portList.nextElement();
            if (temp.getPortType() == CommPortIdentifier.PORT_SERIAL) {// 判断如果端口类型是串口
                if (names.contains(temp.getName())) { // 判断如果端口已经启动就连接
                    System.err.println("端口---" + temp.getName() + "启动监听");
                    CommUtil comm = new CommUtil(temp, temp.getName());
                    comms.add(comm);
                }
            }
        }
    }

}
