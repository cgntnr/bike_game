/*
 *	Author:      CAGIN TANIR
 *	Date:        Nov 28, 2017
 */
package ch.epfl.cs107.play.game.tutorial;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.BasicContactListener;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class ContactGame implements Game {

	float ballRadius = 0.5f;
	float blockWidth = 10.0f;
	float blockHeight = 1.0f;

	private Window window;
	private World world;

	private Entity block;
	private Entity ball;

	// graphical representation of the body
	private ImageGraphics blockGraphics;
	private ShapeGraphics ballGraphics;

	// listener
	private BasicContactListener contactListener;

	@Override
	public boolean begin(Window window, FileSystem fileSystem)
	{

		this.window = window;
		world = new World();
		world.setGravity(new Vector(0.0f, -9.81f));

		EntityBuilder entityBuilder = world.createEntityBuilder();
		entityBuilder.setFixed(true);
		entityBuilder.setPosition(new Vector(-5.0f, -1.0f));
		block = entityBuilder.build();

		PartBuilder partBuilder = block.createPartBuilder();
		Polygon polygon = new Polygon(new Vector(0.0f, 0.0f), new Vector(10.0f, 0.0f), new Vector(10.0f, 1.0f),
				new Vector(0.0f, 1.0f));

		partBuilder.setShape(polygon);
		partBuilder.setFriction(1.5f);
		partBuilder.build();

		Circle circle = new Circle(ballRadius);

		entityBuilder.setFixed(false);
		entityBuilder.setPosition(new Vector(0.0f, 2.0f));
		ball = entityBuilder.build();

		PartBuilder partBuilder2 = ball.createPartBuilder();
		partBuilder2.setShape(circle);
		partBuilder2.build();

		blockGraphics = new ImageGraphics("stone.broken.4.png", 10, 1);
		blockGraphics.setAlpha(1.0f);
		blockGraphics.setDepth(0.0f);
		blockGraphics.setParent(block);

		ballGraphics = new ShapeGraphics(circle, Color.BLUE, Color.BLUE, .1f, 1, 0);
		ballGraphics.setAlpha(1.0f);
		ballGraphics.setDepth(0.0f);
		ballGraphics.setParent(ball);

		// associating listener to the ball
		contactListener = new BasicContactListener();
		ball.addContactListener(contactListener);

		return true;
	}

	@Override
	public void update(float deltaTime)
	{

		int numberOfCollisions = contactListener.getEntities().size();

		if (numberOfCollisions > 0)
		{
			ballGraphics.setFillColor(Color.RED);
		}
		ballGraphics.draw(window);

		window.setRelativeTransform(Transform.I.scaled(10.0f));

		world.update(deltaTime);

		blockGraphics.draw(window);
		ballGraphics.draw(window);

	}

	@Override
	public void end()
	{

	}

}
