package com.quickparser;


public class Elipse {

	private float x;
	private float y;
	private float opacity;
	private String fill;
	private String stroke;
	private float width;
	private float mitter;
	private float w;
	private float h;
	private LinealGradient lg;
	private RadialGradient rg;
	boolean haymatrix;
	
	
	public Elipse( float opacity, String fill,float x, float y,float w, float h) {
		super();
		this.x = x;
		this.y = y;
		this.opacity = opacity;
		this.fill = fill;
		this.w = w;
		this.h = h;
		construirFill1();
	}
	
	
	public Elipse( float opacity, float x, float y,float w, float h, LinealGradient lg, boolean haymatrix) {
		super();
		this.x = x;
		this.y = y;
		this.opacity = opacity;
		this.w = w;
		this.h = h;
		this.lg = lg;
		this.haymatrix = haymatrix;
		construirFill2();
	}
	
	public Elipse( float opacity, float x, float y,float w, float h, RadialGradient rg,  boolean haymatrix) {
		super();
		this.x = x;
		this.y = y;
		this.opacity = opacity;
		this.w = w;
		this.h = h;
		this.rg = rg;
		this.haymatrix = haymatrix;
		construirFill3();
	}
	
	public Elipse( float opacity, String stroke,float x, float y,float w, float h, float width, float mitter) {
		super();
		this.x = x;
		this.y = y;
		this.opacity = opacity;
		this.stroke = stroke;
		this.w = w;
		this.h = h;
		this.width = width;
		this.mitter = mitter;
		construirSTK1();
	}
	
	public Elipse( float opacity, float x, float y,float w, float h, float width, float mitter, LinealGradient lg,  boolean haymatrix) {
		super();
		this.x = x;
		this.y = y;
		this.opacity = opacity;
		this.w = w;
		this.h = h;
		this.width = width;
		this.mitter = mitter;
		this.lg = lg;
		this.haymatrix = haymatrix;
		construirSTK2();
	}
	
	public Elipse( float opacity, float x, float y,float w, float h, float width, float mitter, RadialGradient rg,  boolean haymatrix) {
		super();
		this.x = x;
		this.y = y;
		this.opacity = opacity;
		this.w = w;
		this.h = h;
		this.width = width;
		this.mitter = mitter;
		this.rg = rg;
		this.haymatrix = haymatrix;
		construirSTK3();
	}
	
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
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
	public float getW() {
		return w;
	}
	public void setW(float w) {
		this.w = w;
	}
	public float getH() {
		return h;
	}
	public void setH(float h) {
		this.h = h;
	}
	public LinealGradient getLg() {
		return lg;
	}
	public void setLg(LinealGradient lg) {
		this.lg = lg;
	}
	public RadialGradient getRg() {
		return rg;
	}
	public void setRg(RadialGradient rg) {
		this.rg = rg;
	}
	
	public void construirFill1(){
		String cadena = "\"6 fill1 "+this.fill+" ";
		
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		cadena += "R "+this.x+" "+this.y+" "+this.w+" "+this.h+"/n\"+";
		
		System.out.println(cadena);
	}
	
	public void construirSTK1(){
		String cadena = "\"6 STK 1 "+this.stroke+" ";
		
		cadena += this.width+" ";cadena += this.mitter+" ";
		
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		cadena += "R "+this.x+" "+this.y+" "+this.w+" "+this.h+"/n\"+";
		System.out.println(cadena);
	}
	
	public void construirFill2(){
		String cadena = "\"6 fill2 "+this.lg.getId()+" "+this.lg.getX1()+" "+this.lg.getY1()+" "+this.lg.getX2()+" "+this.lg.getY2()+" ";
		
		if(haymatrix){
			cadena +="X "+this.lg.getA()+" "+this.lg.getB()+" "+this.lg.getC()+" "+this.lg.getD()+" "+this.lg.getE()+" "+this.lg.getF()+" ";
		}
		
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		
		cadena += "R "+this.x+" "+this.y+" "+this.w+" "+this.h+"/n\"+";
		System.out.println(cadena);
	}
	
	public void construirFill3(){
		String cadena = "\"6 fill3 "+this.rg.getId()+" "+this.rg.getX1()+" "+this.rg.getY1()+" "+this.rg.getR()+" ";
		if(haymatrix){
			cadena +="X "+this.rg.getA()+" "+this.rg.getB()+" "+this.rg.getC()+" "+this.rg.getD()+" "+this.rg.getE()+" "+this.rg.getF()+" ";
		}
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		cadena += "R "+this.x+" "+this.y+" "+this.w+" "+this.h+"/n\"+";
		System.out.println(cadena);
	}
	public void construirSTK2(){
		String cadena = "\"6 STK 2 "+this.lg.getId()+" "+this.lg.getX1()+" "+this.lg.getY1()+" "+this.lg.getX2()+" "+this.lg.getY2()+" ";
		if(haymatrix){
			cadena +="X "+this.lg.getA()+" "+this.lg.getB()+" "+this.lg.getC()+" "+this.lg.getD()+" "+this.lg.getE()+" "+this.lg.getF()+" ";
		}
		
		cadena += this.width+" ";cadena += this.mitter+" ";
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		cadena += "R "+this.x+" "+this.y+" "+this.w+" "+this.h+"/n\"+";
		System.out.println(cadena);
	}
	
	public void construirSTK3(){
		String cadena = "\"6 STK 3 "+this.rg.getId()+" "+this.rg.getX1()+" "+this.rg.getY1()+" "+this.rg.getR()+" ";
		if(haymatrix){
			cadena +="X "+this.rg.getA()+" "+this.rg.getB()+" "+this.rg.getC()+" "+this.rg.getD()+" "+this.rg.getE()+" "+this.rg.getF()+" ";
		}
		
		cadena += this.width+" ";cadena += this.mitter+" ";
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		cadena += "R "+this.x+" "+this.y+" "+this.w+" "+this.h+"/n\"+";
		System.out.println(cadena);
	}
	
}
