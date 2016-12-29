package com.gangstal.planetjump.game;

public class Player {

	public float x = 0;
	public float y = 0;
	public Game game;
	public float g;
	public float speed = 5f;
	
	private boolean isJumping = false;
	private int actionCounter;
	private float jumpSpeed;
	private float jumpAngle;
	
	
	public Player(Game game){
		this.game = game;
		g = game.terrain.g;
	}
	
	//angle relative to the terrain
	public void jump(float speed, float angle){
		if(!isJumping){
			isJumping = true;
			jumpSpeed = speed;
			jumpAngle = angle;
			actionCounter = 0;
		}
		
	}
	
	public void update(){
		if(isJumping){
			g = game.terrain.getLocalGravity();
			// x = (c+cos(a)*v)*t
			x = (float) (speed + Math.cos(jumpAngle))*jumpSpeed*actionCounter;
			// y = -1/2*g*t²+sin(a)*v*t
			y = (float) (-0.5*g*(Math.pow(actionCounter, 2))+Math.sin(jumpAngle)*jumpSpeed*actionCounter);
			
			if(y==0 && game.terrain.groundOnPos(x)){
				// TODO Player dies/Game ends
			}
				
		}
	}

}
