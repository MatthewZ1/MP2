package main;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen Setting
    private final int originalTileSize = 16;
    private final int scale = 3;
    /*
     * title size is the size that appears on the screen
     * we have "originalTileSize" to scale better and to draw the thingy
     */
    private final int tileSize = originalTileSize * scale;
    // screen size is 20 by 16 tile
    private final int maxScreenCol = 20;
    private final int maxScreenRow = 16;
    // each tile is 48 pixels and we have 20 * 16 tiles
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;
    // results in a 800 * 768 pixel screen
    // world view
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;
    private final int worldWidth = tileSize * maxScreenCol;
    private final int worldHeight = tileSize * maxScreenRow;

    // for transition between maps
    private final int maxMap = 10;
    private int currentMap = 0;

    KeyHandler keyH = new KeyHandler(this);

    // keeps program running till you stop it
    Thread gameThread;
    // instantiates player
    private Player player = new Player(this, keyH);
    private Entity npc[] = new Entity[100];

    // instantiates tile manager
    private TileManager tileM = new TileManager(this);
    // instantiates collision checker
    private CollisionChecker cChecker = new CollisionChecker(this);
    // instantiates objects and AssetSetter
    private AssetSetter aSetter = new AssetSetter(this);
    // shows maximum of 10 objects at a time on the screen
    private SuperObject obj[] = new SuperObject[100];
    // instantiates UI Class
    private UI ui = new UI(this);

    // GAME STATE
    private int gameState;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int dialogueState = 3;
    // FPS
    private int FPS = 60;
    public int getCurrentMap() {
		return currentMap;
	}

    public void setCurrentMap(int currentMap) {
		this.currentMap = currentMap;
	}

    public KeyHandler getKeyH() {
		return keyH;
	}

    public void setKeyH(KeyHandler keyH) {
		this.keyH = keyH;
	}

    public Thread getGameThread() {
		return gameThread;
	}

    public void setGameThread(Thread gameThread) {
		this.gameThread = gameThread;
	}

    public Player getPlayer() {
		return player;
	}

    public void setPlayer(Player player) {
		this.player = player;
	}

    public Entity[] getNpc() {
		return npc;
	}

    public void setNpc(Entity[] npc) {
		this.npc = npc;
	}

    public TileManager getTileM() {
		return tileM;
	}

    public void setTileM(TileManager tileM) {
		this.tileM = tileM;
	}

    public CollisionChecker getcChecker() {
		return cChecker;
	}

    public void setcChecker(CollisionChecker cChecker) {
		this.cChecker = cChecker;
	}

    public AssetSetter getaSetter() {
		return aSetter;
	}

    public void setaSetter(AssetSetter aSetter) {
		this.aSetter = aSetter;
	}

    public SuperObject[] getObj() {
		return obj;
	}

    public void setObj(SuperObject[] obj) {
		this.obj = obj;
	}

    public UI getUi() {
		return ui;
	}

    public void setUi(UI ui) {
		this.ui = ui;
	}

    public int getGameState() {
		return gameState;
	}

    public void setGameState(int gameState) {
		this.gameState = gameState;
	}

    public int getFPS() {
		return FPS;
	}

    public void setFPS(int fPS) {
		FPS = fPS;
	}

    public int getTouched() {
		return touched;
	}

    public void setTouched(int touched) {
		this.touched = touched;
	}

    public int getOriginalTileSize() {
		return originalTileSize;
	}

    public int getScale() {
		return scale;
	}

    public int getTileSize() {
		return tileSize;
	}

    public int getMaxScreenCol() {
		return maxScreenCol;
	}

    public int getMaxScreenRow() {
		return maxScreenRow;
	}

    public int getScreenWidth() {
		return screenWidth;
	}

    public int getScreenHeight() {
		return screenHeight;
	}

    public int getMaxWorldCol() {
		return maxWorldCol;
	}

    public int getMaxWorldRow() {
		return maxWorldRow;
	}

    public int getWorldWidth() {
		return worldWidth;
	}

    public int getWorldHeight() {
		return worldHeight;
	}

    public int getMaxMap() {
		return maxMap;
	}

    public int getPlayState() {
		return playState;
	}

    public int getPauseState() {
		return pauseState;
	}

    public int getDialogueState() {
		return dialogueState;
	}

	private int touched = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setGame() {
        aSetter.setObject();
        aSetter.setNPC();
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // call run method
    }

    @Override
    // when gameThread starts, this method will run
    public void run() {
        // for FPS draws screen 60 times per second
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        // game loop/ core of the game
        while (gameThread != null) {
            /*
             * goal is to update information
             * update character position
             * update screen location
             * ex: character moves 10 pixels right, screen and character both move same
             * amount
             */

            // update character position
            update();
            // update screen
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                // defensive coding
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                // pause game loop
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (player.getLife() == 0) {
                ui.showMessage("You lost!");
                touched++;
                if (touched > 1) {
                    gameThread = null;
                }
            }
        }
    }

    // These 2 methods used in run and while loop to update screen
    public void update() {
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            // nothing
        }
    }

    //
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // set the parameter g to g2
        Graphics2D g2 = (Graphics2D) g;
        // draws tiles, then objects, then the player
        tileM.draw(g2);

        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                int relativeX = obj[i].worldX - player.getWorldX();
                int relativeY = obj[i].worldY - player.getWorldY();
                obj[i].draw(g2, this, relativeX + (int) (this.getWidth() / 2),
                        relativeY + (int) (this.getHeight() / 2));
            }
        }

        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                int npcRelativeX = npc[i].getWorldX() - player.getWorldX();
                int npcRelativeY = npc[i].getWorldY() - player.getWorldY();
                npc[i].draw(g2, this, npcRelativeX + (int) (this.getWidth() / 2),
                        npcRelativeY + (int) (this.getHeight() / 2));
            }
        }

        player.draw(g2, (int) (this.getWidth() / 2), (int) (this.getHeight() / 2));

        // UI
        ui.draw(g2);

        g2.dispose(); // no clue what it does; ep 2 time 7:56
    }
}
