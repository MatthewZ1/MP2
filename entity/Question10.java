package entity;

import main.GamePanel;

public class Question10 extends Entity {
    public Question10(GamePanel gp) {
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
        setSignDialogues(0, "\n\n\n\n\n\n Who is the most beautiful person on Earth??");
        setSignDialogues(1, "\n\n\n 1. Danny Devito \n\n 2. Elon(gated) Musk(rat) \n\n 3. No one is \n\n 4. Everyone is BEAUTIFUL");
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
