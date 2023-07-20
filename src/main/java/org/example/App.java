package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;


/**
 * Hello world!
 *
 */
public class App {
    private static final JsonMapper jsonMapper = new JsonMapper();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static void readJsonFileUsingJsonSimple(String filePath) {
        JSONObject jsonObject = null;
        try {
            FileReader fileReader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(fileReader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (jsonObject != null) {
            printValuesOfJsonObject(jsonObject);
        }
    }

    public static void readJsonFileUsingURL(String path) {
        JsonNode jsonNode = null;
        try {
            jsonNode = jsonMapper.readTree(new File(path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (jsonNode!= null) {
            printValuesOfJsonNodeObjects(jsonNode);
        }

    }

    public static void printValuesOfJsonNodeObjects(JsonNode jsonNode) {
        System.out.println("JsonNodeObject : " + jsonNode);
        String firstName = String.valueOf(jsonNode.get("fName"));
        String lastName = jsonNode.get("lName").toString();
        System.out.println("FirstName : " + firstName + ", LastName : " + lastName);
        JsonNode address = jsonNode.get("address");
        if (address != null && address.isArray()) {
            address.forEach(e -> {
                System.out.println("City : "+ e.get("city") +
                        ", Locality : " + e.get("locality") +
                        ", PinCode : " + e.get("pinCode") +
                        ", IsRequired : " + e.get("isRequired"));
            });
        }

    }
    public static void printValuesOfJsonObject(JSONObject jsonObject) {
        System.out.println("JSONObject : "+jsonObject);
        String firstName = jsonObject.get("fName").toString();
        String lastName = jsonObject.get("lName").toString();
        System.out.println("FirstName : " + firstName + ", LastName : " + lastName);
        JSONArray jsonArray = (JSONArray) jsonObject.get("address");
        jsonArray.forEach(e -> {
            JSONObject jsonObj = (JSONObject) e;
            System.out.println("City : "+ jsonObj.get("city") +
                    ", Locality : " + jsonObj.get("locality") +
                    ", PinCode : " + jsonObj.get("pinCode") +
                    ", IsRequired : " + jsonObj.get("isRequired"));
        });
    }
    public static void main( String[] args ) {
        String filePath = "./json/sample.json";
//        readJsonFileUsingJsonSimple(filePath);
        readJsonFileUsingURL(filePath);
    }
}
