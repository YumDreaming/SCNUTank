package com.scnu.view;

/**
 * @file: null.java
 * @author: Yum
 * @date: 2024/7/8
 * @description: 游戏贴图
 */


import com.scnu.controller.GameInstance;
import com.scnu.element.map.MapData;
import com.scnu.element.map.Obstacle;
import com.scnu.element.model.Bullet;
import com.scnu.game.GetInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import static com.scnu.element.model.EnemyTank.enemyTank_up;
import static com.scnu.element.model.FastEnemyTank.fastEnemyTank_up;
import static com.scnu.element.model.StrongEnemyTank.StrongEnemyTank_up;
import static com.scnu.game.Application.gameRun;
import static com.scnu.game.Application.tempStop;
import static com.scnu.game.Attributes.*;


public class GamePanel extends JPanel implements Runnable {

    // 生命值贴图
    public static Image lives = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/lives.png"));
    // 子弹数贴图
    public static Image bullet = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/bullet.png"));
    // 生命值限制贴图
    public static Image livesLimit = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/livesLimit.png"));
    // 子弹数限制贴图
    public static Image bulletLimit = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/bulletLimit.png"));
    // 游戏背景贴图
    public static Image floor = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/floor.png"));
    // 游戏介绍图片
    public static JLabel label = new JLabel(new ImageIcon(
            Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/intro.png"))));

    // 唯一游戏实例: 一个 GamePanel 对应一个 GameInstance
    private final GameInstance gameInstance;
    // 唯一应用画框
    private final MainFrame mainFrame;
    // 游戏介绍显示: 一个游戏面板内只能显示一个游戏介绍窗口
    static boolean introShow = false;

    // 帮助按钮
    Button helpButton = new Button("Help");
    // 结束游戏按钮
    Button exitButton = new Button("Exit");
    // 游戏暂停按钮
    Button pauseButton = new Button("Pause");

    public GamePanel(GameInstance gameInstance, MainFrame mainFrame) {

        this.mainFrame = mainFrame;
        this.gameInstance = gameInstance;

        helpButton.setForeground(Color.BLACK);
        helpButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        helpButton.setBackground(Color.WHITE);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 弹出游戏介绍窗口并暂停
                new MyDialogDemo();
                tempStop = true;
            }
        });
        this.add(helpButton);

