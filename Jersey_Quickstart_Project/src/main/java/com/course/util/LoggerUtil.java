package com.course.util;
import java.lang.reflect.Field;

public class LoggerUtil {
    public static void log(Object message) {
        if (Config.getProperty("ENVIRONMENT").equals("DEV")) {
        	System.out.println(message);
        }
    }
    
    public static void log() {
        if (Config.getProperty("ENVIRONMENT").equals("DEV")) {
        	System.out.println();
        }
    }
    
    public static void log(String message, Exception e) {
    	System.out.println(message);
        e.printStackTrace(); // Always print stack trace in both DEV and PROD
    }

    public static void logException(String message, Exception e) {
    	System.out.println(message);
        e.printStackTrace(); // Always print stack trace in both DEV and PROD
    }
    
    public static void printNonNullFields(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        System.out.print(clazz.getSimpleName() + " [");
        boolean first = true;

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(obj);
                if (value != null) {
                    if (!first) {
                        System.out.print(", ");
                    }
                    System.out.print(field.getName() + "=" + value);
                    first = false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        LoggerUtil.log("]");
    }
}