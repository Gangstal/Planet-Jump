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
	
	//Pourquoi pas une gravit� qui peut changer sur un m�me niveau ?
	public float getLocalGravity(){
		return g;
	}
}
