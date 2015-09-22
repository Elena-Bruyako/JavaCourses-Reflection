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

        StringBuilder jsonStringBuilder = new StringBuilder("{");

        Class clazz = object.getClass();
        Field[] arrayFields = clazz.getDeclaredFields();

        for (int i = 0; i < arrayFields.length; i++) {
            arrayFields[i].setAccessible(true);

            JsonValue annotationValue = arrayFields[i].getAnnotation(JsonValue.class);
            CustomDateFormat annotationDate = arrayFields[i].getAnnotation(CustomDateFormat.class);

            if (annotationValue != null) {
                String annotationName = annotationValue.name();
                String value = arrayFields[i].get(object).toString();
                jsonStringBuilder.append(buildJsonValue(annotationName, value));

            } else if (annotationDate != null) {
                String dateFormat = annotationDate.format();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
                String strTime = simpleDateFormat.format(new Date());
                String fieldName = arrayFields[i].getName();
                jsonStringBuilder.append(buildJsonValue(fieldName, strTime));

            } else {
                String valueName = arrayFields[i].getName();
                String value = arrayFields[i].get(object).toString();
                jsonStringBuilder.append(buildJsonValue(valueName, value));
            }
            if (i != arrayFields.length - 1) {
                jsonStringBuilder.append(",");
            }
        }
        return jsonStringBuilder.append("}").toString();
    }

    private static String buildJsonValue(String jsonKey, String jsonValue) {

        StringBuilder str = new StringBuilder();
        str.append("\"");
        str.append(jsonKey);
        str.append("\":\"");
        str.append(jsonValue);
        str.append("\"");

        return str.toString();
    }
}
