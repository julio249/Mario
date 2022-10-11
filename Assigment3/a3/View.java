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
	Model model;
	int scrollPosition;

	//constructor which takes a parameter of type controller and one of type model
	View(Controller c, Model m)
	{
		model = m;
		
		c.setView(this);//"this" refers to the object that is on
		try{
			this.pipeImage = ImageIO.read(new File("pipe.png"));
			
		}
		catch(Exception e){
			e.printStackTrace(System.err);
			System.exit(1);
		}
		pipeImage.getWidth();
	}





	//background and pipe image handling
	public void paintComponent(Graphics g){
		g.setColor(new Color(100, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for(int i = 0; i < model.pipes.size(); i++)
		{
			Pipe t = model.pipes.get(i);
			g.drawImage(this.pipeImage, t.getPipe_x() - scrollPosition, t.getPipe_y(), null);//spawms pipes where clicked
		}
	}



}
