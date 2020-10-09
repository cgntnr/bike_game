/*
 *	Author:      CAGIN TANIR
 *	Date:        Dec 8, 2017
 */
package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.math.Vector;

public class SpeedBoost extends Trigger{
	//when triggered gives impulse to the bike, again this could have been done with pick , but this way 
	//creates more readable levels
	public SpeedBoost(ActorGame game, boolean fixed, Vector position, String pathForImage, float width, float height) {
		super(game, fixed, position, pathForImage, width, height);
		
	}

}
