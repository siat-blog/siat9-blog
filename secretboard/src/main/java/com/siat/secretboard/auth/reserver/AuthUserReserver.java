package com.siat.secretboard.auth.reserver;

import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.siat.secretboard.auth.annotation.SiatAuthMember;
import com.siat.secretboard.auth.dto.MemberInfo;

public class AuthUserReserver extends HandlerMethodArgumentResolverComposite{
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
       return parameter.hasParameterAnnotation(SiatAuthMember.class)&&
       parameter.getParameterType().equals(MemberInfo.class);
    }
    @Override
    @Nullable
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
        Object member=webRequest.getAttribute("memberInfo", RequestAttributes.SCOPE_REQUEST);
        if(member.getClass()!=MemberInfo.class){
            return null;
        }
        return (MemberInfo)member;
    }
}
