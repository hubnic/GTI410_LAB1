package controller;

import model.Pixel;

public class ConversionRGBversCMYK {
	Pixel pixelRecu;
	float C;
	float M;
	float Y;
	float K;
	float rouge;
	float vert;
	float bleu;
	float MAX;
	float MIN;
	
	public ConversionRGBversCMYK(Pixel result){
		pixelRecu =  result;
		
		rouge=(pixelRecu.getRed());
		vert= (pixelRecu.getGreen());
		bleu=(pixelRecu.getBlue());
		
		MAX = Math.max(rouge, Math.max(vert,bleu));
		MIN = Math.min(rouge, Math.min(vert, bleu));
		
		
		k
		
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

