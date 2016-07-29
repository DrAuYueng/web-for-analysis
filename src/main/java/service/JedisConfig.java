package service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("config.jedis")
public class JedisConfig {
    private String url;
    private int port;
    private int timeout;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "JedisConfig [url=" + url + ", port=" + port + ", timeout=" + timeout + "]";
    }

}
