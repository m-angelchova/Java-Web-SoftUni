package bg.softuni.battleships.domain.DTOs;

import jakarta.validation.constraints.Positive;

public class StartBattleDTO {
    @Positive
    private Long attackerId;

    @Positive
    private Long enemyId;

    public Long getAttackerId() {
        return attackerId;
    }

    public void setAttackerId(Long attackerId) {
        this.attackerId = attackerId;
    }

    public Long getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(Long enemyId) {
        this.enemyId = enemyId;
    }
}
