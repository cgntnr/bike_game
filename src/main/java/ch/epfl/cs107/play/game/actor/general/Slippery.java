/*
 *	Author:      CAGIN TANIR
 *	Date:        Dec 8, 2017
 */
package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.math.Vector;

public class Slippery extends Trigger{
	// slippery object which you can't pass without using the speed boost
	public Slippery(ActorGame game, boolean fixed, Vector position, String pathForImage, float width, float height) {
		super(game, fixed, position, pathForImage, width, height);
		
	}
	

}
