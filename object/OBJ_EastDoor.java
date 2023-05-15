package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_EastDoor extends SuperObject{
    GamePanel gp;
    public OBJ_EastDoor(GamePanel gp) {
        name = "Door";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sideDoor.png"));
            uTool.scaleImage(image, gp.getTileSize(), gp.getTileSize());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
