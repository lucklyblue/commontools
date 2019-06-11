package com.utils.data;

import com.utils.date.DateUtils;
import com.utils.string.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
/**
 * 模拟数据程序
 *
 * @author wanchen.chen
 */
public class CreateData {

    /**
     * 模拟数据
     *
     * @param sc
     * @param sqlContext
     */
    public static final String ACTIONPATH = "D:\\IDEAKU\\TXT\\action.txt";
    public static final String USERPATH = "D:\\IDEAKU\\TXT\\user.txt";
    public static final String PRODUCTPATH = "D:\\IDEAKU\\TXT\\product.txt";

    public static void mock() {

        String[] searchKeywords = new String[]{"百雀羚","天山雪莲","金士顿内存条8G","雷蛇"
                ,"IphoneX256G","舒肤佳香皂-柠檬","Dior可可小姐","兰蔻小黑瓶","纪梵尼-鲨鱼外套","UGG-雪地鞋-42"};

        String date = DateUtils.getTodayDate().substring(0, 10);
        String[] actions = new String[]{"search", "click", "order", "pay"};
        Random random = new Random();
        CreateData.createFile(ACTIONPATH);
        for (int i = 0; i < 10000; i++) {
            long userid = random.nextInt(100);

            for (int j = 0; j < 10; j++) {
                String sessionid = UUID.randomUUID().toString().replace("-", "");
                String baseActionTime = date + " " + random.nextInt(23);

                long clickCategoryId = 1;

                for (int k = 0; k < random.nextInt(100); k++) {
                    long pageid = random.nextInt(10);
                    String actionTime = baseActionTime + ":" + StringUtils.fulfuill(String.valueOf(random.nextInt(59))) + ":" + StringUtils.fulfuill(String.valueOf(random.nextInt(59)));
                    String searchKeyword = null;
                    long clickProductId = 1;
                    String orderCategoryIds = null;
                    String orderProductIds = null;
                    String payCategoryIds = null;
                    String payProductIds = null;

                    String action = actions[random.nextInt(4)];
                    if ("search".equals(action)) {
                        searchKeyword = searchKeywords[random.nextInt(10)];
                    } else if ("click".equals(action)) {
                        if (clickCategoryId == 1) {
                            clickCategoryId = Long.valueOf(String.valueOf(random.nextInt(100))) + 1;
                        }
                        clickProductId = Long.valueOf(String.valueOf(random.nextInt(9))) + 1;

                    } else if ("order".equals(action)) {
                        orderCategoryIds = String.valueOf(random.nextInt(100));
                        orderProductIds = String.valueOf(random.nextInt(100));
                    } else if ("pay".equals(action)) {
                        payCategoryIds = String.valueOf(random.nextInt(100));
                        payProductIds = String.valueOf(random.nextInt(100));
                    }

                    int city_id = random.nextInt(31) + 1;
                    String wordLine = date + "," + userid + "," + sessionid + "," + pageid + ","
                            + actionTime + "," + searchKeyword + "," + clickCategoryId + ","
                            + clickProductId + "," + orderCategoryIds + "," + orderProductIds
                            + "," + payCategoryIds + "," + payProductIds + "," + city_id;

                    CreateData.upData(ACTIONPATH, wordLine);
                }
            }
        }


        /**
         * ========================用户==========================================
         */

        CreateData.createFile(USERPATH);
        String[] sexes = new String[]{"male", "female"};
        for (int i = 0; i < 100; i++) {
            long userid = i;
            String username = "user" + i;
            String name = "name" + i;
            int age = random.nextInt(60);
            String professional = "professional" + random.nextInt(100);
            String city = "city" + random.nextInt(100);
            String sex = sexes[random.nextInt(2)];
            String wordLine = userid + "," + username + "," + name + "," + age + "," + professional + "," + city + "," + sex;
            CreateData.upData(USERPATH, wordLine);

        }


        /**
         * ===================产品===============================================
         */

        CreateData.createFile(PRODUCTPATH);
        int[] productStatus = new int[]{0, 1};

        for (int i = 0; i < 100; i++) {
            long productId = i;
            String productName = "product" + i;
            String extendInfo = "" + productStatus[random.nextInt(2)];
            String worldLine = productId + "," + productName + "," + extendInfo;
            CreateData.upData(PRODUCTPATH, worldLine);
        }
    }


    public static void createFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void upData(String path, String data) {
        try {
            FileWriter fileWritter = new FileWriter(path, true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(data + "\r\n");
            bufferWritter.flush();
            bufferWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        mock();
    }
}

