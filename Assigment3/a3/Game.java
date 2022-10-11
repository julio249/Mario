/*Julio Morales
 * ID 010933308
 * 09/16/2022
 * 
 * game.java contains the main method and game class
 *  which also contains member variables and instances of model, controller and view.
 */

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model;
	Controller controller;
	View view;
	public Game()
	{
		
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		this.setTitle("A3 - Map Editor");
		this.setSize(500, 500);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		view.addMouseListener(controller);
		this.addKeyListener(controller);

	}

	void setModel(Model m)
	{
		model = m;
	}

	//runs infinitely, updates controllers and screen
	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 40 milliseconds
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
			
		}
	}
	


	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
}
