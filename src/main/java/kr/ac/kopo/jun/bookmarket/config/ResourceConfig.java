package kr.ac.kopo.jun.bookmarket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Value("${file.uploadDir}")
    String fileDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(fileDir);
        registry.addResourceHandler("/BookMarket/images/**")
                .addResourceLocations("file:///" + fileDir)
                .setCachePeriod(60 * 60 * 24 * 365); //접근 파일 캐싱 시간
    }
}