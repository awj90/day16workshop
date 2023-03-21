package sg.edu.nus.iss.day16workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Rulebook implements Serializable {
    
    private int total_count;
    private String file;

    public int getTotal_count() {
        return total_count;
    }
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }

    public static Rulebook JsonObjectToJavaObject(JsonObject o) {
        Rulebook rulebook = new Rulebook();
        JsonNumber jsonNumber = o.getJsonNumber("total_count");
        String s = o.getString("file");
        rulebook.setTotal_count(jsonNumber.intValue());
        rulebook.setFile(s);
        return rulebook;
    }

    public JsonObjectBuilder toJsonObjectBuilder() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder().add("total_count", this.getTotal_count()).add("file", this.getFile());
        return jsonObjectBuilder;
    }
}
