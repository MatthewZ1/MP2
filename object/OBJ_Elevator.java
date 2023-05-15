package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Elevator extends SuperObject{
    GamePanel gp;
    public OBJ_Elevator(GamePanel gp) {
        name = "Elevator";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/elevator.png"));
            uTool.scaleImage(image, gp.getTileSize(), gp.getTileSize());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
