import processing.core.PApplet;

import java.util.Timer;

public class PowerUp {
    private int x,y,radius;
    private String powerUpType;
    Paddle p1;
    Paddle p2;
    Ball b;

    public String getPowerUpType() {
        return powerUpType;
    }

    public PowerUp(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        String[] types = {"ballSize", "paddleSize", "doublePoints", "paddleSpeed", "ballSpeed"};
        powerUpType = types[(int)(Math.random()*types.length)];
        p1 = new Paddle(325, 20);
        p2 = new Paddle(325, 750);
    }

    public void draw(PApplet window){
        if(powerUpType.equals("ballSize")){
            window.fill(28, 79, 28);
        }
        else if(powerUpType.equals("paddleSize")){
            window.fill(160, 77, 88);
        }
        else if(powerUpType.equals("doublePoints")){
            window.fill(37, 148, 186);
        }
        else if(powerUpType.equals("paddleSpeed")){
            window.fill(151, 65, 186);
        }
        else if(powerUpType.equals("ballSpeed")){
            window.fill(73, 127, 91);
        }
        window.ellipse(x,y, radius*2, radius*2);
    }

    public boolean colliding(Ball other) {
        int sumRadius = (this.getRadius() + other.getRadius());
        double d = distance(this.getX(), this.getY(), other.getX(), other.getY());
        return sumRadius >= d;
    }

    public boolean collision(Ball b) {
        if (colliding(b)) {
            System.out.println("hit something");
            if (powerUpType.equals("ballSize")) {
                ballSize(b);
            }
            if(powerUpType.equals("paddleSize")){
                if(b.lastPaddle() == 1){
                    paddleSize(p1);
                }
                else if(b.lastPaddle() == 2){
                    paddleSize(p2);
                }
            }
            return true;
        }
        return false;
    }

    private void ballSize(Ball b) {
        b.setRadius((int)(Math.random()*35)+5);
    }
    private void paddleSize(Paddle p){p.setLength((int)(Math.random()*400+100));}
    private void doublePoints(){}
    private void ballSpeed(Ball b){b.setSpeed((int)(Math.random()*5)+10);}
    private void paddleSpeed(Paddle p){p.setSpeed((int)(Math.random()*50)+20);}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    private double distance(float x1, float y1, float x2, float y2) {
        float dx = x1-x2;
        float dy = y1-y2;
        return Math.sqrt(dx*dx+dy*dy);
    }
}
