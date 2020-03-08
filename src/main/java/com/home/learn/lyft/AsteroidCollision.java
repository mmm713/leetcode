package com.home.learn.lyft;

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids.length <= 1) {
            return asteroids;
        }
        int fast = 0, slow = -1;
        while (fast < asteroids.length) {
            if (asteroids[fast] >= 0) {
                asteroids[++slow] = asteroids[fast];
            } else {
                while (slow != -1 && asteroids[slow] >= 0 && asteroids[slow] < -asteroids[fast]) slow--;
                if (slow == -1 || asteroids[slow] < 0) {
                    asteroids[++slow] = asteroids[fast];
                } else {
                    if (asteroids[slow] == -asteroids[fast]) {
                        slow--;
                    }
                }
            }
            fast++;
        }
        int[] res = new int[slow + 1];
        System.arraycopy(asteroids, 0, res, 0, res.length);
        return res;
    }
}
