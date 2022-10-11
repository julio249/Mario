// import org.w3c.dom.events.MouseEvent;

/*Julio Morales
 *ID 010933308
 * 
 * 
 */

import java.awt.image.BufferedImage;
import java.awt.Graphics;


 class Pipe{
    //x and y position of pipe
    int pipe_x;
    int pipe_y;

    //pipe dimensions
    int h = 400;
    int w = 55;

    static BufferedImage P;

    public Pipe(int x, int y){
        this.pipe_x = x;
        this.pipe_y = y;

        if(P == null){
            P = View.loadImage("pipe.png");
        }
    }

    //unmarsaling constructor: retrieve atributes from json
    public Pipe(Json ob){ 
        pipe_x = (int)ob.getLong("pipe_x");
        pipe_y = ((int)ob.getLong("pipe_y"));
        h = 400;
        w = 55;
        if(P == null){
            P = View.loadImage("pipe.png");
        }
    }
    
    //marshaling: saves necessary atributes to Json
    Json marshaller(){
        Json ob = Json.newObject();
        
        ob.add("pipe_x", pipe_x);
        ob.add("pipe_y", pipe_y);
        ob.add("h", h);
        ob.add("w", w);
        return ob;
    }
    

    
    //getters
    public int getPipe_x() {
        return pipe_x;
    }
    public int getPipe_y(){
        return pipe_y;
    }
    public int getHeight(){
        return h;
    }
    public int getWidth(){
        return w;
    }

    
    //make sure click is within bounds of image:
    public boolean clickOnExistinPipe(int clickX, int clickY){
        if(clickX >= pipe_x && clickX <= pipe_x + w && clickY >= pipe_y && clickY <= pipe_y + h){
            return true;
        }
        else{
            return false;
        }
    }



    // public void drawYourself(Graphics g){
    //     g.drawImage(P, pipe_x - View.scrollPosition, pipe_y, w, h, null);
        
    // }
}