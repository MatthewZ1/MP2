package entity;

import main.GamePanel;

public class Direction1 extends Entity {
    public Direction1(GamePanel gp) {
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
        setDirection1Dialogues(0, "\n \n \n \n \n Find the signs around the map. There\n are 10 signs and you better get them all correct. \n If you have not picked up all the keys, I strongly \n suggest you do!");
    }

    public void speak() {
        if (getDirection1Dialogues(getDirection1DialogueIndex()) == null) {
            setDirection1DialogueIndex(0);
        }
        gp.getUi().setCurrentDialogue(getDirection1Dialogues(getDirection1DialogueIndex()));
        setDirection1DialogueIndex(getDirection1DialogueIndex() + 1);

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
