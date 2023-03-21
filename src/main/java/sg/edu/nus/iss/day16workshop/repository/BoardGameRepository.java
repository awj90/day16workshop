package sg.edu.nus.iss.day16workshop.repository;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day16workshop.model.Mastermind;

@Repository
public class BoardGameRepository {
    
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public int saveGame(Mastermind mastermind) {
        redisTemplate.opsForValue()
                    .set(mastermind.getId(), mastermind.toJsonObject().toString());
        String s = (String) redisTemplate.opsForValue()
                                        .get(mastermind.getId());
        if (s!=null) {
            return 1; // 1 insertion
        } else {
            return 0; // 0 insertions
        }
    }

    public Mastermind findById(String id) throws IOException {
        String jsonString = (String) redisTemplate.opsForValue().get(id);
        return Mastermind.JsonStringToJavaObject(jsonString);
    }
}
