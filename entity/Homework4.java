package entity;

import main.GamePanel;

public class Homework4 extends Entity {
    private int counter;
    private int secondTimer;
    public Homework4(GamePanel gp) {
        super(gp);
        setSpeed(5);
        setDirection("up");
        getImage();
        setDialogue();
    }
    public void getImage() {
        setUp1(setup("/objects/homework"));
        setUp2(setup("/objects/homework"));
        setDown1(setup("/objects/homework"));
        setDown2(setup("/objects/homework"));
        setLeft1(setup("/objects/homework"));
        setLeft2(setup("/objects/homework"));
        setRight1(setup("/objects/homework"));
        setRight2(setup("/objects/homework"));
    }

    public void setDialogue() {
        setSignDialogues(0, "You have been hit.\nNow suffer homework and lose 1 health.");
    }

    public void speak() {
        if (secondTimer > 120) {
            secondTimer = 0;
            gp.getPlayer().setLife(gp.getPlayer().getLife() - 1);
            if (getSignDialogues(getSignDialogueIndex()) == null) {
                setSignDialogueIndex(0);
            }
            gp.getUi().setCurrentDialogue(getSignDialogues(getSignDialogueIndex()));
            setSignDialogueIndex(getSignDialogueIndex() + 1);
        }
    }
    public void setAction(){
        counter++;
        secondTimer++;
        if(counter > 500){
            setDirection("up");
        }
        if(counter > 1000){
            setDirection("down");
            counter = 0;
        }
    }
}
