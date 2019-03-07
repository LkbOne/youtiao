package com.example.phoebe.youtiao.commmon.util;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.example.phoebe.youtiao.controller.arg.ResetOpenHistoryArg;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;
import java.util.Map;

/**
 * 基于BeanCopier的实现，未使用 自定义转换器
 * Created by yuanj on 2017/8/15.
 */
@Slf4j
public class BeanUtil {

    private static final Map<String, BeanCopier> BEAN_COPIER_CACHE = Maps.newConcurrentMap();

    private static final Map<String, ConstructorAccess> CONSTRUCTOR_ACCESS_CACHE = Maps.newConcurrentMap();

    /**
     * @description 类对象之间转换, 注：useConverter= false
     * @param source 源对象
     * @param target 目标对象类
     * @return
     * @return T
     */
    public static <T> T copy(Object source, Class<T> target) {
        T result = null;
        if (source != null) {
            ConstructorAccess<T> constructorAccess = getConstructorAccess(target);
            result = constructorAccess.newInstance();

            BeanCopier beanCopier = getBeanCopier(source.getClass(), target);
            beanCopier.copy(source, result, null);
        }
        return result;
    }

    /**
     * @description List对象之间转换, 注：useConverter= false
     * @param <T>
     * @param sourceList 源对象
     * @param target     目标对象类
     * @return
     */
    public static <T> List<T> copy(List<?> sourceList, Class<T> target) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Lists.newArrayList();
        }

        List<T> result = Lists.newArrayListWithCapacity(sourceList.size());
        for (Object object : sourceList) {
            T clone = copy(object, target);

            result.add(clone);
        }

        return result;
    }

    /**
     * @description 获取构造器
     * @param targetClass
     * @param <T>
     * @return
     */
    private static <T> ConstructorAccess<T> getConstructorAccess(Class<T> targetClass) {
        ConstructorAccess<T> constructorAccess = CONSTRUCTOR_ACCESS_CACHE.get(targetClass.toString());
        if (constructorAccess != null) {
            return constructorAccess;
        }

        try {
            constructorAccess = ConstructorAccess.get(targetClass);
            CONSTRUCTOR_ACCESS_CACHE.putIfAbsent(targetClass.toString(), constructorAccess);
        } catch (Exception e) {
            log.error("Create new instance of {} failed: ", targetClass, e);
            throw new RuntimeException(e);
        }

        return constructorAccess;
    }

    /**
     * @description 获取BeanCopier
     * @param source
     * @param target
     * @return
     * @return BeanCopier
     */
    private static BeanCopier getBeanCopier(Class<?> source, Class<?> target) {
        String beanCopierKey = generateBeanKey(source, target);
        if (BEAN_COPIER_CACHE.containsKey(beanCopierKey)) {
            return BEAN_COPIER_CACHE.get(beanCopierKey);
        } else {
            BeanCopier beanCopier = BeanCopier.create(source, target, false);
            BEAN_COPIER_CACHE.putIfAbsent(beanCopierKey, beanCopier);
        }
        return BEAN_COPIER_CACHE.get(beanCopierKey);
    }

    /**
     * @description 生成两个类的key
     * @param source
     * @param target
     * @return
     * @return String
     */
    private static String generateBeanKey(Class<?> source, Class<?> target) {
        return source.getName() + "#" + target.getName();
    }

}
