import java.awt.image.BufferedImage;
import java.awt.Graphics;

/*Julio Morales
 * 010933308
 * 
 */
public class Mario extends Sprite{
    int prevX;
    int prevY;

    
    int currentImage; 
    static BufferedImage image;
    BufferedImage[] marios = new BufferedImage[5];
    double vertVelocity =1.2;
    static int speed =5;
    int inAir = 0;

    int marioScroll = Xval - 100;

    boolean rightFacing = true;

    public Mario(int x, int y){
        
        this.Xval = x;
        this.Yval = y;
        this.w = 60;
		this.h = 95;
        
        currentImage = 0;
            for(int i=0; i < marios.length; i++){
                String num = Integer.toString(i+1); 
                marios[i] = View.loadImage("mario" + num +".png");
            }
        
    }

    


    
    public void getOutOfPipe(Sprite p){
        
        //mario  left collision
        if(Xval + w >= p.Xval && prevX + w <= p.Xval){
            
            Xval = p.Xval - w;
        }
        //mario coming from right collision
        if(Xval + w >= p.Xval && prevX - w>= p.Xval ){
            Xval = p.Xval + w;
        }

        //collision from top to bottom
        if(Yval + h >= p.Yval && prevY  <= p.Yval){
            Yval = p.Yval - h;
            vertVelocity = 0.0;
            inAir = 0;
        }
        //mario colliding from bottom to top
        if(Yval <= p.Yval + p.h && prevY >= p.Yval + p.h ){
            Yval = p.Yval + p.h;
            vertVelocity = 0.0;
            inAir = 0;
        }
        
    }
    
    
    public void setPrevPos(){
        prevX = Xval;
        prevY = Yval;
        
    }
    
    

    public void changeImageState(){
        currentImage++;
        if(currentImage >= 4){
            currentImage = 0;
        }
    }


    @Override
    public boolean isMario()
    {
        return true;
    }
    
    @Override
    public void update(){// mario updates
        vertVelocity += 3.5;//this is gravity
        Yval  += vertVelocity;
        inAir++;
        if(Yval > 500 ){ //keep mario on the ground
            vertVelocity = 0.0;
            Yval = 500; //if mario is on a pipe jumps as if he was in the ground
            inAir = 0;
        }
    }

    @Override
    public void draw(Graphics g, int scroll){
        // System.out.println("Mario X " + this.Xval);
        // System.out.println(isMario());
        // if(this.isMario()){
        //     System.out.println("before draw");
        //     g.drawImage(marios[currentImage], Xval - scroll, Yval, null);
        //     System.out.println("MARIO DRAWN");
        // }
        // System.out.println("scroll " + scroll);
        
        if(rightFacing){
			g.drawImage(marios[currentImage], this.Xval - View.scrollPosition, this.Yval, w, h, null);
            
		}
		else{
            g.drawImage(marios[currentImage], this.Xval - View.scrollPosition + this.w, this.Yval, -this.w, this.h, null);
            
		}
    }
    
    @Override 
	public String toString()
	{
        return "mario (x,y) = (" + Xval + ", " + Yval + ")";
	}


    Json marshaller() {
        // TODO Auto-generated method stub
        Json ob = Json.newObject();

		ob.add("MarioX,",Xval);
		ob.add("MarioY,",Yval);
		return ob;
    }

    public Mario(Json ob)
	{
		w = 60;
		h = 95;
		Xval = (int) ob.getLong("MarioX");
		Yval = (int) ob.getLong("MarioY");
		if(image == null){
            image = View.loadImage("pipe.png");
        }

	}
    
    
}
