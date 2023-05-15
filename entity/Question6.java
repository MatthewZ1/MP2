package entity;

import main.GamePanel;

public class Question6 extends Entity {
    public Question6(GamePanel gp) {
        super(gp);

        setDirection("down");
        getImage();
        setDialogue();
    }

    public void getImage() {
        setUp1(setup("/objects/sign"));
        setUp2(setup("/objects/sign"));
        setDown1(setup("/objects/sign"));
        setDown2(setup("/objects/sign"));
        setLeft1(setup("/objects/sign"));
        setLeft2(setup("/objects/sign"));
        setRight1(setup("/objects/sign"));
        setRight2(setup("/objects/sign"));
    }

    public void setDialogue() {
        setSignDialogues(0, "\n\n\n\n\n\n What is the number of bits in an int?");
        setSignDialogues(1, "\n\n\n 1. 32 \n\n 2. 69 \n\n 3. 16 \n\n 4. 8");
    }

    public void speak() {
        if (getSignDialogues(getSignDialogueIndex()) == null) {
            setSignDialogueIndex(0);
        }
        gp.getUi().setCurrentDialogue(getSignDialogues(getSignDialogueIndex()));
        setSignDialogueIndex(getSignDialogueIndex()+1);

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
