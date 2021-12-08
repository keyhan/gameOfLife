package io.github.keyhan.gameoflife;

import io.github.keyhan.gameoflife.graphics.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameOfLifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameOfLifeApplication.class, args);
        System.setProperty("java.awt.headless", "false");
        new Game();
    }

}
