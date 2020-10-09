/*
 *	Author:      CAGIN TANIR
 *	Date:        Nov 29, 2017
 */
package ch.epfl.cs107.play.game.actor;

import java.util.ArrayList;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RevoluteConstraintBuilder;
import ch.epfl.cs107.play.math.RopeConstraintBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WeldConstraintBuilder;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.math.World;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public abstract class ActorGame implements Game {
	private ArrayList<Actor> actorList;

	private Window window;
	private World world;

	private Vector viewCenter;
	private Vector viewTarget;
	private Positionable viewCandidate;
	private static final float VIEW_TARGET_VELOCITY_COMPENSATION = 0.2f;
	private static final float VIEW_INTERPOLATION_RATIO_PER_SECOND = 0.1f;
	private static final float VIEW_SCALE = 11.0f;

	//gets access for the keyboard
	public Keyboard getKeyboard()
	{
		return window.getKeyboard();
	}

	//gets access for the canvas
	public Canvas getCanvas()
	{
		return window;
	}
	
	//setting the world properties
	@Override
	public boolean begin(Window window, FileSystem fileSystem)
	{

		this.window = window;
		world = new World();
		world.setGravity(new Vector(0.0f, -9.81f));

		viewCenter = Vector.ZERO;
		viewTarget = Vector.ZERO;

		actorList = new ArrayList<Actor>();

		return true;
	}

	//updates world and draws each actor also interpolates camera
	@Override
	public void update(float deltaTime)
	{

		world.update(deltaTime);

		for (int i = 0; i < actorList.size(); ++i)
		{
			actorList.get(i).update(deltaTime);
		}

		for (Actor actor : actorList)
		{
			actor.draw(window);
		}

		// Update expected viewport center
		if (viewCandidate != null)
		{
			viewTarget = viewCandidate.getPosition()
					.add(viewCandidate.getVelocity().mul(VIEW_TARGET_VELOCITY_COMPENSATION));
		}
		// Interpolate with previous location
		float ratio = (float) Math.pow(VIEW_INTERPOLATION_RATIO_PER_SECOND, deltaTime);
		viewCenter = viewCenter.mixed(viewTarget, ratio);
		// Compute new viewport
		Transform viewTransform = Transform.I.scaled(VIEW_SCALE).translated(viewCenter);
		window.setRelativeTransform(viewTransform);

	}

	public void setViewCandidate(Positionable newViewCandidate)
	{
		viewCandidate = newViewCandidate;
	}
    
	// calling Builder methods in order not to use a getter for World 
	public EntityBuilder createEntityBuilder()
	{
		return world.createEntityBuilder();
	}

	public WheelConstraintBuilder createWheelConstraintBuilder()
	{
		return world.createWheelConstraintBuilder();

	}

	public RopeConstraintBuilder createRopeConstraintBuilder()
	{
		return world.createRopeConstraintBuilder();
	}

	public RevoluteConstraintBuilder createRevoluteConstraintBuilder()
	{
		return world.createRevoluteConstraintBuilder();
	}

	public WeldConstraintBuilder createWeldConstraintBuilder()
	{
		return world.createWeldConstraintBuilder();
	}
	//adding an actor to list
	public void addActor(Actor a)
	{

		actorList.add(a);
	}
	//removing an actor from the list
	public void removeActor(Actor a)
	{
		actorList.remove(a);
	}

	@Override
	public void end()
	{

	}

	//removing all actors from the array list at once
	public void removeAllActors()
	{
		for (int i = 0; i < actorList.size(); ++i)
		{
			actorList.remove(i);
		}
	}

}
