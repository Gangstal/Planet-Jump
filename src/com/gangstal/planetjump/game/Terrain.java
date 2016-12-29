package com.gangstal.planetjump.game;

public class Terrain {
	
	public Game game;
	public float g = 9.81f;
	
	public Terrain(Game game){
		this.game = game;
	}
	
	public boolean groundOnPos(float x){
		return true;
	}
	
	//Pourquoi pas une gravité qui peut changer sur un même niveau ?
	public float getLocalGravity(){
		return g;
	}
}
