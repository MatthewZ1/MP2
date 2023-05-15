package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Dylan extends Entity{
    public NPC_Dylan(GamePanel gp){
        super(gp);

        setDirection("down");
        setSpeed(1);
        getImage();
        setDialogue();
    }
    public void getImage() {
        setUp1(setup("/npc/Dylan"));
        setUp2(setup("/npc/Dylan"));
        setDown1(setup("/npc/Dylan"));
        setDown2(setup("/npc/Dylan"));
        setLeft1(setup("/npc/Dylan"));
        setLeft2(setup("/npc/Dylan"));
        setRight1(setup("/npc/Dylan"));
        setRight2(setup("/npc/Dylan"));
    }
    public void setDialogue(){
        setDylanDialogues(0, "I'm Dylan"); 
        setDylanDialogues(1, "I'm cool"); 
        setDylanDialogues(2, "Beauty is great"); 
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
        if(getDylanDialogues(getDylanDialogueIndex()) == null){
            setDylanDialogueIndex(0);
        }
        gp.getUi().setCurrentDialogue(getDylanDialogues(getDylanDialogueIndex()));
        setDylanDialogueIndex(getDylanDialogueIndex() + 1);

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
