package com.cloth_management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory("productImages", registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../")) {
            dirName.replace("../", "");
        }
        if(System.getProperty("os.name").startsWith("Windows"))
        {
            uploadPath = "/"+uploadPath;
        }
       // System.out.println("\n\n"+uploadPath+"\n\n");
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file://" + uploadPath+"/");
    }
}
