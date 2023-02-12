package ar.ieslaencanta.com.spaceinvaders2;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Game {
    private Terminal terminal;
    private Screen screen;
    private boolean exit_key;
    private Bullet bullet;
    private Ship ship;
    public static int columns=100;
    public static int rows=30;
    private Enemy enemy;

    public Game() {
        this.exit_key = false;
        try {
            this.terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(this.terminal);
            this.screen.setCursorPosition(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Point2d p=new Point2d(46, 5);
        Point2d b=new Point2d(46, 20);
        Point2d e=new Point2d(46, 60);
        bullet = new Bullet(e);
        ship = new Ship(b);
        enemy = new Enemy(p);
    }

    public void loop() {
        try {
            screen.startScreen();
            screen.clear();
            //this.terminal.setBackgroundColor(TextColor.ANSI.YELLOW);
            while (!this.exit_key) {
                int x =(int) Math.random() *100;
                int y = (int) Math.random()+30;
                //se procesa la entrada
                process_input();
                update();
                paint();
                
                try {
                    Thread.sleep((1/60)*1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                 
                
                    
                
            }
            //cierra la ventana y terminal
            screen.close();
            terminal.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void update(){
        this.ship.movebullets();
    }

    private void paint(){
        TerminalSize terminalSize = screen.getTerminalSize();
        //limpia la pantalla para que no se impriman todos los movimientos sobre otros.
for (int column = 0; column < terminalSize.getColumns(); column++)
    for (int row = 0; row < terminalSize.getRows(); row++) {
        screen.setCharacter(column, row, new TextCharacter(
                ' ',
                TextColor.ANSI.WHITE,
                TextColor.ANSI.BLACK));
    }
        this.bullet.paint(screen);
        this.ship.paint(screen);
        this.enemy.paint(screen);
        try {
            screen.refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
   
    private void process_input() {
        KeyStroke keyStroke = null;
        
        
        
        try {
            keyStroke = screen.pollInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (keyStroke != null) {
            if (keyStroke.getKeyType() == KeyType.Escape) {
                this.exit_key = true;
            }
            if(keyStroke.getKeyType() == KeyType.ArrowUp){
                this.bullet.moveVertical(-1, 0, rows);
            }
            if(keyStroke.getKeyType() == KeyType.ArrowDown){
                this.bullet.moveVertical(1, 0, rows);
            }
            if(keyStroke.getKeyType() == KeyType.ArrowLeft){
                this.ship.moveHorizontal(-1, 0, columns);
            }
            if(keyStroke.getKeyType() == KeyType.ArrowRight){
                this.ship.moveHorizontal(1, 0, columns);
            }
            if(keyStroke.getKeyType() == KeyType.Enter){
                this.ship.shoot();
            }
        }
    }
}
