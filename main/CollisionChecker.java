package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        // makes the box for the sprite to check for collisions
        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gp.getTileSize();
        int entityRightCol = entityRightWorldX / gp.getTileSize();
        int entityTopRow = entityTopWorldY / gp.getTileSize();
        int entityBottomRow = entityBottomWorldY / gp.getTileSize();

        // only need to check 2 points at a time
        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "up":
                // trying to somewhat predict where the player will be before player hits block
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[gp.getCurrentMap()][entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[gp.getCurrentMap()][entityRightCol][entityTopRow];
                if (gp.getTileM().getTile()[tileNum1].isCollision() == true || gp.getTileM().getTile()[tileNum2].isCollision() == true) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[gp.getCurrentMap()][entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileM().getMapTileNum()[gp.getCurrentMap()][entityRightCol][entityBottomRow];
                if (gp.getTileM().getTile()[tileNum1].isCollision() == true || gp.getTileM().getTile()[tileNum2].isCollision() == true) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[gp.getCurrentMap()][entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[gp.getCurrentMap()][entityLeftCol][entityBottomRow];
                if (gp.getTileM().getTile()[tileNum1].isCollision() == true || gp.getTileM().getTile()[tileNum2].isCollision() == true) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[gp.getCurrentMap()][entityRightCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[gp.getCurrentMap()][entityRightCol][entityBottomRow];
                if (gp.getTileM().getTile()[tileNum1].isCollision() == true || gp.getTileM().getTile()[tileNum2].isCollision() == true) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }

    // makes boolean to make sure the entity is a player
    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.getObj().length; i++) {
            if (gp.getObj()[i] != null) {
                // get identity of solid object
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                // get position of object
                gp.getObj()[i].solidArea.x = gp.getObj()[i].getWorldX() + gp.getObj()[i].solidArea.x + 25;
                gp.getObj()[i].solidArea.y = gp.getObj()[i].getWorldY() + gp.getObj()[i].solidArea.y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObj()[i].solidArea)) {
                            // checks if the object is solid or not
                            if (gp.getObj()[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            // checks if collision is of a player and returns index if it is a player
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObj()[i].solidArea)) {
                            // checks if the object is solid or not
                            if (gp.getObj()[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            // checks if collision is of a player and returns index if it is a player
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObj()[i].solidArea)) {
                            // checks if the object is solid or not
                            if (gp.getObj()[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            // checks if collision is of a player and returns index if it is a player
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if (entity.getSolidArea().intersects(gp.getObj()[i].solidArea)) {
                            // checks if the object is solid or not
                            if (gp.getObj()[i].collision) {
                                entity.setCollisionOn(true);
                            }
                            // checks if collision is of a player and returns index if it is a player
                            if (player) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                gp.getObj()[i].solidArea.x = gp.getObj()[i].solidAreaDefaultX;
                gp.getObj()[i].solidArea.y = gp.getObj()[i].solidAreaDefaultY;
            }
        }
        // we check if the player is colliding with an object and if so
        // we return the index of the object that corresponds to object
        return index;
    }

    // NPC OR MONSTER COLLISION
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                // get identity of solid object
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                // get position of object
                target[i].getSolidArea().x = target[i].getWorldX() + target[i].getSolidArea().x + 20;
                target[i].getSolidArea().y = target[i].getWorldY() + target[i].getSolidArea().y + 20;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                target[i].getSolidArea().x = target[i].getSolidAreaDefaultX();
                target[i].getSolidArea().y = target[i].getSolidAreaDefaultY();
            }
        }
        // we check if the player is colliding with an object and if so
        // we return the index of the object that corresponds to object
        return index;
    }

    public void checkPlayer(Entity entity) {
        // get identity of solid object
        entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
        entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
        // get position of object
        gp.getPlayer().getSolidArea().x = gp.getPlayer().getWorldX() + gp.getPlayer().getSolidArea().x;
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getWorldY() + gp.getPlayer().getSolidArea().y;

        switch (entity.getDirection()) {
            case "up":
                entity.getSolidArea().y -= entity.getSpeed();
                if (entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entity.getSolidArea().y += entity.getSpeed();
                if (entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entity.getSolidArea().x -= entity.getSpeed();
                if (entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entity.getSolidArea().x += entity.getSpeed();
                if (entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
        }
        entity.getSolidArea().x = entity.getSolidAreaDefaultX();
        entity.getSolidArea().y = entity.getSolidAreaDefaultY();
        gp.getPlayer().getSolidArea().x = gp.getPlayer().getSolidAreaDefaultX();
        gp.getPlayer().getSolidArea().y = gp.getPlayer().getSolidAreaDefaultY();
    }
}
