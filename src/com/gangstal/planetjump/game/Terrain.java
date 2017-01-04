package com.gangstal.planetjump.game;

public class Terrain {
	public Game game;
	public float g = 9.81f * 5.0f;
	
	public Terrain() {
	}
	
	public boolean groundOnPos(float x) {
		return true;
	}
	
	//Pourquoi pas une gravité qui peut changer sur un même niveau ?
	public float getLocalGravity() {
		return g;
	}
}
