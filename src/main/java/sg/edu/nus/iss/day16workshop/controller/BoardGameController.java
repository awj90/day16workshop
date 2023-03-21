package sg.edu.nus.iss.day16workshop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import sg.edu.nus.iss.day16workshop.model.Mastermind;
import sg.edu.nus.iss.day16workshop.service.BoardGameService;

@RestController
@RequestMapping(path="/api/boardgame", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
public class BoardGameController {
    
    @Autowired
    BoardGameService boardGameService;

    // Task 1
    @PostMapping
    public ResponseEntity<String> insertBoardGame(@RequestBody Mastermind mastermind) {
        int insertCount = boardGameService.saveGame(mastermind);
        
        if (insertCount == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Json.createObjectBuilder()
                                            .add("insert_count", insertCount)
                                            .add("error message", "Server Error")
                                            .build()
                                            .toString());
        } else {
            return ResponseEntity.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Json.createObjectBuilder()
                                            .add("insert_count", insertCount)
                                            .add("id", mastermind.getId())
                                            .build()
                                            .toString());
        }
    }

    // Task 2
    @GetMapping(path="/{id}")
    public ResponseEntity<String> findBoardGameById(@PathVariable String id) throws IOException {
        Mastermind mastermind = boardGameService.findById(id);

        // Seems like if Redis cannot find an Id, it returns a non null mastermind object but with null fields
        if (mastermind.getName() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Json.createObjectBuilder()
                                            .add("error message", "Not Found")
                                            .build()
                                            .toString());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(mastermind.toJsonObject()
                                            .toString());
        }
    }

    // Task 3
    @PutMapping(path="/{id}")
    public ResponseEntity<String> updateBoardGame(@RequestBody Mastermind mastermind, @PathVariable String id, @RequestParam(defaultValue="false") boolean upsert) throws IOException {
        mastermind.setId(id); // this is needed because the incoming payload body does not have the id
        int result = boardGameService.upsertGame(mastermind, upsert);

        if (result==0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Json.createObjectBuilder()
                                            .add("error message", "Upsert false and id not found")
                                            .build()
                                            .toString());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Json.createObjectBuilder()
                                            .add("update_count", result)
                                            .add("id", mastermind.getId())
                                            .build()
                                            .toString());
        }
    }
}
