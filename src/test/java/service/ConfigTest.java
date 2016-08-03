package service;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ConfigTest {
    private final static String[] configFilesChannelAdapterDemo = { "/spring-config.xml" };

    public static void main(String[] args) throws InterruptedException {

        System.out.println("    Loading spring Demo...");
        final GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext(configFilesChannelAdapterDemo);
        DisconfConfig config = applicationContext.getBean(DisconfConfig.class);
        System.out.println(config);
        System.out.println(applicationContext.getEnvironment());

        // MessageQueueConfigService messageConfigService =
        // applicationContext.getBean(MessageQueueConfigService.class);
        // System.out.println(messageConfigService);
        // applicationContext.setParent(new
        // GenericXmlApplicationContext("/beanRefContext.xml"));
        Thread.sleep(50000000l);
    }
}
