package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject{
    GamePanel gp;
    
    public OBJ_Heart(GamePanel gp) {
        this.gp = gp;
        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/dead.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
