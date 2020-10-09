/*
 *	Author:      CAGIN TANIR
 *	Date:        Nov 30, 2017
 */
package ch.epfl.cs107.play.game.actor.general;


import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.ActorGame;
import ch.epfl.cs107.play.game.actor.GameEntity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Entity;
import ch.epfl.cs107.play.math.PartBuilder;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.math.WheelConstraint;
import ch.epfl.cs107.play.math.WheelConstraintBuilder;
import ch.epfl.cs107.play.window.Canvas;

public class Wheel extends GameEntity implements Actor{

	private float ballRadius = 0.5f;
	private ImageGraphics wheelGraphics;
	private WheelConstraint constraint;
		
	Circle circle = new Circle(ballRadius);
	
	public Wheel(ActorGame game, boolean fixed, Vector position) {
		super(game, fixed, position);
		
		PartBuilder pBuilder = super.getEntity().createPartBuilder();
		pBuilder.setShape(circle);
		pBuilder.setFriction(6.5f);
		pBuilder.build();

		
		wheelGraphics = new ImageGraphics("explosive.11.png", 2.0f*ballRadius, 2.0f * ballRadius, new Vector(0.5f, 0.5f));  
		wheelGraphics.setAlpha(1.0f);
		wheelGraphics.setDepth(0.0f);
		wheelGraphics.setParent(super.getEntity());
	}

	@Override
	public void draw(Canvas canvas) {
		wheelGraphics.draw(canvas);
	}

	@Override
	public Transform getTransform() {
		return this.getEntity().getTransform();
	}

	@Override
	public Vector getVelocity() {
		return this.getEntity().getVelocity();
	}
	
	public void attach(Entity vehicle , Vector anchor , Vector axis){
		WheelConstraintBuilder constraintBuilder = super.getOwner().createWheelConstraintBuilder(); // napcaz la burayi
		constraintBuilder.setFirstEntity(vehicle) ;
		// point d'ancrage du véhicule :
		constraintBuilder.setFirstAnchor(anchor) ;
		// Entity associée à la roue :
		constraintBuilder.setSecondEntity(getEntity()) ;  //bakalim caliscak mi
		// point d'ancrage de la roue (son centre) :
		constraintBuilder.setSecondAnchor(Vector.ZERO) ;
		// axe le long duquel la roue peut se déplacer :
		constraintBuilder.setAxis(axis) ;
		// fréquence du ressort associé
		constraintBuilder.setFrequency (3.0f) ;
		constraintBuilder.setDamping (0.5f) ;
		// force angulaire maximale pouvant être appliquée
		//à la roue pour la faire tourner :
		constraintBuilder.setMotorMaxTorque (10.0f) ;
		constraint = constraintBuilder.build () ;
	}
	
	public void power(float speed){
		constraint.setMotorEnabled(true);
		constraint.setMotorSpeed(speed);
	}
	public void relax(){
		constraint.setMotorEnabled(false);
	}
	
	public void detach(){
		constraint.destroy();
	}
	public float getSpeed(){
		float vX = getEntity().getVelocity().getX() * getEntity().getVelocity().getX() ;
		float vY = getEntity().getVelocity().getY() * getEntity().getVelocity().getY() ;
		float v = (float) Math.sqrt((vX+vY)) ;
				
		return getEntity().getAngularVelocity() - v ;
	}
	public void setPosition(Vector pos)
	{
		super.getEntity().setPosition(pos);
	}
	public void setSpeed(Vector speed){
		getEntity().setVelocity(speed);
	}
	
	public float getAngularPosition()
	{
		return super.getEntity().getAngularPosition();
	}

	
}

