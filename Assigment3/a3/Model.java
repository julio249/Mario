/*Julio Morales
 *ID 010933308
 * 09/30/2022
 * 
 * model.java indicates the state of the game/world and draws the pipes
 */
import java.util.ArrayList; // import the ArrayList class
import java.util.Comparator;


class Model
{
	ArrayList<Pipe> pipes;
	
	//constructor to create new pipes
	Model()
	{
		pipes = new ArrayList<Pipe>();
	}

	
	
	public void update()
	{
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


}

class PipeComparator implements Comparator<Pipe>
{
	public int compare(Pipe a, Pipe b)
	{
		if(a.getPipe_x() < b.getPipe_x())
			return -1;
		else if(a.getPipe_y() > b.getPipe_y())
			return 1;
		else
			return 0;
	}

	public boolean equals(Object obj)
	{
		return false;
	}
}