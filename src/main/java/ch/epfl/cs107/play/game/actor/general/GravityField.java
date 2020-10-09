package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.general.Trigger;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class GravityField extends Trigger {

	private ImageGraphics arrowGraphics1;
	private ImageGraphics arrowGraphics2;
	private Entity arrowBox1;
	private Entity arrowBox2;

	public GravityField(ActorGame game, Vector position)
	{
		super(game, true, position, "metal.hollow.6.png", 4f, 8f);

		Polygon polygon2 = new Polygon(new Vector(0.0f, 0.0f), new Vector(1.0f, 0.0f), new Vector(1.0f, 1.0f),
				new Vector(0.0f, 1.0f));

		EntityBuilder entityBuilder = getOwner().createEntityBuilder();

		PartBuilder partBuilder = super.getEntity().createPartBuilder();

		entityBuilder.setFixed(true);
		entityBuilder.setPosition(new Vector(75.5f, -2f));
		arrowBox1 = entityBuilder.build();

		entityBuilder.setFixed(true);
		entityBuilder.setPosition(new Vector(75.5f, -4f));
		arrowBox2 = entityBuilder.build();

		partBuilder.setGhost(true);
		partBuilder.setShape(polygon2);
		partBuilder.build();

		arrowGraphics1 = new ImageGraphics("arrow.green.png", 1f, 1f);
		arrowGraphics1.setAlpha(0.7f);
		arrowGraphics1.setParent(arrowBox1);

		arrowGraphics2 = new ImageGraphics("arrow.green.png", 1f, 1f);
		arrowGraphics2.setAlpha(0.7f);
		arrowGraphics2.setParent(arrowBox2);

	}

	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		arrowGraphics1.draw(canvas);
		arrowGraphics2.draw(canvas);

	}

}
