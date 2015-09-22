package com.bruyako.services;


import com.bruyako.annotations.CustomDateFormat;
import com.bruyako.annotations.JsonValue;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by brunyatko on 18.09.15.
 */
public class JsonDecoderFromJson<T> {

    public void fromJson(String json, T clazz) throws IllegalAccessException {

        String[] jsonValues = json.replaceAll("\\{", "").replaceAll("\\}", "").split(",");

        Field[] arrFields = clazz.getClass().getDeclaredFields();

        for (String jsonValue : jsonValues) {

            String jsonValueKey = getKeyName(jsonValue);
            String jsonValueValue = getKeyValue(jsonValue);

            for (Field field : arrFields) {
                field.setAccessible(true);

                JsonValue annotationValue = field.getAnnotation(JsonValue.class);
                CustomDateFormat annotationDate = field.getAnnotation(CustomDateFormat.class);

                if (annotationValue != null) {
                    String annotationName = annotationValue.name();
                    if (annotationName.equals(jsonValueKey)) {
                        field.set(clazz, jsonValueValue);
                    }
                } else if (annotationDate != null) {

                    String dateFormat = annotationDate.format();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

                    if (field.getName().equals(jsonValueKey)) {
                        LocalDate date = LocalDate.parse(jsonValueValue, formatter);
                        field.set(clazz, date);
                    }

                } else if (field.getName().equals(jsonValueKey)) {
                    field.set(clazz, jsonValueValue);
                }
            }
        }
    }

    private String getKeyName(String jsonValue) {
        String[] result = jsonValue.replaceAll("\"","").split(":");

        return result[0].replaceAll("\\s+","");
    }

    private String getKeyValue(String jsonValue) {
        String[] result = jsonValue.replaceAll("\"","").split(":");
        return result[1].replaceAll("\\s+","");
    }
}
