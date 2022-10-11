/*Julio Morales
 *ID 010933308
 * 09/30/2022
 * 
 * view.java takes care of the visual interface of the game.
 * adds 
 */
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import javax.swing.*;

//view class extends to Jpanel in order to obtain a new window to view the turtle
class View extends JPanel
{
	//member variables:
	JLabel imageLabel;
	BufferedImage pipeImage;
	BufferedImage[] marioImages;
    BufferedImage ground;
	Model model;
	int scrollPosition;

	int groundHeigh = 10;
	
	
	View(){
		marioImages = new BufferedImage[5];
		marioImages[0] = loadImage("mario1.png");
		marioImages[1] = loadImage("mario2.png");
		marioImages[2] = loadImage("mario3.png");
		marioImages[3] = loadImage("mario4.png");
		marioImages[4] = loadImage("mario5.png");
	}
	
	
	//constructor which takes a parameter of type controller and one of type model
	View(Controller c, Model m)
	{
		model = m;
		
		c.setView(this);//"this" refers to the object that is on
		try{
			this.pipeImage = ImageIO.read(new File("pipe.png"));
			this.ground = ImageIO.read(new File("map.png"));
		}
		catch(Exception e){
			e.printStackTrace(System.err);
			System.exit(1);
		}		
	}
	
	
	//background and pipe image handling
	public void paintComponent(Graphics g){
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for(int i = 0; i < model.pipes.size(); i++)
		{
			Pipe t = model.pipes.get(i);
			g.drawImage(this.pipeImage, t.getPipe_x() - scrollPosition, t.getPipe_y(), null);//spawms pipes where clicked
		}
		// model.mario.drawMario(g);
		g.drawImage(model.mario.marios[model.mario.currentImage], model.mario.marioX , model.mario.marioY, null);


		g.setColor(Color.gray);
		g.drawLine(0, 596, 2000, 596);
		
	}


	//loads mario images
	static BufferedImage loadImage(String filename) {
		BufferedImage image = null;
		try{
				image = ImageIO.read(new File(filename));		
		}
		catch(Exception e){
			e.printStackTrace(System.err);
			System.exit(1);
		}
		System.out.println("successfully loaded " + filename + " image" );
		return image;
		
	}
}
