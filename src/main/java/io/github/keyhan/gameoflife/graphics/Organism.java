package io.github.keyhan.gameoflife.graphics;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

@Slf4j
@Getter
public class Organism {

    private final String image = "🥸";

    private final Point position;

    public Organism(int x, int y) {
        position = new Point(x, y);
    }

//    public void draw(Graphics g, ImageObserver observer) {
//        g.drawImage(image,
//                position.x * Board.TILE_SIZE,
//                position.y * Board.TILE_SIZE,
//                observer
//                );
//    }
}
