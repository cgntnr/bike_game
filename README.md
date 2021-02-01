# bike_game
A bike game for the Initiation à la Programmation course at EPFL. It uses JBox2D, a Java Physics Engine. For convenience, a runnable jar file is included. 
This can be run via Terminal. 

The basic controls are implemented as below. 

 - Up Button : gas for the bike
-  Down Button : breaks for the bike
-  Left Button : applying angular force to tilt left
- Right Button : applying angular force to tilt right.

The game consists of 6 levels. They are not necessarily easiest to hardest but rather uses different game logics for different extensions.

The names of the levels are written like Level1, Level2, etc. in project structure but their names are listed here:

Level1 - “Incoming missiles”

Level2 - “Take the pick or burn”

Level3 - “Pitfall”

Level4 - “Run, run, run”

Level5 - “Kinda sticky, kinda slippery “

Level6- “Mixed – up “

**************************************************************************
Level1 - “Incoming missiles”:

This level consists of 2 fixed blocks which you have to pass by applying enough angular force to the bike. Then there is a pit that you have to pass. Before the pit there is a pick that you can’t avoid. When you take it, 5 missiles fall into the pit. If you don’t slow down, you will be hit by those missiles. Either you can wait for one missile and pass it before the second one arrives. Or you can wait for all of them to drop and then jump across. This concludes the first level.

Level2 – “Take the pick or burn”:

This level consists of a hill and down the hill, there is a fire waiting for the bike. If you ride too fast and jump up the hill with a great velocity, you will skip the pick and fall into the 
fire. But if you ride slowly, you will get the pick (star) which will create a revolute. Then the biker will safely pass the obstacle. Afterwards there awaits another fire and to overcome this you have to gently push block into the fire, so again you will get a safe passage.  This level ends after the second fire.

Level3 – “Pitfall”:

In this level, there are two pits to pass. For the first one you should use the trampoline, but you have to ride onto trampoline in a good angle. If not, the biker will find itself in the darkness of the pit. Then another pit will come up, top of it, a rope obstacle is oscillating. You have the wait for the right moment in order to pass this one. Then the level comes to an end.

Level4 – “Run, run, run”:

As you can figure out, in this level you have to ride as soon as the level starts. If not, you risk getting hit by one of stones coming from up above. If you wait for them to fall down and try to pass the level, it would be a lot of effort because the stones are pretty heavy and they’re in vertical position. So, we recommend to “run, run, run “.

Level5 – “Kinda sticky, kinda slippery”:

In this level, there is a hill. And again, if you ride too fast, you will not be able to pass. Safety first, right? Well, in this level you have to be fast in the right place, right time. So down the hill there is a green stuff which is “kinda sticky, kinda slippery”. If you don’t take the pick, which is situated just as the hill goes down, you won’t be able to pass that obstacle. The pick gives you a speed boost, with that, the biker can pass the obstacle. If you didn’t take the pick, you can always go back and use it. When you pass those, safe and sound there is another pit waiting for you with a hanging rope. Again, with the right timing, a piece of cake. Finally, the level comes to end.

Level6 – “Mixed-up”:

Level is named like this because it uses variety of extensions. First the biker will face a revolute obstacle which can easily be passed by riding with enough velocity. If you don’t hang around too much on the revolute, you are good to go. But the difficulty of it comes from the rope situated right after the revolute. So, either you wait for the right moment at the beginning or stop before the rope obstacle. When you pass the first two, you will see a gravity field waiting for you. Ride into that field with almost zero velocity so that you can float onto higher ground easily, if you ride fast into the field, you might be trapped in a loop. 

The game ends here. Now you will be asked if you would like to play again. You can go back to Level1 by pressing Y(yes) or close the game by pressing N(no).	

Enjoy! 

