package com.gangstal.planetjump;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.gangstal.planetjump.game.Game;
import com.gangstal.planetjump.game.Player;
import com.gangstal.planetjump.game.Terrain;
import com.gangstal.planetjump.input.CursorInput;
import com.gangstal.planetjump.input.Input;


public class PlanetJump {
	public static final int DEFAULT_WIDTH  = 640;
	public static final int DEFAULT_HEIGHT = 480;
	
	public static void main(String[] args) {
		PlanetJump game = new PlanetJump();
		game.start();
	}
	
	public boolean running = true;
	
	public long window;
	
	private GLFWKeyCallback keycallback;
	private GLFWCursorPosCallback cursorPos;
	private Game game;
	
	public void init() {
		if (glfwInit() != true) {
			System.err.println("GLFW initialisation failed");
			System.exit(1);
		}
		
		glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		window = glfwCreateWindow(DEFAULT_WIDTH, DEFAULT_HEIGHT, "Window !", NULL, NULL);
		
		if (window == NULL) {
			System.err.println("Could not create window");
			System.exit(1);
		}
		
		glfwSetKeyCallback(window, keycallback = new Input());
		glfwSetCursorPosCallback(window, cursorPos = new CursorInput());
		
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - DEFAULT_WIDTH) / 2, (vidmode.height() - DEFAULT_HEIGHT) / 2);
		
		glfwSwapInterval(0);
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		glEnable(GL_DEPTH_TEST);
		glDisable(GL_TEXTURE_2D);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		
		// gluPerspective
		float fovy = 70.0f, aspectRatio = (float) DEFAULT_WIDTH / (float) DEFAULT_HEIGHT, znear = 100.0f, zfar = 0.01f;
		float ymax = znear * (float) Math.tan(fovy * (float) Math.PI / 360.0f);
		float xmax = ymax * aspectRatio;
		glFrustum(-xmax, xmax, -ymax, ymax, znear, zfar);
		
		glMatrixMode(GL_MODELVIEW);
		
		game = new Game(new Player(), new Terrain());
		
		glfwShowWindow(window);
	}
	
	public void start() {
		running = true;
		init();
		while (running) {
			running = !glfwWindowShouldClose(window);
			if (!game.player.onAir && Input.keys[GLFW_KEY_A])
				game.player.a.x = -100.0f;
			else if (!game.player.onAir && Input.keys[GLFW_KEY_D])
				game.player.a.x = 100.0f;
			else
				game.player.a.x = 0.0f;
			if (Input.keys[GLFW_KEY_W])
				game.player.jump(15.0f);
			game.player.update((1.0f / 60.0f) * 1f);
			
			System.out.println(game.player.p);
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			glTranslatef(-game.player.p.x, -game.player.p.y, -5.0f);
			
			glColor4f(1.0f, 1.0f, 0.0f, 1.0f);
			glBegin(GL_QUADS);
			glVertex3f(-5.0f, -1.0f, 0.0f);
			glVertex3f( 5.0f, -1.0f, 0.0f);
			glVertex3f( 5.0f,  0.0f, 0.0f);
			glVertex3f(-5.0f,  0.0f, 0.0f);
			glEnd();
			
			glPushMatrix();
			glTranslatef(game.player.p.x, game.player.p.y, 0);
			glColor4f(0.0f, 1.0f, 0.0f, 1.0f);
			glBegin(GL_QUADS);
			glVertex3f(-0.5f, 0.0f, 0.0f);
			glVertex3f( 0.5f, 0.0f, 0.0f);
			glVertex3f( 0.5f, 1.0f, 0.0f);
			glVertex3f(-0.5f, 1.0f, 0.0f);
			glEnd();
			glPopMatrix();
			
			glfwSwapBuffers(window);
			glfwPollEvents();
		}
		
		running = false;
		keycallback.free();
		cursorPos.free();
	}
}
