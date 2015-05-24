
/*
Code copie de la classe "RGBColorMediator.java", et adaptée
pour l'espace de couleur CMYK, tel que specifie dans l'enonce de laboratoire
*/

package view;

/**
 * COURS_GTI_410 : 
 * LABORATOIRE_1
 * 
 * EQUIPE : 
 * 			Idriss Aissou AISI01088901
 * 			Nicolas Hubert HUBN30099004
 */
import java.awt.image.BufferedImage;

import controller.ConversionRGBversCMYK;
import model.ObserverIF;
import model.Pixel;

class CMYKColorMediator extends Object implements SliderObserver, ObserverIF {
	
	//déclarations des sliders des différents
	ColorSlider cCS;
	ColorSlider mCS;
	ColorSlider yCS;
	ColorSlider kCS;
	
	//déclaration des couleurs RGB et CMYK utilisées dans la classe
	double c;
	double m;
	double y;
	double k;
	int r;
	int g;
	int b;
	
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
		
		//On crée les images de fond des sliders
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
		if (s == cCS && v != this.getC()) {
			
			c = ((double)v/255);
			updateM = true;
			updateY = true;
			updateK = true;
		}
		if (s == mCS && v != this.getM()) {
			m =((double)v/255);
			updateC = true;
			updateY = true;
			updateK = true;
		}
		if (s == yCS && v != this.getY()) {
			y =((double)v/255);
			updateM = true;
			updateC = true;
			updateK = true;
		}
		if (s == kCS && v != this.getK()) {
			k = ((double)v/255);
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
		
		Pixel pixel = new Pixel(ConversionRGBversCMYK.getR(c, k),
				ConversionRGBversCMYK.getG(m, k), 
				ConversionRGBversCMYK.getB(y, k), 255); 
		result.setPixel(pixel);
	}
	
	
	//On calcul chaque image selon la valeur des "sliders"
	public void computeCImage(double c, double m, double y, double k) { 
		Pixel p = new Pixel(ConversionRGBversCMYK.getR(c, k),
							ConversionRGBversCMYK.getG(m, k), 
							ConversionRGBversCMYK.getB(y, k), 255); 

		for (int i = 0; i<imagesWidth; ++i) {

			p.setRed((int)(ConversionRGBversCMYK.getR(((double)i / (double)imagesWidth),k))); 
			p.setGreen((int)(ConversionRGBversCMYK.getG(m,k))); 
			p.setBlue((int)(ConversionRGBversCMYK.getB(y,k))); 
		
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				cImage.setRGB(i, j, rgb);
			}
		}
		if (cCS != null) {
			cCS.update(cImage);
		}
	}
	
	public void computeMImage(double c, double m, double y, double k) {
		Pixel p = new Pixel(ConversionRGBversCMYK.getR(c, k),
							ConversionRGBversCMYK.getG(m, k), 
							ConversionRGBversCMYK.getB(y, k), 255);
		
		for (int i = 0; i<imagesWidth; ++i) {
			p.setGreen((int)(ConversionRGBversCMYK.getG(((double)i / (double)imagesWidth),k))); 
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				mImage.setRGB(i, j, rgb);
			}
		}
		if (mCS != null) {
			mCS.update(mImage);
		}
	}
	
	public void computeYImage(double c, double m, double y, double k) { 
		//Pixel p = new Pixel(c, m, y, 255); 
		Pixel p = new Pixel(ConversionRGBversCMYK.getR(c, k),
				ConversionRGBversCMYK.getG(m, k), 
				ConversionRGBversCMYK.getB(y, k), 255);

		for (int i = 0; i<imagesWidth; ++i) {
			p.setBlue((int)(ConversionRGBversCMYK.getB(((double)i / (double)imagesWidth),k))); 
			//p.setBlue((int)(ConversionRGBversCMYK.getB(((double)i / (double)imagesWidth*255.0),k))); 
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				yImage.setRGB(i, j, rgb);
			}
		}
		if (yCS != null) {
			yCS.update(yImage);
		}
	}
	public void computeKImage(double c, double m, double y, double k) { 
		Pixel p = new Pixel(ConversionRGBversCMYK.getR(c, k),
				ConversionRGBversCMYK.getG(m, k), 
				ConversionRGBversCMYK.getB(y, k), 255); 

		for (int i = 0; i<imagesWidth; ++i) {

			p.setRed((int)(ConversionRGBversCMYK.getR(c,((double)i / (double)imagesWidth)))); 
			p.setGreen((int)(ConversionRGBversCMYK.getG(m,((double)i / (double)imagesWidth)))); 
			p.setBlue((int)(ConversionRGBversCMYK.getB(y,((double)i / (double)imagesWidth)))); 
			int rgb = p.getARGB();
			for (int j = 0; j<imagesHeight; ++j) {
				kImage.setRGB(i, j, rgb);
			}
		}
		if (kCS != null) {
			kCS.update(kImage);
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
	
	public BufferedImage getKImage() {
		return kImage;
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
	
	
	public void setKCS(ColorSlider slider) {
		kCS = slider;
		slider.addObserver(this);
	}
	/**
	 * @return
	 */
	public int getC() {
		return (int)(c * 255);
	}

	/**
	 * @return
	 */
	public int getM() {
		return  (int)(m * 255);
	}

	/**
	 * @return
	 */
	public int getY() {
		return  (int)(y * 255);
	}
	/**
	 * @return
	 */
	public int getK() {
		return  (int)(k * 255);
	}


	/* (non-Javadoc)
	 * @see model.ObserverIF#update()
	 */
	public void update() {
		// When updated with the new "result" color, if the "currentColor"
		// is aready properly set, there is no need to recompute the images.
		
		Pixel currentColor = new Pixel(ConversionRGBversCMYK.getR(c, k),
										ConversionRGBversCMYK.getG(m, k), 
										ConversionRGBversCMYK.getB(y, k), 255);
		if(currentColor.getARGB() == result.getPixel().getARGB()) return;
		
		c = ConversionRGBversCMYK.getC(result.getPixel());
		m = ConversionRGBversCMYK.getM(result.getPixel());
		y = ConversionRGBversCMYK.getY(result.getPixel());
		k = ConversionRGBversCMYK.getK(result.getPixel());
		
		cCS.setValue((int)c*255);
		mCS.setValue((int)m*255);
		yCS.setValue((int)y*255);
		kCS.setValue((int)k*255);
		
		computeCImage(c, m, y, k);
		computeMImage(c, m, y, k);
		computeYImage(c, m, y, k);
		computeKImage(c, m, y, k);
		
		// Efficiency issue: When the color is adjusted on a tab in the 
		// user interface, the sliders color of the other tabs are recomputed,
		// even though they are invisible. For an increased efficiency, the 
		// other tabs (mediators) should be notified when there is a tab 
		// change in the user interface. This solution was not implemented
		// here since it would increase the complexity of the code, making it
		// harder to understand.
	}

}

