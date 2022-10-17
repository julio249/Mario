/*Julio Morales
 *ID 010933308
 * 09/30/2022
 * 
 * model.java indicates the state of the game/world and draws the pipes
 */
import java.util.ArrayList; // import the ArrayList class
import java.util.Iterator;



class Model
{
	int speed = 4;
	ArrayList<Pipe> pipes;
	Mario mario;
	
	
	
	//constructor to create  pipes and mario
	Model()
	{
		pipes = new ArrayList<Pipe>();
		mario = new Mario(100, 500);
	}
	
	//unmarshaling constructor
	void unmarshal(Json ob){
		pipes = new ArrayList<Pipe>();
		Json tmpList = ob.get("pipes");
		for(int i=0; i < tmpList.size(); i++){
			pipes.add(new Pipe(tmpList.get(i)));
		}
	}

	//marshal method that uses marshal method from Pipe class
	Json marshal(){
		Json ob = Json.newObject(); 
		Json tmpList = Json.newList();

		ob.add("pipes", tmpList);
		for(int i=0; i < pipes.size(); i++){
			tmpList.add(pipes.get(i).marshaller());
		}

		return ob;
	}
	
	
	public void update()
	{
		mario.update(); 
		for(int i=0; i < pipes.size(); i++){
			boolean check = isThereACollision(pipes.get(i));
			if(check ){
				mario.getOutOfPipe(pipes.get(i));
			}

		}
	}
	
	//verifies if the click is on an existing pipe of not in order to add or remove			
	public void addPipe(int x, int y){
			Pipe t = new Pipe(x, y);
			boolean pipeExists = false;//bool variable to keep track when a pipe is clicked
			Iterator<Pipe> it = pipes.iterator();
		
			while(it.hasNext()){
				if(it.next().clickOnExistinPipe(x, y))
				{
					it.remove();
					pipeExists = true;
				}
			}

			if(!pipeExists){
				pipes.add(t);
			}
		}


	//check if collision happens and return true when does
	public boolean isThereACollision(Pipe p){
			if(mario.marioX + mario.w < p.pipe_x ){
				//mario not colliding from left to right
				return false;
			}
			if(mario.marioX > p.pipe_x + p.w){
				//mario not colliding from right to left
				return false;
			}
			if(mario.marioY + mario.h < p.pipe_y){
				//colliding from top to bottom
				return false;
			} // assumes bigger is downward
			if(mario.marioY > p.pipe_y + p.h){
				return false;
			} // assumes bigger is downward
			// System.out.println("mario coliding");
			return true;
		}
		

	
}
