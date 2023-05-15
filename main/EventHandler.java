package main;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];
    private BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;

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

	public int getPreviousEventX() {
		return previousEventX;
	}

	public void setPreviousEventX(int previousEventX) {
		this.previousEventX = previousEventX;
	}

	public int getPreviousEventY() {
		return previousEventY;
	}

	public void setPreviousEventY(int previousEventY) {
		this.previousEventY = previousEventY;
	}

	public boolean isCanTouchEvent() {
		return canTouchEvent;
	}

	public void setCanTouchEvent(boolean canTouchEvent) {
		this.canTouchEvent = canTouchEvent;
	}

	int previousEventX, previousEventY;
    public boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.getMaxScreenCol()][gp.getMaxScreenRow()];

        int col = 0;
        int row = 0;
        while (col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 12;
            eventRect[col][row].y = 38;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].setEventRectDefaultX(eventRect[col][row].x);
            eventRect[col][row].setEventRectDefaultY(eventRect[col][row].y);

            col++;
            if (col == gp.getMaxScreenCol()) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {
        // Check if player is more than 1 tile away from last event
        int xDistance = Math.abs(gp.getPlayer().getWorldX() - previousEventX);
        int yDistance = Math.abs(gp.getPlayer().getWorldY() - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.getTileSize()) {
            canTouchEvent = true;
        }
        // have to be walking right to trigger it
        // if(canTouchEvent){
        // if(hit(12, 37, "right")){
        // crack(gp.dialogueState);
        // }
        // // if(hit(8, 2, "right") || hit(8, 2, "left")){
        // // fallingTile(gp.dialogueState);
        // // }
        // }
    }

    public boolean hit(int col, int row, String requiredDirection) {
        boolean hit = false;
        gp.getPlayer().getSolidArea().x = gp.getPlayer().getWorldX() + gp.getPlayer().getSolidArea().x;
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getWorldY() + gp.getPlayer().getSolidArea().y;
        eventRect[col][row].x = col * gp.getTileSize() + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.getTileSize() + eventRect[col][row].y;

        if (gp.getPlayer().getSolidArea().intersects(eventRect[col][row]) && (eventRect[col][row].isEventDone() == false)) {
            if (gp.getPlayer().getDirection().contentEquals(requiredDirection) || requiredDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.getPlayer().getWorldX();
                previousEventY = gp.getPlayer().getWorldY();
            }
        }
        gp.getPlayer().getSolidArea().x = gp.getPlayer().getSolidAreaDefaultX();
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getSolidAreaDefaultY();
        eventRect[col][row].x = eventRect[col][row].getEventRectDefaultX();
        eventRect[col][row].y = eventRect[col][row].getEventRectDefaultY();

        return hit;
    }
}