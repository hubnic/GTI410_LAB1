package view;

import java.awt.image.BufferedImage;

import controller.ConversionHSV;
import model.ObserverIF;

class HSVColorMediator extends Object implements SliderObserver, ObserverIF {
	ColorSlider hCS;
	ColorSlider sCS;
	ColorSlider vCS;
	int h;
	int s;
	int v;
	BufferedImage hImage;
	BufferedImage sImage;
	BufferedImage vImage;
	int imagesWidth;
	int imagesHeight;
	ConversionHSV conversionHSV;
	ColorDialogResult result;
	HSVColorMediator(ColorDialogResult result, int imagesWidth, int imagesHeight) {
		this.imagesWidth = imagesWidth;
		this.imagesHeight = imagesHeight;
		conversionHSV = new ConversionHSV(result.getPixel());
		
		this.h = conversionHSV.getH();
		this.s = conversionHSV.getS();
		this.v = conversionHSV.getV();
		
		this.result = result;
		result.addObserver(this);
		
		hImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		sImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		vImage = new BufferedImage(imagesWidth, imagesHeight, BufferedImage.TYPE_INT_ARGB);
		
		
		computeHueImage(h, s, v);
		computeSaturationImage(h, s, v);
		computeValueImage(h, s, v); 
	}
	
	
	
	@Override
	public void update(ColorSlider cs, int v) {
		// TODO Auto-generated method stub
		boolean updateHue = false;
		boolean updateSaturation = false;
		boolean updateValue = false;
		
		if (cs == this.hCS && v != this.h) {
			this.h = v;
			updateSaturation = true;
			updateValue = true;
		}
		if (cs == this.sCS && v != this.s) {
			this.s = v;
			updateHue = true;
			updateValue = true;
		}
		if (cs == this.vCS && v != this.v) {
			this.v = v;
			updateHue = true;
			updateSaturation = true;
		}
		if (updateHue) {
			computeHueImage(this.h, this.s, this.v);
		}
		if (updateSaturation) {
			computeSaturationImage(this.h, this.s, this.v);
		}
		if (updateValue) {
			computeValueImage(this.h, this.s, this.v);
		}
		
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		boolean updateHue = false;
		boolean updateSaturation = false;
		boolean updateValue = false;
		
		
		
	}

	public void computeHueImage(int hRecu,int sRecu,int vRecu) {
		// TODO Auto-generated method stub
		
	}
	
	public void computeSaturationImage(int hRecu,int sRecu,int vRecu) {
		// TODO Auto-generated method stub
		
	}
	public void computeValueImage(int hRecu,int sRecu,int vRecu) {
		// TODO Auto-generated method stub
		
	}
	
	
	public BufferedImage getHueImage() {
		return this.hImage;
	}

	/**
	 * @return
	 */
	public BufferedImage getSaturationImage() {
		return this.sImage;
	}

	/**
	 * @return
	 */
	public BufferedImage getValueImage() {
		return this.vImage;
	}
	
	
	public void setHueCS(ColorSlider slider) {
		hCS = slider;
		slider.addObserver(this);
	}

	/**
	 * @param slider
	 */
	public void setSaturationCS(ColorSlider slider) {
		sCS = slider;
		slider.addObserver(this);
	}

	/**
	 * @param slider
	 */
	public void setValueCS(ColorSlider slider) {
		vCS = slider;
		slider.addObserver(this);
	}
	

	
}
