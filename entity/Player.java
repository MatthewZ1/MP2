package entity;

import main.AssetSetter;
import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Key;
import object.OBJ_Portal;
import object.OBJ_Portal1;
import object.OBJ_Portal10;
import object.OBJ_Portal2;
import object.OBJ_Portal3;
import object.OBJ_Portal5;
import object.OBJ_Portal6;
import object.OBJ_Portal7;
import object.OBJ_Portal8;
import object.OBJ_Portal9;
import object.OBJ_Portal11;
import object.OBJ_Puddle;
import object.OBJ_SpeedPotion;
import object.OBJ_VictoryDoor;
import object.OBJ_VictoryDoor1;
import object.OBJ_VictoryDoor2;
import object.OBJ_VictoryDoor3;
import tile.TileManager;
import object.OBJ_Crack;
import object.OBJ_Elevator;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    // GamePanel gp;
    KeyHandler keyH;
    // number of keys player holds at the moment
    private int keys = 0;
    private boolean touchWater = false;
    // each time touches "victory door" itll add one to keep track of floor
    private static int floor = 0;

    // world view
    private final int screenX;
    private final int screenY;
    private boolean canTouchEvent;
    private int previousWorldX, previousWorldY;

    // counter timer
    private int counter;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp); // calling constructor of super class of this class
        // this.gp = gp;
        this.keyH = keyH;
        screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2);
        screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2);
        // makes the sprite a rectangle and when the rectangle hits on object it stops
        // if you make the boarders the side of the actual sprite, it will be a hastle
        setSolidArea(new Rectangle());
        getSolidArea().x = 8;
        getSolidArea().y = 16;
        // recall area value later because solid area x and y later
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);
        getSolidArea().width = 32;
        getSolidArea().height = 32;

        setDefaultValue();
        getPlayerImage();
    }
    public int getKeys(){
        return keys;
    }
    public int getScreenY(){
        return screenY;
    }
    public int getScreenX(){
        return screenX;
    }
    public void setDefaultValue() {
        setWorldX(gp.getTileSize() * 23 - gp.getTileSize());
        setWorldY(gp.getTileSize() * 21 - gp.getTileSize());
        setSpeed(3);
        setDirection("down");

        setMaxLife(10);
        setLife(getMaxLife());
    }

    public void getPlayerImage() {
        setUp1(setup("/player/up1"));
        setUp2(setup("/player/up2"));
        setDown1(setup("/player/down1"));
        setDown2(setup("/player/down2"));
        setLeft1(setup("/player/left1"));
        setLeft2(setup("/player/left2"));
        setRight1(setup("/player/right1"));
        setRight2(setup("/player/right2"));
    }

    public void update() {

        /*
         * if key is pressed, the sprite moves a certain amount
         * amount is equal to playerSpeed
         * since player speed is set to a pixel
         * the sprite moves the amount of pixel speed is set to
         */
        checkEvent();
        if (keyH.isUpPressed() == true || keyH.isDownPressed() == true || keyH.isLeftPressed() == true
                || keyH.isRightPressed() == true) {
            if (keyH.isUpPressed() == true) {
                setDirection("up");
            } else if (keyH.isDownPressed() == true) {
                setDirection("down");
            } else if (keyH.isLeftPressed() == true) {
                setDirection("left");
            } else if (keyH.isRightPressed() == true) {
                setDirection("right");
            }
            // check for collision
            setCollisionOn(false);
            gp.getcChecker().checkTile(this);

            // check collision against objects
            int objIndex = gp.getcChecker().checkObject(this, true);

            pickUp(objIndex);

            // checks NPC collision
            int npcIndex = gp.getcChecker().checkEntity(this, gp.getNpc());
            interactNPC(npcIndex);
            // only moves if collision is false or not touching anything
            if (isCollisionOn() == false) {
                switch (getDirection()) {
                    case "up":
                        setDcWorldY(getSpeed());
                        break;
                    case "down":
                        setIncWorldY(getSpeed());
                        break;
                    case "left":
                        setDcWorldX(getSpeed());
                        break;
                    case "right":
                        setIncWorldX(getSpeed());
                        break;
                }
            }
            incSpriteCounter(1); // 18:34 left off
            if (getSpriteCounter() > 12) {
                if (getSpriteNum() == 1) {
                    setSpriteNum(2);
                } else if (getSpriteNum() == 2) {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            gp.setGameState(gp.getDialogueState());
            gp.getNpc()[i].speak();
        }
    }

    public void pickUp(int i) {
        // if i = 999 then the player touched nothing
        // else it did
        if (i != 999) {
            String objName = gp.getObj()[i].name;
            switch (objName) {
                case "Key":
                    keys++;
                    // deletes obj touched
                    gp.getObj()[i] = null;
                    gp.getUi().showMessage("You got a key. You might want to collect more");
                    break;
                case "Door":
                    if (keys >= 1) {
                        // deletes obj touched
                        // if player has key, door "opens" and deletes
                        gp.getObj()[i] = null;
                        // "you used a key", so you lost it and cant use it again, till you find a new
                        // key
                        keys--;
                        gp.getUi().showMessage("You opened a door. Congrats!");
                    } else {
                        gp.getUi().showMessage("Open the door!");
                    }
                    System.out.println("Keys:" + keys);
                    break;

                /*
                 * Plan
                 * make a bunch of keys and doors
                 * have 1 real door and make the game end when that one secret door is found
                 */
                case "VictoryDoor":
                    System.out.print(floor);
                    floor++;
                    System.out.print(floor);
                    // clears all objects except the 2 north and south doors
                    for (int a = 0; a < 28; a++) {
                        if (a > 3) {
                            gp.getObj()[a] = null;
                        }
                    }
                    gp.getNpc()[0] = null;
                    gp.getNpc()[1] = null;
                    gp.getNpc()[2] = null;
                    // next level clears level and goes to next floor
                    gp.getUi().showMessage("                 Congrats on reaching the 1st floor");
                    setSpeed(3);
                    setWorldX(gp.getTileSize() * 23 - gp.getTileSize());
                    setWorldY(gp.getTileSize() * 21 - gp.getTileSize());
                    /*
                     * IDEA:
                     * the code above clears all the objects off the screen by setting it all to
                     * null
                     * to make it simple to code
                     * the code below
                     */

                    gp.getNpc()[3] = new Direction1(gp);
                    gp.getNpc()[3].setWorldX(24 * gp.getTileSize());
                    gp.getNpc()[3].setWorldY(23 * gp.getTileSize());

                    gp.getNpc()[4] = new Question2(gp);
                    gp.getNpc()[4].setWorldX(14 * gp.getTileSize());
                    gp.getNpc()[4].setWorldY(12 * gp.getTileSize());

                    gp.getNpc()[5] = new Question3(gp);
                    gp.getNpc()[5].setWorldX(19 * gp.getTileSize());
                    gp.getNpc()[5].setWorldY(12 * gp.getTileSize());

                    gp.getNpc()[6] = new Question4(gp);
                    gp.getNpc()[6].setWorldX(24 * gp.getTileSize());
                    gp.getNpc()[6].setWorldY(12 * gp.getTileSize());

                    gp.getNpc()[7] = new Question5(gp);
                    gp.getNpc()[7].setWorldX(29 * gp.getTileSize());
                    gp.getNpc()[7].setWorldY(12 * gp.getTileSize());

                    gp.getNpc()[8] = new Question6(gp);
                    gp.getNpc()[8].setWorldX(34 * gp.getTileSize());
                    gp.getNpc()[8].setWorldY(12 * gp.getTileSize());

                    gp.getNpc()[9] = new Question7(gp);
                    gp.getNpc()[9].setWorldX(37 * gp.getTileSize());
                    gp.getNpc()[9].setWorldY(16 * gp.getTileSize());

                    gp.getNpc()[10] = new Question8(gp);
                    gp.getNpc()[10].setWorldX(37 * gp.getTileSize());
                    gp.getNpc()[10].setWorldY(23 * gp.getTileSize());

                    gp.getNpc()[11] = new Question9(gp);
                    gp.getNpc()[11].setWorldX(37 * gp.getTileSize());
                    gp.getNpc()[11].setWorldY(30 * gp.getTileSize());

                    gp.getNpc()[12] = new Question10(gp);
                    gp.getNpc()[12].setWorldX(37 * gp.getTileSize());
                    gp.getNpc()[12].setWorldY(37 * gp.getTileSize());

                    gp.getObj()[5] = new OBJ_VictoryDoor1(gp);
                    gp.getObj()[5].setWorldX(39 * gp.getTileSize() + 20);
                    gp.getObj()[5].setWorldY(39 * gp.getTileSize() + 20);

                    gp.getObj()[6] = new OBJ_SpeedPotion(gp);
                    gp.getObj()[6].setWorldX(26 * gp.getTileSize());
                    gp.getObj()[6].setWorldY(24 * gp.getTileSize());
                    break;
                // case "Puddle":
                // gp.ui.finished = 2;
                case "Puddle":
                    touchWater = true;
                    gp.getUi().showMessage("You got slowed. The water is poision!! :O");
                    // gp.ui.showMessage("You can go to the next level to remove the slowness");
                    // gp.ui.showMessage("Or you can pick up one of the speed potions");
                    setSpeed(2);
                    break;
                case "Speed Potion":
                    gp.getObj()[i] = null;
                    gp.getUi().showMessage("You got a potion that speeds you up!");
                    setSpeed(getSpeed() + 2);
                    break;
                case "Hole Cover":
                    gp.getObj()[i] = null;
                    break;
                case "Crack":
                    previousWorldX = getWorldX();
                    previousWorldY = getWorldY();
                    if (canTouchEvent) {
                        gp.setGameState(gp.getDialogueState());
                        gp.getUi().setCurrentDialogue("A tile cracked and you barely manage to dodge it.\nHowever, you got hurt.");
                        gp.getPlayer().setLife(getLife() - 1);
                        canTouchEvent = false;
                    }
                    break;

                // loads 2nd floor
                case "VictoryDoor1":
                    gp.setCurrentMap(0);
                    gp.getUi().showMessage("                 Congrats on reaching the 2nd floor");
                    setWorldX(gp.getTileSize() * 23 - gp.getTileSize());
                    setWorldY(gp.getTileSize() * 21 - gp.getTileSize());
                    for (int a = 0; a < 50; a++) {
                        gp.getObj()[a] = null;
                    }
                    for (int b = 0; b < 50; b++) {
                        gp.getNpc()[b] = null;
                    }
                    gp.getObj()[5] = new OBJ_VictoryDoor2(gp);
                    gp.getObj()[5].setWorldX(9 * gp.getTileSize() + 20);
                    gp.getObj()[5].setWorldY(8 * gp.getTileSize() + 50);

                    gp.getNpc()[0] = new Direction2(gp);
                    gp.getNpc()[0].setWorldX(24 * gp.getTileSize());
                    gp.getNpc()[0].setWorldX(23 * gp.getTileSize());

                    gp.getNpc()[1] = new Homework1(gp);
                    gp.getNpc()[1].setWorldX(11 * gp.getTileSize());
                    gp.getNpc()[1].setWorldY(9 * gp.getTileSize() + 10);

                    gp.getNpc()[2] = new Homework2(gp);
                    gp.getNpc()[2].setWorldX(11 * gp.getTileSize());
                    gp.getNpc()[2].setWorldY(13 * gp.getTileSize() + 20);

                    gp.getNpc()[3] = new Homework3(gp);
                    gp.getNpc()[3].setWorldX(13 * gp.getTileSize());
                    gp.getNpc()[3].setWorldY(23 * gp.getTileSize() + 20);

                    gp.getNpc()[4] = new Homework4(gp);
                    gp.getNpc()[4].setWorldX(28 * gp.getTileSize());
                    gp.getNpc()[4].setWorldY(28 * gp.getTileSize());

                    gp.getNpc()[5] = new Homework5(gp);
                    gp.getNpc()[5].setWorldX(35 * gp.getTileSize());
                    gp.getNpc()[5].setWorldY(23 * gp.getTileSize());

                    gp.getNpc()[6] = new Homework6(gp);
                    gp.getNpc()[6].setWorldX(35 * gp.getTileSize());
                    gp.getNpc()[6].setWorldY(39 * gp.getTileSize());

                    gp.getNpc()[7] = new Homework7(gp);
                    gp.getNpc()[7].setWorldX(32 * gp.getTileSize());
                    gp.getNpc()[7].setWorldY(12 * gp.getTileSize());

                    gp.getNpc()[8] = new Homework8(gp);
                    gp.getNpc()[8].setWorldX(25 * gp.getTileSize());
                    gp.getNpc()[8].setWorldY(20 * gp.getTileSize());

                    break;

                // loads 3rd floor
                case "VictoryDoor2":
                    gp.setCurrentMap(1);
                    gp.getUi().showMessage("                 Congrats on reaching the 3rd floor");
                    setWorldX(gp.getTileSize() * 23 - gp.getTileSize());
                    setWorldY(gp.getTileSize() * 21 - gp.getTileSize());
                    for (int b = 0; b < 50; b++) {
                        gp.getNpc()[b] = null;
                    }
                    gp.getObj()[5] = null;

                    gp.getObj()[5] = new OBJ_VictoryDoor3(gp);
                    gp.getObj()[5].setWorldX(9 * gp.getTileSize() + 20);
                    gp.getObj()[5].setWorldY(8 * gp.getTileSize() + 50);

                    gp.getNpc()[0] = new Direction2(gp);
                    gp.getNpc()[0].setWorldX(24 * gp.getTileSize());
                    gp.getNpc()[0].setWorldY(23 * gp.getTileSize());

                    gp.getNpc()[1] = new Homework1(gp);
                    gp.getNpc()[1].setWorldX(11 * gp.getTileSize());
                    gp.getNpc()[1].setWorldY(9 * gp.getTileSize() + 10);

                    gp.getNpc()[2] = new Homework2(gp);
                    gp.getNpc()[2].setWorldX(16 * gp.getTileSize());
                    gp.getNpc()[2].setWorldY(15 * gp.getTileSize() + 20);

                    gp.getNpc()[3] = new Homework3(gp);
                    gp.getNpc()[3].setWorldX(13 * gp.getTileSize());
                    gp.getNpc()[3].setWorldY(23 * gp.getTileSize() + 20);

                    gp.getNpc()[4] = new Homework4(gp);
                    gp.getNpc()[4].setWorldX(28 * gp.getTileSize());
                    gp.getNpc()[4].setWorldY(28 * gp.getTileSize());

                    gp.getNpc()[5] = new Homework5(gp);
                    gp.getNpc()[5].setWorldX(35 * gp.getTileSize());
                    gp.getNpc()[5].setWorldY(23 * gp.getTileSize());

                    gp.getNpc()[6] = new Homework6(gp);
                    gp.getNpc()[6].setWorldX(35 * gp.getTileSize());
                    gp.getNpc()[6].setWorldY(39 * gp.getTileSize());

                    gp.getNpc()[7] = new Homework7(gp);
                    gp.getNpc()[7].setWorldX(32 * gp.getTileSize());
                    gp.getNpc()[7].setWorldY(12 * gp.getTileSize());

                    gp.getNpc()[8] = new Homework8(gp);
                    gp.getNpc()[8].setWorldX(25 * gp.getTileSize());
                    gp.getNpc()[8].setWorldY(20 * gp.getTileSize());
                    break;

                // laods 4th floor
                case "VictoryDoor3":
                    gp.setCurrentMap(0);
                    gp.getUi().showMessage("                 Congrats on reaching the 4th floor");
                    setWorldX(gp.getTileSize() * 23 - gp.getTileSize());
                    setWorldY(gp.getTileSize() * 21 - gp.getTileSize());
                    for (int b = 0; b < 50; b++) {
                        gp.getNpc()[b] = null;
                    }
                    gp.getObj()[5] = null;

                    gp.getObj()[28] = new OBJ_Key(gp);
                    gp.getObj()[28].setWorldX(33 * gp.getTileSize());
                    gp.getObj()[28].worldY = 11 * gp.getTileSize();

                    gp.getObj()[29] = new OBJ_Key(gp);
                    gp.getObj()[29].setWorldX(37 * gp.getTileSize());
                    gp.getObj()[29].setWorldY(34 * gp.getTileSize());

                    gp.getObj()[30] = new OBJ_Key(gp);
                    gp.getObj()[30].setWorldX(10 * gp.getTileSize());
                    gp.getObj()[30].setWorldY(38 * gp.getTileSize());

                    gp.getNpc()[0] = new Direction3(gp);
                    gp.getNpc()[0].setWorldX(24 * gp.getTileSize());
                    gp.getNpc()[0].setWorldY(23 * gp.getTileSize());

                    gp.getObj()[0] = new OBJ_Elevator(gp);
                    gp.getObj()[0].setWorldX(25 * gp.getTileSize());
                    gp.getObj()[0].setWorldY(7 * gp.getTileSize() + 50);

                    gp.getObj()[1] = new OBJ_Portal(gp);
                    gp.getObj()[1].setWorldX(20 * gp.getTileSize());
                    gp.getObj()[1].setWorldY(7 * gp.getTileSize() + 50);

                    gp.getObj()[5] = new OBJ_SpeedPotion(gp);
                    gp.getObj()[5].setWorldX(12 * gp.getTileSize());
                    gp.getObj()[5].setWorldY(10 * gp.getTileSize());

                    gp.getObj()[6] = new OBJ_Puddle(gp);
                    gp.getObj()[6].setWorldX(13 * gp.getTileSize());
                    gp.getObj()[6].setWorldY(10 * gp.getTileSize());

                    gp.getObj()[7] = new OBJ_Puddle(gp);
                    gp.getObj()[7].setWorldX(22 * gp.getTileSize());
                    gp.getObj()[7].setWorldY(32 * gp.getTileSize());

                    gp.getObj()[8] = new OBJ_Puddle(gp);
                    gp.getObj()[8].setWorldX(12 * gp.getTileSize());
                    gp.getObj()[8].setWorldY(30 * gp.getTileSize());

                    gp.getObj()[9] = new OBJ_SpeedPotion(gp);
                    gp.getObj()[9].setWorldX(25 * gp.getTileSize());
                    gp.getObj()[9].setWorldY(25 * gp.getTileSize());

                    gp.getObj()[10] = new OBJ_SpeedPotion(gp);
                    gp.getObj()[10].setWorldX(12 * gp.getTileSize());
                    gp.getObj()[10].setWorldY(13 * gp.getTileSize());

                    gp.getObj()[11] = new OBJ_SpeedPotion(gp);
                    gp.getObj()[11].setWorldX(32 * gp.getTileSize());
                    gp.getObj()[11].setWorldY(37 * gp.getTileSize());

                    gp.getObj()[12] = new OBJ_Puddle(gp);
                    gp.getObj()[12].setWorldX(26 * gp.getTileSize());
                    gp.getObj()[12].setWorldY(30 * gp.getTileSize());

                    gp.getObj()[13] = new OBJ_Puddle(gp);
                    gp.getObj()[13].setWorldX(16 * gp.getTileSize());
                    gp.getObj()[13].setWorldY(36 * gp.getTileSize());

                    gp.getObj()[14] = new OBJ_Puddle(gp);
                    gp.getObj()[14].setWorldX(27 * gp.getTileSize());
                    gp.getObj()[14].setWorldY(23 * gp.getTileSize());

                    gp.getObj()[15] = new OBJ_Puddle(gp);
                    gp.getObj()[15].setWorldX(14 * gp.getTileSize());
                    gp.getObj()[15].setWorldY(38 * gp.getTileSize());

                    gp.getObj()[16] = new OBJ_Puddle(gp);
                    gp.getObj()[16].setWorldX(37 * gp.getTileSize());
                    gp.getObj()[16].setWorldY(14 * gp.getTileSize());

                    gp.getObj()[17] = new OBJ_Puddle(gp);
                    gp.getObj()[17].setWorldX(35 * gp.getTileSize());
                    gp.getObj()[17].setWorldY(9 * gp.getTileSize());

                    gp.getObj()[18] = new OBJ_SpeedPotion(gp);
                    gp.getObj()[18].setWorldX(30 * gp.getTileSize());
                    gp.getObj()[18].setWorldY(12 * gp.getTileSize());

                    gp.getObj()[19] = new OBJ_SpeedPotion(gp);
                    gp.getObj()[19].setWorldX(25 * gp.getTileSize());
                    gp.getObj()[19].setWorldY(25 * gp.getTileSize());

                    gp.getObj()[20] = new OBJ_Puddle(gp);
                    gp.getObj()[20].setWorldX(37 * gp.getTileSize());
                    gp.getObj()[20].setWorldY(27 * gp.getTileSize());

                    gp.getObj()[21] = new OBJ_Puddle(gp);
                    gp.getObj()[21].setWorldX(35 * gp.getTileSize());
                    gp.getObj()[21].setWorldY(37 * gp.getTileSize());

                    gp.getObj()[22] = new OBJ_Puddle(gp);
                    gp.getObj()[22].setWorldX(36 * gp.getTileSize());
                    gp.getObj()[22].setWorldY(37 * gp.getTileSize());

                    gp.getObj()[23] = new OBJ_Puddle(gp);
                    gp.getObj()[23].setWorldX(36 * gp.getTileSize());
                    gp.getObj()[23].setWorldY(30 * gp.getTileSize());

                    gp.getObj()[24] = new OBJ_Puddle(gp);
                    gp.getObj()[24].setWorldX(26 * gp.getTileSize());
                    gp.getObj()[24].worldY = 36 * gp.getTileSize();

                    gp.getObj()[25] = new OBJ_Puddle(gp);
                    gp.getObj()[25].setWorldX(22 * gp.getTileSize());
                    gp.getObj()[25].setWorldY(11 * gp.getTileSize());

                    gp.getObj()[26] = new OBJ_Puddle(gp);
                    gp.getObj()[26].setWorldX(27 * gp.getTileSize());
                    gp.getObj()[26].setWorldY(12 * gp.getTileSize());

                    gp.getObj()[27] = new OBJ_Puddle(gp);
                    gp.getObj()[27].setWorldX(22 * gp.getTileSize());
                    gp.getObj()[27].setWorldY(14 * gp.getTileSize());
                    break;

                // laods roof
                case "Elevator":
                    if (keys >= 5) {
                        setSpeed(3);
                        gp.getObj()[i] = null;
                        keys -= 5;
                        gp.getUi().showMessage("Congrats! You're going to the roof...");
                        gp.setCurrentMap(2);
                        setWorldX(gp.getTileSize() * 23 - gp.getTileSize());
                        setWorldY(gp.getTileSize() * 21 - gp.getTileSize());
                        for (int a = 0; a < 50; a++) {
                            gp.getObj()[a] = null;
                        }
                        for (int b = 0; b < 50; b++) {
                            gp.getNpc()[b] = null;
                        }
                        gp.getNpc()[0] = new DirectionFinal(gp);
                        gp.getNpc()[0].setWorldX(24 * gp.getTileSize());
                        gp.getNpc()[0].setWorldY(23 * gp.getTileSize());

                        gp.getObj()[0] = new OBJ_Portal1(gp);
                        gp.getObj()[0].setWorldX(440);
                        gp.getObj()[0].setWorldY(615);

                        gp.getObj()[1] = new OBJ_Portal2(gp);
                        gp.getObj()[1].setWorldX(590);
                        gp.getObj()[1].setWorldY(450);

                        gp.getObj()[2] = new OBJ_Portal3(gp);
                        gp.getObj()[2].setWorldX(1690);
                        gp.getObj()[2].setWorldY(450);

                        gp.getObj()[3] = new OBJ_Portal5(gp);
                        gp.getObj()[3].setWorldX(1878);
                        gp.getObj()[3].setWorldY(606);

                        gp.getObj()[4] = new OBJ_Portal6(gp);
                        gp.getObj()[4].setWorldX(1100);
                        gp.getObj()[4].setWorldY(828);

                        gp.getObj()[5] = new OBJ_Portal7(gp);
                        gp.getObj()[5].setWorldX(900);
                        gp.getObj()[5].setWorldY(990);

                        gp.getObj()[6] = new OBJ_Portal8(gp);
                        gp.getObj()[6].setWorldX(1260);
                        gp.getObj()[6].setWorldY(990);

                        gp.getObj()[7] = new OBJ_Portal9(gp);
                        gp.getObj()[7].setWorldX(450);
                        gp.getObj()[7].setWorldY(1415);

                        gp.getObj()[8] = new OBJ_Portal10(gp);
                        gp.getObj()[8].setWorldX(770);
                        gp.getObj()[8].setWorldY(1415);

                        gp.getObj()[9] = new OBJ_Portal11(gp);
                        gp.getObj()[9].setWorldX(1710);
                        gp.getObj()[9].setWorldY(1320);
                    } else {
                        gp.getUi().showMessage("You need more keys!");
                    }
                    System.out.println("Keys:" + keys);
                    break;
                case "Portal":
                    gp.getObj()[0] = null;
                    gp.getObj()[1] = null;
                    gp.setCurrentMap(0);
                    AssetSetter AssetSetter1 = new AssetSetter(gp);
                    AssetSetter1.setObject();
                    break;
                case "Portal1":
                    // go back to start
                    // 1083, 990
                    setWorldX(1083);
                    setWorldY(990);
                    break;
                case "Portal2":
                    // go to upper right
                    // 1713, 600
                    setWorldX(1713);
                    setWorldY(600);
                    break;
                case "Portal3":
                    // go back to start
                    // 1083, 990
                    setWorldX(1083);
                    setWorldY(990);
                    break;
                case "Portal5":
                    // go to end
                    setWorldX(1722);
                    setWorldY(1464);
                    break;
                case "Portal6":
                    // go to upper right
                    // 1713, 600
                    setWorldX(1713);
                    setWorldY(600);
                    break;
                case "Portal7":
                    // go to upper left
                    // 618, 600
                    setWorldX(618);
                    setWorldY(600);
                    break;
                case "Portal8":
                    // go to bottom left
                    // 618, 1464
                    setWorldX(618);
                    setWorldY(1464);
                    break;
                case "Portal9":
                    // go to upper left
                    // 618, 600
                    setWorldX(618);
                    setWorldY(600);
                    break;
                case "Portal10":
                    // restart
                    for (int a = 0; a < 50; a++) {
                        gp.getObj()[a] = null;
                    }
                    for (int b = 0; b < 50; b++) {
                        gp.getNpc()[b] = null;
                    }
                    gp.setCurrentMap(0);
                    AssetSetter AssetSetter2 = new AssetSetter(gp);
                    AssetSetter2.setObject();
                    setWorldX(gp.getTileSize() * 23 - gp.getTileSize());
                    setWorldY(gp.getTileSize() * 21 - gp.getTileSize());
                    break;
                case "Portal11":
                    gp.getUi().setFinished(1);
                    break;

            }
        }
    }

    public void checkEvent() {
        int xDistance = Math.abs(gp.getPlayer().getWorldX() - previousWorldX);
        int yDistance = Math.abs(gp.getPlayer().getWorldY() - previousWorldY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.getTileSize()) {
            canTouchEvent = true;
        }
    }

    public void draw(Graphics2D g2, int middleX, int middleY) {
        // the character sprite
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.getTileSize(), gp.getTileSize());

        BufferedImage image = null;

        switch (getDirection()) {
            case "up":
                if (getSpriteNum() == 1) {
                    image = getUp1();
                }
                if (getSpriteNum() == 2) {
                    image = getUp2();
                }
                break;
            case "down":
                if (getSpriteNum() == 1) {
                    image = getDown1();
                }
                if (getSpriteNum() == 2) {
                    image = getDown2();
                }
                break;
            case "left":
                if (getSpriteNum() == 1) {
                    image = getLeft1();
                }
                if (getSpriteNum() == 2) {
                    image = getLeft2();
                }
                break;
            case "right":
                if (getSpriteNum() == 1) {
                    image = getRight1();
                }
                if (getSpriteNum() == 2) {
                    image = getRight2();
                }
                break;
        }
        g2.drawImage(image, middleX - 22, middleY - 16, null);

    }
}
