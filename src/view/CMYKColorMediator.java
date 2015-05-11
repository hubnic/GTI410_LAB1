
/*
Code copie de la classe "RGBColorMediator.java", et adapte
pour l'espace de couleur CMYK, tel que specifie dans l'enonce de laboratoire
*/

package view;

import java.awt.image.BufferedImage;

import controller.ConversionCMYK;
import model.ObserverIF;
import model.Pixel;

class CMYKColorMediator extends Object implements SliderObserver, ObserverIF {
	ColorSlider cCS;
	ColorSlider mCS;
	ColorSlider yCS;
	ColorSlider kCS;
	
	int c;
	int m;
	int y;
	int k;
	
	BufferedImage cImage;
	BufferedImage mImage;
	BufferedImage yImage;
	BufferedImage kImage;
	
	int imagesWidth;
	int imagesHeight;
	ColorDialogResult result;
	
	CMYKColorMediator(ColorDialogResult result, int imagesWidth, int imagesHeight) {
		this.imagesWidth = imagesWidth;
		this.imagesHeight = imagesHeight;
		
		
		/*ICI ON APPELLE LES METHODE DE CONVERSION POUR C M Y K */
		this.c = ConversionCMYK.getC(result.getPixel());
		this.m = ConversionCMYK.getM(result.getPixel());
		this.y = ConversionCMYK.getY(result.getPixel());
		this.k = ConversionCMYK.getK(result.getPixel());
		this.result = result;
		result.addObserver(this);
		
		cImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		mImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		yImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		kImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		computeCImage(c, m, y, k);
		computeMImage(c, m, y, k);
		computeYImage(c, m, y, k); 
		computeKImage(c, m, y, k);
	}
	
	
	/*
	 * @see View.SliderObserver#update(double)
	 */
	public void update(ColorSlider s, int v) {
		boolean updateC = false;
		boolean updateM = false;
		boolean updateY = false;
		boolean updateK = false;
		if (s == cCS && v != c) {
			c = v;
			updateM = true;
			updateY = true;
			updateK = true;
		}
		if (s == mCS && v != m) {
			m = v;
			updateC = true;
			updateY = true;
			updateK = true;
		}
		if (s == yCS && v != y) {
			y = v;
			updateM = true;
			updateC = true;
			updateK = true;
		}
		if (s == yCS && v != k) {
			k = v;
			updateM = true;
			updateY = true;
			updateC = true;
		}
		if (updateC) {
			computeCImage(c, m, y, k);
		}
		if (updateM) {
			computeMImage(c, m, y, k);
		}
		if (updateY) {
			computeYImage(c, m, y, k);
		}
		if (updateK) {
			computeKImage(c, m, y, k);
		}
		
		Pixel pixel = new Pixel(red, green, blue, 255);
		result.setPixel(pixel);
	}
	
	public void computeRedImage(int red, int green, int blue) { 
		Pixel p = new Pixel(red, green, blue, 255); 
		for (int i = 0; i<imagesWidth; ++i) {
			p.setRed((int)(((double)i / (double)imagesWidth)*255.0)); 
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				redImage.setRGB(i, j, rgb);
			}
		}
		if (redCS != null) {
			redCS.update(redImage);
		}
	}
	
	public void computeGreenImage(int red, int green, int blue) {
		Pixel p = new Pixel(red, green, blue, 255); 
		for (int i = 0; i<imagesWidth; ++i) {
			p.setGreen((int)(((double)i / (double)imagesWidth)*255.0)); 
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				greenImage.setRGB(i, j, rgb);
			}
		}
		if (greenCS != null) {
			greenCS.update(greenImage);
		}
	}
	
	public void computeBlueImage(int red, int green, int blue) { 
		Pixel p = new Pixel(red, green, blue, 255); 
		for (int i = 0; i<imagesWidth; ++i) {
			p.setBlue((int)(((double)i / (double)imagesWidth)*255.0)); 
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				blueImage.setRGB(i, j, rgb);
			}
		}
		if (blueCS != null) {
			blueCS.update(blueImage);
		}
	}
	
	/**
	 * @return
	 */
	public BufferedImage getBlueImage() {
		return blueImage;
	}

	/**
	 * @return
	 */
	public BufferedImage getGreenImage() {
		return greenImage;
	}

	/**
	 * @return
	 */
	public BufferedImage getRedImage() {
		return redImage;
	}

	/**
	 * @param slider
	 */
	public void setRedCS(ColorSlider slider) {
		redCS = slider;
		slider.addObserver(this);
	}

	/**
	 * @param slider
	 */
	public void setGreenCS(ColorSlider slider) {
		greenCS = slider;
		slider.addObserver(this);
	}

	/**
	 * @param slider
	 */
	public void setBlueCS(ColorSlider slider) {
		blueCS = slider;
		slider.addObserver(this);
	}
	/**
	 * @return
	 */
	public double getBlue() {
		return blue;
	}

	/**
	 * @return
	 */
	public double getGreen() {
		return green;
	}

	/**
	 * @return
	 */
	public double getRed() {
		return red;
	}


	/* (non-Javadoc)
	 * @see model.ObserverIF#update()
	 */
	public void update() {
		// When updated with the new "result" color, if the "currentColor"
		// is aready properly set, there is no need to recompute the images.
		Pixel currentColor = new Pixel(red, green, blue, 255);
		if(currentColor.getARGB() == result.getPixel().getARGB()) return;
		
		red = result.getPixel().getRed();
		green = result.getPixel().getGreen();
		blue = result.getPixel().getBlue();
		
		redCS.setValue(red);
		greenCS.setValue(green);
		blueCS.setValue(blue);
		computeRedImage(red, green, blue);
		computeGreenImage(red, green, blue);
		computeBlueImage(red, green, blue);
		
		// Efficiency issue: When the color is adjusted on a tab in the 
		// user interface, the sliders color of the other tabs are recomputed,
		// even though they are invisible. For an increased efficiency, the 
		// other tabs (mediators) should be notified when there is a tab 
		// change in the user interface. This solution was not implemented
		// here since it would increase the complexity of the code, making it
		// harder to understand.
	}

}

