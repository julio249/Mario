// import org.w3c.dom.events.MouseEvent;

/*Julio Morales
 *ID 010933308
 * 
 * 
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
// import java.awt.Graphics;


 class Pipe extends Sprite{
    

    //pipe dimensions
    int h = 390;
    int w = 55;

    static BufferedImage P;

    public Pipe(int x, int y){
        this.Xval = x;
        this.Yval = y;
        h = 390;
        w = 55;

        if(P == null){
            P = View.loadImage("pipe.png");
        }
    }

    //unmarsaling constructor: retrieve atributes from json
    public Pipe(Json ob){ 
        Xval = (int)ob.getLong("Xval");
        Yval = ((int)ob.getLong("Yval"));
        h = 400;
        w = 55;
        if(P == null){
            P = View.loadImage("pipe.png");
        }
    }
    
    // //marshaling: saves necessary atributes to Json
    Json marshaller(){
        Json ob = Json.newObject();
        
        ob.add("Xval", Xval);
        ob.add("Yval", Yval);
        ob.add("h", h);
        ob.add("w", w);
        return ob;
    }

    
    
    
    //getters
    public int getXval() {
        return Xval;
    }
    public int getYval(){
        return Yval;
    }
    public int getHeight(){
        return h;
    }
    public int getWidth(){
        return w;
    }
    
    
    //make sure click is within bounds of image:
    public boolean clickOnExistinPipe(int clickX, int clickY){
        if(clickX >= Xval && clickX <= Xval + w && clickY >= Yval && clickY <= Yval + h){
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void draw(Graphics g, int scroll) {
        // TODO Auto-generated method stub
        // System.out.println("PIPE DRAWN");
        g.drawImage(P, Xval - scroll, Yval, null);//spawms sprites where clicked
        // System.out.println("pipe drawn");
    }
    
    @Override
    public boolean isPipe()
    {
        return true;
    }
   
    
    
    // public void drawYourself(Graphics g){
        //     g.drawImage(P, Xval - View.scrollPosition, Yval, w, h, null);
        
        // }
    }