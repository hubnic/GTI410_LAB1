package controller;

import model.Pixel;

public class ConversionRGBversHSV {
	Pixel pixelRecu;
	float H;
	float S;
	float V;
	float rouge;
	float vert;
	float bleu;
	float MAX;
	float MIN;
	
	public ConversionRGBversHSV(){}
	
	public double[] RGBversHSV(int rougeR,int vertR,int bleuR){
		float H = 0;
		float S = 0;
		float V = 0;
		float MAX;
		float MIN;
		double[] TSV_TAB = new double[3];
		  System.out.println("CONVERSION EN HSV (RGB RECU) : "+rougeR+" "+vertR+" "+bleuR);
		
		MAX = Math.max(rougeR, Math.max(vertR,bleuR));
		MIN = Math.min(rougeR, Math.min(vertR, bleuR));
		
		
		//HUE CALCUL (TEINTE)
		if(rougeR == MAX){
			H= (60 * ((vertR-bleuR)/(MAX-MIN)+360))%360;
		}
		
		if(vertR == MAX){
			H= 60 * ((bleuR-rougeR)/(MAX-MIN)) + 120;
		}
		if(bleuR == MAX){
			H= 60 * ((rougeR-vertR)/(MAX-MIN)) + 240;
		}
		if( H < 0 ){
			  H += 360;
		}
	      
		if(MAX == MIN){
			H= 0;
		}
		
		//SATURATION
		if(MAX==0){
			S=0;
		}else if(MAX!=0){
			S=1-(MIN/MAX);
		}
		
		//VALEUR
		V=Math.max(rougeR/255, Math.max(vertR/255,bleuR/255));
		TSV_TAB[0]=H;
		TSV_TAB[1]=S;
		TSV_TAB[2]=V;
		
		System.out.println("COULEUR EN HSV : "+TSV_TAB[0]+" "+TSV_TAB[1]+" "+TSV_TAB[2]);
		
		return TSV_TAB;
	}
	
	
}
