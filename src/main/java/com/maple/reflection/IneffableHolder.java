package com.maple.reflection;

import java.lang.reflect.Method;

public class IneffableHolder {

    public static String getMyName() {
        return "Maple Ray";
    }

    public static void main(String[] args) throws Exception {
        Method getMyName = IneffableHolder.class.getMethod("getMyName");

        Object invoke = getMyName.invoke(new IneffableHolder());

        System.out.println(invoke);
    }
}
