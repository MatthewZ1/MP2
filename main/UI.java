package main;

import java.awt.image.BufferedImage;

import object.OBJ_Heart;
import object.OBJ_Key;
import object.SuperObject;

import java.awt.*;

public class UI {
    GamePanel gp;
    private Graphics2D g2;
    private BufferedImage heart_full, heart_half, heart_dead;
    private Font arial_40, arial_80b;
    private BufferedImage keyImage;
    private boolean messageOn = false;
    private String message = "";
    private int messageTimer = 0;
    private int finished;
    private String currentDialogue = "";

    public GamePanel getGp() {
		return gp;
	}

	public void setGp(GamePanel gp) {
		this.gp = gp;
	}

	public Graphics2D getG2() {
		return g2;
	}

	public void setG2(Graphics2D g2) {
		this.g2 = g2;
	}

	public BufferedImage getHeart_full() {
		return heart_full;
	}

	public void setHeart_full(BufferedImage heart_full) {
		this.heart_full = heart_full;
	}

	public BufferedImage getHeart_half() {
		return heart_half;
	}

	public void setHeart_half(BufferedImage heart_half) {
		this.heart_half = heart_half;
	}

	public BufferedImage getHeart_dead() {
		return heart_dead;
	}

	public void setHeart_dead(BufferedImage heart_dead) {
		this.heart_dead = heart_dead;
	}

	public Font getArial_40() {
		return arial_40;
	}

	public void setArial_40(Font arial_40) {
		this.arial_40 = arial_40;
	}

	public Font getArial_80b() {
		return arial_80b;
	}

	public void setArial_80b(Font arial_80b) {
		this.arial_80b = arial_80b;
	}

	public BufferedImage getKeyImage() {
		return keyImage;
	}

	public void setKeyImage(BufferedImage keyImage) {
		this.keyImage = keyImage;
	}

	public boolean isMessageOn() {
		return messageOn;
	}

	public void setMessageOn(boolean messageOn) {
		this.messageOn = messageOn;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMessageTimer() {
		return messageTimer;
	}

	public void setMessageTimer(int messageTimer) {
		this.messageTimer = messageTimer;
	}

	public int getFinished() {
		return finished;
	}

	public void setFinished(int finished) {
		this.finished = finished;
	}

	public String getCurrentDialogue() {
		return currentDialogue;
	}

	public void setCurrentDialogue(String currentDialogue) {
		this.currentDialogue = currentDialogue;
	}
    public UI getUI(){
        return this;
    }

	public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80b = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;

        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_dead = heart.image3;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        if (finished == 1) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x = gp.getScreenWidth() / 2;
            int y = gp.getScreenHeight() / 2;

            text = "You arrived at Stuy suddenly... To be continued.";
            // length of text
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            // alligns text to center
            x = gp.getScreenWidth() / 2 - textLength / 2;
            y = gp.getScreenHeight() / 2 - (gp.getTileSize() * 3);
            g2.drawString(text, x, y);

            g2.setFont(arial_80b);
            g2.setColor(Color.white);

            text = "You passed";
            // length of text
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            // alligns text to center
            x = gp.getScreenWidth() / 2 - textLength / 2;
            y = gp.getScreenHeight() / 2 + (gp.getTileSize() * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        } else if (finished == 2) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x = gp.getScreenWidth() / 2;
            int y = gp.getScreenHeight() / 2;

            text = "You FAILED";
            // length of text
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            // alligns text to center
            x = gp.getScreenWidth() / 2 - textLength / 2;
            y = gp.getScreenHeight() / 2 - (gp.getTileSize() * 3);
            g2.drawString(text, x, y);

            g2.setFont(arial_80b);
            g2.setColor(Color.white);

            text = "NO POGGERS";
            // length of text
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            // alligns text to center
            x = gp.getScreenWidth() / 2 - textLength / 2;
            y = gp.getScreenHeight() / 2 + (gp.getTileSize() * 2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.getTileSize() / 2, gp.getTileSize() / 2, gp.getTileSize(), gp.getTileSize(), null);
            g2.drawString("x " + gp.getPlayer().getKeys(), 85, 60);

            // message displayed
            if (messageOn) {
                g2.drawString(message, gp.getTileSize() / 2, gp.getTileSize() * 5);
                messageTimer++;

                if (messageTimer > 120) {
                    messageTimer = 0;
                    messageOn = false;
                }
            }
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            if (gp.getGameState() == gp.getPlayState()) {
                drawPlayerLife();
            }
            // Pause State
            if (gp.getGameState() == gp.getPauseState()) {
                drawPlayerLife();
                drawPauseScreen();
            }
            // Dialogue State
            if (gp.getGameState() == gp.getDialogueState()) {
                drawPlayerLife();
                drawDialogueScreen();
            }
        }
    }

    public void drawPlayerLife() {

        int x = gp.getTileSize() * 15 - 20;
        int y = gp.getTileSize() / 2 - 25;
        int i = 0;
        // Draw Max Life
        while (i < gp.getPlayer().getMaxLife() / 2) {
            g2.drawImage(heart_dead, x, y, null);
            i++;
            x += gp.getTileSize();
        }
        // Reset
        x = gp.getTileSize() * 15 - 20;
        y = gp.getTileSize() / 2 - 25;
        i = 0;

        // Draw Current Life
        while (i < gp.getPlayer().getLife()) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.getPlayer().getLife()) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.getTileSize();
        }
    }

    public void drawPauseScreen() {
        // Display text at center of screen
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.getScreenHeight() / 2;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // Parameters for Window (Can put regular numbers)
        int x = gp.getTileSize() * 2;
        int y = gp.getTileSize() / 2;
        int width = gp.getScreenWidth() - (gp.getTileSize() * 4);
        int height = gp.getTileSize() * 15;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.getTileSize();
        y += gp.getTileSize();

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;

        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color color = new Color(255, 255, 255, 210); // RGB Values
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 25, 25); // 35, 35

        color = new Color(0, 0, 0);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 15, 15);

    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.getScreenWidth() / 2 - length / 2;
        return x;
    }
}
