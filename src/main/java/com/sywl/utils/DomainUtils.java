package com.sywl.utils;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Zhanglj on 2017/7/15.
 */
public class DomainUtils {

    /**
     * 将查询实体需要更新的属性更新到从数据库查询出来的对应属性中（只针对基本数据类型，包括String、int、short、double、boolean、Date,自定义类型属性不更新）
     *
     * @param requestDomain 请求实体
     * @param dbDomain  从数据库根据Id查询得到的实体
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static Object updateField(Object requestDomain, Object dbDomain) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        Field[] fields = requestDomain.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
        for(int j=0 ; j<fields.length ; j++){     //遍历所有属性
            String name = fields[j].getName();    //获取属性的名字

            System.out.println("attribute name:"+name);
            name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
            String type = fields[j].getGenericType().toString();    //获取属性的类型
            if(type.equals("class java.lang.String")){   //如果type是类类型，则前面包含"class "，后面跟类名
                Method m = requestDomain.getClass().getMethod("get"+name);
                String value = (String) m.invoke(requestDomain);    //调用getter方法获取属性值
                if(StringUtils.isNotEmpty(value)){
                    Field field = dbDomain.getClass().getDeclaredField(fields[j].getName());   //获取数据库查询实体对应属性Field
                    field.setAccessible(true);  //设置属性可以访问
                    field.set(dbDomain,value);  //为属性重新赋值
                    System.out.println("attribute value:"+value);
                }
            }
            if(type.equals("class java.lang.Integer")){
                Method m = requestDomain.getClass().getMethod("get"+name);
                Integer value = (Integer) m.invoke(requestDomain);
                if(value != null){
                    Field field = dbDomain.getClass().getDeclaredField(fields[j].getName());   //获取数据库查询实体对应属性Field
                    //field.setAccessible(true);  //设置属性可以访问
                    field.set(dbDomain,value);  //为属性重新赋值
                    System.out.println("attribute value:"+value);
                }
            }
            if(type.equals("class java.lang.Short")){
                Method m = requestDomain.getClass().getMethod("get"+name);
                Short value = (Short) m.invoke(requestDomain);
                if(value != null){
                    Field field = dbDomain.getClass().getDeclaredField(fields[j].getName());   //获取数据库查询实体对应属性Field
                    //field.setAccessible(true);  //设置属性可以访问
                    field.set(dbDomain,value);  //为属性重新赋值
                    System.out.println("attribute value:"+value);
                }
            }
            if(type.equals("class java.lang.Double")){
                Method m = requestDomain.getClass().getMethod("get"+name);
                Double value = (Double) m.invoke(requestDomain);
                if(value != null){
                    Field field = dbDomain.getClass().getDeclaredField(fields[j].getName());   //获取数据库查询实体对应属性Field
                    //field.setAccessible(true);  //设置属性可以访问
                    field.set(dbDomain,value);  //为属性重新赋值
                    System.out.println("attribute value:"+value);
                }
            }
            if(type.equals("class java.lang.Boolean")){
                Method m = requestDomain.getClass().getMethod("get"+name);
                Boolean value = (Boolean) m.invoke(requestDomain);
                if(value != null){
                    Field field = dbDomain.getClass().getDeclaredField(fields[j].getName());   //获取数据库查询实体对应属性Field
                    //field.setAccessible(true);  //设置属性可以访问
                    field.set(dbDomain,value);  //为属性重新赋值
                    System.out.println("attribute value:"+value);
                }
            }
            if(type.equals("class java.util.Date")){
                Method m = requestDomain.getClass().getMethod("get"+name);
                Date value = (Date) m.invoke(requestDomain);
                if(value != null){
                    Field field = dbDomain.getClass().getDeclaredField(fields[j].getName());   //获取数据库查询实体对应属性Field
                    //field.setAccessible(true);  //设置属性可以访问
                    field.set(dbDomain,value);  //为属性重新赋值
                    System.out.println("attribute value:"+value.toLocaleString());
                }
            }
        }
        return dbDomain;
    }
}
