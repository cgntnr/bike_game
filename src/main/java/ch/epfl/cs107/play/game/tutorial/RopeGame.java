/*
 *	Author:      CAGIN TANIR
 *	Date:        Nov 27, 2017
 */
package ch.epfl.cs107.play.game.tutorial;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class RopeGame implements Game {

	float ballRadius = 0.6f;
	float blockWidth = 1.0f;
	float blockHeight = 1.0f;

	private Window window;

	private World world;
	private Entity block;
	private Entity ball;

	// graphical representation of the body
	private ImageGraphics graphics;
	private ShapeGraphics ballgraphics;

	@Override
	public boolean begin(Window window, FileSystem fileSystem)
	{

		// Store context
		this.window = window;

		world = new World();
		world.setGravity(new Vector(0.0f, -9.81f));

		EntityBuilder entityBuilder = world.createEntityBuilder();
		entityBuilder.setFixed(true);
		entityBuilder.setPosition(new Vector(1.f, 0.5f));
		block = entityBuilder.build();

		PartBuilder partBuilder = block.createPartBuilder();

		Polygon polygon = new Polygon(new Vector(0.0f, 0.0f), new Vector(1.0f, 0.0f), new Vector(1.0f, 1.0f),
				new Vector(0.0f, 1.0f));
		partBuilder.setShape(polygon);
		partBuilder.setFriction(1.5f);
		partBuilder.build();

		Circle circle = new Circle(ballRadius);

		entityBuilder.setFixed(false);
		entityBuilder.setPosition(new Vector(0.6f, 4.0f));
		ball = entityBuilder.build();

		PartBuilder partBuilder2 = ball.createPartBuilder();
		partBuilder2.setShape(circle);
		partBuilder2.build();

		graphics = new ImageGraphics("stone.broken.4.png", 1, 1);
		graphics.setAlpha(1.0f);
		graphics.setDepth(0.0f);
		graphics.setParent(block);

		ballgraphics = new ShapeGraphics(circle, Color.BLUE, Color.RED, .1f, 1.f, 0);
		ballgraphics.setAlpha(1.0f);
		ballgraphics.setDepth(0.0f);
		ballgraphics.setParent(ball);

		// to attach ball and block
		RopeConstraintBuilder ropeConstraintBuilder = world.createRopeConstraintBuilder();
		ropeConstraintBuilder.setFirstEntity(block);
		ropeConstraintBuilder.setFirstAnchor(new Vector(blockWidth / 2, blockHeight / 2));
		ropeConstraintBuilder.setSecondEntity(ball);
		ropeConstraintBuilder.setSecondAnchor(Vector.ZERO);
		ropeConstraintBuilder.setMaxLength(6.0f);
		ropeConstraintBuilder.setInternalCollision(true);
		ropeConstraintBuilder.build();

		return true;
	}

	@Override
	public void update(float deltaTime)
	{

		window.setRelativeTransform(Transform.I.scaled(10.0f));

		world.update(deltaTime);

		graphics.draw(window);
		ballgraphics.draw(window);

	}

	@Override
	public void end()
	{

	}

}
