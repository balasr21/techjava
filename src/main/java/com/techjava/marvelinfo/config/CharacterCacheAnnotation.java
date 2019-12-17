package com.techjava.marvelinfo.config;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Custom Annotation for Caching Character Information
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Cacheable(cacheNames = "characterdetails",sync=true)
public @interface CharacterCacheAnnotation {

}
