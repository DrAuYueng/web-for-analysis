package service;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ConfigTest {
    private final static String[] configFilesChannelAdapterDemo = { "/spring-config.xml" };

    public static void main(String[] args) throws InterruptedException {

        System.out.println("    Loading spring Demo...");
        final GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext(configFilesChannelAdapterDemo);
        JedisConfigService service = applicationContext.getBean(JedisConfigService.class);
        System.out.println("ip:" + service.getIp() + ",port:" + service.getPort());

        MessageQueueConfigService messageConfigService = applicationContext.getBean(MessageQueueConfigService.class);
        System.out.println(messageConfigService);
        // applicationContext.setParent(new
        // GenericXmlApplicationContext("/beanRefContext.xml"));
        Thread.sleep(50000000l);
    }
}
