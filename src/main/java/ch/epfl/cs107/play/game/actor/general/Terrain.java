/*
 *	Author:      CAGIN TANIR
 *	Date:        Nov 30, 2017
 */
package ch.epfl.cs107.play.game.actor.general;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Terrain extends GameEntity implements Actor {

	private Polyline line;
	private ShapeGraphics lineGraphics;

	//the terrain on which bike goes, uses polyline to draw
	public Terrain(ActorGame game, Vector position, float... points)
	{
		super(game, true, position);
		PartBuilder pBuilder = super.getEntity().createPartBuilder();
		line = new Polyline(points);

		pBuilder.setShape(line);
		pBuilder.build();

		lineGraphics = new ShapeGraphics(line, Color.RED, Color.BLUE, .1f, 1.f, 0);

	}

	@Override
	public void draw(Canvas canvas)
	{
		lineGraphics.draw(canvas);
	}

	@Override
	public Transform getTransform()
	{
		return this.getEntity().getTransform();
	}

	@Override
	// Terrain doesn't move
	public Vector getVelocity()
	{
		return Vector.ZERO;
	}

	public Polyline getLine()
	{
		return line;
	}

}