package sg.edu.nus.iss.day16workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Type implements Serializable {

    private String type;
    private int count;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public static Type JsonObjectToJavaObject(JsonObject o) {
        Type t = new Type();
        String s = o.getString("type");
        JsonNumber jsonNumber = o.getJsonNumber("count");
        t.setType(s);
        t.setCount(jsonNumber.intValue());
        return t;
    }

    public JsonObjectBuilder toJsonObjectBuilder() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder().add("type", this.getType()).add("count", this.getCount());
        return jsonObjectBuilder;
    }
    
}
