/**
 * Copyright (c) 2014 Saúl Piña <sauljabin@gmail.com>.
 * <p>
 * This file is part of FuzzyBattle.
 * <p>
 * FuzzyBattle is licensed under The MIT License.
 * For full copyright and license information please see the LICENSE file.
 */

package app.animation.animated;

import app.Config;
import app.animation.Animated;
import app.util.ClipLoader;

import javax.sound.sampled.Clip;
import java.awt.*;

public class AnimatedBullet extends Animated {

    public static int DIR_UP = 1;
    public static int DIR_RIGHT = 2;
    public static int DIR_DOWN = 3;
    public static int DIR_LEFT = 4;
    public static int DIR_NONE = 5;
    public int direction;
    private int speed;
    private int latticeSize;
    private int cellSize;
    private Rectangle rectangle;

    private int attack;
    private Animated creator;

    public AnimatedBullet(Animated creator) {
        this.creator = creator;
        latticeSize = Integer.parseInt(Config.get("LATTICE_SIZE"));
        cellSize = Integer.parseInt(Config.get("CELL_SIZE"));
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void init() {
        setWidth(10);
        setHeight(4);
        setZ(80);
        speed = 10;
        rectangle = new Rectangle();

        if (Config.get("SOUND_EFFECTS").equals("OFF"))
            return;

        Thread soundThread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Clip sound = ClipLoader.getClip("tank-firing");
                    if (sound != null)
                        sound.start();

                    if (Config.get("OS").toLowerCase().contains("linux")) {
                        Thread.sleep(1000);
                        sound.close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        soundThread.start();
    }

    @Override
    public Shape getShape() {
        rectangle.setFrame(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        return rectangle;
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.GRAY);
        g.fillOval(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
    }

    @Override
    public void animate() {

        for (int i = 0; i < getAnimator().getAnimateds().size(); i++) {
            Animated animated = getAnimator().getAnimateds().get(i);
            if (animated != this && this.getShape() != null && animated.getShape() != null)
                if (this.getShape().intersects(animated.getShape().getBounds2D())) {

                    if (animated instanceof AnimatedWall) {
                        getAnimator().removeAnimated(animated);
                    } else if (animated instanceof AnimatedBase) {
                        AnimatedBase base = (AnimatedBase) animated;
                        base.setDestroy(true);
                        AnimatedGameOver animatedGameOver = new AnimatedGameOver(false);
                        getAnimator().addAnimated(animatedGameOver);
                    } else if (animated instanceof AnimatedBullet) {
                        continue;
                    } else if (animated instanceof AnimatedEnemy && creator instanceof AnimatedEnemy) {
                        continue;
                    } else if (animated instanceof AnimatedPlayer && creator instanceof AnimatedEnemy) {
                        AnimatedPlayer player = (AnimatedPlayer) animated;
                        player.calculateDamage(attack);
                        player.calculateSpeed();
                    } else if (animated instanceof AnimatedEnemy && creator instanceof AnimatedPlayer) {
                        AnimatedEnemy enemy = (AnimatedEnemy) animated;
                        enemy.calculateDamage(attack);
                        enemy.calculateSpeed();
                    }
                    AnimatedImpact animatedImpact = new AnimatedImpact();
                    animatedImpact.setX(getX());
                    animatedImpact.setY(getY());
                    getAnimator().addAnimated(animatedImpact);
                    removeAnimated();
                }

        }

        if (direction == DIR_UP) {
            setAngle(90);
            if (getY() - getHeight() / 2 > 0)
                setY(getY() - speed);
            else
                removeAnimated();
        } else if (direction == DIR_DOWN) {
            setAngle(270);
            if (getY() + getHeight() / 2 < latticeSize * cellSize)
                setY(getY() + speed);
            else
                removeAnimated();
        } else if (direction == DIR_LEFT) {
            setAngle(180);
            if (getX() - getWidth() / 2 > 0)
                setX(getX() - speed);
            else
                removeAnimated();
        } else if (direction == DIR_RIGHT) {
            setAngle(0);
            if (getX() + getWidth() / 2 < latticeSize * cellSize)
                setX(getX() + speed);
            else
                removeAnimated();
        }
    }

}
