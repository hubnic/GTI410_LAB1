
package controller;
/**
 * COURS_GTI_410 : 
 * LABORATOIRE_1
 * 
 * EQUIPE : 
 * 			Idriss Aissou AISI01088901
 * 			Nicolas Hubert HUBN30099004
 */

/**
 * Classe qui permet de convertir du format HSV au RGB
 *
 */
public class ConversionHSVversRGB {

	/**
	 * Constructeur par defaut de la classe ConversionHSVversRGB
	 */
	public ConversionHSVversRGB(){}
	
	/**
	 * Methode permettant de convertir le hsv eb rgb
	 * Source de la methode de calcul :
	 * http://www.rapidtables.com/convert/color/hsv-to-rgb.htm
	 * @param hRecu (teinte)
	 * @param sRecu (saturation)
	 * @param vRecu (valeur)
	 * @return int []Tableau RGB
	 */
	public int[] HSVversRGB (double hRecu, double sRecu, double vRecu){
		int[] RGB_TAB =  new int[3];
		double[] RGB_tmp =  new double[3];
		double c;
		double x;
		double m;
		c= (vRecu * sRecu);
		x = c * (1-Math.abs((hRecu/60 % 2) -1));
		m= (vRecu - c);


		if(hRecu>=0 & hRecu<60){
			RGB_tmp[0]= c;
			RGB_tmp[1]= x;
			RGB_tmp[2]= 0;
		}
		else if(hRecu>=60 & hRecu<120){
			RGB_tmp[0]= x;
			RGB_tmp[1]= c;
			RGB_tmp[2]= 0;
		}
		else if(hRecu>=120 & hRecu<180){
			RGB_tmp[0]= 0;
			RGB_tmp[1]= c;
			RGB_tmp[2]= x;
		}
		else if(hRecu>=180 & hRecu<240){
			RGB_tmp[0]= 0;
			RGB_tmp[1]= x;
			RGB_tmp[2]= c;
		}
		else if(hRecu>=240 & hRecu<300){
			RGB_tmp[0]= x;
			RGB_tmp[1]= 0;
			RGB_tmp[2]= c;
		}
		else if(hRecu>=300 & hRecu<360){
			RGB_tmp[0]= c;
			RGB_tmp[1]= 0;
			RGB_tmp[2]= x;
		}
		RGB_TAB[0]= (int) Math.round((RGB_tmp[0]+m)*255);
		RGB_TAB[1]= (int) Math.round((RGB_tmp[1]+m)*255);
		RGB_TAB[2]= (int) Math.round((RGB_tmp[2]+m)*255);
        
        
		return RGB_TAB;
	}
	
}

