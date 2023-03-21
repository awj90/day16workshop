package sg.edu.nus.iss.day16workshop.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day16workshop.model.Mastermind;
import sg.edu.nus.iss.day16workshop.repository.BoardGameRepository;

@Service
public class BoardGameService {

    @Autowired
    BoardGameRepository boardGameRepository;

    public int saveGame(Mastermind mastermind) {
        return boardGameRepository.saveGame(mastermind);
    }

    public Mastermind findById(String id) throws IOException {
        return boardGameRepository.findById(id);
    }

    public int upsertGame(Mastermind mastermind, boolean upsert) throws IOException {
        if (upsert) {
            int result = boardGameRepository.saveGame(mastermind);
            return result;
        } else {
            Mastermind m = boardGameRepository.findById(mastermind.getId());
            if (m.getName() == null) {
                return 0; // do nothing if upsert = false and no id match is found
            } else {
                return boardGameRepository.saveGame(mastermind);
            }
        }
    }
}
