package control;
import java.util.List;
import domain.Enemy;
import domain.Goblin;
import domain.Wolf;

public class LevelManager {
    private String difficulty;
    private List<Enemy> enemyPool = new java.util.ArrayList<>();
    private List<Enemy> backupPool = new java.util.ArrayList<>();
    
    public LevelManager(String difficulty) {
        this.difficulty = difficulty;
    }
    
    public List<Enemy> spawnInitialEnemies(){
        if (difficulty.equals("Easy")) {
            enemyPool.add(new Goblin("Goblin A"));
            enemyPool.add(new Goblin("Goblin B"));
            enemyPool.add(new Goblin("Goblin C"));
        }
        else if (difficulty.equals("Medium")) {
            enemyPool.add(new Goblin("Goblin"));
            enemyPool.add(new Wolf("Wolf"));
        }
        else if (difficulty.equals("Hard")) {
            enemyPool.add(new Goblin("Goblin A"));
            enemyPool.add(new Goblin("Goblin B"));
        }
        return enemyPool;
    }
    public List<Enemy> spawnBackupEnemies() {
        if (difficulty.equals("Medium")) {
            backupPool.add(new Wolf("Wolf A"));
            backupPool.add(new Wolf("Wolf B"));
        }
        else if (difficulty.equals("Hard")) {
            backupPool.add(new Goblin("Goblin C"));
            backupPool.add(new Wolf("Wolf A"));
            backupPool.add(new Wolf("Wolf B"));
        }
        return backupPool;
    }

}
