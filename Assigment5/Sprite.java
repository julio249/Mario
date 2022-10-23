import java.awt.Graphics;

abstract class Sprite {
    int Xval;
    int Yval;
    
    int h;
    int w;
    
    abstract public void draw(Graphics g, int scroll);
    abstract public void update();
    abstract Json marshaller();



     //marshaling: saves necessary atributes to Json
    //check type
    public boolean isMario()      { return false; }
    public boolean isPipe()      { return false; }
    

}
