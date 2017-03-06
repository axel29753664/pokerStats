package lv.axel.services;

import lv.axel.domain.Player;
import lv.axel.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player findPlayerById(Long id) {
        return playerRepository.findOne(id);
    }

    public Player findPlayerByName(String name) {
        return playerRepository.findByName(name);
    }
}

