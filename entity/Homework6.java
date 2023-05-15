package entity;

import main.GamePanel;

public class Homework6 extends Entity {
    private int counter;
    private int secondTimer;
    public Homework6(GamePanel gp) {
        super(gp);
        setSpeed(2);
        setDirection("left");
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
        if(counter > 600){
            setDirection("right");
        }
        if(counter > 1200){
            setDirection("left");
            counter = 0;
        }
    }
}
