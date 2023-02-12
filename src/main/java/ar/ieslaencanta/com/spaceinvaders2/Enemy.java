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
public class Enemy {
    private Point2d position;
    private int width=6;
    private int height=2;
    private int contador=0;
    private String cartoonUp[] = {
              "⣆⡵⣤⡴⣅⡆",
              "⢘⠟⠛⠛⢟⠀",
};
    private String cartoonDown[] ={
          "⢀⡵⣤⡴⣅ ",
          "⠏⢟⡛⣛⠏⠇",
    };
        
    private TextColor.ANSI color;
    private TextColor.ANSI backgroundcolor;
    
    public Enemy(Point2d posicion) {
        this.position = posicion;
     //   this.init();
    }

    public Enemy(int x, int y) {
        this.position = new Point2d(x,y);
      //  this.init();
    }

    public Enemy() {
        this.position = new Point2d();
    //    this.init();
    }

    public Point2d getPosicion() {
        return position;
    }

    public void setPosicion(Point2d posicion) {
        this.position = posicion;
    }




    
        public void paint(Screen s){
        char c;
        
        contador=(int) (Math.random()*2);
        if(contador==1){
        for(int i=0;i<this.height;i++){
            for(int j=0;j<this.width;j++){
                c=this.cartoonUp[i].charAt(j);
                s.setCharacter(this.position.getX()+j, this.position.getY()+i, new TextCharacter(c,TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
            }
        }
        } else {
            for(int i=0;i<this.height;i++){
            for(int j=0;j<this.width;j++){
                c=this.cartoonDown[i].charAt(j);
                s.setCharacter(this.position.getX()+j, this.position.getY()+i, new TextCharacter(c,TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
            }
        }
        }
        
        }
}
