package com.siat.secretboard.auth.reserver;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.siat.secretboard.auth.annotation.SiatAuthMember;
import com.siat.secretboard.auth.dto.MemberInfo;

@Component
public class AuthUserReserver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
       return parameter.hasParameterAnnotation(SiatAuthMember.class)&&
       parameter.getParameterType().equals(MemberInfo.class);
    }
    @Override
    @Nullable
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
        MemberInfo member=(MemberInfo)webRequest.getAttribute("memberInfo",RequestAttributes.SCOPE_REQUEST);
        System.out.println("mebewbaiejeigjwegjpwegjopawegjopawegjopawegjawemember"+ member);
        if(member==null||member.getClass()!=MemberInfo.class){
            return null;
        }
        return (MemberInfo)member;
    }
}
