/*Julio Morales
 * 010933308
 * 09/16/2022
 * 
 * controller.java contains the keyboard and mouse methods 
 * in order to move up, down, left, or right and the mouse clicks
 */
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;

	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;

	//constructor
	Controller(Model m)
	{
		model = m;
	}

	//removes buttom when pushed
	public void actionPerformed(ActionEvent e)
	{
		// System.out.println("you pushed the unpushable buttom");
		view.removeButtom();
	}


	//view setter
	void setView(View v){
		view = v;
	}

	// void update(){

	// }

	//mouse accions methods
	public void mousePressed(MouseEvent e)
	{
		model.setDestination(e.getX(), e.getY());
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }


	//key acction methods
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		if(keyRight) model.dest_x += 4;
		if(keyLeft) model.dest_x -= 4;
		if(keyDown) model.dest_y += 4;
		if(keyUp) model.dest_y -= 4;
	}

}
