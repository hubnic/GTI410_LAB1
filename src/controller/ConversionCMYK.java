package controller;

import model.Pixel;

public class ConversionCMYK {
	

	public static int getC(Pixel result){
		return 1-result.getRed();
	}
	
	public static int getM(Pixel result){
		return 1-result.getGreen();
	}
	
	public static int getY(Pixel result){
		return 1-result.getBlue();
	}

	public  static int getK(Pixel result){
		return result.getAlpha();
	}
}
