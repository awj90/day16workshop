package sg.edu.nus.iss.day16workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class DecodingBoard implements Serializable {
    
    private int total_count;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public static DecodingBoard JsonObjectToJavaObject(JsonObject o) {
        DecodingBoard decodingBoard = new DecodingBoard();
        JsonNumber jsonNumber = o.getJsonNumber("total_count");
        decodingBoard.setTotal_count(jsonNumber.intValue());
        return decodingBoard;
    }

    public JsonObjectBuilder toJsonObjectBuilder() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder().add("total_count", this.getTotal_count());
        return jsonObjectBuilder;
    }
}
