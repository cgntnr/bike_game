package ch.epfl.cs107.play.game.actor.bike;

import java.awt.Color;
import com.sun.glass.events.KeyEvent;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.general.Wheel;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Contact;
import ch.epfl.cs107.play.math.ContactListener;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.EntityBuilder;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Polygon;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Bike extends GameEntity implements Actor {
	// instantiating of variables
	private Wheel leftWheel;
	private Wheel rightWheel;
	private Wheel wheel;
	private Entity hitbox;
	final float MAX_WHEEL_SPEED = 20.0f;

	private Circle head;
	private ShapeGraphics headGraphics;
	private ShapeGraphics bodyToRightGraphics;
	private ShapeGraphics leg1Graphics;  
	private ShapeGraphics leg2Graphics;
	private ShapeGraphics bodyToLeftGraphics;
	private ShapeGraphics victoryToLeftGraphics;
	private ShapeGraphics victoryToRightGraphics;

	private boolean toRight;  //checks if bike is going to right
	private boolean hasCrashed = false;  //checks if biker has crashed yet
	private boolean contactWithGhost = false; 
	private boolean contactWithObject = false;
	private boolean handsUp = false;  //signals the victory position

	private Vector rightKneeLocation = new Vector(0.1f, 0.9f);
	private Vector leftKneeLocation = new Vector(0.0f, 0.7f);
	private Vector rightFootLocation = new Vector(0.2f, 0.2f);
	private Vector leftFootLocation = new Vector(-0.1f, -0.0f);

	public Bike(ActorGame game, boolean fixed, Vector position)
	{
		super(game, fixed, position);

		// initializing the direction boolean
		toRight = true;

		// form of the hitbox
		Polygon polygon = new Polygon(0.0f, 0.5f, 0.5f, 1.0f, 0.0f, 2.0f, -0.5f, 1.0f);

		PartBuilder pBuilder = super.getEntity().createPartBuilder();

		// building the hitbox
		EntityBuilder entityBuilder2 = game.createEntityBuilder();
		entityBuilder2.setFixed(false);
		entityBuilder2.setPosition(position);
		hitbox = entityBuilder2.build();

		pBuilder.setShape(polygon);
		pBuilder.setFriction(4.5f);
		pBuilder.setGhost(true);
		pBuilder.build();

		// creating wheels
		rightWheel = new Wheel(game, fixed, position.add(1.0f, 0.0f));
		leftWheel = new Wheel(game, fixed, position.add(-1.0f, 0.0f));

		// attaching wheels
		rightWheel.attach(this.getEntity(), new Vector(1.0f, 0.0f), new Vector(0.5f, -1.0f));
		leftWheel.attach(this.getEntity(), new Vector(-1.0f, 0.0f), new Vector(-0.5f, -1.0f));

		// creating head and forming the graphics of head
		head = new Circle(0.2f, getHeadLocation());
		headGraphics = new ShapeGraphics(head, Color.CYAN, Color.CYAN, .1f, 1.f, 0);
		headGraphics.setParent(getEntity());

		wheel = leftWheel;

		// calling the method which attributes listener to each composant
		addContactListener(listener);
	}

	// drawing composants of bike and victory situation
	@Override
	public void draw(Canvas canvas)
	{
		rightWheel.draw(canvas);
		leftWheel.draw(canvas);
		headGraphics.draw(canvas);

		if (getDirection())
		{
			//boolean indicating the victory position for hands
			if (handsUp)
			{
				victoryToRightGraphics.draw(canvas);
				leg1Graphics.draw(canvas);
			} else 
				//normal drawing whilst going right
			{
				bodyToRightGraphics.draw(canvas);
				leg1Graphics.draw(canvas);
			}
		} else {
			//boolean indicating the victory position for hands whils going left
			if(handsUp)		
		{
			victoryToLeftGraphics.draw(canvas);
			leg2Graphics.draw(canvas);
		} 
			else
				//normal drawing whilst going left
		{
			bodyToLeftGraphics.draw(canvas);
			leg2Graphics.draw(canvas);
		}
			  }	
}

	@Override
	public Transform getTransform()
	{
		return this.getEntity().getTransform();
	}

	@Override
	public Vector getVelocity()
	{
		return getEntity().getVelocity();
	}

	// listener for the bike, anonymous class
	ContactListener listener = new ContactListener() {
		@Override
		public void beginContact(Contact contact)
		{
			if (contact.getOther().isGhost())
			{
				contactWithGhost = true;
			}

			// Case contact with finishLine
			else if (contact.getOwner().isGhost() && !contact.getOther().isGhost())
			{
				hasCrashed = true;
				contactWithObject = true;
			}
		}

		@Override
		public void endContact(Contact contact)
		{
			contactWithGhost = false;
			contactWithObject = false;
		}

	};

	public void update(float deltaTime)
	{
		// does the drawing of bike and composants
		drawings();

		// constantly changes position of knees in order to show pedaling
		doPedaling();

		// getting the direction
		if (getDirection())
		{
			wheel = leftWheel;
		} else
		{
			wheel = rightWheel;
		}

		// angular force to tilt left
		if (getOwner().getKeyboard().get(KeyEvent.VK_LEFT).isDown())
		{
			this.getEntity().applyAngularForce(20.0f);
		}
		// angular force to tilt right
		if (getOwner().getKeyboard().get(KeyEvent.VK_RIGHT).isDown())
		{
			this.getEntity().applyAngularForce(-20.0f);
		}
		// changing direction
		if (getOwner().getKeyboard().get(KeyEvent.VK_SPACE).isPressed())
		{
			toRight = !toRight;
		}
		// brake in order of direction
		if (getOwner().getKeyboard().get(KeyEvent.VK_DOWN).isDown())
		{
			if (getDirection())
			{
				wheel.power(20f);
			} else
				wheel.power(-20f);
		}
		// release breaks
		if (getOwner().getKeyboard().get(KeyEvent.VK_DOWN).isReleased())
		{
			wheel.relax();
		}
		// accelerating in order of direction
		if (getOwner().getKeyboard().get(KeyEvent.VK_UP).isDown())
		{
			if (getDirection())
			{
				if (leftWheel.getSpeed() < MAX_WHEEL_SPEED)
				{
					wheel.power(-20.0f);
				} else
					wheel.relax();
			}

			else
			{
				if (rightWheel.getSpeed() > -MAX_WHEEL_SPEED)
				{
					wheel.power(+20.0f);
				} else
					wheel.relax();
			}
		}
		// release gas
		if (getOwner().getKeyboard().get(KeyEvent.VK_UP).isReleased())
		{
			wheel.relax();
		}

	}// end of update

	// getting the Vector points of Polyline
	public Vector getHeadLocation()
	{
		return new Vector(0.0f, 1.75f);
	}

	public Vector getShoulderLocation()
	{
		return new Vector(-0.2f, 1.5f);
	}

	public Vector getHandLocation()
	{
		return new Vector(0.5f, 1.2f);
	}

	public Vector getElbowLocation()
	{
		return new Vector(0.2f, 1.1f);
	}

	public Vector getHipLocation()
	{
		return new Vector(-0.5f, 1.0f);
	}

	public Vector getRightKneeLocation()
	{
		return rightKneeLocation;
	}

	public Vector getLeftKneeLocation()
	{
		return leftKneeLocation;
	}

	public Vector getRightFootLocation()
	{
		return rightFootLocation;
	}

	public Vector getLeftFootLocation()
	{
		return leftFootLocation;
	}

	// returns true if going right
	public boolean getDirection()
	{
		return toRight;
	}

	// destroys the bike
	@Override
	public void destroy()
	{
		super.destroy();
		leftWheel.destroy();
		rightWheel.destroy();
	}

	// adds contact listener to composants of bike
	@Override
	public void addContactListener(ContactListener listener)
	{
		super.addContactListener(listener);
		hitbox.addContactListener(listener);
		leftWheel.addContactListener(listener);
		rightWheel.addContactListener(listener);
	}

	// tells if contact is with a ghost or non ghost or there is no contact
	public int contactSituation()
	{
		if (contactWithGhost)
		{
			return 1;
		}
		if (contactWithObject)
		{
			return 0;
		}
		return -1;
	}

	// to use applyForce method in Level through bike
	public void applyForce(Vector v)
	{
		this.getEntity().applyForce(v, super.getEntity().getPosition());
	}

	// to use applyImpulse method in Level through bike
	public void applyImpulse(Vector v)
	{
		this.getEntity().applyImpulse(v, super.getEntity().getPosition());
	}

	// contact with concrete objects (non-ghost objects)
	public boolean hasCrashed()
	{
		return hasCrashed;
	}

	// changes the boolean to signal arrival if finishLine
	public void setVictoryPosition()
	{
		handsUp = true;
	}

	// sets knee and foot location for the pedaling using angular position of motor wheel
	public void doPedaling()
	{
		rightKneeLocation = new Vector(
				(((float) (Math.cos(leftWheel.getAngularPosition() + (Math.PI / 2)))) * (0.15f)) + 0.1f,
				(((float) (Math.sin(leftWheel.getAngularPosition() + (Math.PI / 2)))) * (0.15f)) + 0.9f);
		leftKneeLocation = new Vector((((float) (Math.cos(leftWheel.getAngularPosition()))) * (0.15f) - 0.1f),
				((float) (Math.sin(leftWheel.getAngularPosition()))) * (0.15f) + 0.8f);

		if (getDirection())
		{
			rightFootLocation = rightKneeLocation.add(0.05f, -0.5f);
			leftFootLocation = leftKneeLocation.add(0.05f, -0.5f);
		} else
		{
			rightFootLocation = rightKneeLocation.add(0.3f, -0.6f);
			leftFootLocation = leftKneeLocation.add(0.0f, -0.6f);
		}
	}

	// gathers all the drawings of biker
	//biker is drawn with two polyline one continues from one hand to one leg
	//leg1 and leg2 are the other legs for right and left in this order
	public void drawings()
	{
		Polyline bodyToRight = new Polyline(getHandLocation(), getElbowLocation(), getShoulderLocation(),
				getHipLocation(), getRightKneeLocation(), getRightFootLocation());
		bodyToRightGraphics = new ShapeGraphics(bodyToRight, null, Color.CYAN, .1f, 1.f, 0);
		bodyToRightGraphics.setParent(getEntity());

		// adds the other leg as polyline whilst going right
		Polyline leg1 = new Polyline(getHipLocation(), getLeftKneeLocation(), getLeftFootLocation());
		leg1Graphics = new ShapeGraphics(leg1, null, Color.CYAN, .1f, 1.f, 0);
		leg1Graphics.setParent(getEntity());

		Polyline bodyToLeft = new Polyline(getHandLocation().add(-1f, 0f), getElbowLocation().add(-0.4f, 0f),
				getShoulderLocation().add(0.4f, 0.0f), getHipLocation().add(0.9f, 0f),
				getRightKneeLocation().add(-0.4f, -0.0f), getRightFootLocation().add(-0.5f, 0));
		bodyToLeftGraphics = new ShapeGraphics(bodyToLeft, null, Color.CYAN, .1f, 1.f, 0);
		bodyToLeftGraphics.setParent(getEntity());

		// adds the other leg as polyline whilst going left
		Polyline leg2 = new Polyline(getHipLocation().add(0.9f, 0), getLeftKneeLocation().add(-0.1f, 0),
				getLeftFootLocation().add(0.1f, 0));
		leg2Graphics = new ShapeGraphics(leg2, null, Color.CYAN, .1f, 1.f, 0);
		leg2Graphics.setParent(getEntity());

		// victory position to right
		Polyline victoryToRight = new Polyline(getHandLocation().add(0.2f, 0.5f), getElbowLocation().add(0.20f, 0.3f),
				getShoulderLocation(), getHipLocation(), getRightKneeLocation(), getRightFootLocation());
		victoryToRightGraphics = new ShapeGraphics(victoryToRight, null, Color.CYAN, .1f, 1.f, 0);
		victoryToRightGraphics.setParent(getEntity());

		// victory position to left
		Polyline victoryToLeft = new Polyline(getHandLocation().add(-1f, 0.8f), getElbowLocation().add(-0.6f, 0.5f),
				getShoulderLocation().add(0.4f, 0.0f), getHipLocation().add(0.9f, 0f),
				getRightKneeLocation().add(-0.4f, -0.0f), getRightFootLocation().add(-0.5f, 0));
		victoryToLeftGraphics = new ShapeGraphics(victoryToLeft, null, Color.CYAN, .1f, 1.f, 0);
		victoryToLeftGraphics.setParent(getEntity());
	}

}