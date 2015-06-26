package controller;

import model.ImageDouble;
import model.ImageX;
import model.Pixel;
import model.PixelDouble;

/**
 * Implementation de la gestion des bordures en mode Reflexion
 * @author i7
 *
 */
public class PaddingMiror extends PaddingStrategy {

	@Override
	public Pixel pixelAt(ImageX image, int x, int y) {
		// TODO Auto-generated method stub
		
		//Gauche
		if(x==-1){
			if(y==-1){
				return image.getPixel(0, 0);
			}
			if(y==image.getImageHeight()){
				return image.getPixel(0, image.getImageHeight()-1);
			}
			else{
				return image.getPixel(x+1, y);
			}
		}
		//Droite
		if(x==image.getImageWidth()){
			if(y==-1){
				return image.getPixel(x-1, y+1);
			}
			if(y==image.getImageHeight()){
				return image.getPixel(x-1, y-1);
			}
			else{
				return image.getPixel(x-1, y);
			}
		}
		
		//LIGNE DU HAUT
		if(y==-1 && x!=-1 && x!=image.getImageHeight()){
	
				return image.getPixel(x, y+1);
		}
		
		//LIGNE DU BAS
		if(y==image.getImageHeight() && x!=-1 && x!=image.getImageHeight()){
			
			return image.getPixel(x, y-1);
		}
				
		//Sinon retourner la valeur
		else{
			return image.getPixel(x, y);
		}
		
	}

	@Override
	public PixelDouble pixelAt(ImageDouble image, int x, int y) {
		// TODO Auto-generated method stub
		//Gauche
		if(x==-1){
			if(y==-1){
				return image.getPixel(0, 0);
			}
			if(y==image.getImageHeight()){
				return image.getPixel(0, image.getImageHeight()-1);
			}
			else{
				return image.getPixel(x+1, y);
			}
		}
		//Droite
		if(x==image.getImageWidth()){
			if(y==-1){
				return image.getPixel(x-1, y+1);
			}
			if(y==image.getImageHeight()){
				return image.getPixel(x-1, y-1);
			}
			else{
				return image.getPixel(x-1, y);
			}
		}
		
		//LIGNE DU HAUT
		if(y==-1 && x!=-1 && x!=image.getImageHeight()){
	
				return image.getPixel(x, y+1);
		}
		
		//LIGNE DU BAS
		if(y==image.getImageHeight() && x!=-1 && x!=image.getImageHeight()){
			
			return image.getPixel(x, y-1);
		}
				
		//Sinon retourner la valeur
		else{
			return image.getPixel(x, y);
		}
	}

}
