package com.quickparser;

import java.util.ArrayList;

public class LinealGradient {
	
	private int id;
	private float x1, x2, y1, y2,a,b,c,d,e,f;
	public LinealGradient(int id, float x1, float x2, float y1, float y2) {
		super();
		this.id = id;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public LinealGradient(int id, float x1, float x2, float y1, float y2, float a, float b, float c, float d, float e, float f) {
		super();
		this.id = id;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.a = a; this.b=b; this.c=c; this.d=d; this.e=e; this.f=f;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getX1() {
		return x1;
	}
	public void setX1(float x1) {
		this.x1 = x1;
	}
	public float getX2() {
		return x2;
	}
	public void setX2(float x2) {
		this.x2 = x2;
	}
	public float getY1() {
		return y1;
	}
	public void setY1(float y1) {
		this.y1 = y1;
	}
	public float getY2() {
		return y2;
	}
	public void setY2(float y2) {
		this.y2 = y2;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	public float getC() {
		return c;
	}

	public void setC(float c) {
		this.c = c;
	}

	public float getD() {
		return d;
	}

	public void setD(float d) {
		this.d = d;
	}

	public float getE() {
		return e;
	}

	public void setE(float e) {
		this.e = e;
	}

	public float getF() {
		return f;
	}

	public void setF(float f) {
		this.f = f;
	}
	
	
	
}
