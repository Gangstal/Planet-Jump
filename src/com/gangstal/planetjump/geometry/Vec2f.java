package com.gangstal.planetjump.geometry;

public class Vec2f {
	public float x, y;
	
	public Vec2f set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public Vec2f set(Vec2f v) {
		return set(v.x, v.y);
	}
	
	public Vec2f add(Vec2f v) {
		return set(x + v.x, y + v.y);
	}
	
	public Vec2f sub(Vec2f v) {
		return set(x - v.x, y - v.y);
	}
	
	public Vec2f mul(float f) {
		return set(x * f, y * f);
	}
	
	public Vec2f div(float f) {
		return mul(1.0f / f);
	}
	
	public String toString() {
		return "x: " + x + ", y: " + y;
	}
}
