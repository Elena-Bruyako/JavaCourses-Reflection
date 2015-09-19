package com.bruyako.services;

import com.bruyako.annotations.CustomDateFormat;
import com.bruyako.annotations.JsonValue;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by brunyatko on 14.09.15.
 */
public class JsonDecoderToJson {

    public static String toJson(Object object) throws IllegalAccessException {

        StringBuilder json = new StringBuilder("{");

        Class clazz = object.getClass();
        Field[] arrayFields = clazz.getDeclaredFields();

        for (int i = 0; i < arrayFields.length; i++) {

            JsonValue annotation = arrayFields[i].getAnnotation(JsonValue.class);
            CustomDateFormat annotation1 = arrayFields[i].getAnnotation(CustomDateFormat.class);

            if (annotation != null) {
                arrayFields[i].setAccessible(true);
                String newName = annotation.name();
                String value = arrayFields[i].get(object).toString();

                json.append(formatDisplay(newName, value));

            } else if (annotation1 != null) {
                arrayFields[i].setAccessible(true);
                String newName = annotation1.format();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(newName);
                String strTime = simpleDateFormat.format(new Date());
                String valueName = arrayFields[i].getName();

                json.append(formatDisplay(valueName, strTime));

            } else {
                arrayFields[i].setAccessible(true);
                String valueName = arrayFields[i].getName();
                String value = arrayFields[i].get(object).toString();

                json.append(formatDisplay(valueName, value));
            }

            if (i != arrayFields.length - 1) {
                json.append(",");
            }
        }
        return json.append("}").toString();
    }

    private static String formatDisplay(String valueName, String value) {

        StringBuilder str = new StringBuilder();
        str.append("\"");
        str.append(valueName);
        str.append("\":\"");
        str.append(value);
        str.append("\"");

        return str.toString();
    }
}
