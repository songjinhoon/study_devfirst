package com.devfirst.admin.epic.config.auth;

import com.devfirst.admin.epic.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override // @LoginUser를 파라미터에 지원할 수 있도록 정의
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass; // @LoginUser가 파라미터에 있어야하고, 클래스 타입이 SessionUser야 true
    }

    @Override // @LoginUser가 정의되어있는 파라미터에 값을 뭐로 넣을지 정의
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 파라미터에 전달할 객체를 session에서 가져온다. 
        // 단, 기본적으로 구글에서 로그인 성공 시 저장되는 이름은 'user'이므로, 다른 session 이름을 적어주면 실제 @LoginUser가 정의되어있는 파라미터에 제대로 값이 안들어감
        return httpSession.getAttribute("user");
    }
}
