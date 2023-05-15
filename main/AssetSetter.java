package main;

import entity.Homework1;
import entity.NPC_Dylan;
import entity.NPC_SmartGuy;
import entity.Sign;
import object.OBJ_Crack;
import object.OBJ_Door;
import object.OBJ_EastDoor;
import object.OBJ_Key;
import object.OBJ_SpeedPotion;
import object.OBJ_Puddle;
import object.OBJ_VictoryDoor;
import tile.TileManager;
import object.OBJ_HoleCoverCrack;

public class AssetSetter {
    GamePanel gp;
    //TileManager tm = new TileManager(gp);

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // for basement
    public void setObject() {
        gp.getObj()[0] = new OBJ_Key(gp);
        gp.getObj()[0].worldX = 17 * gp.getTileSize();
        gp.getObj()[0].worldY = 13 * gp.getTileSize();

        // north
        gp.getObj()[1] = new OBJ_Door(gp);
        gp.getObj()[1].worldX = 25 * gp.getTileSize();
        gp.getObj()[1].worldY = 7 * gp.getTileSize() + 50;

        // south
        gp.getObj()[2] = new OBJ_Door(gp);
        gp.getObj()[2].worldX = 25 * gp.getTileSize();
        gp.getObj()[2].worldY = 40 * gp.getTileSize() + 20;

        // east
        gp.getObj()[3] = new OBJ_EastDoor(gp);
        gp.getObj()[3].worldX = 39 * gp.getTileSize() + 20;
        gp.getObj()[3].worldY = 23 * gp.getTileSize();

        // west
        gp.getObj()[4] = new OBJ_VictoryDoor(gp);
        gp.getObj()[4].worldX = 9 * gp.getTileSize() + 20;
        gp.getObj()[4].worldY = 23 * gp.getTileSize();

        /*
         * parts of the map
         * X: 9-38
         * 29 tiles wide
         * y: 7-40
         * 33 tiles wide
         */

        gp.getObj()[5] = new OBJ_SpeedPotion(gp);
        gp.getObj()[5].worldX = 12 * gp.getTileSize();
        gp.getObj()[5].worldY = 10 * gp.getTileSize();

        gp.getObj()[6] = new OBJ_Puddle(gp);
        gp.getObj()[6].worldX = 13 * gp.getTileSize();
        gp.getObj()[6].worldY = 10 * gp.getTileSize();

        gp.getObj()[7] = new OBJ_Puddle(gp);
        gp.getObj()[7].worldX = 22 * gp.getTileSize();
        gp.getObj()[7].worldY = 32 * gp.getTileSize();

        gp.getObj()[8] = new OBJ_Puddle(gp);
        gp.getObj()[8].worldX = 12 * gp.getTileSize();
        gp.getObj()[8].worldY = 30 * gp.getTileSize();

        gp.getObj()[9] = new OBJ_SpeedPotion(gp);
        gp.getObj()[9].worldX = 25 * gp.getTileSize();
        gp.getObj()[9].worldY = 25 * gp.getTileSize();

        gp.getObj()[10] = new OBJ_SpeedPotion(gp);
        gp.getObj()[10].worldX = 12 * gp.getTileSize();
        gp.getObj()[10].worldY = 13 * gp.getTileSize();

        gp.getObj()[11] = new OBJ_SpeedPotion(gp);
        gp.getObj()[11].worldX = 32 * gp.getTileSize();
        gp.getObj()[11].worldY = 37 * gp.getTileSize();

        gp.getObj()[12] = new OBJ_Puddle(gp);
        gp.getObj()[12].worldX = 26 * gp.getTileSize();
        gp.getObj()[12].worldY = 30 * gp.getTileSize();

        gp.getObj()[13] = new OBJ_Puddle(gp);
        gp.getObj()[13].worldX = 16 * gp.getTileSize();
        gp.getObj()[13].worldY = 36 * gp.getTileSize();

        gp.getObj()[14] = new OBJ_Puddle(gp);
        gp.getObj()[14].worldX = 27 * gp.getTileSize();
        gp.getObj()[14].worldY = 23 * gp.getTileSize();

        gp.getObj()[15] = new OBJ_Puddle(gp);
        gp.getObj()[15].worldX = 14 * gp.getTileSize();
        gp.getObj()[15].worldY = 38 * gp.getTileSize();

        gp.getObj()[16] = new OBJ_Puddle(gp);
        gp.getObj()[16].worldX = 37 * gp.getTileSize();
        gp.getObj()[16].worldY = 14 * gp.getTileSize();

        gp.getObj()[17] = new OBJ_Puddle(gp);
        gp.getObj()[17].worldX = 35 * gp.getTileSize();
        gp.getObj()[17].worldY = 9 * gp.getTileSize();

        gp.getObj()[18] = new OBJ_SpeedPotion(gp);
        gp.getObj()[18].worldX = 30 * gp.getTileSize();
        gp.getObj()[18].worldY = 12 * gp.getTileSize();

        gp.getObj()[19] = new OBJ_SpeedPotion(gp);
        gp.getObj()[19].worldX = 25 * gp.getTileSize();
        gp.getObj()[19].worldY = 25 * gp.getTileSize();

        gp.getObj()[20] = new OBJ_Puddle(gp);
        gp.getObj()[20].worldX = 37 * gp.getTileSize();
        gp.getObj()[20].worldY = 27 * gp.getTileSize();

        gp.getObj()[21] = new OBJ_Puddle(gp);
        gp.getObj()[21].worldX = 35 * gp.getTileSize();
        gp.getObj()[21].worldY = 37 * gp.getTileSize();

        gp.getObj()[22] = new OBJ_Puddle(gp);
        gp.getObj()[22].worldX = 36 * gp.getTileSize();
        gp.getObj()[22].worldY = 37 * gp.getTileSize();

        gp.getObj()[23] = new OBJ_Puddle(gp);
        gp.getObj()[23].worldX = 36 * gp.getTileSize();
        gp.getObj()[23].worldY = 30 * gp.getTileSize();

        gp.getObj()[24] = new OBJ_Puddle(gp);
        gp.getObj()[24].worldX = 26 * gp.getTileSize();
        gp.getObj()[24].worldY = 36 * gp.getTileSize();

        gp.getObj()[25] = new OBJ_Puddle(gp);
        gp.getObj()[25].worldX = 22 * gp.getTileSize();
        gp.getObj()[25].worldY = 11 * gp.getTileSize();

        gp.getObj()[26] = new OBJ_Puddle(gp);
        gp.getObj()[26].worldX = 27 * gp.getTileSize();
        gp.getObj()[26].worldY = 12 * gp.getTileSize();

        gp.getObj()[27] = new OBJ_Puddle(gp);
        gp.getObj()[27].worldX = 22 * gp.getTileSize();
        gp.getObj()[27].worldY = 14 * gp.getTileSize();

        gp.getObj()[28] = new OBJ_Key(gp);
        gp.getObj()[28].worldX = 34 * gp.getTileSize();
        gp.getObj()[28].worldY = 12 * gp.getTileSize();

        gp.getObj()[29] = new OBJ_Key(gp);
        gp.getObj()[29].worldX = 38 * gp.getTileSize();
        gp.getObj()[29].worldY = 33 * gp.getTileSize();

        gp.getObj()[30] = new OBJ_Key(gp);
        gp.getObj()[30].worldX = 12 * gp.getTileSize();
        gp.getObj()[30].worldY = 38 * gp.getTileSize();

        gp.getObj()[31] = new OBJ_Crack(gp);
        gp.getObj()[31].worldX = 12 * gp.getTileSize();
        gp.getObj()[31].worldY = 37 * gp.getTileSize();

        gp.getObj()[32] = new OBJ_HoleCoverCrack(gp);
        gp.getObj()[32].worldX = 12 * gp.getTileSize();
        gp.getObj()[32].worldY = 37 * gp.getTileSize();
    }

    public void setNPC() {
        gp.getNpc()[0] = new NPC_Dylan(gp);
        gp.getNpc()[0].setWorldX(gp.getTileSize() * 26);
        gp.getNpc()[0].setWorldY(gp.getTileSize() * 18);

        gp.getNpc()[1] = new Sign(gp);
        gp.getNpc()[1].setWorldX(gp.getTileSize() * 23);
        gp.getNpc()[1].setWorldY(gp.getTileSize() * 23);

        gp.getNpc()[2] = new NPC_SmartGuy(gp);
        gp.getNpc()[2].setWorldX(gp.getTileSize() * 23);
        gp.getNpc()[2].setWorldY(gp.getTileSize() * 31);

        gp.getNpc()[3] = new Homework1(gp);
        gp.getNpc()[3].setWorldX(35 * gp.getTileSize());
        gp.getNpc()[3].setWorldY(10 * gp.getTileSize());

    }
}
