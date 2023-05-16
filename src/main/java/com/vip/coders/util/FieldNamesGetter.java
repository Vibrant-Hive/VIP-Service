package com.vip.coders.util;

import java.lang.reflect.Field;

public class FieldNamesGetter {

    public static String[] getFieldNames(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        String[] fieldNames = new String[fields.length];

        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }

        return fieldNames;
    }
}
