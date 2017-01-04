package com.gangstal.planetjump.math;

public class Matrix4f {
	public static final int MATSIZE = 4 * 4;
	
	public static final Matrix4f IDENTITY = new Matrix4f(new float[] {
			1.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 1.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 1.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 1.0f,
	});
	
	public float[] matrix;
	
	public Matrix4f(float[] matrix) {
		this.matrix = matrix;
	}
	
	public Matrix4f(Matrix4f m) {
		this(m.matrix);
	}
	
	public Matrix4f() {
		this(new float[MATSIZE]);
	}
	
	public final Matrix4f init(Matrix4f m) {
		System.arraycopy(m.matrix, 0, matrix, 0, MATSIZE);
		return this;
	}
	
	public final Matrix4f initIdentity() {
		return init(IDENTITY);
	}
	
	public final Matrix4f initTranslate(Vector3f vector) {
		initIdentity();
		matrix[0 + 3 * 4] = vector.x;
		matrix[1 + 3 * 4] = vector.y;
		matrix[2 + 3 * 4] = vector.z;
		return this;
	}
	
	public final Matrix4f initRotateX(float a) {
		initIdentity();
		float r = (float) Math.toRadians(a);
		float	cos = (float) Math.cos(r),
				sin = (float) Math.sin(r);
		matrix[1 + 1 * 4] = cos;
		matrix[2 + 1 * 4] = -sin;
		matrix[1 + 2 * 4] = sin;
		matrix[2 + 2 * 4] = cos;
		return this;
	}
	
	public final Matrix4f initRotateY(float a) {
		initIdentity();
		float r = (float) Math.toRadians(a);
		float	cos = (float) Math.cos(r),
				sin = (float) Math.sin(r);
		matrix[0 + 0 * 4] = cos;
		matrix[2 + 0 * 4] = sin;
		matrix[0 + 2 * 4] = -sin;
		matrix[2 + 2 * 4] = cos;
		return this;
	}
	
	public final Matrix4f initRotateZ(float a) {
		initIdentity();
		float r = (float) Math.toRadians(a);
		float	cos = (float) Math.cos(r),
				sin = (float) Math.sin(r);
		matrix[0 + 0 * 4] = cos;
		matrix[1 + 0 * 4] = -sin;
		matrix[0 + 1 * 4] = sin;
		matrix[1 + 1 * 4] = cos;
		return this;
	}
	
	public final Matrix4f initRotate(float x, float y, float z) {
		return initRotateX(x).multiply(new Matrix4f().initRotateY(y)).multiply(new Matrix4f().initRotateZ(z));
	}
	
	public final Matrix4f multiply(Matrix4f mat) {
		Matrix4f res = new Matrix4f();
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				float sum = 0.0f;
				for (int e = 0; e < 4; e++)
					sum += matrix[e + y * 4] + mat.matrix[x + e * 4];
				res.matrix[x + y * 4] = sum;
			}
		}
		init(res);
		return this;
	}
}
