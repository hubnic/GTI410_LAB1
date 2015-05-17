package view;
import java.awt.image.BufferedImage;
import model.ObserverIF;
import model.Pixel;


/*
Utilisation de la classe "RGBColorMediator.java", et adaptee
pour l'espace de couleur HSV (TSV),
tel que specifie dans l'enonce de laboratoire 1
GTI_410
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
	
	//BUFFERED IMAGE TSV
	BufferedImage teinteImage;
	BufferedImage saturationImage;
	BufferedImage valeurImage;
	
	//LE RESULTAT DES COULEURS
	ColorDialogResult result;
	
	
	HSVColorMediator(ColorDialogResult result, int imagesWidthR, int imagesHeightR) {
		
		RVB_tab[0]=result.getPixel().getRed();
		RVB_tab[1]=result.getPixel().getGreen();
		RVB_tab[2]=result.getPixel().getBlue();
		
		this.imagesHeight = imagesHeightR;
		this.imagesWidth = imagesWidthR;
		this.result = result;
		result.addObserver(this);
		teinteImage = new BufferedImage(imagesWidth, imagesHeight,BufferedImage.TYPE_INT_ARGB);
		saturationImage = new BufferedImage(imagesWidth, imagesHeight,BufferedImage.TYPE_INT_ARGB);
		valeurImage = new BufferedImage(imagesWidth, imagesHeight,BufferedImage.TYPE_INT_ARGB);
		
		
		
		TSV_tab = this.conversionRGBversHSV(RVB_tab[0], RVB_tab[1], RVB_tab[2]);
		
		this.computeImageGlobale(TSV_tab[0], TSV_tab[1], TSV_tab[2]);
	}
	
	

	private void computeHueImage(double h, double s, double v) {

		Pixel pUpdate = new Pixel (this.RVB_tab[0],this.RVB_tab[1],this.RVB_tab[2],255);
				for (int i = 0; i < imagesWidth; ++i) {
		            RVB_tab = this.conversionHSVversRGB((((double) i / (double) imagesWidth) * 360),s, v);
		
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
				RVB_tab = this.conversionHSVversRGB(h,((double) i / (double) imagesWidth), v);
	
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
				RVB_tab = this.conversionHSVversRGB(h,s, ((double) i / (double) imagesWidth));
	
	            pUpdate.setRed(RVB_tab[0]);pUpdate.setGreen(RVB_tab[1]);pUpdate.setBlue(RVB_tab[2]);
	
		            for (int j = 0; j < imagesHeight; j++) {
		                    valeurImage.setRGB(i, j, pUpdate.getARGB());
		            }
			}
	    if (valeurCS != null) {
	    	  valeurCS.update(valeurImage);
	    }
	}
	

	public int[] conversionHSVversRGB (double hRecu, double sRecu, double vRecu){
		int[] RGB_TAB =  new int[3];
		double[] RGB_tmp =  new double[3];
		double c;
		double x;
		double m;
		c= (vRecu * sRecu);
		x = c * (1-Math.abs((hRecu/60 % 2) -1));
		m= (vRecu - c);
		 System.out.println("CONVERSION EN RGB (c,x,m) : "+c+" "+x+" "+m);
        System.out.println("CONVERSION EN RGB (HSV RECU) : "+hRecu+" "+sRecu+" "+vRecu);

		if(hRecu>=0 & hRecu<60){
			RGB_tmp[0]= c;
			RGB_tmp[1]= x;
			RGB_tmp[2]= 0;
		}
		else if(hRecu>=60 & hRecu<120){
			RGB_tmp[0]= x;
			RGB_tmp[1]= c;
			RGB_tmp[2]= 0;
		}
		else if(hRecu>=120 & hRecu<180){
			RGB_tmp[0]= 0;
			RGB_tmp[1]= c;
			RGB_tmp[2]= x;
		}
		else if(hRecu>=180 & hRecu<240){
			RGB_tmp[0]= 0;
			RGB_tmp[1]= x;
			RGB_tmp[2]= c;
		}
		else if(hRecu>=240 & hRecu<300){
			RGB_tmp[0]= x;
			RGB_tmp[1]= 0;
			RGB_tmp[2]= c;
		}
		else if(hRecu>=300 & hRecu<360){
			RGB_tmp[0]= c;
			RGB_tmp[1]= 0;
			RGB_tmp[2]= x;
		}
		RGB_TAB[0]= (int) Math.round((RGB_tmp[0]+m)*255);
		RGB_TAB[1]= (int) Math.round((RGB_tmp[1]+m)*255);
		RGB_TAB[2]= (int) Math.round((RGB_tmp[2]+m)*255);
        
        System.out.println("CONVERSION EN RGB : "+RGB_TAB[0]+" "+RGB_TAB[1]+" "+RGB_TAB[2]);
        
		return RGB_TAB;
	}
	
	public double[] conversionRGBversHSV(int rougeR,int vertR,int bleuR){
		float H = 0;
		float S = 0;
		float V = 0;
		float MAX;
		float MIN;
		double[] TSV_TAB = new double[3];
		  System.out.println("CONVERSION EN HSV (RGB RECU) : "+rougeR+" "+vertR+" "+bleuR);
		
		MAX = Math.max(rougeR, Math.max(vertR,bleuR));
		MIN = Math.min(rougeR, Math.min(vertR, bleuR));
		
		
		//HUE CALCUL (TEINTE)
		if(rougeR == MAX){
			H= (60 * ((vertR-bleuR)/(MAX-MIN)+360))%360;
		}
		
		if(vertR == MAX){
			H= 60 * ((bleuR-rougeR)/(MAX-MIN)) + 120;
		}
		if(bleuR == MAX){
			H= 60 * ((rougeR-vertR)/(MAX-MIN)) + 240;
		}
		if( H < 0 ){
			  H += 360;
		}
	      
		if(MAX == MIN){
			H= 0;
		}
		
		//SATURATION
		if(MAX==0){
			S=0;
		}else if(MAX!=0){
			S=1-(MIN/MAX);
		}
		
		//VALEUR
		V=Math.max(rougeR/255, Math.max(vertR/255,bleuR/255));
		TSV_TAB[0]=H;
		TSV_TAB[1]=S;
		TSV_TAB[2]=V;
		
		System.out.println("COULEUR EN HSV : "+TSV_TAB[0]+" "+TSV_TAB[1]+" "+TSV_TAB[2]);
		
		return TSV_TAB;
	}

	public void update() {
		
		 RVB_tab = this.conversionHSVversRGB(TSV_tab[0], TSV_tab[1], TSV_tab[2]);
	     
		Pixel currentColor = new Pixel(RVB_tab[0], RVB_tab[1], RVB_tab[2], 255);
         if (currentColor.getARGB() == result.getPixel().getARGB())
                 return;
         
         RVB_tab[0]=this.result.getPixel().getRed();
         RVB_tab[1]=this.result.getPixel().getGreen();
         RVB_tab[2]=this.result.getPixel().getBlue();
         
         TSV_tab= this.conversionRGBversHSV(RVB_tab[0], RVB_tab[1],RVB_tab[2]);
         
         teinteCS.setValue((int) ((((TSV_tab[0] / 360) * 255))));
         saturationCS.setValue((int) ((TSV_tab[1] * 255)));
         valeurCS.setValue((int) ((TSV_tab[2] * 255)));
     
         this.computeImageGlobale(TSV_tab[0], TSV_tab[1], TSV_tab[2]);
         
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

        RVB_tab = this.conversionHSVversRGB(TSV_tab[0], TSV_tab[1], TSV_tab[2]);


        Pixel pixel = new Pixel(RVB_tab[0],RVB_tab[1], RVB_tab[2],255);
        this.result.setPixel(pixel);
	}
	
	
	

	
	
	
	
	
	
	
	
		public BufferedImage getHueImage() {
		        return teinteImage;
		}
		
		public BufferedImage getSaturationImage() {
		        return saturationImage;
		}
		
		public BufferedImage getValueImage() {
		        return valeurImage;
		}
		
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
		
		void computeImageGlobale(double teinte, double saturation, double valeur){
			computeHueImage(teinte, saturation, valeur);
		    computeSaturationImage(teinte, saturation, valeur);
		    computeValueImage(teinte, saturation, valeur);
		}

}
