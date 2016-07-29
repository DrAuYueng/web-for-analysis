package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JedisConfigService {

    @Autowired
    private JedisConfig config;
    @Value("${redis.host}")
    private String ip;
    @Value("${redis.port}")
    private Integer port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void printConfig() {
        System.out.println(config);
    }

}
