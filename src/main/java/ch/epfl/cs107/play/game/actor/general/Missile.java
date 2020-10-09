
package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Missile extends GameEntity implements Actor{

	
	private ImageGraphics missileGraphics;
	
	
	// constructing the missiles as same logic as blocks, in order not to be confusing, we created another missile
	//class, so constructing another object would be clear to read in level classes
	public Missile(ActorGame game, Vector position,String image,float width,float height) {
		super(game, false, position);
		
		if(image == null || image.equals(""))
		{
			throw new IllegalArgumentException("Image file not found !");
		}
		
		if(width<0){
			throw new IllegalArgumentException("Width can't be negative!");
		}
		if(height<0){
			throw new IllegalArgumentException("Height can't be negative!");
		}
		
		PartBuilder pBuilder = super.getEntity().createPartBuilder();
		Polygon polygon = new Polygon(new Vector(0.0f, 0.0f), 
									  new Vector(width, 0.0f), 
									  new Vector(width, height),
									  new Vector(0.0f, height));
		pBuilder.setFriction(0.5f);
		pBuilder.setShape(polygon);
		pBuilder.build();
		
		
		missileGraphics = new ImageGraphics(image, width, height);
		missileGraphics.setAlpha(1.0f);
		missileGraphics.setDepth(0.0f);
		missileGraphics.setParent(super.getEntity());
						
	}


	@Override
	public void draw(Canvas canvas) {
		missileGraphics.draw(canvas);
	}

	@Override
	public Transform getTransform() {
		return this.getEntity().getTransform();
	}

	@Override
	public Vector getVelocity() {
		return this.getEntity().getVelocity();
	}


}
