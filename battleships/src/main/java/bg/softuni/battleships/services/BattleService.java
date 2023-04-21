package bg.softuni.battleships.services;

import bg.softuni.battleships.domain.DTOs.StartBattleDTO;
import bg.softuni.battleships.domain.entities.Ship;
import bg.softuni.battleships.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleService {
    private final ShipRepository shipRepository;

    @Autowired
    public BattleService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public void attack(StartBattleDTO startBattleDTO){
        Optional<Ship> attackerOpt = this.shipRepository.findById(startBattleDTO.getAttackerId());
        Optional<Ship> enemyOpt = this.shipRepository.findById(startBattleDTO.getEnemyId());

        if(attackerOpt.isEmpty() || enemyOpt.isEmpty()){
            throw new NoSuchElementException();
        }

        Ship attacker = attackerOpt.get();
        Ship defender = enemyOpt.get();

        long newDefHealth = defender.getHealth() - attacker.getPower();

        if (newDefHealth <= 0){
            this.shipRepository.delete(defender);
        }else {
            defender.setHealth(newDefHealth);
            this.shipRepository.save(defender);
        }

    }
}
