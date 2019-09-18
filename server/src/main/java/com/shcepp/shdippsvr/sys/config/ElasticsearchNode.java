package com.shcepp.shdippsvr.sys.config;

/**
 * @author kzhou
 * @date2018/10/23
 */
public class ElasticsearchNode {
    private String hostName;
    private int port;
    private String scheme;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("{").append("schema=")
                .append(scheme).append(",host=").append(hostName).append(",port=").append(port).append("}");
        return stringBuilder.toString();
    }
}
