package com.gangstal.planetjump.game;

import com.gangstal.planetjump.geometry.Vec2f;

public class Entity {
	public Terrain terrain;
	public Vec2f a, v, p;
	public boolean onAir;
	public float speed;
	
	public Entity(Vec2f a, Vec2f v, Vec2f p) {
		this.a = a;
		this.v = v;
		this.p = p;
	}
	
	public Entity() {
		this(new Vec2f(), new Vec2f(), new Vec2f());
	}
	
	public void update(float ellapsed) {
		if (!onAir) {
			if (Math.abs(v.x) > 60.0f * ellapsed)
				v.x = (float) Math.signum(v.x) * ((float) Math.abs(v.x) - 60.0f * ellapsed);
			else
				v.x = 0.0f;
		}
		if (Math.abs(v.x) > 10.0f)
			v.x = Math.signum(v.x) * 10.0f;
		a.y = -terrain.getLocalGravity();
		v.add(new Vec2f().set(a).mul(ellapsed));
		p.add(new Vec2f().set(v).mul(ellapsed));
		if (p.y < 0.0f) {
			v.y = 0.0f;
			p.y = 0.0f;
			onAir = false;
		} else if (v.y != 0) {
			onAir = true;
		}
	}
	
	public void jump(float speed) {
		if (!onAir)
			v.y += speed;
	}
}
