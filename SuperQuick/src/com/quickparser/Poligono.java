package com.quickparser;


public class Poligono {
	
	private float opacity;
	private String fill;
	private String stroke;
	private float width;
	private float mitter;
	private String matriz;
	private LinealGradient lg;
	private RadialGradient rg;
	boolean haymatrix;
	
	public Poligono(float opacity, String fill,String matriz) {
		super();
		this.opacity = opacity;
		this.fill = fill;
		this.matriz = matriz;
		
		construirFill1();
	}
	public Poligono(float opacity, String matriz, LinealGradient lg, boolean haymatrix) {
		super();
		this.opacity = opacity;
		this.matriz = matriz;
		this.lg = lg;
		this.haymatrix = haymatrix;
		construirFill2();
	}

	public Poligono(float opacity, String matriz, RadialGradient rg, boolean haymatrix) {
		super();
		this.opacity = opacity;
		this.matriz = matriz;
		this.rg = rg;
		this.haymatrix = haymatrix;
		construirFill3();
	}
	
	public Poligono(float opacity, String stroke, float width,
			float mitter, String matriz) {
		super();
		this.opacity = opacity;
		this.stroke = stroke;
		this.width = width;
		this.mitter = mitter;
		this.matriz = matriz;
		construirSTK1();
	}


	public Poligono(float opacity, float width, float mitter,
			String matriz, LinealGradient lg, boolean haymatrix) {
		super();
		this.opacity = opacity;
		this.width = width;
		this.mitter = mitter;
		this.matriz = matriz;
		this.lg = lg;
		this.haymatrix = haymatrix;
		construirSTK2();
	}

	public Poligono(float opacity, float width, float mitter,
			String matriz, RadialGradient rg, boolean haymatrix) {
		super();
		this.opacity = opacity;
		this.width = width;
		this.mitter = mitter;
		this.matriz = matriz;
		this.rg = rg;
		this.haymatrix = haymatrix;
		construirSTK3();
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
	public String getMatriz() {
		return matriz;
	}
	public void setMatriz(String matriz) {
		this.matriz = matriz;
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
		String cadena = "\"7 fill1 ";
		if(!this.fill.equals("")){
			cadena += this.fill+" ";
		}
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		if(!this.matriz.equals("")){
			cadena += this.matriz;
		}
		System.out.println(cadena);
	}
	
	public void construirSTK1(){
		String cadena = "\"7 STK 1 "+this.stroke+" ";
		
		cadena += this.width+" ";cadena += this.mitter+" ";
		
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		if(!this.matriz.equals("")){
			cadena += this.matriz;
		}
		System.out.println(cadena);
	}
	
	public void construirFill2(){
		String cadena = "\"7 fill2 "+this.lg.getId()+" "+this.lg.getX1()+" "+this.lg.getY1()+" "+this.lg.getX2()+" "+this.lg.getY2()+" ";
		
		if(haymatrix){
			cadena +="X "+this.lg.getA()+" "+this.lg.getB()+" "+this.lg.getC()+" "+this.lg.getD()+" "+this.lg.getE()+" "+this.lg.getF()+" ";
		}
		
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		if(!this.matriz.equals("")){
			cadena += this.matriz;
		}
		System.out.println(cadena);
	}
	
	public void construirFill3(){
		String cadena = "\"7 fill3 "+this.rg.getId()+" "+this.rg.getX1()+" "+this.rg.getY1()+" "+this.rg.getR()+" ";
		
		if(haymatrix){
			cadena +="X "+this.rg.getA()+" "+this.rg.getB()+" "+this.rg.getC()+" "+this.rg.getD()+" "+this.rg.getE()+" "+this.rg.getF()+" ";
		}
		
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		if(!this.matriz.equals("")){
			cadena += this.matriz;
		}
		System.out.println(cadena);
	}
	public void construirSTK2(){
		String cadena = "\"7 STK 2"+this.lg.getId()+" "+this.lg.getX1()+" "+this.lg.getY1()+" "+this.lg.getX2()+" "+this.lg.getY2()+" ";
		
		if(haymatrix){
			cadena +="X "+this.lg.getA()+" "+this.lg.getB()+" "+this.lg.getC()+" "+this.lg.getD()+" "+this.lg.getE()+" "+this.lg.getF()+" ";
		}
		
		cadena += this.width+" ";cadena += this.mitter+" ";
		
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		if(!this.matriz.equals("")){
			cadena += this.matriz;
		}
		System.out.println(cadena);
	}
	
	public void construirSTK3(){
		String cadena = "\"7 STK 3"+this.rg.getId()+" "+this.rg.getX1()+" "+this.rg.getY1()+" "+this.rg.getR()+" ";
		if(haymatrix){
			cadena +="X "+this.rg.getA()+" "+this.rg.getB()+" "+this.rg.getC()+" "+this.rg.getD()+" "+this.rg.getE()+" "+this.rg.getF()+" ";
		}
		cadena += this.width+" ";cadena += this.mitter+" ";
		if(this.opacity!=-1){
			cadena += "O "+this.opacity+" ";
		}
		if(!this.matriz.equals("")){
			cadena += this.matriz;
		}
		System.out.println(cadena);
	}
	

}
