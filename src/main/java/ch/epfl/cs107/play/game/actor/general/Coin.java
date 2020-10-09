package ch.epfl.cs107.play.game.actor.general;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.bike.Level;
import ch.epfl.cs107.play.game.actor.general.Trigger;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.Vector;

public class Coin extends Trigger
{
	//constructor
	public Coin(ActorGame game, Vector position)
	{
		super(game, true, position, "coin.gold.png", 1f, 1f);
	}
	
	@Override
	public void beginContact(Contact contact)
	{
		//in case of contact , if not triggered before
		if(!super.hasTriggered())
		{
			Level.coinScore++;
			super.setAlpha(0);
			
		}
		super.beginContact(contact);
	}

}
