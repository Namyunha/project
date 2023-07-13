package com.icia.project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration // 해당 클래스에 정의한 설정정보를 스프링 컨테이너에 등록
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/upload/**"; // html 에서 접근할 경로
    private String savePath = "file:///D:/Springboot_project_img/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath).addResourceLocations(savePath);
    }

    @RequiredArgsConstructor
    @Configuration
    @EnableWebSocket
    public class WebSocketConfig implements WebSocketConfigurer {
        private final WebSocketHandler webSocketHandler;
        @Override
        public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
        }
    }



//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginCheckInterceptor()) // 인터셉터로 등록할 클래스
//                .order(1) // 해당 인터셉터의 우선순위
//                .addPathPatterns("/**") // 인터셉터로 체크할 주소(모든주소)
//                .excludePathPatterns("/", "/member/save", "/member/login", "/member/login/axios", "/member/duCheck", "/member/mypage",
//                        "/studygroup/list", "/studygroup/detail", "/js/**", "/css/**", "/images/**", "/upload/**", "/groupUser/save",
//                        "/studygroup/**", "/member/groupList/**", "/member/**", "/oauth/kakao/**",
//                        "/*.ico", "/favicon/**"); // 인터셉터 검증을 하지 않을 주소
//    }


}

