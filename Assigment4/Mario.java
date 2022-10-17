import java.awt.image.BufferedImage;
import java.awt.Graphics;

/*Julio Morales
 * 10/06/2022
 * 
 */
public class Mario {
    int marioX, prevX;
    int marioY, prevY;
    
    int h = 95;
    int w = 60;
    int currentImage; 
    BufferedImage[] marios;
    double vertVelocity =1.2;
    static int speed =5;
    int inAir = 0;

    boolean rightFacing = true;

    public Mario(int x, int y){
        marioX = x;
        marioY = y;
        
        currentImage = 0;
        marios = new BufferedImage[5];
        for(int i=0; i < marios.length; i++){
            marios[i] = View.loadImage("mario" + (i+1) +".png");
        }
    }

    // public void drawMario(Graphics g){
    //     g.drawImage(marios[currentImage], marioX , marioY, null);
    //     // g.setColor(Color.gray);
    //     // g.drawLine(0, 596, 2000, currentImage);
    // }

    public int getMarioX() {
        return marioX;
    }
    public int getMarioY(){
        return marioY;
    }
    public int getMarioHeight(){
        return h;
    }
    public int getMariowigth(){
        return w;
    }

    public void update(){
        vertVelocity += 3.5;//this is gravity
        marioY  += vertVelocity;
        inAir++;
        if(marioY > 500 ){ //keep mario on the ground
            vertVelocity = 0.0;
            marioY = 500; //if mario is on a pipe jumps as if he was in the ground
            inAir = 0;
        }
    }

    public void getOutOfPipe(Pipe p){

        //mario  left collision
        if(marioX + w >= p.pipe_x && prevX + w <= p.pipe_x){
            
            marioX = p.pipe_x - w;
        }
        //mario coming from right collision
        if(marioX + w >= p.pipe_x && prevX - w>= p.pipe_x ){
            marioX = p.pipe_x + w;
        }

        //collision from top to bottom
        if(marioY + h >= p.pipe_y && prevY  <= p.pipe_y){
            marioY = p.pipe_y - h;
            vertVelocity = 0.0;
            inAir = 0;
        }
        //mario colliding from bottom to top
        if(marioY <= p.pipe_y + p.h && prevY >= p.pipe_y + p.h ){
            marioY = p.pipe_y + p.h;
        }

    }


    public void setPrevPos(){
        prevX = marioX;
        prevY = marioY;
        
    }



    public void changeImageState(){
        currentImage++;
        if(currentImage > 4){
            currentImage = 0;
        }
    }


    @Override 
	public String toString()
	{
		return "mario (x,y) = (" + marioX + ", " + marioY + ")";
	}

}
