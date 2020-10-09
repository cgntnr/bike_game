package ch.epfl.cs107.play.game.actor.crate;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Crate extends GameEntity implements Actor
{	
		
	private ImageGraphics graphics;

	//constructor
	public Crate(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
	
		PartBuilder pBuilder = super.getEntity().createPartBuilder();
		Polygon polygon = new Polygon(new Vector(0.0f, 0.0f), 
									  new Vector(1.0f, 0.0f), 
									  new Vector(1.0f, 1.0f),
									  new Vector(0.0f, 1.0f));
		pBuilder.setShape(polygon);
		pBuilder.build();
		
		
		graphics = new ImageGraphics("stone.broken.4.png", 1, 1);
		graphics.setAlpha(1.0f);
		graphics.setDepth(0.0f);
		graphics.setParent(super.getEntity());
	}
	
	//drawings
	@Override
	public void draw(Canvas canvas)
	{
		graphics.draw(canvas);
	}

	@Override
	public Transform getTransform()
	{
		return this.getEntity().getTransform();
	}

	@Override
	public Vector getVelocity()
	{
		return this.getEntity().getVelocity();
	}

}