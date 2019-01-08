package com.acm.sample;

import com.alibaba.edas.acm.ConfigService;
import com.alibaba.edas.acm.exception.ConfigException;
import com.alibaba.edas.acm.listener.ConfigChangeListener;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class App {

    private static Properties appCfg = new Properties();

    public static void initAndWatchConfig() {

        final String dataId = "com.acm.myapp.app.cfg";
        final String group = "myapp";
        final long timeoutInMills = 3000;

//生产环境的获取方式 begin
        // 从控制台命名空间管理中拷贝对应值
//        Properties properties = new Properties();
//        properties.put("endpoint", "$endpoint");
//        properties.put("namespace", "$namespace");
        // 通过 ECS 实例 RAM 角色访问 ACM
        // properties.put("ramRoleName", "$ramRoleName");
//        properties.put("accessKey", "$accessKey");
//        properties.put("secretKey", "$secretKey");


        // 如果是加密配置，则添加下面两行进行自动解密
        // properties.put("openKMSFilter", true);
        // properties.put("regionId", "$regionId");

//        ConfigService.init(properties);
//生产环境的获取方式 end

//开发测试的做法 begin
		ConfigService.init("acm.aliyun.com", "", "", "");
//开发测试的做法 end

        // 直接获取配置内容
        try {
            String configInfo = ConfigService.getConfig(dataId, group, timeoutInMills);
			System.out.println(configInfo);
            appCfg.load(new StringReader(configInfo));
        } catch (ConfigException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 监听配置变化，获取最新推送值
        ConfigService.addListener(dataId, group, new ConfigChangeListener() {
            public void receiveConfigInfo(String configInfo) {
                try {
                    appCfg.load(new StringReader(configInfo));
                } catch (Exception e) {
                    // process exception
                }

                refreshApp();
            }
        });
    }

    public static void refreshApp() {
        System.out.println("current thread pool size: " + appCfg.getProperty("threadPoolSize"));
        System.out.println("current log level: " + appCfg.getProperty("logLevel"));
        System.out.println("");
    }

    public static void main(String[] args) {

        initAndWatchConfig();

        // 测试让主线程不退出，因为订阅配置是守护线程，主线程退出守护线程就会退出。 正式代码中无需下面代码
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

    }

}