package controller;

import model.ImageDouble;
import model.ImageX;
import model.PixelDouble;

public class ImageClampAbsNormalize0To255 extends ImageClampStrategy {

	public ImageX convert(ImageDouble image) {
		int imageWidth = image.getImageWidth();
		int imageHeight = image.getImageHeight();
		ImageX newImage = new ImageX(0, 0, imageWidth, imageHeight);
		PixelDouble curPixelDouble = null;

		newImage.beginPixelUpdate();
		
		
		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				curPixelDouble = image.getPixel(x,y);
				
				/**newImage.setPixel(x, y, new Pixel((int)(clamp0To255(curPixelDouble.getRed())),
												  (int)(clamp0To255(curPixelDouble.getGreen())),
												  (int)(clamp0To255(curPixelDouble.getBlue())),
												  (int)(clamp0To255(curPixelDouble.getAlpha()))));**/
			}
		}
		
		
		newImage.endPixelUpdate();
		return newImage;
		
		 
	 }
	
}
