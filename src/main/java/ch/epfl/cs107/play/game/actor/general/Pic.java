/*
 *	Author:      CAGIN TANIR
 *	Date:        Dec 9, 2017
 */
package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.math.Vector;

//constructing picks (star images) in game to add different game plays
public class Pic extends Trigger{

	public Pic(ActorGame game, Vector position, String image) {
		super(game, true, position,image, 1f, 1f);
		
		//wrong file path for the image file
		if(image == null || image.equals(""))
		{
			throw new IllegalArgumentException("Image file not found !");
		}
	}

}
