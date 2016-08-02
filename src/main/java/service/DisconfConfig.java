package service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("config.disconf")
public class DisconfConfig {
    private String[] scanPackageList;
    private String[] locations;

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    public String[] getScanPackageList() {
        return scanPackageList;
    }

    public void setScanPackageList(String[] scanPackageList) {
        this.scanPackageList = scanPackageList;
    }

}
