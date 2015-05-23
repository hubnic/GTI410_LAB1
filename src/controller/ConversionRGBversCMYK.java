package controller;

import model.Pixel;
/**
 * COURS_GTI_410 : 
 * LABORATOIRE_1
 * 
 * EQUIPE : 
 * 			Idriss Aissou AISI01088901
 * 			Nicolas Hubert HUBN30099004
 */

//Les formules sont tirées du site :  http://www.rapidtables.com/convert/color/rgb-to-cmyk.htm et http://www.rapidtables.com/convert/color/cmyk-to-rgb.htm
public class ConversionRGBversCMYK {
	
	/**
	 * @param pixel, pixel passé en paramètre, sur lequel nous irons chercher les paramètres
	 * @return double, la valeur entre 0 et 1 du paramètre Cyan 
	 */
	public static double getC(Pixel pixel){
		if(getK(pixel) == 1){
			return 0;
		}
		else{
		double red = (double)pixel.getRed()/255;
		return (1-red-getK(pixel))/(1-getK(pixel));
		}
	}
	/**
	 * @param pixel, pixel passé en paramètre, sur lequel nous irons chercher les paramètres
	 * @return double, la valeur entre 0 et 1 du paramètre Magenta
	 */
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
	 /**
		 * @param pixel, pixel passé en paramètre, sur lequel nous irons chercher les paramètres
		 * @return double, la valeur entre 0 et 1 du paramètre Jaune
		 */
	public static double getY(Pixel pixel){
		if(getK(pixel) == 1){
			return 0;
		}
		else{
		double blue = (double)pixel.getBlue()/255;
		System.out.println("Blue:" + blue);
		return (1-blue-getK(pixel))/(1-getK(pixel));
		}
		
	/**
	 * @param pixel, pixel passé en paramètre, sur lequel nous irons chercher les paramètres
	 * @return double, la valeur entre 0 et 1 du paramètre K, soit la teinte de noir
	 */
	}
	public static double getK(Pixel pixel){
		
		return 1-Math.max((double)pixel.getRed()/255, Math.max((double)pixel.getBlue()/255, (double)pixel.getGreen()/255));
	}
	
	
	
	/**
	 * @param c, la valeur Cyan que nous utilisons pour faire le calcul du paramètre rouge
	 * @param k, la valeur de K, la teinte de noir,  que nous utilisons pour faire le calcul du paramètre rouge
	 * @return rouge, renvoi la valeur de la couleur rouge convertie
	 */
	public static int getR(double c, double k){
		System.out.println("C: " + c +"K: " + k);
		System.out.println("Rouge: " + (int) ((1-c)*(1-k)));
		return   (int) (int) ((1-c)*(1-k)*255);
	}
	/**
	 * @param m, la valeur Magenta que nous utilisons pour faire le calcul du paramètre vert
	 * @param k, la valeur de K, la teinte de noir,  que nous utilisons pour faire le calcul du paramètre vert
	 * @return vert, renvoi la valeur de la couleur rouge convertie
	 */
	public static int getG(double m, double k){
		return (int) ((1-m)*(1-k)*255);
	}
	/**
	 * @param y, la valeur jaune que nous utilisons pour faire le calcul du paramètre bleu
	 * @param k, la valeur de K, la teinte de noir,  que nous utilisons pour faire le calcul du paramètre bleu
	 * @return bleu, renvoi la valeur de la couleur rouge convertie
	 */
	public static int getB(double y, double k){
		return (int) (int) ((1-y)*(1-k)*255);
	}
	
}

