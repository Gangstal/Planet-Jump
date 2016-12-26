package com.gangstal.planetjump.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class CursorInput extends GLFWCursorPosCallback{

	@Override
	public void invoke(long window, double x, double y) {
		System.out.println("X: " + x + " Y: "+y);
	}

}
