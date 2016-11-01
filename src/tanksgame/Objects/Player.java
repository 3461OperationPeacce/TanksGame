/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tanksgame.Objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import tanksgame.Engine.MainGame;

/**
 *
 *  two images 
 * one is the tank
 * one is the barrel
 * barrel angle is determined by the position of the mouse
 * 
 * @author LGarceau
 */
public class Player extends GameObject{

    public double canAngle = 0;
    public BufferedImage can = MainGame.funct.getImageFromName("tankCan.png"),
            tank = MainGame.funct.getImageFromName("tank30.png");
    
    public Player(int x, int y, double velX, double velY, ID id, String image) {
        super(x, y, velX, velY, id, image);
    }

    @Override
    public void render(Graphics2D g) {
//        System.out.println("angle|"+angle);
        
        
        can = MainGame.funct.getImageFromName("tankCan.png");
        tank = MainGame.funct.getImageFromName("tank30.png");
        System.out.println("Mouse X|"+MainGame.mouseX);
        System.out.println("Mouse Y|"+MainGame.mouseY);
        // need to make the mouse position relive to the tanks position
        double mouseX = (MainGame.mouseX-0)-this.getX(),
                mouseY = (MainGame.mouseY)-this.getY();
        canAngle = Math.atan(-mouseX/mouseY);
//        System.out.println("angle|"+Math.toDegrees(canAngle));
        
        if(MainGame.mouseY-this.getY() > 0){
            canAngle += Math.PI;
        }
//        System.out.println(Math.toDegrees(angle));
        double xRotConstant = 22, yRotConstant = 52;
        g.drawImage(tank, (int)(x-tank.getWidth()/2), (int)y-tank.getHeight()/2, null);
        g.rotate(canAngle, (int)(x-can.getWidth()/2)+xRotConstant, (int)y-can.getHeight()/2+yRotConstant);
        g.drawImage(can, (int)(x-tank.getWidth()/2)+22, (int)y-tank.getHeight()/2+12, null);
        g.rotate(-canAngle, (int)(x-can.getWidth()/2)+xRotConstant, (int)y-can.getHeight()/2+yRotConstant);
        
//        g.drawImage(op.filter(can, ), (int) (x-tank.getWidth()/2)+20, (int) y-tank.getHeight()/2, null);
    }
    
    
    
     public String getIntersectedWall(){
        String wall = "";
        
        if(collision(MainGame.bottom)){
            return "bottom";
        }
        if(collision(MainGame.top)){
            return "top";
        }
        if(collision(MainGame.left)){
            return "left";
        }
        if(collision(MainGame.right)){
            return "right";
        }
        
        return wall;
    }

    @Override
    public void tick() {
        
        
        x+=velX;
        y+=velY;
        
//        System.out.println("velX |"+velX);
//        System.out.println("velY |"+velY);
//        System.out.println("angle |"+angle);
//        System.out.println("x|"+x);
//        System.out.println("y|"+y);
    }

    @Override
    public boolean collision(GameObject obj) {
        Rectangle thisRect = new Rectangle((int)this.x-tank.getWidth()/2, (int)this.y-tank.getHeight()/2, (int)this.width, (int)this.height),
                objRect = new Rectangle((int)obj.x, (int)obj.y, (int)obj.width, (int)obj.height);
        
        return thisRect.intersects(objRect);
    }
    
    
    
}
