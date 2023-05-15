package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
//parent class for NPC
public class Entity {
    GamePanel gp;
    private int worldX, worldY;
    private int speed;

    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private String direction;

    private int spriteCounter = 0;
    private int spriteNum = 1;
    
    //set up boarder for player
    //collisions
    private Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    private int solidAreaDefaultX, solidAreaDefaultY;
    private boolean collisionOn = false;
    private int entityCounter = 0;
    private String dylanDialogues[] = new String[20];
    private int dylanDialogueIndex = 0;
    private String smartDialogues[] = new String[20];
    private int smartDialogueIndex = 0;
    private String signDialogues[] = new String[20];
    private int signDialogueIndex = 0;
    private String direction1Dialogues[] = new String[20];
    private int direction1DialogueIndex = 0;

    //Character Status
    private int maxLife;
    private int life;

    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void setAction(){

    }
    public void speak(){

    }
    public void update(){
        setAction();
        collisionOn = false;
        gp.getcChecker().checkTile(this);
        gp.getcChecker().checkObject(this, false);
        gp.getcChecker().checkPlayer(this);

        if(collisionOn == false) {
            switch(direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed; 
                    break;
                case "right":
                    worldX += speed; 
                    break; 
            }
        }
        spriteCounter++; //18:34 left off
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2, GamePanel gp, int x, int y){
        BufferedImage image = null;
        switch(direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
            image = right1;
                break;
        }
        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }

    public GamePanel getGp() {
		return gp;
	}
    public void setGp(GamePanel gp) {
		this.gp = gp;
	}
    public int getWorldX() {
		return worldX;
	}
  public void setWorldX(int worldX){
    this.worldX = worldX;
  }
  public void setIncWorldX(int inc) {
		this.worldX += inc;
	}
  public void setDcWorldX(int dec) {
		this.worldX -= dec;
	}
    public int getWorldY() {
		return worldY;
	}
  public void setWorldY(int worldY){
    this.worldY = worldY;
  }
    public void setIncWorldY(int inc) {
		this.worldY += inc;
	}
  public void setDcWorldY(int dec) {
		this.worldY -= dec;
	}
    public int getSpeed() {
		return speed;
	}
    public void setSpeed(int speed) {
		this.speed = speed;
	}
    public BufferedImage getUp1() {
		return up1;
	}
    public void setUp1(BufferedImage up1) {
		this.up1 = up1;
	}
    public BufferedImage getUp2() {
		return up2;
	}
    public void setUp2(BufferedImage up2) {
		this.up2 = up2;
	}
    public BufferedImage getDown1() {
		return down1;
	}
    public void setDown1(BufferedImage down1) {
		this.down1 = down1;
	}
    public BufferedImage getDown2() {
		return down2;
	}
    public void setDown2(BufferedImage down2) {
		this.down2 = down2;
	}
    public BufferedImage getLeft1() {
		return left1;
	}
    public void setLeft1(BufferedImage left1) {
		this.left1 = left1;
	}
    public BufferedImage getLeft2() {
		return left2;
	}
    public void setLeft2(BufferedImage left2) {
		this.left2 = left2;
	}
    public BufferedImage getRight1() {
		return right1;
	}
    public void setRight1(BufferedImage right1) {
		this.right1 = right1;
	}
    public BufferedImage getRight2() {
		return right2;
	}
    public void setRight2(BufferedImage right2) {
		this.right2 = right2;
	}
    public String getDirection() {
		return direction;
	}
    public void setDirection(String direction) {
		this.direction = direction;
	}
    public int getSpriteCounter() {
		return spriteCounter;
	}
    public void setSpriteCounter(int spriteCounter) {
		this.spriteCounter = spriteCounter;
	}
  public void incSpriteCounter(int x){
    this.spriteCounter++;
  }
    public int getSpriteNum() {
		return spriteNum;
	}
    public void setSpriteNum(int spriteNum) {
		this.spriteNum = spriteNum;
	}
    public Rectangle getSolidArea() {
		return solidArea;
	}
    public void setSolidArea(Rectangle solidArea) {
		this.solidArea = solidArea;
	}
    public int getSolidAreaDefaultX() {
		return solidAreaDefaultX;
	}
    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
		this.solidAreaDefaultX = solidAreaDefaultX;
	}
    public int getSolidAreaDefaultY() {
		return solidAreaDefaultY;
	}
    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
		this.solidAreaDefaultY = solidAreaDefaultY;
	}
    public boolean isCollisionOn() {
		return collisionOn;
	}
    public void setCollisionOn(boolean collisionOn) {
		this.collisionOn = collisionOn;
	}
    public int getEntityCounter() {
		return entityCounter;
	}
    public void setEntityCounter(int entityCounter) {
		this.entityCounter = entityCounter;
	}
    public String getDylanDialogues(int index) {
		return dylanDialogues[index];
	}
    public void setDylanDialogues(int index, String str) {
		dylanDialogues[index] = str;
	}
    public int getDylanDialogueIndex() {
		return dylanDialogueIndex;
	}
    public void setDylanDialogueIndex(int index) {
		this.dylanDialogueIndex = index;
	}
  public String getSmartDialogues(int index) {
		return smartDialogues[index];
	}
    public void setSmartDialogues(int index, String str) {
		smartDialogues[index] = str;
	}
    public int getSmartDialogueIndex() {
		return smartDialogueIndex;
	}
    public void setSmartDialogueIndex(int index) {
		this.smartDialogueIndex = index;
	}
  public String getSignDialogues(int index) {
		return signDialogues[index];
	}
    public void setSignDialogues(int index, String str) {
		signDialogues[index] = str;
	}
    public int getSignDialogueIndex() {
		return signDialogueIndex;
	}
    public void setSignDialogueIndex(int index) {
		this.signDialogueIndex = index;
	}
    public String getDirection1Dialogues(int index) {
		return direction1Dialogues[index];
	}
    public void setDirection1Dialogues(int index, String str) {
		direction1Dialogues[index] = str;
	}
    public int getDirection1DialogueIndex() {
		return direction1DialogueIndex;
	}
    public void setDirection1DialogueIndex(int index) {
		this.direction1DialogueIndex = index;
	}
    public int getMaxLife() {
		return maxLife;
	}
    public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}
    public int getLife() {
		return life;
	}
    public void setLife(int life) {
		this.life = life;
	}
	public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;

        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.getTileSize(), gp.getTileSize());
        }catch(IOException e){
            e.printStackTrace();
        }
        return scaledImage;
    }
}
