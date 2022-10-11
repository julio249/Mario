/*Julio Morales
 *ID 010933308
 * 09/16/2022
 * 
 * view.java takes care of the visual interface of the game.
 * adds 
 */
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

//view class extends to Jpanel in order to obtain a new window to view the turtle
class View extends JPanel
{
	//member variables:
	JButton b1;
	BufferedImage turtle_image;
	Model model;

	//constructor which takes a parameter of type controller and one of type model
	View(Controller c, Model m)
	{
		b1 = new JButton("PUSH");
		b1.addActionListener(c);
		model = m;

		this.add(b1);
		
		c.setView(this);//"this" refers to the object that is on
		try{
			this.turtle_image = ImageIO.read(new File("turtle.png"));
		}
		catch(Exception e){
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	//removes buttom when clicked
	void removeButtom(){
		this.remove(b1);
		this.repaint();
	}

	//background and turtle image handling
	public void paintComponent(Graphics g){
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(this.turtle_image, model.turtle_x, model.turtle_y, null);
	}
}
