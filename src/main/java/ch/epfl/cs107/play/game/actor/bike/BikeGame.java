package ch.epfl.cs107.play.game.actor.bike;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.bike.Level;
import ch.epfl.cs107.play.game.actor.bike.Level1;
import ch.epfl.cs107.play.game.actor.bike.Level2;
import ch.epfl.cs107.play.game.actor.bike.Level3;
import ch.epfl.cs107.play.game.actor.bike.Level4;
import ch.epfl.cs107.play.game.actor.bike.Level5;
import ch.epfl.cs107.play.game.actor.bike.Level6;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class BikeGame extends ActorGame implements GameWithLevels 
{
	private List<Level> list;
	private Level currentLevel;
	private int i = 0;
	private FileSystem newFileSystem;

	//overrides begin method, creates list and sets which level is current
	@Override
	public boolean begin(Window window, FileSystem fileSystem)
	{
		super.begin(window, fileSystem);
		newFileSystem = fileSystem;
		list = createLevelList();
		currentLevel = list.get(i);
		newLevel(currentLevel);
		return true;
	}

	//this algorithm makes the smooth transition between levels and in case of death, resets level
	//this also deals with closing the application if the player chooses not to play again
	@Override
	public void update(float deltaTime)
	{
		super.update(deltaTime);
		if (currentLevel.isGameOver())
		{
			if (currentLevel.getAlpha() < 0)
			{

				currentLevel.setAlpha(1f);  //reset transparency back to 1 (opaque)
				if (currentLevel.hasWon())
				{
					if (i < list.size() - 1)  //case last level isn't reached yet
						nextLevel();
					
					else if(currentLevel instanceof Level6)
					{
						if(((Level6)currentLevel).replay()) //case user wants to repeat, sets current level, Level1
						{
							nextLevel();
						} 
						else
						{
							System.exit(-1); //case user wants to close the game
						}
					}
					
				} 
				
				else
					resetLevel();  //case level is lost , resets the current level
			}

			else
			{
				currentLevel.setAlpha(currentLevel.getAlpha() - deltaTime / 2);
			}
		}
	}

	@Override
	public void end()
	{
	}
	
	//list of levels
	protected List<Level> createLevelList()
	{
		List<Level> list = new ArrayList<Level>();
		list.add(new Level1());
		list.add(new Level2());
		list.add(new Level3());
		list.add(new Level4());
		list.add(new Level5());
		list.add(new Level6());

		return list;
	}

	//setting the new game and adding actors
	public void newLevel(Level l)
	{

		l.setGame(this);
		l.createAllActors();
		super.addActor(l);
	}

	//removing old level's actors and starts the next one
	@Override
	public void nextLevel()
	{

		Level temp = currentLevel;
		temp.removeAllActors();
		++i; 								// transition to next level
		i = i % list.size(); 					// transition between last and first level
		currentLevel = list.get(i);
		newLevel(currentLevel);
		this.begin((Window) getCanvas(), newFileSystem);
	}

	//resets the current level
	@Override
	public void resetLevel()
	{
		this.begin((Window) getCanvas(), newFileSystem);
	}
}