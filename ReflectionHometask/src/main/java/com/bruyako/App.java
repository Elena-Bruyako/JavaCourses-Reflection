package com.bruyako;

import com.bruyako.model.Human;
import com.bruyako.services.JsonDecoderFromJson;
import com.bruyako.services.JsonDecoderToJson;

import java.time.LocalDate;

/**
 * Created by brunyatko on 14.09.15.
 */
public class App {

    public static void main(String[] args) throws IllegalAccessException {

        Human human = new Human("Elena", "Bruyako", "Photo", LocalDate.now());
        JsonDecoderToJson js = new JsonDecoderToJson();
        System.out.println(js.toJson(human));

        JsonDecoderFromJson jsFromJson = new JsonDecoderFromJson();
        String json = js.toJson(human);
        jsFromJson.fromJson(json, );
    }
}
