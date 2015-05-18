package view;
import java.awt.image.BufferedImage;

import controller.ConversionHSVversRGB;
import controller.ConversionRGBversHSV;
import model.ObserverIF;
import model.Pixel;


/*
Utilisation de la classe "RGBColorMediator.java", et adaptee
pour l'espace de couleur HSV (TSV),
tel que specifie dans l'enonce de laboratoire 1
GTI_410
*/
/**
 * Classe permettant de mettre a jour les sliders du Panel HSV.
 * Celle-ci permet de convertir les données RGB en HSV puis HSV vers RGB
 * grace aux classes de conversion : ConversionHSVversRGB  et ConversionRGBversHSV
 * afin de mettre a jour la couleur de l objet dessiner sur le panneau
 *
 */
class HSVColorMediator extends Object implements SliderObserver, ObserverIF {
	//LES DIMENSIONS DE L'IMAGE
	int imagesWidth;
	int imagesHeight;
	
	//SLIDER HSV
	ColorSlider teinteCS;
	ColorSlider saturationCS;
	ColorSlider valeurCS;
	

	//COULEUR EN HSV DANS TAB
	double[] TSV_tab = new double[3];
	//COULEUR EN RVB DANS TAB
	int[] RVB_tab = new int [3];
	
	//LES CONVERTISSEURS
	ConversionHSVversRGB convertisseurHsvRgb = new ConversionHSVversRGB();
	ConversionRGBversHSV convertisseurRgbHsv = new ConversionRGBversHSV();
	
	//BUFFERED IMAGE TSV
	BufferedImage teinteImage;
	BufferedImage saturationImage;
	BufferedImage valeurImage;
	
	//LE RESULTAT DES COULEURS
	ColorDialogResult result;
	
	/**
	 * Constructeur de la classe HSVColorMediator
	 * @param result
	 * @param imagesWidthR (taille de l image)
	 * @param imagesHeightR (taille de l image)
	 */
	HSVColorMediator(ColorDialogResult result, int imagesWidthR, int imagesHeightR) {
		
		//ON RECUPERE LES VALEURS DU PIXEL ET ON LES ATTRIBUT A TAB RVB
		this.updateRVBtab(result);
		//ON RECUPERE LES DIMENSIONS DE l IMAGE
		this.imagesHeight = imagesHeightR;
		this.imagesWidth = imagesWidthR;
		//ON RECUPERE LES DIMENSIONS DE l IMAGE
		this.result = result;
		result.addObserver(this);
		
		//BUFF IMAGE
		teinteImage = new BufferedImage(imagesWidth, imagesHeight,BufferedImage.TYPE_INT_ARGB);
		saturationImage = new BufferedImage(imagesWidth, imagesHeight,BufferedImage.TYPE_INT_ARGB);
		valeurImage = new BufferedImage(imagesWidth, imagesHeight,BufferedImage.TYPE_INT_ARGB);
		
		//ON CONVERTIT LES RGB EN HSV
		TSV_tab = convertisseurRgbHsv.RGBversHSV(RVB_tab[0], RVB_tab[1], RVB_tab[2]);
		
		//COMPUTE IMAGE SELON HSV
		computeImageGlobale(TSV_tab[0], TSV_tab[1], TSV_tab[2]);
	}
	
	/*GET*/
	
	
	public BufferedImage getHueImage() {
	        return teinteImage;
	}
		
	public BufferedImage getSaturationImage() {
		        return saturationImage;
	}
		
	public BufferedImage getValueImage() {
		        return valeurImage;
	}
	public double getHue() {
		        return this.TSV_tab[0];
	}
		
	public double getSaturation() {
		        return this.TSV_tab[1];
	}
			
	public double getValue() {
		        return this.TSV_tab[2];
	}
		
	public int getHueColor() {
		        return (int) ((TSV_tab[0] / 360) * 255);
	}
		
	public int getSaturationColor() {
		        return (int) (TSV_tab[1] * 255);
	}
		
	public int getValueColor() {
				return (int) (TSV_tab[2] * 255);
	}
	
	/*SET*/
	public void setHueCS(ColorSlider slider) {
        teinteCS = slider;
        slider.addObserver(this);
	}

	public void setSaturationCS(ColorSlider slider) {
        saturationCS = slider;
        slider.addObserver(this);
	}

	public void setValueCS(ColorSlider slider) {
        valeurCS = slider;
        slider.addObserver(this);
	}
	
	/*COMPUTE IMAGE*/
	private void computeImageGlobale(double teinte, double saturation, double valeur){
		computeHueImage(teinte, saturation, valeur);
	    computeSaturationImage(teinte, saturation, valeur);
	    computeValueImage(teinte, saturation, valeur);
	}

