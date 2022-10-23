/*Julio Morales
 *ID 010933308
 * 09/30/2022
 * 
 * model.java indicates the state of the game/world and draws the sprites
 */
import java.util.ArrayList; // import the ArrayList class
import java.util.Iterator;



class Model
{
	int speed = 4;
	// ArrayList<Pipe> pipes;
	ArrayList<Sprite> sprites;
	Mario mario;
	
	
	
	//constructor to create  sprites and mario
	Model()
	{
		mario = new Mario(500,500);
		sprites = new ArrayList<Sprite>();
		sprites.add((Sprite)mario);
		
	}
	
	//unmarshaling constructor
	void unmarshal(Json ob){
		sprites = new ArrayList<Sprite>();
		sprites.add(mario);

		Json tmpListPipes = ob.get("pipes");

		for(int i=0; i < sprites.size(); i++){
			if(sprites.get(i).isPipe()){
				sprites.add(new Pipe(tmpListPipes.get(i)));
			}
			
		}
	}

	//marshal method that uses marshal method from Pipe class
	Json marshal(){
		Json ob = Json.newObject(); 
		Json tmpListPipes = Json.newList();
		ob.add("pipes", tmpListPipes);
		Json tmpListMario = Json.newList();
		ob.add("Mario", tmpListMario);

		for(int i=0; i < sprites.size(); i++){
			
			// tmpListPipes.add(sprites.get(i).marshaller());
			if(sprites.get(i).isPipe()){
				tmpListPipes.add(sprites.get(i).marshaller());
			}
			if(sprites.get(i).isMario()){
				tmpListMario.add(sprites.get(i).marshaller());
			}

		}

		return ob;
	}
	
	
	public void update()
	{
		// sprites = new ArrayList<Sprite>();
		// mario = new Mario(100, 100);
		// sprites.add((Sprite)mario);
		System.out.println("sprite size " + sprites.size());
		
		for(int i=0; i < sprites.size(); i++){
			sprites.get(i).update(); 
			
			
			// boolean check = isThereACollision(sprites.get(i));
			

			// if(sprites.get(i).isMario()){
			// 	System.out.println("IS mario is true "+ sprites.get(i).Xval + " " + sprites.get(i).Yval);
			// 	if(check){
			// 		if(sprites.get(i).isPipe()){
			// 			mario.getOutOfPipe((Pipe)sprites.get(i));
			// 		}
			// 	}
			// }
			
			
		}
	}
	
	//verifies if the click is on an existing pipe of not in order to add or remove			
	public void addPipe(int x, int y){
			Sprite t = new Pipe(x, y);
			boolean pipeExists = false;//bool variable to keep track when a pipe is clicked
			// Iterator<Sprite> it = sprites.iterator();

			for(int i=0; i< sprites.size(); i++){
				if(sprites.get(i).isPipe()){
					sprites.remove(i);
					pipeExists = true;
				}
			}


			// while(it.hasNext()){
			// 	if(it.next().isPipe() == true){
			// 		if(( (Pipe) it.next()).clickOnExistinPipe(x, y)) //isPipe?
			// 		{
			// 			it.remove();
			// 			pipeExists = true;
			// 		}

			// 	}
			// }

			if(!pipeExists){
				sprites.add(t);
			}
		}


	//check if collision happens and return true when does
	public boolean isThereACollision(Sprite sprite){
			if(sprite.isPipe() == true){
				if(mario.Xval + mario.w < sprite.Xval ){
					//mario not colliding from left to right
					return false;
				}
				if(mario.Xval > sprite.Xval + sprite.w){
					//mario not colliding from right to left
					return false;
				}
				if(mario.Yval + mario.h < sprite.Yval){
					//colliding from top to bottom
					return false;
				} // assumes bigger is downward
				if(mario.Yval > sprite.Yval + sprite.h){
					return false;
				} // assumes bigger is downward
				// System.out.println("mario coliding");
				
			}
			return true;
		}
		

	
}
