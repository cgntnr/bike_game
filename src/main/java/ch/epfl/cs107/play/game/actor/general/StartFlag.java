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

public class StartFlag extends GameEntity implements Actor{

	
	private ImageGraphics startFlagGraphics;
	
	
	//start flag showing where to start , visual purposes only
	public StartFlag(ActorGame game, Vector position) {
		super(game,true, position);
		
		PartBuilder pBuilder = super.getEntity().createPartBuilder();
		Polygon polygon = new Polygon(new Vector(0.0f, 0.0f), 
									  new Vector(1f, 0.0f), 
									  new Vector(1f, 1f),
									  new Vector(0.0f, 1f));
		
		pBuilder.setFriction(0.5f);
		pBuilder.setShape(polygon);
		pBuilder.build();
		
		
		startFlagGraphics = new ImageGraphics("flag.green.png", 1f, 1f);
		startFlagGraphics.setAlpha(1.0f);
		startFlagGraphics.setDepth(0.0f);
		startFlagGraphics.setParent(super.getEntity());
						
	}


	@Override
	public void draw(Canvas canvas) {
		startFlagGraphics.draw(canvas);
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

