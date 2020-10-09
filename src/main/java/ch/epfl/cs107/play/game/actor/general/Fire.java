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

public class Fire extends GameEntity implements Actor{

	
	private ImageGraphics fireGraphics;
	
	
	
	public Fire(ActorGame game, boolean fixed, Vector position,String image,float width,float height) {
		super(game, fixed, position);
		
		
		if(image == null || image.equals(""))
		{
			throw new IllegalArgumentException("Image file not found !");
		}
		// for invalid arguments
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
		pBuilder.setGhost(true);
		pBuilder.build();
		
		
		fireGraphics = new ImageGraphics(image, width, height);
		fireGraphics.setAlpha(1.0f);
		fireGraphics.setDepth(0.0f);
		fireGraphics.setParent(super.getEntity());
						
	}


	@Override
	public void draw(Canvas canvas) {
		fireGraphics.draw(canvas);
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