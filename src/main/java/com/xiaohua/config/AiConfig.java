package com.xiaohua.config;

import com.zhipu.oapi.ClientV4;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 好好学Java
 * @author: XiaoHua
 **/
@Configuration
//加载配置文件注解
@ConfigurationProperties(prefix = "ai")
@Data
public class AiConfig {
//    apiKey需要从平台获取
//    https://open.bigmodel.cn/dev/api/devguide/sdk-install

    private String apiKey;

    @Bean
    public ClientV4 getClientV4(){
        return new ClientV4.Builder(apiKey).build();

    }

}
