package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    public boolean isUpPressed() {
		return upPressed;
	}

	public void setUpPressed(boolean upPressed) {
		this.upPressed = upPressed;
	}

	public boolean isDownPressed() {
		return downPressed;
	}

	public void setDownPressed(boolean downPressed) {
		this.downPressed = downPressed;
	}

	public boolean isLeftPressed() {
		return leftPressed;
	}

	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used?
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // number associated with key typed and puts number in "code"
        int code = e.getKeyCode();

        if (gp.getGameState() == gp.getPlayState()) {
            // controls for sprite
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P) {
                gp.setGameState(gp.getPauseState());
            }
            if (code == KeyEvent.VK_Y) {
                switch (gp.getCurrentMap()) {
                    case 0:
                        gp.getTileM().loadMap("/maps/world.txt", 0);
                        break;
                    case 1:
                        gp.getTileM().loadMap("/maps/battle.txt", 1);
                        break;
                }
            }
        }
        // Pause State
        else if (gp.getGameState() == gp.getPauseState()) {
            if (code == KeyEvent.VK_P) {
                gp.setGameState(gp.getPlayState());
            }

        }
        // Dialogue State
        else if (gp.getGameState() == gp.getDialogueState()) {
            if (code == KeyEvent.VK_ENTER) {
                gp.setGameState(gp.getPlayState());
            }
            if (code == KeyEvent.VK_1) {
                gp.setGameState(gp.getPlayState());
                gp.getPlayer().setLife(gp.getPlayer().getLife() - 1);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // controls for sprite
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

}
