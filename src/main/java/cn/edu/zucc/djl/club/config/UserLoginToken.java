package cn.edu.zucc.djl.club.config;

import cn.edu.zucc.djl.club.entity.AdminEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    String type();
    boolean required() default true;
}
