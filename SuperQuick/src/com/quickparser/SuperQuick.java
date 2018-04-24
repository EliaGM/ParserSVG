package com.quickparser;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class SuperQuick {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		parserXML();

	}
	
	public static String formatearMatriz(String matriz){
		matriz = matriz.replace(',', ' ');
		matriz = matriz.replace("-", " -");
		matriz = matriz.replace("c", " c ");
		matriz = matriz.replace("C", " C ");
		matriz = matriz.replace("s", " s ");
		matriz = matriz.replace("S", " S ");
		matriz = matriz.replace("H", " H ");
		matriz = matriz.replace("V", " V ");
		matriz = matriz.replace("h", " h ");
		matriz = matriz.replace("v", " v ");
		matriz = matriz.replace("l", " l ");
		matriz = matriz.replace("L", " L ");
		matriz = matriz.replace("z", " z/n\"+");
		matriz = matriz.replace("M", " M ");
		return matriz;
	}
	
	public static String formatearPoints(String matriz){
		
		matriz = matriz.replace(',', ' ');
		matriz = matriz.replace("-", " -");
		matriz += "/n\"+";
		return matriz;
	}

	public static void parserXML() {
		try {
			 File xmlFile = new File("D://QUICKPARSER//NUEVOSPUZZLES//PUZZLE7//semillas2.xml");  
			   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
			     .newInstance();  
			   DocumentBuilder documentBuilder = documentFactory  
			     .newDocumentBuilder();  
			   Document doc = documentBuilder.parse(xmlFile);  
			  
			   doc.getDocumentElement().normalize();  
			
			   NodeList nodeList = doc.getElementsByTagName("svg");  
			   NodeList nodeList2 = nodeList.item(0).getChildNodes();
			   Path path; Rectangulo rect;
			   Poligono poli; Elipse elipse;
			   Texto texto;
			   NamedNodeMap att;
			   boolean haymatrix = false;
			  
			   int indice = 0; ArrayList<Gradiante> gradiantes = new ArrayList<Gradiante>(); 
			   
			   RadialGradient rg = null; LinealGradient lg = null;
			   
			   for (int i = 0; i < nodeList2.getLength(); i++) {  
				   Node node = nodeList2.item(i);  
				   //Log.v("ELIA", "NODE: "+node.getNodeName());
				   if(node.getNodeName().equals("path")){
					   att = node.getAttributes();
					   String fill=null, stroke=null, matriz=null; float mitter=0, widht=0; float opacity = -1;
					   boolean transparent = false;
					   
					   for(int a=0; a<att.getLength(); a++){
						   if(att.item(a).getNodeName().equals("d")){
							   matriz = formatearMatriz(att.getNamedItem("d").getNodeValue());
							   //System.out.println(matriz);
						   }else if(att.item(a).getNodeName().equals("opacity")){
							   opacity = Float.valueOf(att.getNamedItem("opacity").getNodeValue());
							   //System.out.println(opacity);
						   }else if(att.item(a).getNodeName().equals("fill")){
							   fill = att.getNamedItem("fill").getNodeValue();
							   //System.out.println(fill);
						   }else if(att.item(a).getNodeName().equals("stroke-miterlimit")){
							   mitter = Float.valueOf(att.getNamedItem("stroke-miterlimit").getNodeValue());
							   //System.out.println(mitter);
						   }else if(att.item(a).getNodeName().equals("stroke")){
							   stroke = att.getNamedItem("stroke").getNodeValue();  
							   //System.out.println(stroke);
						   }else if(att.item(a).getNodeName().equals("stroke-width")){
							   //System.out.println("EXISTE STROKE-WIDHT");
							   widht = Float.valueOf(att.getNamedItem("stroke-width").getNodeValue());
							  // System.out.println(widht);
						   }
					   }
					   if(fill==null){
						   fill = "#000000";
					   }else{
						   if(fill.equals("none")){
							   transparent = true;
								  fill = "#FFFFFF";
						   }
					   }
					   if(fill.charAt(0)=='#'){
						   if(transparent){
							   path = new Path(0,fill,matriz);
						   }else{
							   path = new Path(opacity,fill,matriz);
						   }
						   
					   }else if(lg!=null){
						   path = new Path(opacity,matriz, lg, haymatrix);
						   
					   }else if(rg!=null){
						   path = new Path(opacity,matriz, rg, haymatrix); 
					   } 
					   
					   if(stroke==null && mitter!=0){
						   stroke = "#000000";
					   }
					   if(stroke!=null){
						   
						   if(widht==0){
							   widht = 1;
						   }
						   if(stroke.charAt(0)=='#'){
							   path = new Path(opacity,stroke,widht,mitter,matriz);
						   }else if(lg!=null){
							   path = new Path(opacity,widht,mitter,matriz,lg, haymatrix);
							   
						   }else if(rg!=null){
							   path = new Path(opacity,widht,mitter,matriz,rg, haymatrix);
						   }
					   }
					   lg = null; rg=null; haymatrix = false;
					   
				   }else if(node.getNodeName().equals("linearGradient")){
					   float x1,y1,x2,y2,a = 0,b=0,c=0,d=0,e=0,f=0; ArrayList<String>colors = new ArrayList<String>();
					   ArrayList<Float>points = new ArrayList<Float>();
					   //System.out.println("LINEAR GRADIENT");
					   att = node.getAttributes();
					   x1 = Float.valueOf(att.getNamedItem("x1").getNodeValue());
					   y1 = Float.valueOf(att.getNamedItem("y1").getNodeValue());
					   x2 = Float.valueOf(att.getNamedItem("x2").getNodeValue());
					   y2 = Float.valueOf(att.getNamedItem("y2").getNodeValue());
					   
					   
					   for(int j=0; j<att.getLength(); j++){
						   if(att.item(j).getNodeName().equals("gradientTransform")){
							   haymatrix = true;
							   String gradientTransform = att.getNamedItem("gradientTransform").getNodeValue();
							   int pos = gradientTransform.indexOf("(");
							   pos++;
							   gradientTransform = gradientTransform.substring(pos, gradientTransform.length()-1);
							   
							   String[] matrix = gradientTransform.split(" ");
							   a = Float.valueOf(matrix[0]);b = Float.valueOf(matrix[1]);c = Float.valueOf(matrix[2]);
							   d = Float.valueOf(matrix[3]);e = Float.valueOf(matrix[4]);f = Float.valueOf(matrix[5]);
							   
						   }
					   }
					   
					   String style;
					   NodeList ncolors = node.getChildNodes();
					   for(int x=0; x<ncolors.getLength(); x++){
						   Node nc = ncolors.item(x);
						   if(!nc.getNodeName().equals("#text")){
							  // System.out.println(nc.getNodeName());
							   att = nc.getAttributes();
							   for(int j=0; j<att.getLength(); j++){
								   if(att.item(j).getNodeName().equals("offset")){
									  // System.out.println("point "+att.getNamedItem("offset").getNodeValue());
									   points.add(Float.valueOf(att.getNamedItem("offset").getNodeValue()));
								   }else if(att.item(j).getNodeName().equals("style")){
									   style = att.getNamedItem("style").getNodeValue();
									   int pos = style.indexOf(":");
									   pos++;
									   //System.out.println("color "+style.substring(pos));
									   colors.add(style.substring(pos));
								   }
							   }
						   }
						   
						  
					   }
					  
					   int indg = -1;
					   int indicegradiante = 0;
					   for(Gradiante g : gradiantes){
						   if(g.getColors().length==colors.size() && indg==-1){
							   for(int k=0; k<colors.size();k++){
								   //System.out.println("SON IGUALES? "+colors.get(c)+" - "+g.getColors()[c]);
								   if(!colors.get(k).equals(g.getColors()[k])){
									   indg = -1;
									   break;
								   }else{
									   indg = k;
								   }
							   }
							   if(indg!=-1){
								   break;
							   }
						   }
						   indicegradiante++;
					   }
					   if(indg!=-1){
						  
						   if(haymatrix){
							   lg = new LinealGradient(indicegradiante, x1, x2, y1, y2,a,b,c,d,e,f);
						   }else{
							   lg = new LinealGradient(indicegradiante, x1, x2, y1, y2);
						   }
						   
					   }else{
						   if(haymatrix){
							   lg = new LinealGradient(indice, x1, x2, y1, y2,a,b,c,d,e,f);
						   }else{
							   lg = new LinealGradient(indice, x1, x2, y1, y2);
						   }
						   
						   String[] coloritos = new String[colors.size()];
						   for(int h=0; h<colors.size(); h++){
							   coloritos[h] = colors.get(h);
						   }
						   float[] puntitos = new float[points.size()];
						   for(int h=0; h<points.size(); h++){
							   puntitos[h] = points.get(h);
						   }
						   gradiantes.add(new Gradiante(coloritos, puntitos));
						   indice++;
					   }
					   
					   
				   }else if(node.getNodeName().equals("radialGradient")){
					   float cx,cy,r,a=0,b=0,c=0,d=0,e=0,f=0; ArrayList<String>colors = new ArrayList<String>();
					   ArrayList<Float>points = new ArrayList<Float>();
					   //System.out.println("RADIAL GRADIENT");
					   att = node.getAttributes();
					   cx = Float.valueOf(att.getNamedItem("cx").getNodeValue());
					   cy = Float.valueOf(att.getNamedItem("cy").getNodeValue());
					   r = Float.valueOf(att.getNamedItem("r").getNodeValue());
					   
					   for(int j=0; j<att.getLength(); j++){
						   if(att.item(j).getNodeName().equals("gradientTransform")){
							   haymatrix = true;
							   String gradientTransform = att.getNamedItem("gradientTransform").getNodeValue();
							   int pos = gradientTransform.indexOf("(");
							   pos++;
							   gradientTransform = gradientTransform.substring(pos, gradientTransform.length()-1);
							  
							   String[] matrix = gradientTransform.split(" ");
							   a = Float.valueOf(matrix[0]);b = Float.valueOf(matrix[1]);c = Float.valueOf(matrix[2]);
							   d = Float.valueOf(matrix[3]);e = Float.valueOf(matrix[4]);f = Float.valueOf(matrix[5]);
							   
							   break;
						   }
					   }
					   
					   String style;
					   NodeList ncolors = node.getChildNodes();
					   for(int x=0; x<ncolors.getLength(); x++){
						   Node nc = ncolors.item(x);
						   if(!nc.getNodeName().equals("#text")){
							   //System.out.println(nc.getNodeName());
							   att = nc.getAttributes();
							   for(int j=0; j<att.getLength(); j++){
								   if(att.item(j).getNodeName().equals("offset")){
									   //System.out.println("point "+att.getNamedItem("offset").getNodeValue());
									   points.add(Float.valueOf(att.getNamedItem("offset").getNodeValue()));
								   }else if(att.item(j).getNodeName().equals("style")){
									   style = att.getNamedItem("style").getNodeValue();
									   int pos = style.indexOf(":");
									   pos++;
									   //System.out.println("color "+style.substring(pos));
									   colors.add(style.substring(pos));
								   }
							   }
						   }
						   
						  
					   }
					  
					   int indg = -1;
					   int indicegradiante = 0;
					   for(Gradiante g : gradiantes){
						   if(g.getColors().length==colors.size() && indg==-1){
							   for(int k=0; k<colors.size();k++){
								   //System.out.println("SON IGUALES? "+colors.get(c)+" - "+g.getColors()[c]);
								   if(!colors.get(k).equals(g.getColors()[k])){
									   indg = -1;
									   break;
								   }else{
									   indg = k;
								   }
							   }
							   if(indg!=-1){
								   break;
							   }
						   }
						   indicegradiante++;
					   }
					   if(indg!=-1){
						   if(haymatrix){
							   rg = new RadialGradient(indicegradiante, cx, cy, r,a,b,c,d,e,f);
						   }else{
							   rg = new RadialGradient(indicegradiante, cx, cy, r);
						   }
						   
					   }else{
						   if(haymatrix){
							   rg = new RadialGradient(indice, cx, cy, r,a,b,c,d,e,f);
						   }else{
							   rg = new RadialGradient(indice, cx, cy, r);
						   }
						   
						   String[] coloritos = new String[colors.size()];
						   for(int h=0; h<colors.size(); h++){
							   coloritos[h] = colors.get(h);
						   }
						   float[] puntitos = new float[points.size()];
						   for(int h=0; h<points.size(); h++){
							   puntitos[h] = points.get(h);
						   }
						   gradiantes.add(new Gradiante(coloritos, puntitos));
						   indice++;
					   }
					   
					   
				   }else if(node.getNodeName().equals("rect")){
					   att = node.getAttributes();
					   String fill=null, stroke=null; float mitter=0, widht=0; float opacity = -1;
					   boolean transparent = false;
					   float x = 0, y = 0, w = 0, h = 0;
					   for(int a=0; a<att.getLength(); a++){
						   if(att.item(a).getNodeName().equals("width")){
							   w = Float.valueOf(att.getNamedItem("width").getNodeValue());
							   //System.out.println(w);
						   }else if(att.item(a).getNodeName().equals("height")){
							   h = Float.valueOf(att.getNamedItem("height").getNodeValue());
							   //System.out.println(h);
							  
						   }else if(att.item(a).getNodeName().equals("x")){
							   x = Float.valueOf(att.getNamedItem("x").getNodeValue());
							   //System.out.println(x);
						   }else if(att.item(a).getNodeName().equals("y")){
							   y = Float.valueOf(att.getNamedItem("y").getNodeValue());
							   //System.out.println(y);
						   }else if(att.item(a).getNodeName().equals("opacity")){
							   opacity = Float.valueOf(att.getNamedItem("opacity").getNodeValue());
							   //System.out.println(opacity);
						   }else if(att.item(a).getNodeName().equals("fill")){
							   fill = att.getNamedItem("fill").getNodeValue();
							   //System.out.println(fill);
						   }else if(att.item(a).getNodeName().equals("stroke-miterlimit")){
							   mitter = Float.valueOf(att.getNamedItem("stroke-miterlimit").getNodeValue());
							   //System.out.println(mitter);
						   }else if(att.item(a).getNodeName().equals("stroke")){
							   stroke = att.getNamedItem("stroke").getNodeValue();  
							   //System.out.println(stroke);
						   }else if(att.item(a).getNodeName().equals("stroke-width")){
							   
							   widht = Float.valueOf(att.getNamedItem("stroke-width").getNodeValue());
							   //System.out.println(widht);
						   }
					   }
					   if(fill==null){
						   fill = "#000000";
					   }else{
						   if(fill.equals("none")){
							   transparent = true;
								  fill = "#FFFFFF";
						   }
					   }
					   if(fill.charAt(0)=='#'){
						   if(transparent){
							   rect = new Rectangulo(0, fill, x, y, w, h);
						   }else{
							   rect = new Rectangulo(opacity, fill, x, y, w, h);
						   }
						   
					   }else if(lg!=null){
						   rect = new Rectangulo(opacity, x, y, w, h, lg,haymatrix);
						   
					   }else if(rg!=null){
						   rect = new Rectangulo(opacity, x, y, w, h, rg, haymatrix);
						   
					   } 
					   
					   if(stroke==null && mitter!=0){
						   stroke = "#000000";
					   }
					   if(stroke!=null){
						   
						   if(widht==0){
							   widht = 1;
						   }
						   if(stroke.charAt(0)=='#'){
							   rect = new Rectangulo(opacity, stroke, x, y, w, h, widht, mitter);
						   }else if(lg!=null){
							   rect = new Rectangulo(opacity, x, y, w, h, widht, mitter, lg, haymatrix);
							   
						   }else if(rg!=null){
							   rect = new Rectangulo(opacity, x, y, w, h, widht, mitter, rg, haymatrix);
						   }
					   }
					   lg = null; rg=null;haymatrix = false;
					   
				   }else if(node.getNodeName().equals("polygon")){
					   att = node.getAttributes();
					   String fill=null, stroke=null, matriz=null; float mitter=0, widht=0; float opacity = -1;
					   boolean transparent = false;
					   
					   for(int a=0; a<att.getLength(); a++){
						   if(att.item(a).getNodeName().equals("points")){
							   matriz = formatearPoints("M "+att.getNamedItem("points").getNodeValue());
							   //System.out.println(matriz);
						   }else if(att.item(a).getNodeName().equals("opacity")){
							   opacity = Float.valueOf(att.getNamedItem("opacity").getNodeValue());
							   //System.out.println(opacity);
						   }else if(att.item(a).getNodeName().equals("fill")){
							   fill = att.getNamedItem("fill").getNodeValue();
							   //System.out.println(fill);
						   }else if(att.item(a).getNodeName().equals("stroke-miterlimit")){
							   mitter = Float.valueOf(att.getNamedItem("stroke-miterlimit").getNodeValue());
							   //System.out.println(mitter);
						   }else if(att.item(a).getNodeName().equals("stroke")){
							   stroke = att.getNamedItem("stroke").getNodeValue();  
							   //System.out.println(stroke);
						   }else if(att.item(a).getNodeName().equals("stroke-width")){
							   //System.out.println("EXISTE STROKE-WIDHT");
							   widht = Float.valueOf(att.getNamedItem("stroke-width").getNodeValue());
							  // System.out.println(widht);
						   }
					   }
					   if(fill==null){
						   fill = "#000000";
					   }else{
						   if(fill.equals("none")){
							   transparent = true;
								  fill = "#FFFFFF";
						   }
					   }
					   if(fill.charAt(0)=='#'){
						   if(transparent){
							   poli = new Poligono(0,fill,matriz);
						   }else{
							   poli = new Poligono(opacity,fill,matriz);
						   }
						   
					   }else if(lg!=null){
						   poli = new Poligono(opacity,matriz, lg, haymatrix);
						   
					   }else if(rg!=null){
						   poli = new Poligono(opacity,matriz, rg, haymatrix); 
					   } 
					   
					   if(stroke==null && mitter!=0){
						   stroke = "#000000";
					   }
					   if(stroke!=null){
						   
						   if(widht==0){
							   widht = 1;
						   }
						   if(stroke.charAt(0)=='#'){
							   poli = new Poligono(opacity,stroke,widht,mitter,matriz);
						   }else if(lg!=null){
							   poli = new Poligono(opacity,widht,mitter,matriz,lg, haymatrix);
							   
						   }else if(rg!=null){
							   poli = new Poligono(opacity,widht,mitter,matriz,rg, haymatrix);
						   }
					   }
					   lg = null; rg=null; haymatrix = false;
				   }else if(node.getNodeName().equals("ellipse")){
					   att = node.getAttributes();
					   String fill=null, stroke=null; float mitter=0, widht=0; float opacity = -1;
					   boolean transparent = false;
					   float x = 0, y = 0, w = 0, h = 0;
					   for(int a=0; a<att.getLength(); a++){
						   if(att.item(a).getNodeName().equals("rx")){
							   w = Float.valueOf(att.getNamedItem("rx").getNodeValue());
							   //System.out.println(w);
						   }else if(att.item(a).getNodeName().equals("ry")){
							   h = Float.valueOf(att.getNamedItem("ry").getNodeValue());
							   //System.out.println(h);
							  
						   }else if(att.item(a).getNodeName().equals("cx")){
							   x = Float.valueOf(att.getNamedItem("cx").getNodeValue());
							   //System.out.println(x);
						   }else if(att.item(a).getNodeName().equals("cy")){
							   y = Float.valueOf(att.getNamedItem("cy").getNodeValue());
							   //System.out.println(y);
						   }else if(att.item(a).getNodeName().equals("opacity")){
							   opacity = Float.valueOf(att.getNamedItem("opacity").getNodeValue());
							   //System.out.println(opacity);
						   }else if(att.item(a).getNodeName().equals("fill")){
							   fill = att.getNamedItem("fill").getNodeValue();
							   //System.out.println(fill);
						   }else if(att.item(a).getNodeName().equals("stroke-miterlimit")){
							   mitter = Float.valueOf(att.getNamedItem("stroke-miterlimit").getNodeValue());
							   //System.out.println(mitter);
						   }else if(att.item(a).getNodeName().equals("stroke")){
							   stroke = att.getNamedItem("stroke").getNodeValue();  
							   //System.out.println(stroke);
						   }else if(att.item(a).getNodeName().equals("stroke-width")){
							   
							   widht = Float.valueOf(att.getNamedItem("stroke-width").getNodeValue());
							   //System.out.println(widht);
						   }
					   }
					   if(fill==null){
						   fill = "#000000";
					   }else{
						   if(fill.equals("none")){
							   transparent = true;
								  fill = "#FFFFFF";
						   }
					   }
					   if(fill.charAt(0)=='#'){
						   if(transparent){
							   elipse = new Elipse(0, fill, x, y, w, h);
						   }else{
							   elipse = new Elipse(opacity, fill, x, y, w, h);
						   }
						   
					   }else if(lg!=null){
						   elipse = new Elipse(opacity, x, y, w, h, lg,haymatrix);
						   
					   }else if(rg!=null){
						   elipse = new Elipse(opacity, x, y, w, h, rg, haymatrix);
						   
					   } 
					   
					   if(stroke==null && mitter!=0){
						   stroke = "#000000";
					   }
					   if(stroke!=null){
						   
						   if(widht==0){
							   widht = 1;
						   }
						   if(stroke.charAt(0)=='#'){
							   elipse = new Elipse(opacity, stroke, x, y, w, h, widht, mitter);
						   }else if(lg!=null){
							   elipse = new Elipse(opacity, x, y, w, h, widht, mitter, lg, haymatrix);
							   
						   }else if(rg!=null){
							   elipse = new Elipse(opacity, x, y, w, h, widht, mitter, rg, haymatrix);
						   }
					   }
					   lg = null; rg=null;haymatrix = false;
				   }else if(node.getNodeName().equals("text")){
					   att = node.getAttributes(); String matriz=null;
					   String fill=null, stroke=null; float mitter=0, widht=0; 
					   float a=0, b=0,c=0,d=0,e=0,f=0, size = 0, opacity = -1;
					   String text = node.getTextContent();
					   for(int x=0; x<att.getLength(); x++){
						   if(att.item(x).getNodeName().equals("d")){
							   matriz = formatearMatriz(att.getNamedItem("d").getNodeValue());
						   }else if(att.item(x).getNodeName().equals("fill")){
							   fill = att.getNamedItem("fill").getNodeValue();
							   //System.out.println(fill);
						   }else if(att.item(x).getNodeName().equals("opacity")){
							   opacity = Float.valueOf(att.getNamedItem("opacity").getNodeValue());
							   //System.out.println(opacity);
						   }else if(att.item(x).getNodeName().equals("stroke-miterlimit")){
							   mitter = Float.valueOf(att.getNamedItem("stroke-miterlimit").getNodeValue());
							   //System.out.println(mitter);
						   }else if(att.item(x).getNodeName().equals("stroke")){
							   stroke = att.getNamedItem("stroke").getNodeValue();  
							   //System.out.println(stroke);
						   }else if(att.item(x).getNodeName().equals("stroke-width")){
							   //System.out.println("EXISTE STROKE-WIDHT");
							   widht = Float.valueOf(att.getNamedItem("stroke-width").getNodeValue());
							  // System.out.println(widht);
						   }else if(att.item(x).getNodeName().equals("font-size")){
							   //System.out.println("EXISTE STROKE-WIDHT");
							   size = Float.valueOf(att.getNamedItem("font-size").getNodeValue());
							  // System.out.println(widht);
						   }
					   }
					   if(fill==null){
						   fill = "#000000";
					   }else{
						   if(!fill.equals("none")){
							   texto = new Texto(opacity, fill,size,text,matriz);
						   }
					   }
					   
					   
					   if(stroke==null && mitter!=0){
						   stroke = "#000000";
					   }
					   if(stroke!=null){
						   
						   if(widht==0){
							   widht = 1;
						   }
						   if(stroke.charAt(0)=='#'){
							   texto = new Texto(opacity, stroke,widht,mitter,size,text,matriz);
						   }
							
						}
					   
					   lg = null; rg=null; haymatrix = false;
				   }
			   }
		
			for(Gradiante g : gradiantes){
				g.pintarGradiante();
			}
			
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
		
	}

}
