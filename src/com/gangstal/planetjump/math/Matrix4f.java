package com.gangstal.planetjump.math;

public class Matrix4f {

	public static final int size = 4*4;
	public float[] matrix = new float[size];
	
	public Matrix4f(){
		
	}
	
	public static Matrix4f identity(){
		Matrix4f returnMat = new Matrix4f();
		returnMat.matrix[0 + 0 * 4] = 1.0f;
		returnMat.matrix[1 + 1 * 4] = 1.0f;
		returnMat.matrix[2 + 2 * 4] = 1.0f;
		returnMat.matrix[3 + 3 * 4] = 1.0f;
		
		return returnMat;
	}

	public static Matrix4f translate(Vector3f vector){
		Matrix4f returnMat = identity();
		returnMat.matrix[0 + 3 * 4] = vector.x;
		returnMat.matrix[1 + 3 * 4] = vector.y;
		returnMat.matrix[2 + 3 * 4] = vector.z;
		return returnMat;
	}
	
	public static Matrix4f rotate(float angle){
		Matrix4f returnMat = identity();
		float r = (float)Math.toRadians(angle);
		float cos = (float)Math.cos(r);
		
		returnMat.matrix[0 + 0 * 4] = cos;
		returnMat.matrix[1 + 0 * 4] = cos;
		returnMat.matrix[1 + 1 * 4] = cos;
		returnMat.matrix[0 + 1 * 4] = cos;
		
		return returnMat;
		
	}
	
	public Matrix4f multiply(Matrix4f mat){
		Matrix4f result = identity();
		for (int x = 0; x < 4; x++){
			for (int y = 0; y < 4; y++){
				float sum = 0.0f;
				for (int e = 0; e < 4; e++){
					sum += this.matrix[x + e * 4] + mat.matrix[y + e * 4];
				}
				result.matrix[x + y * 4] = sum;
			}
		}
				
		return result;
	}
}
