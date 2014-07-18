package com.suning.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectUtils {
    final static Logger log = LoggerFactory.getLogger(ObjectUtils.class);

   /**
    * 
    * 功能描述：将list转换成数组map，list中的元素对象的属性及属性值存储在一个map中(静态属性不获取)
    * @param list 需要转换成 Map[]的list集合 
    * @param clz  转换的集合类型为clz类型，根据clz获取需要的属性以及属性值
    * @return map数组
    */
    public static <T> Map<String, Object>[] List2MapArrays(List<T> list, Class<?> clz) {
        @SuppressWarnings("unchecked")
        Map<String, Object> mapArray[] = new HashMap[list.size()];
        Field[] fields = clz.getDeclaredFields();
        for (int i = 0, len = list.size(); i < len; i++) {
            T t = list.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            mapArray[i] = map;
            for (Field field : fields) {
                try {
                    if (!Modifier.isStatic(field.getModifiers())) {
                        Method methodGetX = new PropertyDescriptor( field.getName(),clz).getReadMethod();
                        Object value = methodGetX.invoke(t);
                        if (value != null) {
                            map.put(field.getName(), value);
                        }
                    }
                } catch (InvocationTargetException e) {
                    log.info("反射出现异常", e);
                } catch (IllegalAccessException e) {
                    log.info("参数不合法不正确 ", e);
                } catch (IntrospectionException e) {
                    log.info("参数不合法不正确 ", e);
                }

            }
        }

        return mapArray;
    }
   
    /**
     * 
     * 功能描述： bean的非静态属性(包括父类非静态属性)转换成map 
     * @param object 需要转换的bean
     * @return bean 的属性map ,key是属性名称，value是属性的值
     */
    public static Map<String, Object> object2Map(Object object) {
        Class<?> clzz = object.getClass();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(clzz);
        } catch (IntrospectionException e) {
            throw new RuntimeException("获取bean的属性出现异常", e);
        }
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Object value = null;
                try {
                    value = descriptor.getReadMethod().invoke(object);
                } catch (InvocationTargetException e) {
                    log.info("反射出现异常", e);
                } catch (IllegalAccessException e) {
                    log.info("参数不合法不正确 ", e);
                }
                if(value!=null){
                    returnMap.put(propertyName, value);
                }

            }
        }
        return returnMap;
    }
//    public static void main(String[] args) {
//        QueryWinBeanParam params=new QueryWinBeanParam();
//        params.setCustNum("1");
//        params.setMaxCount(10);
//        params.setStartIndex(0);
//        Map<String,Object> map=ObjectUtils.object2Map(params);
//       System.out.println( map.get("maxCount").equals("10"));
//    }

}
