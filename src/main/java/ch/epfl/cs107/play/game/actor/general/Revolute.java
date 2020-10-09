
package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;

import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Revolute extends GameEntity implements Actor {

	private Entity block;
	private Entity plank;

	private float blockWidth = 10.0f;
	private float blockHeight = 1.0f;
	private float plankWidth = 10.0f;
	private float plankHeight = 0.2f;
	private ImageGraphics plankGraphics;

	public Revolute(ActorGame game, boolean fixed, Vector position)
	{
		super(game, fixed, position);

		EntityBuilder entityBuilder = getOwner().createEntityBuilder();

		// block
		entityBuilder.setFixed(true);
		entityBuilder.setPosition(position);
		block = entityBuilder.build();
		PartBuilder partBuilder = block.createPartBuilder();
		Polygon polygon = new Polygon(new Vector(0.0f, 0.0f), new Vector(10.0f, 0.0f), new Vector(10.0f, 1.0f),
				new Vector(0.0f, 1.0f));
		partBuilder.setShape(polygon);
		partBuilder.setFriction(1.5f);


		// plank
		entityBuilder.setFixed(false);
		entityBuilder.setPosition(position.add(-5f, plankWidth));
		plank = entityBuilder.build();

		PartBuilder partBuilder2 = plank.createPartBuilder();
		Polygon polygon1 = new Polygon(new Vector(0.0f, 0.0f), new Vector(10.0f, 0.0f), new Vector(10.0f, 0.2f),
				new Vector(0.0f, 0.2f));
		partBuilder2.setShape(polygon1);
		partBuilder2.setFriction(3f);
		partBuilder2.build();

		plankGraphics = new ImageGraphics("wood.3.png", 10.0f, 0.2f);
		plankGraphics.setAlpha(1.0f);
		plankGraphics.setDepth(0.0f);
		plankGraphics.setParent(plank);

		// creating the constraint 
		RevoluteConstraintBuilder revoluteConstraintBuilder = getOwner().createRevoluteConstraintBuilder();
		revoluteConstraintBuilder.setFirstEntity(block);
		revoluteConstraintBuilder.setFirstAnchor(new Vector(blockWidth / 2, (blockHeight * 7) / 4));
		revoluteConstraintBuilder.setSecondEntity(plank);
		revoluteConstraintBuilder.setSecondAnchor(new Vector(plankWidth / 2, plankHeight / 2));
		revoluteConstraintBuilder.setInternalCollision(true);
		revoluteConstraintBuilder.setLimitEnabled(true);
		revoluteConstraintBuilder.setLowerAngleLimit((float) (-Math.PI / 8));
		revoluteConstraintBuilder.setUpperAngleLimit((float) (Math.PI / 8));
		revoluteConstraintBuilder.build();

	}// end of constructor

	@Override
	public void draw(Canvas canvas)
	{
		plankGraphics.draw(canvas);
		
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