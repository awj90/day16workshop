package sg.edu.nus.iss.day16workshop.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.UUID;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Mastermind implements Serializable {
    
    private String name;
    private Pieces pieces;
    private Rulebook rulebook;
    private String id;

    public Mastermind() {
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Pieces getPieces() {
        return pieces;
    }
    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }
    public Rulebook getRulebook() {
        return rulebook;
    }
    public void setRulebook(Rulebook rulebook) {
        this.rulebook = rulebook;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                    .add("name", this.getName())
                    .add("pieces", this.getPieces().toJsonObjectBuilder())
                    .add("id", this.getId())
                    .build();
    }

    public static Mastermind JsonStringToJavaObject(String json) throws IOException {
        Mastermind m = new Mastermind();
        
        if (json!=null) {
            InputStream is = new ByteArrayInputStream(json.getBytes()); 
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            
            m.setName(jsonObject.getString("name"));
            m.setPieces(Pieces.JsonObjectToJavaObject(jsonObject.getJsonObject("pieces")));
            m.setId(jsonObject.getString("id"));
        }

        return m;
    }

}
