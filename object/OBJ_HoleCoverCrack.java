package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_HoleCoverCrack extends SuperObject {
    GamePanel gp;

    public OBJ_HoleCoverCrack(GamePanel gp) {
        name = "Hole Cover";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor.jpg"));
            uTool.scaleImage(image, gp.getTileSize(), gp.getTileSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}