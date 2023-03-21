package sg.edu.nus.iss.day16workshop.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Pegs implements Serializable {
    
    private int total_count;
    private List<Type> types;

    public int getTotal_count() {
        return total_count;
    }
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
    public List<Type> getTypes() {
        return types;
    }
    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public static Pegs JsonObjectToJavaObject(JsonObject o) {
        Pegs pegs = new Pegs();
        
        JsonNumber jsonNumber = o.getJsonNumber("total_count");
        pegs.setTotal_count(jsonNumber.intValue());

        List<Type> listOfTypes = new LinkedList<>();
        JsonArray jsonArray = o.getJsonArray("types");
        
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.getJsonObject(i);
            listOfTypes.add(Type.JsonObjectToJavaObject(jsonObject));
        }
        pegs.setTypes(listOfTypes);

        return pegs;
    }

    public JsonObjectBuilder toJsonObjectBuilder() {
        
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Type t: this.getTypes()) {
            jsonArrayBuilder.add(t.toJsonObjectBuilder());
        }

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder().add("total_count", this.getTotal_count()).add("types", jsonArrayBuilder);

        return jsonObjectBuilder;
    }
}
