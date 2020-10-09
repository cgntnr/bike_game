/*
 *	Author:      CAGIN TANIR
 *	Date:        Dec 5, 2017
 */
package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Shape;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Rope extends GameEntity implements Actor {
	
	private ImageGraphics ballGraphics;
	private float ballRadius = 0.5f;
	private Entity fixedObject;
	private float fixedObjectWidth = 1f;
	private float fixedObjectHeight = 1f;
	private Entity ball;
	private ImageGraphics fixedObjectGraphics;

	public Rope(ActorGame game, boolean fixed, Vector position, Vector fixedPosition, Vector ballPosition)
	{
		super(game, fixed, position);
		
		if(fixedPosition == null || ballPosition == null)
		{
			throw new IllegalArgumentException("Positions hasn't been initialized !");
		}
		
		Circle circle = new Circle(ballRadius);
		EntityBuilder entityBuilder = getOwner().createEntityBuilder();

		entityBuilder.setFixed(false);
		entityBuilder.setPosition(new Vector(0.6f, 4.0f));
		ball = entityBuilder.build();

		PartBuilder partBuilder = ball.createPartBuilder();
		partBuilder.setShape(circle);
		partBuilder.build();

		Polygon polygon = new Polygon(new Vector(0.0f, 0.0f), new Vector(1.0f, 0.0f), new Vector(1.0f, 1.0f),
				new Vector(0.0f, 1.0f));

		entityBuilder.setFixed(true);
		entityBuilder.setPosition(fixedPosition);
		fixedObject = entityBuilder.build();

		PartBuilder partBuilder2 = fixedObject.createPartBuilder();
		partBuilder2.setShape(polygon);
		partBuilder2.setFriction(1.5f);
		partBuilder2.build();

		//creating the rope constraint 
		RopeConstraintBuilder ropeConstraintBuilder = getOwner().createRopeConstraintBuilder();
		ropeConstraintBuilder.setFirstEntity(fixedObject);
		ropeConstraintBuilder.setFirstAnchor(new Vector(fixedObjectWidth / 2, fixedObjectHeight / 2));
		ropeConstraintBuilder.setSecondEntity(ball);
		ropeConstraintBuilder.setSecondAnchor(Vector.ZERO);
		ropeConstraintBuilder.setMaxLength(6.0f);
		ropeConstraintBuilder.setInternalCollision(true);
		ropeConstraintBuilder.build();

		fixedObjectGraphics = new ImageGraphics("torch.lit.1.png", 1, 1);
		fixedObjectGraphics.setParent(fixedObject);

		ballGraphics = new ImageGraphics("stone.11.png", 2.0f * ballRadius, 2.0f * ballRadius, new Vector(0.5f, 0.5f));
		ballGraphics.setParent(ball);

	}// end of constructor

	@Override
	public void draw(Canvas canvas)
	{
		ballGraphics.draw(canvas);

		//drawing of chain between fixed object and ball
		Shape chain = new Polyline(fixedObject.getTransform().onPoint(fixedObjectWidth / 2, fixedObjectHeight / 2),
				ball.getPosition().add(0, 0));
		canvas.drawShape(chain, Transform.I, Color.WHITE, Color.BLUE, 0.1f, 1f, 0f);

		fixedObjectGraphics.draw(canvas);

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