package controller;

import model.Pixel;

public class ConversionHSV {
	Pixel pixelRecu;
	int H;
	int S;
	int V;
	int r;
	int g;
	int b;
	int MAX;
	int MIN;
	public ConversionHSV(Pixel result){
		pixelRecu =  result;
		
		r=pixelRecu.getRed();
		g=pixelRecu.getGreen();
		b=pixelRecu.getRed();
		
		
		V = this.max(r, g, b);
		S = (V-min(r,g,b))/V;
				
		if ()
	}
	
	public int getH(){
		return this.H;
	}
	public int getS(){
		return this.S;
	}
	public int getV(){
		return this.V;
	}
	
	public int max(int r,int g,int b){
		int max = r;
		
		if (g >max){
			max=g;
		}
		if (b >max){
			max=b;
		}
		
		return max;
	}
	
	public int min(int r,int g,int b){
		int min = r;
		
		if (g < min){
			min=g;
		}
		if (b < min){
			min=b;
		}
		return min;
	}
	
	
}
