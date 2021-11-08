package bw.cg.My2DProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KeyGame extends Canvas implements AWTEventListener, ActionListener {

    //Player Position
    int xpos;
    int ypos;
    enum movingDirection{LEFT,UP,DOWN,RIGHT,NONE}
    movingDirection playerMoves;

    //Game Loop Timer
    Timer timer;

    //Double buffer: image and graphics objects
    Image dbImage;
    Graphics dbg;

    //Prob settings
    int xRock;
    int yRock;

    boolean chestOpen;

    boolean btn1;
    boolean btn2;

    boolean btnLU;
    boolean btnLD;
    boolean btnRU;
    boolean btnRD;

    boolean doorOpen;




    public static void main(String[] args) {
        final JFrame frame = new JFrame("Key Finder"){
            private static final long serialVersionUID=1L;
            public void processWindowevent(java.awt.event.WindowEvent e){
                super.processWindowEvent(e);
                if (e.getID() == WindowEvent.WINDOW_CLOSING){
                    System.exit(-1);
                }
            }
        };
        frame.setPreferredSize(new Dimension(1000,1050));
        frame.add((new KeyGame()));
        frame.pack();
        frame.setVisible(true);
    }


    public KeyGame(){
        this.setPreferredSize(new Dimension(1000,1000));

        timer = new Timer(30,this);
        timer.setInitialDelay(0);
        timer.start();

        this.getToolkit().addAWTEventListener(this,AWTEvent.KEY_EVENT_MASK);

        stageReset();

    }
    @Override
    public void update (Graphics g) {
        // initialize buffer
        if (dbImage == null) {

            dbImage = createImage (1000, 1050);
            dbg = dbImage.getGraphics ();
        }

        // clear screen in background
        dbg.setColor (Color.white);
        dbg.fillRect (0, 0, 1000, 1050);

        // draw elements in background
        paint (dbg);

        // draw image on the screen
        g.drawImage (dbImage, 0, 0, this);
    }




    @Override
    public void paint(Graphics g){
        drawScene((Graphics2D)g);

    }

    public void drawScene (Graphics g2d){
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0,0,1000,1000);
        Color backround = new Color(10,150,10);





        g2d.setColor(backround);
        g2d.fillRect(50,100,900,850);     /**Draw Map Outline*/
        g2d.setColor(Color.GRAY);
        g2d.fillRect(400,800,50,200);
        g2d.fillRect(550,800,50,200);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(450,950,100,50);
        for(int i=0;i<950;i+=50){
            g2d.drawRect(i,50,50,50);
        }for(int i=0;i<950;i+=50){
            g2d.drawRect(0,i+50,50,50);
        }for(int i=0;i<950;i+=50){
            g2d.drawRect(i,950,50,50);
        }for(int i=0;i<950;i+=50){
        g2d.drawRect(950,i+50,50,50);
        }for(int i=800;i<950;i+=50){
        g2d.drawRect(400,i,50,50);
        }for(int i=800;i<950;i+=50){
            g2d.drawRect(550,i,50,50);
        }                                                   /**Draw Sections*/

        Color bushes = new Color(10,50,10);
        g2d.setColor(bushes);
        g2d.fillRect(50,475,400,50);
        g2d.fillRect(400,575,50,225);
        g2d.fillRect(400,100,50,325);

        g2d.fillRect(550,475,400,50);
        g2d.fillRect(550,575,50,225);
        g2d.fillRect(550,100,50,325);
//---------------------------------------------------------------------------------------------------

        g2d.fillRect(100,575,300,50);   /**Draw Down Left Section*/
        g2d.fillRect(100,625,50,225);       //Walls
        g2d.fillRect(100,850,250,50);
//---------------------------------------------------------------------------------------------------
        g2d.setColor(Color.GRAY);

        g2d.fillRect(185,660,50,50);        //Buttons
        g2d.fillRect(285,660,50,50);
        g2d.fillRect(285,760,50,50);
        g2d.fillRect(185,760,50,50);
//---------------------------------------------------------------------------------------------------
        g2d.setColor(Color.BLACK);
        g2d.drawRect(185,660,50,50);
        g2d.drawRect(285,660,50,50);
        g2d.drawRect(285,760,50,50);
        g2d.drawRect(185,760,50,50);            //Button Outlines

        g2d.drawRect(195,670,30,30);
        g2d.drawRect(295,670,30,30);
        g2d.drawRect(295,770,30,30);
        g2d.drawRect(195,770,30,30);
   //---------------------------------------------------------------------------------------------------

        g2d.setColor(bushes);                   /** Draw upper left section*/
        g2d.fillRect(300,150,50,100);
        g2d.fillRect(300,300,50,125);

        g2d.fillRect(150,150,50,100);
        g2d.fillRect(150,300,50,125);

        g2d.fillRect(100,150,50,50);
        g2d.fillRect(100,350,50,75);

        g2d.fillRect(250,200,50,50);    //Draw Walls
        g2d.fillRect(250,300,50,50);

        //---------------------------------------------------------------------------------------------------
        g2d.setColor(Color.GRAY);
        g2d.fillRect(75,250,50,50);         //Draw Button
        g2d.setColor(Color.BLACK);
        g2d.drawRect(75,250,50,50);
        g2d.drawRect(85,260,30,30);
        //---------------------------------------------------------------------------------------------------
        g2d.setColor(new Color(50,30,20));
        g2d.fillOval(xRock,yRock,30,30);  //Draw Rock
        //---------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------------------------
        g2d.setColor(Color.GRAY);
        g2d.fillRect(750,250,50,50);         //Draw Button in upper right sections
        g2d.setColor(Color.BLACK);
        g2d.drawRect(750,250,50,50);
        g2d.drawRect(760,260,30,30);
        //---------------------------------------------------------------------------------------------------
        Color brown =new Color(50, 20, 0);
        if (chestOpen==false) {
            g2d.setColor(brown);
            g2d.fillRect(750, 750, 70, 50);        //Draw Chest
            g2d.setColor(Color.BLACK);
            g2d.drawRect(750, 750, 70, 50);
            g2d.drawRect(752, 752, 66, 22);

            for (int i = 756; i < 775; i += 4) {
                g2d.drawLine(752, i, 818, i);
            }
            for (int i = 752; i < 818; i += 4) {
                g2d.drawLine(i, 774, i, 800);
            }

            g2d.fillRect(783, 775, 4, 8);
        }else{
            g2d.setColor(brown);
            g2d.fillRect(750, 750, 70, 50);

            g2d.setColor(Color.black);
            g2d.fillRect(750,750,70,24);


            g2d.setColor(Color.black);
            g2d.drawRect(750, 750, 70, 50);
            g2d.drawRect(752, 752, 66, 22);
            g2d.setColor(brown);
            g2d.drawRect(750,750,70,24);



            g2d.setColor(Color.black);
            for (int i = 752; i < 818; i += 4) {
                g2d.drawLine(i, 774, i, 800);
            }

            g2d.fillRect(783, 775, 4, 8);



        }
        //---------------------------------------------------------------------------------------------------
        if(doorOpen==false) {
            g2d.setColor(brown);                                                //draw door
            g2d.fillRect(450,0,101,100);
            g2d.setColor(Color.black);
            g2d.drawRect(450,0,101,100);
            g2d.fillRect(498,0,4,100);
            g2d.setColor(Color.yellow);
            g2d.fillRect(485,30,30,40);
            g2d.setColor(Color.black);
            g2d.fillRect(494,40,12,12);
            g2d.fillRect(498,50,4,12);
            g2d.fillRect(497,57,6,5);
        }else {
            g2d.setColor(Color.black);
            g2d.fillRect(450,0,101,100);
            g2d.setColor(brown);
            g2d.fillRect(451,0,20,100);
            g2d.fillRect(530,0,20,100);
        }
        g2d.setColor(Color.YELLOW);
        g2d.fillRect(xpos,ypos,10,10);
        //---------------------------------------------------------------------------------------------------
        if(doorOpen==true&&ypos<110&&xpos>450&&xpos<550){
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(0,0,1000,1000);
            g2d.setColor(Color.red);
            char[] youwin= {'Y','o','u',' ','W','i','n','!'};
            g2d.drawChars(youwin,0,youwin.length,450,450);
        }



    }


    @Override
    public void eventDispatched(AWTEvent event) {
        if(event instanceof KeyEvent){
            KeyEvent key = (KeyEvent)event;
            switch(key.getKeyCode())
            {
                case 87: //W
                    playerMoves=movingDirection.UP;
                    break;
                case 83: //S
                    playerMoves=movingDirection.DOWN;
                    break;
                case 65: //A
                    playerMoves=movingDirection.LEFT;
                    break;
                case 68: //D
                    playerMoves=movingDirection.RIGHT;
                    break;
                case 82: //R
                    stageReset();
                    repaint();
                    break;
                case 38: //Key_up
                    playerMoves=movingDirection.UP;
                    break;
                case 40: //Key_down
                    playerMoves=movingDirection.DOWN;
                    break;
                case 37: //Key_left
                    playerMoves=movingDirection.LEFT;
                    break;
                case 39: //Key_right
                    playerMoves=movingDirection.RIGHT;
                    break;
                case 27: //ESC
                    System.exit(0);
            }
            key.consume();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(playerMoves!=movingDirection.NONE){
            switch (playerMoves){                                      /** moves in direction and checking collision*/
                case UP:
                    ypos-=7;
                    if(ypos<100){
                        ypos=100;
                    }
                    if(ypos>850){
                        if((xpos>90)&&(350>xpos)&&(ypos<900)){
                            ypos=900;
                        }
                    }else if(ypos>750){
                        if((xpos>740)&&(xpos<820)&&(ypos<800)){
                            ypos=800;
                        }
                    }else if(ypos>600){
                        if((xpos>=150)&&(xpos<450)&&(ypos<625)){
                            ypos=625;
                        }
                    }else if(ypos>500){
                        if((ypos<525)&&(xpos<450)||(ypos<525)&&(xpos>=550)){
                            ypos=525;
                        }
                    }else if(ypos>400&&ypos<425){
                        if((xpos>=90&&xpos<200)||(xpos>=290&&xpos<350)||(xpos>=390&&xpos<450)||(xpos>=540&&xpos<600)){
                            ypos=425;
                        }
                    }else if (ypos>325&&ypos<350){
                        if(xpos>=240&&xpos<300){
                            ypos=350;
                        }
                    }

                    break;
                case DOWN:
                    ypos+=7;
                    if(ypos>940){
                        ypos=940;
                    }
                    if(ypos<165&&ypos>140){
                        if((xpos>=100&&xpos<200)||(xpos>=300&&xpos<350)){
                            ypos=140;
                        }
                    }else if(ypos<215&&ypos>190){
                        if(xpos>=250&&xpos<300){
                            ypos=190;
                        }
                    }else if(ypos<315&&ypos>290){
                        if((xpos>=150&&xpos<200)||(xpos>=250&&xpos<350)){
                            ypos=290;
                        }
                    }else if(ypos<365&&ypos>340){
                        if(xpos>=100&&xpos<150){
                            ypos=340;
                        }
                    }
                    else if(ypos<500) {
                        if ((ypos > 465) && (xpos < 450) || (ypos > 465) && (xpos >= 550)) {
                            ypos = 465;
                        }
                    }else if(ypos<590&&ypos>565){
                            if((xpos>=90&&xpos<450)||(xpos>=550&&xpos<600)){
                                ypos=565;
                            }
                    }else if(ypos<765&&ypos>740){
                        if (xpos>=740&&xpos<820){
                            ypos=740;
                        }
                    }else if(ypos<865&&ypos>840){
                        if (xpos>=90&&xpos<350){
                            ypos=840;
                        }
                    }



                    break;
                case LEFT:
                    xpos-=7;
                    if(xpos<50){
                        xpos=50;
                    }if(xpos>795&&xpos<820){
                        if(ypos>=740&&ypos<800) {
                            xpos = 820;
                        }
                    }else if(xpos>575&&xpos<600){
                        if ((ypos<425||ypos>565)){
                            xpos=600;
                        }
                    }else if ((xpos>425&&xpos<450)){
                        if (ypos<425||ypos>564||(ypos>=475&&ypos<525)){
                            xpos=450;
                        }
                    }else if(xpos>325&&xpos<350){
                        if ((ypos<250&&ypos>150)||(ypos>300&&ypos<425)||(ypos>850&&ypos<900)){
                            xpos=350;
                        }
                    }else if (xpos>125&&xpos<150){
                        if (ypos>625&&ypos<850){
                            xpos=150;
                        }
                    }

                    break;
                case RIGHT:
                    xpos+=7;
                    if (xpos>940){
                        xpos=940;
                    }if(xpos>90&&xpos<115){
                        if((ypos>150&&ypos<250)||(ypos>300&&ypos<450)||(ypos>575&&ypos<900)){
                            xpos=90;
                        }
                    }else if(xpos>240&&xpos<265){
                        if ((ypos>190&&ypos<250)||(ypos>290&&ypos<350)){
                            xpos=240;
                        }
                    }else if(xpos>290&&xpos<315){
                        if((ypos>140&&ypos<200)||(ypos>340&&ypos<425)){
                            xpos=290;
                        }
                    }else if(xpos>390&&xpos<415){
                        if (ypos<425||ypos>580){
                            xpos=390;
                        }
                    }else if(xpos>540&&xpos<565){
                    if (ypos<425||ypos>580||(ypos>465&&ypos<525)){
                        xpos=540;
                        }
                    }else if(xpos>740&&xpos<765){
                        if(ypos>740&&ypos<800){
                            xpos=740;
                        }
                }


                    break;
            }
            if(xpos>65&&xpos<125&&ypos>240&&ypos<300){
                btn1=true;
            }if (xpos>740&&xpos<800&&ypos>240&&ypos<300){
                btn2=true;
            }if(btn1==true&&btn2==true){
                chestOpen=true;
            }
            if(chestOpen==true){
                if(xpos>175&&xpos<235&&ypos>650&&ypos<710){
                    btnLU=true;
                }if (ypos>650&&ypos<710&&xpos>275&&xpos<335){
                    if (btnLU==true) {
                        btnRU = true;
                    }else{
                        btnLU=false;
                        btnRU=false;
                        btnLD=false;
                        btnRD=false;
                    }
                }if (xpos>175&&xpos<235&&ypos>750&&ypos<810){
                    if(btnLU==true&&btnRU==true){
                        btnLD=true;
                    }else{
                        btnLU=false;
                        btnRU=false;
                        btnLD=false;
                        btnRD=false;
                    }
                }if (ypos>750&&ypos<810&&xpos>275&&xpos<335){
                    if(btnLU==true&&btnRU==true&&btnLD==true){
                        btnRD=true;
                        doorOpen=true;
                    }else{
                        btnLU=false;
                        btnRU=false;
                        btnLD=false;
                        btnRD=false;
                    }
                }
            }


            playerMoves= movingDirection.NONE;
            repaint();
        }
    }
    public void stageReset(){
        xpos=495;
        ypos=900;
        playerMoves=movingDirection.NONE;
        xRock= 250;
        yRock= 425;
        chestOpen=false;
        boolean btn1=false;
        boolean btn2=false;
        boolean btnLU=false;
        boolean btnLD=false;
        boolean btnRU=false;
        boolean btnRD=false;
        boolean doorOpen=false;
    }
}
