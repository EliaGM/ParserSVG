package com.quickparser;


public class Texto {
	
	private float opacity;
	private String fill;
	private String stroke;
	private float width;
	private float mitter;
	private float size;
	private String texto;
	private String matriz;
	
	public Texto(float opacity, String fill,float size, String texto, String matriz) {
		super();
		this.opacity = opacity;
		this.fill = fill;
		this.size = size;
		this.texto = texto;
		this.matriz = matriz;
		construirFill1();
	}
	
	public Texto(float opacity, String stroke, float width,
			float mitter,float size, String texto, String matriz) {
		super();
		this.opacity = opacity;
		this.stroke = stroke;
		this.width = width;
		this.mitter = mitter;
		this.size = size;
		this.texto = texto;
		this.matriz = matriz;
		construirSTK1();
	}


	

	public float getOpacity() {
		return opacity;
	}
	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}
	public String getFill() {
		return fill;
	}
	public void setFill(String fill) {
		this.fill = fill;
	}
	public String getStroke() {
		return stroke;
	}
	public void setStroke(String stroke) {
		this.stroke = stroke;
	}
	public float getWidth() {
		return width;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public float getMitter() {
		return mitter;
	}
	public void setMitter(float mitter) {
		this.mitter = mitter;
	}
	
	
	public float getSize() {
		return size;
	}
	public void setSize(float size) {
		this.size = size;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	

	public String getMatriz() {
		return matriz;
	}

	public void setMatriz(String matriz) {
		this.matriz = matriz;
	}

	public void construirFill1(){
		String cadena = "\"8 fill1 ";
		if(!this.fill.equals("")){
			cadena += this.fill+" ";
		}
		cadena += this.size+" "+this.texto+" ";
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		cadena +=this.matriz;
		System.out.println(cadena);
	}
	
	public void construirSTK1(){
		String cadena = "\"8 STK1 "+this.stroke+" ";
		
		cadena += this.width+" ";cadena += this.mitter+" ";
		cadena += this.size+" "+this.texto+" ";
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		cadena +=this.matriz;
		System.out.println(cadena);
	}
	
}
