package service;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ConfigTest {
    private final static String[] configFilesChannelAdapterDemo = { "/spring-config.xml" };

    public static void main(String[] args) throws InterruptedException {

        System.out.println("    Loading spring Demo...");
        final GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext(configFilesChannelAdapterDemo);
        Thread.sleep(50000000l);
    }
}
