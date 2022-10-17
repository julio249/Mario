/*Julio Morales
 * 010933308
 * 09/30/2022
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
	Pipe pipe;

	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean q;
	boolean esc;
	boolean editing = false;
	boolean keySpace;

	//constructor
	Controller(Model m)
	{
		model = m;
	}

	public void actionPerformed(ActionEvent e)
	{
	}


	//view setter
	void setView(View v){
		view = v;
	}


	//mouse accions methods
	public void mousePressed(MouseEvent e)
	{
		
		if(editing == true){
			model.addPipe(e.getX() + view.scrollPosition, e.getY());
		}
		
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {
		// if(e.getY() < 100){
		// 	System.out.println("break here");
		// }
	}


	//key acction methods
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_Q: q = true; break;
			case KeyEvent.VK_ESCAPE: esc = true; break;
			case KeyEvent.VK_SPACE: keySpace = true; break;
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
			case KeyEvent.VK_SPACE: keySpace = false; break;
		}
		
		char c = Character.toLowerCase(e.getKeyChar());
		if(c == 'q' || c == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		if(c == 's'){
			Json saveOb = model.marshal();
			saveOb.save("map.json");
		}


		if(c == 'e' && editing == false)//enable editing
		{
			editing = true;
			System.out.println("Editing mode on");
		}
		else if(c == 'e' && editing == true)//disable editing
		{
			editing = false;
			System.out.println("Editing mode off");
		}
		
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.mario.setPrevPos();

		if(keyRight){
			// view.scrollPosition += 10;
			model.mario.marioX += 10; 
			model.mario.changeImageState();
			model.mario.rightFacing =true;

		} 
		if(keyLeft) {
			// view.scrollPosition -= 10;
			// model.mario.rightFacing = false;
			model.mario.marioX -= 10;
			model.mario.changeImageState();
			model.mario.rightFacing =false;
		}
	
		if(keySpace && model.mario.inAir < 5 ){
			//mario jump 
			model.mario.vertVelocity -= 10;
			// model.mario.marioY -= 50;
		} 	
	}

}
