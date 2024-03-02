package com.mopal.randomizer;
import java.util.Random;

public class ObstacleRandomizer {
    private static final int NUM_LANES = 3;
    private static final int INITIAL_SPEED = 5;
    private static final int MAX_SPEED = 15;
    private static final int SPEED_INCREASE_INTERVAL = 30;

    private Random random;
    private float obstacleSpeed;
    private long startTime;

    public ObstacleRandomizer() {
        random = new Random();
        obstacleSpeed = INITIAL_SPEED;
        startTime = System.currentTimeMillis();
    }

    public Obstacle generateObstacle() {
        updateSpeed();

        Obstacle.Type type = getRandomObstacleType();
        int lane = getRandomLane();
        return new Obstacle(type, lane, obstacleSpeed);
    }

    private Obstacle.Type getRandomObstacleType() {
        Obstacle.Type[] types = Obstacle.Type.values();
        return types[random.nextInt(types.length)];
    }

    private int getRandomLane() {
        return random.nextInt(NUM_LANES);
    }

    private void updateSpeed() {
        long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;

        if (elapsedTime % SPEED_INCREASE_INTERVAL == 0 && obstacleSpeed < MAX_SPEED) {
            obstacleSpeed += 2;
        }
    }
}
