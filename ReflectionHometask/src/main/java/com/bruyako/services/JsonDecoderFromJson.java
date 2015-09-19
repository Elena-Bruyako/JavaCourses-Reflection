package com.bruyako.services;

/**
 * Created by brunyatko on 18.09.15.
 */
public class JsonDecoderFromJson {

    public static void fromJson(String json, Class<T> clazz) {

        String result = json.replaceAll("\\{"," ");
        String resultOne = result.replaceAll(":"," ");
        String resultTwo = resultOne.replaceAll(","," ");
        String resultThree = resultTwo.replaceAll("\""," ");
        String res = resultThree.replaceAll("\\}"," ");

        char[] array = res.toCharArray();
        for (int i = 0; i < array.length; i++) {


        }
    }
}
