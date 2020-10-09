/*
 *	Author:      CAGIN TANIR
 *	Date:        Nov 22, 2017
 */
package ch.epfl.cs107.play.game.tutorial;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Window;

public class SimpleCrateGame implements Game {

	private Window window;

	private World world;

	private Entity block;

	private Entity crate;

	// graphical representation of the body
	private ImageGraphics graphics;

	private ImageGraphics crategraph;

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {

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

		// giving physical properties to block
		partBuilder.setShape(polygon);
		partBuilder.setFriction(1.5f);
		partBuilder.build();

		entityBuilder.setFixed(false);
		entityBuilder.setPosition(new Vector(0.2f, 4.0f));
		crate = entityBuilder.build();

		// giving physical properties to crate
		PartBuilder partBuilder2 = crate.createPartBuilder();
		partBuilder2.setShape(polygon);
		partBuilder2.build();

		// graphics of block
		graphics = new ImageGraphics("stone.broken.4.png", 1, 1);
		graphics.setAlpha(1.0f);
		graphics.setDepth(0.0f);
		graphics.setParent(block);

		// graphics of crate
		crategraph = new ImageGraphics("box.4.png", 1, 1);
		crategraph.setAlpha(1.0f);
		crategraph.setDepth(0.0f);
		crategraph.setParent(crate);

		return true;
	}

	@Override
	public void update(float deltaTime) {

		window.setRelativeTransform(Transform.I.scaled(10.0f));

		world.update(deltaTime);

		graphics.draw(window);
		crategraph.draw(window);

	}

	@Override
	public void end() {

	}

}
