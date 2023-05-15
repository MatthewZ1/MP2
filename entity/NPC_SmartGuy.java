package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_SmartGuy extends Entity{
    public NPC_SmartGuy(GamePanel gp){
        super(gp);
        setDirection("down");
        setSpeed(1);

        getImage();
        setDialogue();
    }
    public void getImage() {
        setUp1(setup("/npc/up1"));
        setUp2(setup("/npc/up1"));
        setDown1(setup("/npc/down1"));
        setDown2(setup("/npc/down2"));
        setLeft1(setup("/npc/left1"));
        setLeft2(setup("/npc/left2"));
        setRight1(setup("/npc/right1"));
        setRight2(setup("/npc/right2"));
    }
    public void setDialogue(){
        setSmartDialogues(0, "I'm Smart"); 
        setSmartDialogues(1, "I'm Very Smart"); 
        setSmartDialogues(2, "Being Smart is Great"); 
    }
    public void setAction(){
        setEntityCounter(getEntityCounter() + 1);
        if(getEntityCounter() == 120){
            Random random = new Random();
            int i = random.nextInt(100) + 1; //1-100
            if(i <= 25){
                setDirection("up");
            }
            if(i > 25 && i <= 50){
                setDirection("down");
            }
            if(i > 50 && i <= 75){
                setDirection("left");
            }
            if(i > 75 && i <= 100){
                setDirection("right");
            }
            setEntityCounter(0);
        }
    }
    public void speak(){
        if(getSmartDialogues(getSmartDialogueIndex()) == null){
            setSmartDialogueIndex(0);
        }
        gp.getUi().setCurrentDialogue(getSmartDialogues(getSmartDialogueIndex()));
        setSmartDialogueIndex(getSmartDialogueIndex() + 1);

        switch (gp.getPlayer().getDirection()) {
            case "up":
                setDirection("down");
                break;
            case "down":
                setDirection("up");
                break;
            case "left":
                setDirection("right");
                break;
            case "right":
                setDirection("left");
                break;
        }
    }
}
