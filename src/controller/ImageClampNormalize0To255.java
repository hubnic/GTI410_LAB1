package controller;

import model.ImageDouble;
import model.ImageX;
import model.Pixel;
import model.PixelDouble;
/**
 * COURS_GTI_410 : 
 * LABORATOIRE_3
 * 
 * EQUIPE : 
 * 			Idriss Aissou AISI01088901
 * 			Nicolas Hubert HUBN30099004
 */
public class ImageClampNormalize0To255 extends ImageClampStrategy{
	
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
				}
			
			}
			newImage.endPixelUpdate();
			return newImage;

		 
	 }
	 private double normalisation(double cone, double R, double V, double B){
		 
		return 0;
	 }

}
