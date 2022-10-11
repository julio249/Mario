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
        vertVelocity += 3;//this is gravity
        marioY  += vertVelocity;
        inAir++;

        if(marioY > 500){
            vertVelocity = 0.0;
            marioY = 500;
            inAir = 0;
        }
        

    }

    public void getOutOfPipe(Pipe p){

        //mario coming from left collision
        if(marioX + w >= p.getPipe_x() && prevX + w <= p.getPipe_x()){
            marioX = p.pipe_x - w;
            System.out.println("mario colliding from left");
        }
        //mario coming from right collision
        if(marioX >= p.getPipe_x() + w && prevX >= p.getPipe_x() + p.getWidth()){
            marioX = prevX - p.pipe_x;
            System.out.println("mario colliding from right");
        }
        // System.out.println("no collision");
        // System.out.println("marioX = " + marioX );
        
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
