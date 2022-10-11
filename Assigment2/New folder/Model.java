/*Julio Morales
 *ID 010933308
 * 
 * model.java indicates the state of the game and draws the turtle
 */

class Model
{
	//local variables
	int turtle_x;
	int turtle_y;
	int dest_x;
	int dest_y;

	//constructor
	Model()
	{
	}

	//represents the state of the game and draws the turtle.
	public void update()
	{
		// Move the turtle 
		//increase with Math.min in order not to bounce
		if(this.turtle_x < this.dest_x)
			turtle_x += Math.min(4,dest_x - turtle_x);
		else if(this.turtle_x > this.dest_x)
			turtle_x -= Math.min(4,dest_x + turtle_x);
		if(this.turtle_y < this.dest_y)
			turtle_y += Math.min(4,dest_y - turtle_y);
		else if(this.turtle_y > this.dest_y)
			turtle_y -= Math.min(4,dest_y + turtle_y);
	}

	//moves turtle where clicked
	public void setDestination(int x, int y)
	{
		this.dest_x = x;
		this.dest_y = y;
	}
}