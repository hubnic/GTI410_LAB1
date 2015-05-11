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
				
		if (r == MAX & g==MIN) {
			H = 5+ ((r-b)/(r-g));
		}
		else if (r == MAX & b== MIN){
			H=1-((r-g)/(r-b));
		}
		else if (g == MAX & b== MIN){
			H=1+((g-r)/(g-b));
		}
		else if (g == MAX & r== MIN){
			H=3-((g-b)/(g-r));
		}
		else if (b == MAX & r== MIN){
			H=3+((b-g)/(b-r));
		}
		else if (b == MAX & g== MIN){
			H=5-((b-r)/(b-g));
		}
		H= H * 60;
		
		if(H<0){
			H += 360; 
		}
		
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
		int MAX = r;
		
		if (g > MAX){
			MAX=g;
		}
		if (b > MAX){
			MAX=b;
		}
		
		return MAX;
	}
	
	public int min(int r,int g,int b){
		int MIN = r;
		
		if (g < MIN){
			MIN=g;
		}
		if (b < MIN){
			MIN=b;
		}
		return MIN;
	}
	
	
}
