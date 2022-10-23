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
	BufferedImage[] images;
    BufferedImage ground;
	Model model;
	static int scrollPosition;

	int groundHeigh = 10;
	
	

	
	//constructor which takes a parameter of type controller and one of type model
	View(Controller c, Model m)
	{
		model = m;
		
		c.setView(this);//"this" refers to the object that is on
		try{
			this.pipeImage = loadImage("pipe.png");
		}
		catch(Exception e){
			e.printStackTrace(System.err);
			System.exit(1);
		}		
	}
	
	
	//background and pipe image handling
	public void paintComponent(Graphics g){

		scrollPosition = model.mario.Xval - 100;
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// if(model.mario.rightFacing){
		// 	g.drawImage(model.mario.marios[model.mario.currentImage], model.mario.Xval - scrollPosition, model.mario.Yval, model.mario.w, model.mario.h, null);

		// }
		// else{
		// 	g.drawImage(model.mario.marios[model.mario.currentImage], model.mario.Xval - scrollPosition + model.mario.w, model.mario.Yval, -model.mario.w, model.mario.h, null);

		// }

		// System.out.println("sprite size"+model.sprites.size());
		for(int i = 0; i < model.sprites.size(); i++)
		{	
			// System.out.println("current image is: "+model.mario.currentImage);
			if(model.sprites.get(i).isMario() || model.sprites.get(i).isPipe()){
				
				model.sprites.get(i).draw(g, scrollPosition);
				// model.sprites.get(i).draw(g, scrollPosition);
			}
			// s.draw(g, scrollPosition);
			// g.drawImage(this.pipeImage, t.Xval - scrollPosition, t.Yval, null);//spawms sprites where clicked
			// System.out.println("sprite drawn");
		}

		// g.drawImage(model.mario.marios[model.mario.currentImage], model.mario.Xval - scrollPosition, model.mario.Yval, null);


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
