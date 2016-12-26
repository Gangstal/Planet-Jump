package com.gangstal.planetjump;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVersionString;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetCursorPosCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.system.MemoryUtil.NULL;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import com.gangstal.planetjump.input.CursorInput;
import com.gangstal.planetjump.input.Input;


public class PlanetJump implements Runnable{

	private Thread thread;
	public boolean running = true;
	
	public static int width = 720;
	public static int height = 480;
	
	public long window;
	
	private GLFWKeyCallback keycallback;
	private GLFWCursorPosCallback cursorPos;
	
	public void start(){
		running = true;
		thread = new Thread(this, "EndlessRunner");
		thread.start();
	}
	
	public void init(){
		if(glfwInit() != true){
			System.err.println("GLFW initialisation failed");
		}
		
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		window = glfwCreateWindow(width, height, "Window !", NULL, NULL);
	
		if (window == NULL){
			System.err.println("Could not create window");
		}
		
		glfwSetKeyCallback(window, keycallback = new Input());
		glfwSetCursorPosCallback(window, cursorPos = new CursorInput());
		//"ByteBuffer" dans le tuto
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, 1920/2, 1080/2);
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();

		glfwShowWindow(window);
		glClearColor(0.56f, 0.258f, 0.458f, 1.0f);
		glEnable(GL_DEPTH_TEST);
		System.out.println("OpenGL " + glfwGetVersionString());
	}
	
	public void update(){
		
		glfwPollEvents();
		if(Input.keys[GLFW_KEY_SPACE])
			System.out.println("Barre d'espace pressée");
	}
	
	public void render(){
		glfwSwapBuffers(window);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	@Override
	public void run() {
		init();
		while(running){
			update();
			render();
			
			
			if(glfwWindowShouldClose(window))
				running = false;
			
		}
		
		keycallback.free();
	}
		
	public static void main(String[] args){
	
		PlanetJump game = new PlanetJump();
		game.start();
		
	}
}
