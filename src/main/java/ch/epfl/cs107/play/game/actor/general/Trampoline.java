/*
 *	Author:      CAGIN TANIR
 *	Date:        Nov 30, 2017
 */
package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WeldConstraintBuilder;
import ch.epfl.cs107.play.window.Canvas;

public class Trampoline extends Trigger {

	private Entity block;
	private float blockWidth = 1f;
	private float blockHeight = 1f;
	private ImageGraphics blockGraphics;

	//it's a trigger , so it applies impulse to bike when triggered
	public Trampoline(ActorGame game, Vector position)
	{
		super(game, false, position, "wood.3.png", 4f, 0.2f);

		EntityBuilder entityBuilder = getOwner().createEntityBuilder();
		PartBuilder pBuilder = super.getEntity().createPartBuilder();
		Polygon polygon = new Polygon(new Vector(0.0f, 0.0f), new Vector(1f, 0.0f), new Vector(1f, 1f),
				new Vector(0.0f, 1f));

		entityBuilder.setFixed(true);
		entityBuilder.setPosition(position);
		block = entityBuilder.build();

		pBuilder.setFriction(1f);
		pBuilder.setShape(polygon);
		pBuilder.build();

		blockGraphics = new ImageGraphics("stone.4.png", 1f, 1f);
		blockGraphics.setAlpha(1.0f);
		blockGraphics.setDepth(0.0f);
		blockGraphics.setParent(block);

		//creating constraint builder, this is just to fix trampoline to the block
		WeldConstraintBuilder weldConstraintBuilder = getOwner().createWeldConstraintBuilder();
		weldConstraintBuilder.setFirstEntity(block);
		weldConstraintBuilder.setFirstAnchor(new Vector(blockWidth, (blockHeight / 2)));
		weldConstraintBuilder.setSecondEntity(this.getEntity());
		weldConstraintBuilder.setSecondAnchor(new Vector(0f, 0.05f));
		weldConstraintBuilder.setInternalCollision(true);
		weldConstraintBuilder.setFrequency(6f);

		weldConstraintBuilder.setDamping(0.1f);
		weldConstraintBuilder.build();

	}

	@Override
	public void update(float deltaTime)
	{

	}

	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		blockGraphics.draw(canvas);

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