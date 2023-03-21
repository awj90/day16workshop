package sg.edu.nus.iss.day16workshop.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Pieces implements Serializable {
    
    private DecodingBoard decoding_board;
    private Pegs pegs;
    private Rulebook rulebook;

    public DecodingBoard getDecoding_board() {
        return decoding_board;
    }
    public void setDecoding_board(DecodingBoard decoding_board) {
        this.decoding_board = decoding_board;
    }
    public Pegs getPegs() {
        return pegs;
    }
    public void setPegs(Pegs pegs) {
        this.pegs = pegs;
    }
    public Rulebook getRulebook() {
        return rulebook;
    }
    public void setRulebook(Rulebook rulebook) {
        this.rulebook = rulebook;
    }

    public static Pieces JsonObjectToJavaObject(JsonObject o) {
        Pieces pieces = new Pieces();
        JsonObject db = o.getJsonObject("decoding_board");
        JsonObject p = o.getJsonObject("pegs");
        JsonObject r = o.getJsonObject("rulebook");
        pieces.setDecoding_board(DecodingBoard.JsonObjectToJavaObject(db));
        pieces.setPegs(Pegs.JsonObjectToJavaObject(p));
        pieces.setRulebook(Rulebook.JsonObjectToJavaObject(r));
        return pieces;
    }

    public JsonObjectBuilder toJsonObjectBuilder() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder()
                                                    .add("decoding_board",this.getDecoding_board().toJsonObjectBuilder())
                                                    .add("pegs",this.getPegs().toJsonObjectBuilder())
                                                    .add("rulebook", this.getRulebook().toJsonObjectBuilder());
        return jsonObjectBuilder;
    }
}