	private void computeHueImage(double h, double s, double v) {

		Pixel pUpdate = new Pixel (this.RVB_tab[0],this.RVB_tab[1],this.RVB_tab[2],255);
				for (int i = 0; i < imagesWidth; ++i) {
		            RVB_tab = this.convertisseurHsvRgb.HSVversRGB((((double) i / (double) imagesWidth) * 360),s, v);
		
		            pUpdate.setRed(RVB_tab[0]);pUpdate.setGreen(RVB_tab[1]);pUpdate.setBlue(RVB_tab[2]);

		            for (int j = 0; j < imagesHeight; j++) {
		                    teinteImage.setRGB(i, j, pUpdate.getARGB());
		            }
				}
	    if (teinteCS != null) {
             teinteCS.update(teinteImage);
	    }		
	}

	private void computeSaturationImage(double h, double s, double v) {

		Pixel pUpdate = new Pixel (RVB_tab[0],RVB_tab[1],RVB_tab[2],255);
		
			for (int i = 0; i < imagesWidth; ++i) {
				RVB_tab = this.convertisseurHsvRgb.HSVversRGB(h,((double) i / (double) imagesWidth), v);
	
	            pUpdate.setRed(RVB_tab[0]);pUpdate.setGreen(RVB_tab[1]);pUpdate.setBlue(RVB_tab[2]);

		            for (int j = 0; j < imagesHeight; j++) {
		                    saturationImage.setRGB(i, j, pUpdate.getARGB());
		            }
			}
	     if (saturationCS != null) {
	    	  saturationCS.update(saturationImage);
	     }
	}
	
	private void computeValueImage(double h, double s, double v) {

		Pixel pUpdate = new Pixel (RVB_tab[0],RVB_tab[1],RVB_tab[2],255);
			for (int i = 0; i < imagesWidth; ++i) {
				RVB_tab = this.convertisseurHsvRgb.HSVversRGB(h,s, ((double) i / (double) imagesWidth));
	
	            pUpdate.setRed(RVB_tab[0]);pUpdate.setGreen(RVB_tab[1]);pUpdate.setBlue(RVB_tab[2]);
	
		            for (int j = 0; j < imagesHeight; j++) {
		                    valeurImage.setRGB(i, j, pUpdate.getARGB());
		            }
			}
	    if (valeurCS != null) {
	    	  valeurCS.update(valeurImage);
	    }
	}
	
	/*UPDATE*/
	public void updateRVBtab(ColorDialogResult resultR){
		RVB_tab[0]=resultR.getPixel().getRed();
		RVB_tab[1]=resultR.getPixel().getGreen();
		RVB_tab[2]=resultR.getPixel().getBlue();
	}
	
	public void update(ColorSlider cs, int v) {

        boolean updateHue = false;
        boolean updateSaturation = false;
        boolean updateValue = false;

        if (cs == teinteCS && v != this.getHueColor()) {
                this.TSV_tab[0] = (((double) v / 255) * 360);
                updateSaturation = true;
                updateValue = true;
        }


        if (cs == saturationCS && v != this.getSaturationColor()) {
                this.TSV_tab[1] = ((double) v / 255);
                updateHue = true;
                updateValue = true;
        }


        if (cs == valeurCS && v != this.getValueColor()) {
                this.TSV_tab[2] = ((double) v / 255);
                updateHue = true;
                updateSaturation = true;
        }


        if (updateHue) {
                computeHueImage(TSV_tab[0], TSV_tab[1], TSV_tab[2]);
        }

        if (updateSaturation) {
                computeSaturationImage(TSV_tab[0], TSV_tab[1], TSV_tab[2]);
        }

        if (updateValue) {
                computeValueImage(TSV_tab[0], TSV_tab[1], TSV_tab[2]);
        }

        RVB_tab = this.convertisseurHsvRgb.HSVversRGB(TSV_tab[0], TSV_tab[1], TSV_tab[2]);


        Pixel pixel = new Pixel(RVB_tab[0],RVB_tab[1], RVB_tab[2],255);
        this.result.setPixel(pixel);
	}
	
			
	public void update() {
			
			RVB_tab = this.convertisseurHsvRgb.HSVversRGB(TSV_tab[0], TSV_tab[1], TSV_tab[2]);
		     
			Pixel currentColor = new Pixel(RVB_tab[0], RVB_tab[1], RVB_tab[2], 255);
	        if (currentColor.getARGB() == result.getPixel().getARGB())
	                 return;
	         
	        this.updateRVBtab(this.result);
	         
	        TSV_tab= this.convertisseurRgbHsv.RGBversHSV(RVB_tab[0], RVB_tab[1],RVB_tab[2]);
	         
	        teinteCS.setValue((int) ((((TSV_tab[0] / 360) * 255))));
	        saturationCS.setValue((int) ((TSV_tab[1] * 255)));
	        valeurCS.setValue((int) ((TSV_tab[2] * 255)));
	     
	        this.computeImageGlobale(TSV_tab[0], TSV_tab[1], TSV_tab[2]);
	 		// Efficiency issue: When the color is adjusted on a tab in the 
	 		// user interface, the sliders color of the other tabs are recomputed,
	 		// even though they are invisible. For an increased efficiency, the 
	 		// other tabs (mediators) should be notified when there is a tab 
	 		// change in the user interface. This solution was not implemented
	 		// here since it would increase the complexity of the code, making it
	 		// harder to understand.
		}

}
