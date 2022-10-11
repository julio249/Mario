/*Julio Morales
 *ID 010933308
 * 09/30/2022
 * 
 * model.java indicates the state of the game/world and draws the pipes
 */
import java.util.ArrayList; // import the ArrayList class



class Model
{
	static int speed = 4;
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
		// mario = new Mario(mario.getMarioX() - mario.w, mario.getMarioY()); 
		for(int i=0; i < pipes.size(); i++){
			boolean check = isThereACollision(pipes.get(i));
			// System.out.println(isThereACollision(pipes.get(i))); 
			// mario.getOutOfPipe(pipes.get(i));
			if(check ){
				mario.getOutOfPipe(pipes.get(i));
				System.out.println("Mario got out of pipe");
			}

			System.out.println("marioX value: " + mario.marioX);
			System.out.println("marioY value: " + mario.marioY);

			System.out.println("pipex value: " + pipes.get(i).pipe_x);
			System.out.println("pipeY value: " + pipes.get(i).pipe_y);

		}
		// System.out.println(mario);
	}
	
	//verifies if the click is on an existing pipe of not in order to add or remove			
	public void addPipe(int x, int y){
			Pipe t = new Pipe(x, y);
			boolean pipeExists = false;//bool variable to keep track when a pipe is clicked
		
			for(int i =0; i < pipes.size(); i++){
				if(pipes.get(i).clickOnExistinPipe(x, y))
				{
					pipes.remove(i);
					pipeExists = true;
				}
			}
			if(!pipeExists){
				pipes.add(t);
			}
		}



	public boolean isThereACollision(Pipe p){
			if(mario.marioX + mario.w < p.pipe_x){
				// System.out.println("value of marioX:  " + mario.getMarioX());
				System.out.println("mario NOT coliding from left to right");
				return false;
			}
			if(mario.marioX > p.pipe_x + p.w){
				System.out.println("mario NOT coliding from right to left");
				return false;
			}
			// if(m.marioY + m.h < p.pipe_y){
			// 	System.out.println("mario not coliding");
			// 	return false;
			// } // assumes bigger is downward
			// if(m.marioY > p.pipe_y + p.h){
			// 	System.out.println("mario not coliding");
			// 	return false;
			// } // assumes bigger is downward
			System.out.println("mario coliding");
			return true;
			
			
		}
}
