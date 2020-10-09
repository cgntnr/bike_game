package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Trigger extends GameEntity implements Actor, ContactListener {
	
	private ImageGraphics graphics;
	private boolean isTriggered = false;	//case the trigger is being contacted 
	private boolean hasTriggered = false;   //case the trigger has been contacted once

	//constructing trigger which is inherited by many classes , gives signals
	public Trigger(ActorGame game, boolean fixed, Vector position, String pathForImage, float width, float height)
	{
		super(game, fixed, position);

		//validating the parameters
		if(pathForImage == null || pathForImage.equals(""))
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
		Polygon polygon = new Polygon(new Vector(0.0f, 0.0f), new Vector(width, 0.0f), new Vector(width, height),
				new Vector(0.0f, height));
		pBuilder.setShape(polygon);
		pBuilder.setGhost(true);
		pBuilder.build();

		graphics = new ImageGraphics(pathForImage, width, height);
		graphics.setAlpha(1.0f);
		graphics.setDepth(0.0f);
		graphics.setParent(super.getEntity());
		addContactListener(this);
	}

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

	//setting hasTriggered to false means it has been triggered for once and we memorized it
	@Override
	public void beginContact(Contact contact)
	{
		hasTriggered = true;
		isTriggered = true;
	}

	//at the end of contact isTriggered is set to false meaning active contact has been lost
	@Override
	public void endContact(Contact contact)
	{
		isTriggered = false;
	}

	public boolean isTriggered()
	{
		return isTriggered;
	}

	public boolean hasTriggered()
	{
		return hasTriggered;
	}

	public void setParent(Entity e)
	{
		graphics.setParent(e);
	}
	public void setAlpha(float alpha)
	{
		graphics.setAlpha(alpha);
	}
}