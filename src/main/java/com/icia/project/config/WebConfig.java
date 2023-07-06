package com.icia.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 해당 클래스에 정의한 설정정보를 스프링 컨테이너에 등록
public class WebConfig implements WebMvcConfigurer {

    private String resourcePath = "/upload/**"; // html 에서 접근할 경로
    private String savePath = "file:///C:/Springboot_project_img/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath).addResourceLocations(savePath);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()) // 인터셉터로 등록할 클래스
                .order(1) // 해당 인터셉터의 우선순위
                .addPathPatterns("/**") // 인터셉터로 체크할 주소(모든주소)
                .excludePathPatterns("/", "/member/save", "/member/login", "/member/login/axios", "/member/duCheck", "/member/mypage",
                        "/studygroup/list", "/studygroup/detail", "/js/**", "/css/**", "/images/**", "/upload/**",
                        "/studygroup/**", "/member/groupList/**", "/member/applyHistory/**",
                        "/*.ico", "/favicon/**"); // 인터셉터 검증을 하지 않을 주소
    }


}

