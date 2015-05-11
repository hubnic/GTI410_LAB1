package controller;

import model.Pixel;

public class ConversionCMYK {
	
	
	public int getC(Pixel result){
		return 1-result.getRed();
	}
	
	public int getM(Pixel result){
		return 1-result.getGreen();
	}
	
	public int getY(Pixel result){
		return 1-result.getBlue();
	}

	public int getK(Pixel result){
		return result.getAlpha();
	}
}