        exitButton.setForeground(Color.BLACK);
        exitButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        exitButton.setBackground(Color.WHITE);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameRun = false;
            }
        });
        this.add(exitButton);

        pauseButton.setForeground(Color.BLACK);
        pauseButton.setFont(new Font("幼圆", Font.PLAIN, 20));
        pauseButton.setBackground(Color.WHITE);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tempStop == false) {
                    // 如果没有暂停,则暂停,并把按钮文本设置为 run
                    tempStop = true;
                    pauseButton.setLabel("Run");
                    mainFrame.requestFocus();
                } else {
                    // 如果暂停了,则运行,并把按钮文本设置为 pause
                    tempStop = false;
                    pauseButton.setLabel("Pause");
                    mainFrame.requestFocus();
                }
            }
        });
        this.add(pauseButton);
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, WINDOW_LENGTH, WINDOW_WIDTH);

        // 绘制游戏状态栏
        g.setColor(Color.GRAY);
        g.fillRect(WINDOW_LENGTH, 0, 200, WINDOW_WIDTH);

        exitButton.setSize(50, 40);
        exitButton.setLocation(610, 10);
        helpButton.setSize(50, 40);
        helpButton.setLocation(670, 10);
        pauseButton.setSize(60, 40);
        pauseButton.setLocation(730, 10);

        g.setColor(Color.WHITE);
        g.setFont(new Font("幼圆", Font.BOLD, 20));
        g.drawString("剩余敌人:", 630, 100);

        // 剩余敌人统计
        int[] counts = gameInstance.countKind();
        g.drawImage(enemyTank_up, 630, 120, 40, 40, this);
        g.drawString("x " + counts[0], 690, 150);
        g.drawImage(fastEnemyTank_up, 630, 180, 40, 40, this);
        g.drawString("x " + counts[1], 690, 210);
        g.drawImage(StrongEnemyTank_up, 630, 240, 40, 40, this);
        g.drawString("x " + counts[2], 690, 270);

        g.drawString("剩余生命:", 630, 320);

        g.drawString("难度: " + GameInstance.difficulty, 630, 540);
        g.drawString("得分: " + gameInstance.calculateScore(), 630, 580);

        // 如果我方坦克未加载完,先不执行下面的绘制
        if (gameInstance.getMyTank() == null) {
            return;
        }

        synchronized (gameInstance.getMyTank()) {

            for (int i = 0; i < gameInstance.getMyTank().getHpLimit(); i++) {
                if (i < 5) {
                    g.drawImage(livesLimit, 630 + i * 30, 340, 25, 20, this);
                } else {
                    g.drawImage(livesLimit, 630 + (i - 5) * 30, 370, 25, 20, this);
                }

            }
            for (int i = 0; i < gameInstance.getMyTank().getHp(); i++) {
                if (i < 5) {
                    g.drawImage(lives, 630 + i * 30, 340, 25, 20, this);
                } else {
                    g.drawImage(lives, 630 + (i - 5) * 30, 370, 25, 20, this);
                }
            }

            g.drawString("剩余弹药:", 630, 420);
            for (int i = 0; i < gameInstance.getMyTank().getBulletNumLimit(); i++) {
                if (i < 10) {
                    g.drawImage(bulletLimit, 630 + i * 15, 440, 10, 25, this);
                } else {
                    g.drawImage(bulletLimit, 630 + (i - 10) * 15, 470, 10, 25, this);
                }
            }
            for (int i = 0; i < gameInstance.getMyTank().getBulletNum(); i++) {
                if (i < 10) {
                    g.drawImage(bullet, 630 + i * 15, 440, 10, 25, this);
                } else {
                    g.drawImage(bullet, 630 + (i - 10) * 15, 470, 10, 25, this);
                }
            }
        }


        for (int i = 0; i < MapData.dotsLength / 2 + 1; i++) {
            for (int j = 0; j < MapData.dotsWidth / 2 + 1; j++) {
                g.drawImage(floor, i * 80 - 40, j * 80 - 40, 80, 80, this);
            }
        }

        // 绘制游戏内容
        // 先画底层的水
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.RIVER));
        // 画 buff
        drawObjects(g, gameInstance.getBuffList());
        // 绘制坦克
        drawObjects(g, gameInstance.getEnemyTanksList());
        drawObjects(g, gameInstance.getMyTank());
        // 绘制子弹
        drawObjects(g, gameInstance.getAllBulletList());
        // 绘制地图其他障碍物
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.WALL));
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.TREE));
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.BRICK));
        drawObjects(g, gameInstance.getObstacleMap().get(Obstacle.ObstacleKind.BASE));

        // 绘制滚动信息栏
        g.setColor(Color.WHITE);
        g.setFont(new Font("幼圆", Font.BOLD, 15));
        if (gameInstance.getInfoMap().isEmpty()) {
            return;
        }
        int size = gameInstance.getInfoMap().size();
        Integer[] times = gameInstance.getInfoMap().keySet().toArray(new Integer[0]);
        for (int i = 0; i < size; i++) {
            String str = gameInstance.getInfoMap().get(times[i]);
            g.drawString(str, 300 - str.length()*5, 30 + i * 20);
        }


    }

    private <T extends GetInfo> void drawObjects(Graphics g, Vector<T> list) {
        // 集成绘制游戏物体方法,通过 GetInfo 接口获取位置与贴图
        int size;
        if (list.isEmpty()) {
            return;
        }
        if (list.get(0) instanceof Bullet) {
            size = BULLET_SIZE;
        } else {
            size = OBJECT_SIZE;
        }
        synchronized (list) {
            for (T t : list) {
                g.drawImage(t.getImage(), t.getX(), t.getY(), size, size, this);
            }
        }
    }

    private <T extends GetInfo> void drawObjects(Graphics g, T t) {
        int size = OBJECT_SIZE;
        g.drawImage(t.getImage(), t.getX(), t.getY(), size, size, this);
    }

    public GameInstance getGameInstance() {
        return gameInstance;
    }

    @Override
    public void run() {
        // 刷新游戏面板线程
        while (gameRun) {
            try {
                // 线程休息,控制刷新率
                Thread.sleep(REFRESH_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            repaint();
        }
    }

    public class MyDialogDemo extends JDialog {

        public MyDialogDemo() {
            if (introShow) {
                return;
            }

            introShow = true;
            tempStop = true;
            this.setVisible(true);
            this.setBounds(800, 0, 920, 550);
            this.setTitle("游戏介绍 (显示时游戏暂停)");

            Container container = this.getContentPane();

            label.setSize(900, 500);
            container.add(label);
            // 设置图片在中心
            label.setHorizontalAlignment(SwingConstants.CENTER);

            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // 关闭窗口,结束暂停
                    tempStop = false;
                    mainFrame.requestFocus();
                    introShow = false;
                }
            });
        }
    }
}

