package com.scnu.element.map;

/**
 * @file: null.java
 * @author: Yum
 * @date: 2024/7/8
 * @description: 建立地图障碍物 河、墙、树、砖
 */

import com.scnu.game.GetInfo;

import java.awt.*;

public class Obstacle implements GetInfo {

    /*
     *
     *  建立地图上的障碍物
     *  河,墙,树,砖
     *  根据不同的类型附上不同的属性
     * */

    ObstacleKind obstacleKind;

    public enum ObstacleKind {
        RIVER {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Obstacle(x, y, MapData.imageRiver, RIVER);
            }
        }, WALL {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Obstacle(x, y, MapData.imageWall, WALL);
            }
        }, TREE {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Obstacle(x, y, MapData.imageTree, TREE);
            }
        }, BRICK {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Obstacle(x, y, MapData.imageBrick, BRICK);
            }
        }, BASE {
            @Override
            public Obstacle returnObject(int x, int y) {
                return new Obstacle(x, y, MapData.imageBase, BASE);
            }
        };

        public Obstacle returnObject(int x, int y) {
            return null;
        }
    }

    boolean isLive;

    final int x;
    final int y;

    Image image;

    public Obstacle(int x, int y, Image image, ObstacleKind obstacleKind) {
        this.obstacleKind = obstacleKind;
        this.x = x;
        this.y = y;
        this.image = image;
        this.isLive = true;
    }

    public ObstacleKind getKind() {
        return obstacleKind;
    }

    public void setObstacleKind(ObstacleKind obstacleKind) {
        this.obstacleKind = obstacleKind;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
