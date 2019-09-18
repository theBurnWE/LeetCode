package com.shcepp.shdippsvr.sys.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * elasticsearch server 配置
 * @author kzhou
 * @date2018/10/23
 */
@Configuration
@ConfigurationProperties("nodes")
public class ElasticsearchConfig {
    private List<ElasticsearchNode> nodes;

    public List<ElasticsearchNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<ElasticsearchNode> nodes) {
        this.nodes = nodes;
    }



    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("");
        nodes.stream().forEach(node -> {sb.append(node.toString()).append(',');});
        return sb.substring(0,sb.length()-1);
    }
}
