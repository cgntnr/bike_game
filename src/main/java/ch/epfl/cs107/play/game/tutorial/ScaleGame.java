/*
 *	Author:      CAGIN TANIR
 *	Date:        Nov 27, 2017
 */
package ch.epfl.cs107.play.game.tutorial;

import com.sun.glass.events.KeyEvent;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class ScaleGame implements Game {

	float ballRadius = 0.5f;
	float blockWidth = 10.0f;
	float blockHeight = 1.0f;
	float plankWidth = 5.0f;
	float plankHeight = 0.2f;

	private Window window;

	private World world;
	private Entity block;
	private Entity ball;
	private Entity plank;

	// graphical representation of the body
	private ImageGraphics blockGraphics;
	private ImageGraphics ballGraphics;
	private ImageGraphics plankGraphics;

	@Override
	public boolean begin(Window window, FileSystem fileSystem)
	{

		this.window = window;

		world = new World();
		world.setGravity(new Vector(0.0f, -9.81f));

		EntityBuilder entityBuilder = world.createEntityBuilder();

		// block
		entityBuilder.setFixed(true);
		entityBuilder.setPosition(new Vector(-5.0f, -1.0f));
		block = entityBuilder.build();
		PartBuilder partBuilder = block.createPartBuilder();

		Polygon polygon = new Polygon(new Vector(0.0f, 0.0f), new Vector(10.0f, 0.0f), new Vector(10.0f, 1.0f),
				new Vector(0.0f, 1.0f));
		partBuilder.setShape(polygon);
		partBuilder.setFriction(1.5f);
		partBuilder.build();

		blockGraphics = new ImageGraphics("stone.broken.4.png", 10.0f, 1.0f);
		blockGraphics.setAlpha(1.0f);
		blockGraphics.setDepth(0.0f);
		blockGraphics.setParent(block);

		// plank

		entityBuilder.setFixed(false);
		entityBuilder.setPosition(new Vector(-2.5f, 0.8f));
		plank = entityBuilder.build();

		PartBuilder partBuilder2 = plank.createPartBuilder();
		Polygon polygon1 = new Polygon(new Vector(0.0f, 0.0f), new Vector(5.0f, 0.0f), new Vector(5.0f, 0.2f),
				new Vector(0.0f, 0.2f));
		partBuilder2.setShape(polygon1);
		partBuilder2.setFriction(1.5f);
		partBuilder2.build();

		plankGraphics = new ImageGraphics("wood.3.png", 5.0f, 0.2f);
		plankGraphics.setAlpha(1.0f);
		plankGraphics.setDepth(0.0f);
		plankGraphics.setParent(plank);

		// ball
		Circle circle = new Circle(ballRadius);

		entityBuilder.setFixed(false);
		entityBuilder.setPosition(new Vector(0.5f, 4.0f));
		ball = entityBuilder.build();

		PartBuilder partBuilder3 = ball.createPartBuilder();
		partBuilder3.setShape(circle);
		partBuilder3.setFriction(1.5f);
		partBuilder3.build();

		ballGraphics = new ImageGraphics("explosive.11.png", 2.0f * ballRadius, 2.0f * ballRadius,
				new Vector(0.5f, 0.5f));
		ballGraphics.setAlpha(1.0f);
		ballGraphics.setDepth(0.0f);
		ballGraphics.setParent(ball);

		RevoluteConstraintBuilder revouluteConstraintBuilder = world.createRevoluteConstraintBuilder();
		revouluteConstraintBuilder.setFirstEntity(block);
		revouluteConstraintBuilder.setFirstAnchor(new Vector(blockWidth / 2, (blockHeight * 7) / 4));
		revouluteConstraintBuilder.setSecondEntity(plank);
		revouluteConstraintBuilder.setSecondAnchor(new Vector(plankWidth / 2, plankHeight / 2));
		revouluteConstraintBuilder.setInternalCollision(true);
		revouluteConstraintBuilder.build();

		return true;
	}

	@Override
	public void update(float deltaTime)
	{

		window.setRelativeTransform(Transform.I.scaled(10.0f));

		if (window.getKeyboard().get(KeyEvent.VK_LEFT).isDown())
		{
			ball.applyAngularForce(10.0f);
		} else if (window.getKeyboard().get(KeyEvent.VK_RIGHT).isDown())
		{
			ball.applyAngularForce(-10.0f);
		}

		world.update(deltaTime);

		plankGraphics.draw(window);
		blockGraphics.draw(window);
		ballGraphics.draw(window);

	}

	@Override
	public void end()
	{

	}

}
