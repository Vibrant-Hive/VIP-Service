package com.vip.coders.util;

import java.lang.reflect.Field;

public class GetterSetter {

    public static void callSetter(Object object, String fieldName, String fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, fieldValue);
    }

    public static Object callGetter(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
}
