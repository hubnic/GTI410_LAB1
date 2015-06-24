package controller;

import model.ImageDouble;
import model.ImageX;
import model.Pixel;
import model.PixelDouble;

public class ImageClampAbsNormalizeTo255 extends ImageClampStrategy {

	public ImageX convert(ImageDouble image) {
		int imageWidth = image.getImageWidth();
		int imageHeight = image.getImageHeight();
		ImageX newImage = new ImageX(0, 0, imageWidth, imageHeight);
		PixelDouble curPixelDouble = null;

		newImage.beginPixelUpdate();
		
		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				curPixelDouble = image.getPixel(x,y);
				double ROUGE = curPixelDouble.getRed();
				double VERT = curPixelDouble.getGreen();
				double BLEU = curPixelDouble.getBlue();
				double ALPHA = curPixelDouble.getAlpha();
				
				newImage.setPixel(x, y, new Pixel((int)(normalisation(ROUGE,ROUGE,VERT,BLEU)),
												  (int)(normalisation(VERT,ROUGE,VERT,BLEU)),
												  (int)(normalisation(BLEU,ROUGE,VERT,BLEU)),
												  (int)(normalisation(ALPHA,ALPHA,ALPHA,ALPHA))));
			}
		}
		
		
		newImage.endPixelUpdate();
		return newImage;
		
	 }
	double normalisation(double cone, double R,double V, double B){
		double calcul;
		calcul = Math.abs(cone);
		return ((calcul/(R+V+B))*255);
	}
	
}
