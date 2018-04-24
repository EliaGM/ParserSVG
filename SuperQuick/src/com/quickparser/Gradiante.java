package com.quickparser;

import java.util.ArrayList;

public class Gradiante {
	
	private String[] colors;
	private float[] points;
	public Gradiante(String[] colors, float[] points) {
		super();
		this.colors = colors;
		this.points = points;
	}
	public String[] getColors() {
		return colors;
	}
	public void setColors(String[] colors) {
		this.colors = colors;
	}
	public float[] getPoints() {
		return points;
	}
	public void setPoints(float[] points) {
		this.points = points;
	}
	
	public void pintarGradiante(){
		String cadena = "";
		cadena = "gradiantes.add(new Gradiante(new int[]{";
		for(String c : colors){
			cadena += "Color.parseColor(\""+c+"\"),";
		}
		cadena = cadena.substring(0, cadena.length()-1);
		cadena += "},new float[]{";
		for(Float f : points){
			cadena += f+"f,";
		}
		cadena = cadena.substring(0, cadena.length()-1);
		cadena += "}));";
		
		System.out.println(cadena);
	}

}
