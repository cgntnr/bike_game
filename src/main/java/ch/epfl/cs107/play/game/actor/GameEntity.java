package ch.epfl.cs107.play.game.actor;


import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Vector;

public abstract class GameEntity {
	
	
	private Entity entity;
	private ActorGame actorGame;
	
	//building GameEntities in constructor
	 public GameEntity(ActorGame game, boolean fixed , Vector position)
			{	
		 		//invalid null parameter for game
		 		if(game == null){
		 			throw new NullPointerException();
		 		}		 		
				actorGame = game;
		 		EntityBuilder entityBuilder = actorGame.createEntityBuilder();
		 		entityBuilder.setFixed(fixed);
				entityBuilder.setPosition(position);
				entity = entityBuilder.build();
			}
			
			public GameEntity(ActorGame game, boolean fixed )
			{
				this(game,fixed,null);
			}
	
	public void destroy()
	{
		entity.destroy();
	}
	
	//getters used in various parts of project to reach entity and owner 
	protected Entity getEntity()
	{  
		return entity;
	}
	
	protected ActorGame getOwner()
	{
		return actorGame;
	}
	
	public void addContactListener(ContactListener listener) 
	{
		entity.addContactListener(listener);
	}
		
	
}
