package ch.epfl.cs107.play.game.actor.crate;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Window;

public class CrateGame extends ActorGame
{
	private Crate c1;
	private Crate c2;
	private Crate c3;
	
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem)
	{
		super.begin(window, fileSystem);
		c1 = new Crate(this, false, new Vector(0.0f, 5.0f));
		c2 = new Crate(this, false, new Vector(0.2f, 7.0f));
		c3 = new Crate(this, false, new Vector(2.0f, 6.0f));

		super.addActor(c1);
		super.addActor(c2);
		super.addActor(c3);
		return true;
	}
}