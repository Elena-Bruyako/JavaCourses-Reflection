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
        JsonDecoderToJson toJsonDecoder = new JsonDecoderToJson();
        System.out.println(toJsonDecoder.toJson(human));

        Human person = new Human();
        String personJson = "{\"firstName\": \"Inna\"," +
                              "\"lastName\": \"Bruyako\"," +
                                   "\"fun\": \"Dances\"," +
                             "\"birthDate\": \"23-02-1987\"}";

        JsonDecoderFromJson<Human> fromJsonDecoder = new JsonDecoderFromJson<Human>();
        fromJsonDecoder.fromJson(personJson, person);
        printHuman(person);
    }

    private static void printHuman(Human person) {

        System.out.println("FirstName = " + person.getFirstName());
        System.out.println("LastName = " + person.getLastName());
        System.out.println("Hobby = " + person.getHobby());
        System.out.println("BirthDate = " + person.getBirthDate().toString());
    }
}
