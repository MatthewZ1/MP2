package entity;

import main.GamePanel;

public class Sign extends Entity {
    public Sign(GamePanel gp) {
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
        setSignDialogues(0, "\n \nIt is 1/18 and you are talking to your best friend \nBen Dover in the cafeteria. You suddenly \nhear a BOOM and the ground shakes. Your \nknees buckle as confusion shocks you. \nYou hear the alarm go off as it bursts your ear \ndrums. You take a step forward as you try to run \naway, but you suddenly wake up here in the \nbasement. What happened? What will you do? \nCry? No! You shall take charge and escape this \nschool. How though… Well it is quite \nsimple: get to the top floor, 8th floor, and \njump out. There is no better way. \nGood Luck!");
        setSignDialogues(1, "\n \n \n \n \n \n Your first objective is to go through a door and \n enter the first floor.");
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
