package com.scnu.element.map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.scnu.game.Attributes.*;

/**
 * @file: null.java
 * @author: Yum
 * @date: 2024/7/8
 * @description: 加载地图贴图 二维数组列表存储地图
 */



public class MapData {

    /*
     *
     *  加载地图贴图
     *  二位数组列表存储地图
     * */

    public static int dotsLength = WINDOW_LENGTH / OBJECT_SIZE;

    public static int dotsWidth = WINDOW_WIDTH / OBJECT_SIZE;

    static Image imageRiver = Toolkit.getDefaultToolkit().getImage(
            Panel.class.getResource("/img/River.png"));
    static Image imageBrick = Toolkit.getDefaultToolkit().getImage(
            Panel.class.getResource("/img/Brick.png"));
    static Image imageTree = Toolkit.getDefaultToolkit().getImage(
            Panel.class.getResource("/img/Tree.png"));
    static Image imageWall = Toolkit.getDefaultToolkit().getImage(
            Panel.class.getResource("/img/Wall.png"));
    static Image imageBase = Toolkit.getDefaultToolkit().getImage(
            Panel.class.getResource("/img/Base.png"));

    public static Map<Obstacle.ObstacleKind, ArrayList<int[]>> map_1;

    public static Map<Obstacle.ObstacleKind, ArrayList<int[]>> map_2;

    public static Map<Obstacle.ObstacleKind, ArrayList<int[]>> map_3;

