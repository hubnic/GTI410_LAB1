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
				double RED = curPixelDouble.getRed();
				double VERT = curPixelDouble.getGreen();
				double BLEU = curPixelDouble.getBlue();
				double ALPHA = curPixelDouble.getAlpha();
				
				newImage.setPixel(x, y, new Pixel((int)(normalisation(RED)),
												  (int)(normalisation(VERT)),
												  (int)(normalisation(BLEU)),
												  (int)(normalisation(ALPHA))));
			}
		}
		
		
		newImage.endPixelUpdate();
		return newImage;
		
	 }
	double normalisation(double cone){
		double calcul;
		calcul = Math.abs(cone);
		
		//***********
		return calcul;
	}
	
}
