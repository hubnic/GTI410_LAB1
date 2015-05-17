
/*
Code copie de la classe "RGBColorMediator.java", et adapte
pour l'espace de couleur CMYK, tel que specifie dans l'enonce de laboratoire
*/

package view;

import java.awt.image.BufferedImage;

import controller.ConversionRGBversCMYK;
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
	
	private ConversionRGBversCMYK conversionCMYK;
	
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
		this.c = ConversionRGBversCMYK.getC(result.getPixel());
		this.m = ConversionRGBversCMYK.getM(result.getPixel());
		this.y = ConversionRGBversCMYK.getY(result.getPixel());
		this.k = ConversionRGBversCMYK.getK(result.getPixel());
		this.result = result;
		result.addObserver(this);
		
		cImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		mImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		yImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		kImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		computeCImage(c, m, y);
		computeMImage(c, m, y);
		computeYImage(c, m, y); 
		//computeKImage(k);
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
			computeCImage(c, m, y);
		}
		if (updateM) {
			computeMImage(c, m, y);
		}
		if (updateY) {
			computeYImage(c, m, y);
		}
		/*if (updateK) {
			computeKImage(k);
		}*/
		
		Pixel pixel = new Pixel(c, m, y, 255);
		result.setPixel(pixel);
	}
	
	public void computeCImage(int c, int m, int y) { 
		Pixel p = new Pixel(c, m, y, 255); 
		for (int i = 0; i<imagesWidth; ++i) {
			p.setRed((int)(((double)1-i / (double)imagesWidth)*255.0)); 
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				cImage.setRGB(i, j, rgb);
			}
		}
		if (cCS != null) {
			cCS.update(cImage);
		}
	}
	
	public void computeMImage(int c, int m, int y) {
		Pixel p = new Pixel(c, m, y, 255); 
		for (int i = 0; i<imagesWidth; ++i) {
			p.setGreen((int)(((double)i / (double)imagesWidth)*255.0)); 
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				mImage.setRGB(i, j, rgb);
			}
		}
		if (mCS != null) {
			mCS.update(mImage);
		}
	}
	
	public void computeYImage(int c, int m, int y) { 
		Pixel p = new Pixel(c, m, y, 255); 
		for (int i = 0; i<imagesWidth; ++i) {
			p.setBlue((int)(((double)i / (double)imagesWidth)*255.0)); 
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				yImage.setRGB(i, j, rgb);
			}
		}
		if (yCS != null) {
			yCS.update(yImage);
		}
	}
	
	/**
	 * @return
	 */
	public BufferedImage getCImage() {
		return cImage;
	}

	/**
	 * @return
	 */
	public BufferedImage getMImage() {
		return mImage;
	}

	/**
	 * @return
	 */
	public BufferedImage getYImage() {
		return yImage;
	}

	/**
	 * @param slider
	 */
	public void setCCS(ColorSlider slider) {
		cCS = slider;
		slider.addObserver(this);
	}

	/**
	 * @param slider
	 */
	public void setMCS(ColorSlider slider) {
		mCS = slider;
		slider.addObserver(this);
	}

	/**
	 * @param slider
	 */
	public void setYCS(ColorSlider slider) {
		yCS = slider;
		slider.addObserver(this);
	}
	/**
	 * @return
	 */
	public double getC() {
		return c;
	}

	/**
	 * @return
	 */
	public double getM() {
		return m;
	}

	/**
	 * @return
	 */
	public double getY() {
		return y;
	}


	/* (non-Javadoc)
	 * @see model.ObserverIF#update()
	 */
	public void update() {
		// When updated with the new "result" color, if the "currentColor"
		// is aready properly set, there is no need to recompute the images.
		
		Pixel currentColor = new Pixel(c, m, y, 255);
		if(currentColor.getARGB() == result.getPixel().getARGB()) return;
		
		c = ConversionRGBversCMYK.getC(result.getPixel());
		m = ConversionRGBversCMYK.getM(result.getPixel());
		y = ConversionRGBversCMYK.getY(result.getPixel());
		
		cCS.setValue(c);
		mCS.setValue(m);
		yCS.setValue(y);
		computeCImage(c, m, y);
		computeMImage(c, m, y);
		computeYImage(c, m, y);
		
		// Efficiency issue: When the color is adjusted on a tab in the 
		// user interface, the sliders color of the other tabs are recomputed,
		// even though they are invisible. For an increased efficiency, the 
		// other tabs (mediators) should be notified when there is a tab 
		// change in the user interface. This solution was not implemented
		// here since it would increase the complexity of the code, making it
		// harder to understand.
	}

}

