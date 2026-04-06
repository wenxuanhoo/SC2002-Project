package control;
import java.util.List;
import domain.Enemy;
import domain.Goblin;
import domain.Wolf;

public class LevelManager {
    private String difficulty;
    private List<Enemy> enemyPool = new java.util.ArrayList<>();
    private List<Enemy> backupPool = new java.util.ArrayList<>();
    public List<Enemy> spawnInitialEnemies(){
        if (difficulty.equals("Easy")) {
            enemyPool.add(new Goblin());
            enemyPool.add(new Goblin());
            enemyPool.add(new Goblin());
        }
        else if (difficulty.equals("Medium")) {
            enemyPool.add(new Goblin());
            enemyPool.add(new Wolf());
        }
        else if (difficulty.equals("Hard")) {
            enemyPool.add(new Goblin());
            enemyPool.add(new Goblin());
        }
        return enemyPool;
    }
    public List<Enemy> spawnBackupEnemies() {
        if (difficulty.equals("Medium")) {
            backupPool.add(new Wolf());
            backupPool.add(new Wolf());
        }
        else if (difficulty.equals("Hard")) {
            backupPool.add(new Goblin());
            backupPool.add(new Wolf());
            backupPool.add(new Wolf());
        }
        return backupPool;
    }
    public boolean isInitialWaveDefeated() {
        boolean allDefeated = true;
        for (Enemy enemy:enemyPool) {
            if (!enemy.isDefeated()) {
                allDefeated = false;
            }
        }
        return allDefeated;
    }
}