    static {
        // RIVER, WALL, TREE, BRICK, BASE
        // 从初始化时使用 二维数组 是因为好写, 可以直接用 python(dotAdder.py)(java写的好慢) 输出复制过来

        map_1 = new HashMap<>();

        map_1.put(Obstacle.ObstacleKind.RIVER, new ArrayList<>(Arrays.asList( // River
                new int[][]{}
        )));
        map_1.put(Obstacle.ObstacleKind.WALL, new ArrayList<>(Arrays.asList( // Wall
                new int[][]{{2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {5, 1}, {5, 2}, {5, 3}, {5, 4}, {5, 5}, {5, 6},
                        {4, 3}, {4, 4}, {9, 1}, {9, 3}, {9, 4}, {9, 5}, {10, 6}, {11, 6}, {12, 6}, {13, 1}, {13, 2}, {13, 3},
                        {13, 4}, {3, 8}, {4, 8}, {2, 9}, {2, 10}, {3, 10}, {3, 11}, {5, 11}, {5, 12}, {2, 13}, {3, 13}, {4, 13},
                        {9, 8}, {10, 8}, {11, 8}, {12, 8}, {11, 10}, {11, 11}, {11, 12}, {11, 13}
                }
        )));
        map_1.put(Obstacle.ObstacleKind.TREE, new ArrayList<>(Arrays.asList( // Tree
                new int[][]{
                        {6, 2}, {6, 3}, {6, 4}, {7, 2}, {7, 3}, {7, 4}, {6, 10}, {7, 10}, {7, 11}, {8, 10}, {8, 11}, {9, 10}, {9, 11},
                        {0, 0}, {0, 1}, {1, 0}, {13, 0}, {14, 0}, {14, 1}, {0, 13}, {0, 14}, {1, 14}, {13, 14}, {14, 13}, {14, 14}
                }
        )));
        map_1.put(Obstacle.ObstacleKind.BRICK, new ArrayList<>(Arrays.asList( // Brick
                new int[][]{
                        {0, 4}, {0, 5}, {1, 10}, {1, 9}, {11, 1}, {12, 1}, {10, 3}, {11, 3}, {7, 5}, {7, 6}, {7, 9},
                        {13, 10},{14, 8},  {14, 10}, {14, 11}, {7, 13}, {7, 14}, {8, 13}, {9, 12}, {9, 13}
                }
        )));
        map_1.put(Obstacle.ObstacleKind.BASE, new ArrayList<>(Arrays.asList( // Base
                new int[][]{{5, 8}, {13, 6}}
        )));


        map_2 = new HashMap<>();

        map_2.put(Obstacle.ObstacleKind.RIVER, new ArrayList<>(Arrays.asList( // River
                new int[][]{}
        )));
        map_2.put(Obstacle.ObstacleKind.WALL, new ArrayList<>(Arrays.asList( // Wall
                new int[][]{{3, 0}, {3, 1}, {3, 7}, {3, 8}, {0, 8}, {7, 6}, {8, 5},
                        {9, 3}, {10, 2}, {12, 4}, {10, 9}}
        )));
        map_2.put(Obstacle.ObstacleKind.TREE, new ArrayList<>(Arrays.asList( // Tree
                new int[][]{{0, 4}, {0, 5}, {1, 5}, {4, 6}, {4, 7}, {5, 6}, {6, 6}, {10, 4}, {10, 5}, {10, 6},
                        {0, 0}, {0, 1}, {1, 0}, {13, 0}, {14, 0}, {14, 1}, {0, 13}, {0, 14}, {1, 14}, {13, 14}, {14, 13}, {14, 14}}
        )));
        map_2.put(Obstacle.ObstacleKind.BRICK, new ArrayList<>(Arrays.asList( // Brick
                new int[][]{{1, 1}, {1, 2}, {3, 3}, {3, 4}, {6, 2}, {7, 1}, {7, 2}, {5, 5}, {9, 1},
                        {9, 2}, {9, 4}, {11, 1}, {11, 2}, {11, 4}, {1, 6}, {2, 6}, {3, 6}, {11, 6},
                        {1, 8}, {1, 9}, {1, 10}, {1, 11}, {1, 12}, {3, 9}, {3, 10}, {3, 12}, {5, 7},
                        {5, 8}, {5, 9}, {5, 10}, {6, 9}, {7, 9}, {7, 10},
                        {9, 7}, {9, 9}, {9, 11}, {9, 12}, {11, 6}, {11, 7}, {11, 8},
                        {11, 9}, {11, 11}, {11, 12}}
        )));
        map_2.put(Obstacle.ObstacleKind.BASE, new ArrayList<>(Arrays.asList( // Base
                new int[][]{{6, 4}, {6, 10}}
        )));


        map_3 = new HashMap<>();

        map_3.put(Obstacle.ObstacleKind.RIVER, new ArrayList<>(Arrays.asList( // River
                new int[][]{}
        )));
        map_3.put(Obstacle.ObstacleKind.WALL, new ArrayList<>(Arrays.asList( // Wall
                new int[][]{
                        {5, 5}, {6, 5}, {5, 6}, {8, 5}, {9, 5}, {9, 6}, {0, 8}, {1, 8}, {1, 10}, {13, 11}, {14, 8},
                        {13, 9}, {14, 9}, {7, 12}, {7, 13}, {7, 14}, {8, 12}, {8, 13}, {8, 14}, {6, 9}, {6, 10},
                        {7, 9}, {8, 9}, {8, 10},
                }
        )));
        map_3.put(Obstacle.ObstacleKind.TREE, new ArrayList<>(Arrays.asList( // Tree
                new int[][]{
                        {0, 0}, {0, 1}, {1, 0}, {13, 0}, {14, 0}, {14, 1}, {0, 13}, {0, 14}, {1, 14},
                        {13, 14}, {14, 13}, {14, 14}, {2, 11}, {3, 11}, {3, 12}, {4, 12}, {5, 12},
                        {12, 11}, {12, 12}, {11, 12}, {11, 13}, {6, 6}, {8, 6}
                }
        )));
        map_3.put(Obstacle.ObstacleKind.BRICK, new ArrayList<>(Arrays.asList( // Brick
                new int[][]{
                        {4, 1}, {4, 2}, {5, 1}, {5, 2}, {7, 2}, {7, 3}, {8, 2}, {8, 3}, {11, 1},
                        {11, 2}, {11, 3}, {12, 1}, {12, 2}, {12, 3}, {13, 1}, {13, 2}, {13, 3},
                        {10, 5}, {10, 6}, {11, 5}, {11, 6}, {12, 5}, {12, 6}, {3, 7}, {3, 8}, {3, 9},
                        {4, 7}, {4, 8}, {4, 9}, {9, 8}, {9, 9}, {10, 8}, {10, 9}, {11, 8}, {11, 9},
                        {2, 3}, {2, 4}, {2, 5}, {3, 5}, {6, 3}, {9, 3}, {6, 8}, {2, 12},
                        {3, 13}, {4, 13}, {10, 12}, {11, 11}
                }
        )));
        map_3.put(Obstacle.ObstacleKind.BASE, new ArrayList<>(Arrays.asList( // Base
                new int[][]{{5, 3}, {7, 10}}
        )));
    }


}
