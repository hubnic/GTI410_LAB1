package controller;

import model.Pixel;

public class ConversionHSV {
	Pixel pixelRecu;
	float H;
	float S;
	float V;
	float rouge;
	float vert;
	float bleu;
	float MAX;
	float MIN;
	
	public ConversionHSV(Pixel result){
		pixelRecu =  result;
		
		rouge=(pixelRecu.getRed());
		vert= (pixelRecu.getGreen());
		bleu=(pixelRecu.getBlue());
		
		MAX = Math.max(rouge, Math.max(vert,bleu));
		MIN = Math.min(rouge, Math.min(vert, bleu));
		
		
		//HUE CALCUL (TEINTE)
		if(rouge == MAX){
			System.out.println("Je suis dans la cas ou Rouge = MAX");
			H= (60 * ((vert-bleu)/(MAX-MIN)+360))%360;
		}
		
		if(vert == MAX){
			System.out.println("Je suis dans la cas ou Vert = MAX");
			H= 60 * ((bleu-rouge)/(MAX-MIN)) + 120;
		}
		if(bleu == MAX){
			System.out.println("Je suis dans la cas ou Bleu = MAX");
			H= 60 * ((rouge-vert)/(MAX-MIN)) + 240;
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
		V=Math.max(rouge/255, Math.max(vert/255,bleu/255));
		
	}
	
	
	
	public int getH(){
		return (int)this.H;
	}
	public int getS(){
		return (int)this.S;
	}
	public int getV(){
		return (int)this.V;
	}
	
	
}
