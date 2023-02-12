/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.ieslaencanta.com.spaceinvaders2;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

/**
 *
 * @author rudolfakopov
 */
public class Ship {
    private Point2d position;
    private int height=2;
    private int width=7;
    private Bullet bullets[];
    private static int max_bullets=3;
    private int contador=0;
    private String cartoon[] = {
          " ⢀⣀⣾⣷⣀⡀ ",
          " ⣿⣿⣿⣿⣿⣿ "  
    };
    
    private TextColor.ANSI color;
    private TextColor.ANSI backgroundcolor;


     public Ship(Point2d position) {
        this.position = position;
        this.bullets = new Bullet[Ship.max_bullets];
       
     //   this.init();
    }

    public Ship(int x, int y) {
        this.position = new Point2d(x,y);
        this.bullets = new Bullet[Ship.max_bullets];
     //   this.init();
    }

    public Ship() {
        this.position = new Point2d();
         this.bullets = new Bullet[Ship.max_bullets];
       
     //   this.init();
    }
   
    
    public Point2d getPosition() {
        return position;
    }

    public void setPosition(Point2d position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void shoot(){
    this.bullets[0]=new Bullet(
            this.position.getX()+this.width/2,
            this.position.getY()-2);
}

 //   private void init() {
    //    this.color = TextColor.ANSI.GREEN;
    //    this.backgroundcolor = TextColor.ANSI.BLACK;
      //  this.shipsymbol = TextCharacter.fromCharacter(Symbols.DOUBLE_LINE_BOTTOM_RIGHT_CORNER)[0].withForegroundColor(this.color).withBackgroundColor(this.backgroundcolor);
        
   // }
    public void moveHorizontal(int inc_x, int min, int max){
        if(this.position.getX()+inc_x+1>=min && this.position.getX()+this.width+inc_x<=max){
            this.position.addX(inc_x);
        }
    }
    public void paint(Screen s){
        char c;
        for(int i=0;i<this.height;i++){
            for(int j=0;j<this.width;j++){
                c=this.cartoon[i].charAt(j);
                s.setCharacter(this.position.getX()+j, this.position.getY()+i, new TextCharacter(c,TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
            }
        }
        for(int i=0;i<this.bullets.length;i++){
            if(this.bullets[i] != null){
                this.bullets[i].paint(s);
            }
        }
    }
        public void movebullets(){
            
        for(int i=0;i<this.bullets.length;i++){
            
            if(this.bullets[i] != null){
                
                this.bullets[i].moveVertical(-1, 0, Game.rows);
            }
        }
    }
}