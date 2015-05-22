package controller;

import model.Pixel;
/**
 * COURS_GTI_410 : 
 * LABORATOIRE_1
 * 
 * EQUIPE : 
 * 			Idriss Aissou AISI01088901
 * 			Nicolas Hubert
 */
public class ConversionRGBversCMYK {
	
	public static double getC(Pixel pixel){
		if(getK(pixel) == 1){
			return 0;
		}
		else{
		double red = (double)pixel.getRed()/255;
		return (1-red-getK(pixel))/(1-getK(pixel));
		}
	}
	public static double getM(Pixel pixel){
		if(getK(pixel) == 1){
			return 0;
		}
		else{
		double green = (double)pixel.getGreen()/255;
		System.out.println("Green:" + green);
		return (1-green-getK(pixel))/(1-getK(pixel));
		}
	}
	public static double getY(Pixel pixel){
		if(getK(pixel) == 1){
			return 0;
		}
		else{
		double blue = (double)pixel.getBlue()/255;
		System.out.println("Blue:" + blue);
		return (1-blue-getK(pixel))/(1-getK(pixel));
		}
	}
	public static double getK(Pixel pixel){
		
		return 1-Math.max((double)pixel.getRed()/255, Math.max((double)pixel.getBlue()/255, (double)pixel.getGreen()/255));
	}
	
	
	
	public static int getR(double c, double k){
		System.out.println("C: " + c +"K: " + k);
		System.out.println("Rouge: " + (int) ((1-c)*(1-k)));
		return   (int) (int) ((1-c)*(1-k)*255);
	}
	public static int getG(double m, double k){
		return (int) ((1-m)*(1-k)*255);
	}
	public static int getB(double y, double k){
		return (int) (int) ((1-y)*(1-k)*255);
	}
	
}

