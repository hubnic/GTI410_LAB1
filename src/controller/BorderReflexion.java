package controller;

import model.ImageDouble;
import model.ImageX;
import model.Pixel;
import model.PixelDouble;

public class BorderReflexion extends PaddingStrategy  {
	ImageX ImageBordureReflexion;
	@Override
	public Pixel pixelAt(ImageX image, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PixelDouble pixelAt(ImageDouble image, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ImageX Reflexion(ImageX imageRecu){
		ImageBordureReflexion = new ImageX(imageRecu.getCenter().x ,
				imageRecu.getCenter().y,
				imageRecu.getImageWidth()+2,
				imageRecu.getImageHeight()+2);
		
		//Affectation des coins de l'image avec la bordure Reflexion
		//Coin haut gauche
		ImageBordureReflexion.setPixel(0, 0, imageRecu.getPixel(0, 0));
		//Coin bas gauche
		ImageBordureReflexion.setPixel(0, ImageBordureReflexion.getImageHeight()-1, imageRecu.getPixel(0, imageRecu.getImageHeight()-1));
		//haut Droit
		ImageBordureReflexion.setPixel(ImageBordureReflexion.getImageWidth()-1, 0, imageRecu.getPixel(imageRecu.getImageWidth()-1, 0));
		//bas Gauche
		ImageBordureReflexion.setPixel(ImageBordureReflexion.getImageWidth()-1, ImageBordureReflexion.getImageHeight()-1, imageRecu.getPixel(imageRecu.getImageWidth()-1, imageRecu.getImageHeight()-1));
		
		//Recopie les bords sans les coins
		CopieCotes(imageRecu);
		
		//Recopie l'image
		for(int x=0; x<imageRecu.getImageWidth(); x++){
			for(int y=0; y<imageRecu.getImageHeight(); y++){
				ImageBordureReflexion.setPixel(x+1,y+1,imageRecu.getPixel(x, y));
			}
		}
		
		
		return ImageBordureReflexion;
	}

	void CopieCotes(ImageX imageRecu){
		
		for(int x=0; x<imageRecu.getImageWidth(); x++){
			ImageBordureReflexion.setPixel(x+1 ,0,imageRecu.getPixel(x, 0));
			ImageBordureReflexion.setPixel(x+1 ,ImageBordureReflexion.getImageHeight()-1,imageRecu.getPixel(x, imageRecu.getImageHeight()-1));
		}
		
		for(int y=0; y<imageRecu.getImageHeight(); y++){
			ImageBordureReflexion.setPixel(0 ,y+1,imageRecu.getPixel(0, y));
			ImageBordureReflexion.setPixel(ImageBordureReflexion.getImageWidth()-1 ,y,imageRecu.getPixel(imageRecu.getImageWidth()-1, y));
		}
		
	}
	
	
}
