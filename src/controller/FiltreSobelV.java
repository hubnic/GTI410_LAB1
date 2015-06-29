package controller;

import model.ImageDouble;
import model.ImageX;
import model.PixelDouble;


import model.ImageDouble;
import model.ImageX;
import model.PixelDouble;
/**
 * COURS_GTI_410 : 
 * LABORATOIRE_3
 * 
 * EQUIPE : 
 * 			Idriss Aissou AISI01088901
 * 			Nicolas Hubert HUBN30099004
 */

/**
 * Classe permettant d'appliquer le filtre Sobel Y
 *
 */
public class FiltreSobelV extends Filter {
	private double filterMatrixH[][] = null;
	private double filterMatrixV[][] = null;

	/**
	 * Default constructor.
	 * @param paddingStrategy PaddingStrategy used
	 * @param conversionStrategy ImageConversionStrategy used
	 */
	public FiltreSobelV(PaddingStrategy paddingStrategy,
						ImageConversionStrategy conversionStrategy) {
		super(paddingStrategy, conversionStrategy);

		filterMatrixV = new double[3][3];

		filterMatrixV[0][0] = -1;
		filterMatrixV[1][0] = -2;
		filterMatrixV[2][0] = -1;
		filterMatrixV[0][1] = 0;
		filterMatrixV[1][1] = 0;
		filterMatrixV[2][1] = 0;
		filterMatrixV[0][2] = 1;
		filterMatrixV[1][2] = 2;
		filterMatrixV[2][2] = 1;
	}

	/**
	 * Filters an ImageX and returns a ImageDouble.
	 */
	public ImageDouble filterToImageDouble(ImageX image) {
		return filter(conversionStrategy.convert(image));
	}

	/**
	 * Filters an ImageDouble and returns a ImageDouble.
	 */
	public ImageDouble filterToImageDouble(ImageDouble image) {
		return filter(image);
	}

	/**
	 * Filters an ImageX and returns an ImageX.
	 */
	public ImageX filterToImageX(ImageX image) {
		ImageDouble filtered = filter(conversionStrategy.convert(image));
		return conversionStrategy.convert(filtered);
	}

	/**
	 * Filters an ImageDouble and returns a ImageX.
	 */
	public ImageX filterToImageX(ImageDouble image) {
		ImageDouble filtered = filter(image);
		return conversionStrategy.convert(filtered);
	}

	/*
	 * Filter Implementation
	 */
	private ImageDouble filter(ImageDouble image) {
		int imageWidth = image.getImageWidth();
		int imageHeight = image.getImageHeight();
		ImageDouble newImage = new ImageDouble(imageWidth, imageHeight);
		PixelDouble newPixel = null;
		double resultH = 0;
		double resultV = 0;

		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				newPixel = new PixelDouble();

				//*******************************
				// RED
				for (int i = 0; i <= 2; i++) {
					for (int j = 0; j <= 2; j++) {
						resultV += filterMatrixV[i][j] * getPaddingStrategy().pixelAt(image,
								x + (i - 1),
								y + (j - 1)).getRed();
					}
				}
				newPixel.setRed(Math.abs(resultV));
				resultV = 0;
				//*******************************
				// Green
				for (int i = 0; i <= 2; i++) {
					for (int j = 0; j <= 2; j++) {
						resultV += filterMatrixV[i][j] * getPaddingStrategy().pixelAt(image,
								x + (i - 1),
								y + (j - 1)).getGreen();
					}
				}
				newPixel.setGreen(Math.abs(resultV));
				resultV = 0;
				//*******************************
				// Blue
				for (int i = 0; i <= 2; i++) {
					for (int j = 0; j <= 2; j++) {
						resultV += filterMatrixV[i][j] * getPaddingStrategy().pixelAt(image,
								x + (i - 1),
								y + (j - 1)).getBlue();
					}
				}
				newPixel.setBlue(Math.abs(resultV));
				resultV = 0;
				//*******************************
				// Alpha - Untouched in this filter
				newPixel.setAlpha(getPaddingStrategy().pixelAt(image, x,y).getAlpha());

				//*******************************
				// Done
				newImage.setPixel(x, y, newPixel);
			}
		}
		getMinMax(newImage);
		return newImage;
	}


	/**
	 * Permet d initialiser la valeur de sigma permettant les calculs
	 * @param sigmaRecu
	 */
	void setSigma(double sigmaRecu){
		this.sigma = sigmaRecu;
	}
	public double[] getMinMax(ImageDouble image) {
		int imageWidth = image.getImageWidth();
		int imageHeight = image.getImageHeight();
		PixelDouble curPixelDouble = null;
		double MIN = 255;

		double MAX = 0;

		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				curPixelDouble = image.getPixel(x, y);

				// CALCUL DU MIN
				if (MIN > curPixelDouble.getRed()) {
					MIN = curPixelDouble.getRed();
				}
				if (MIN > curPixelDouble.getGreen()) {
					MIN = curPixelDouble.getGreen();
				}
				if (MIN > curPixelDouble.getBlue()) {
					MIN = curPixelDouble.getBlue();
				}
				// CALCUL DU MAX
				if (MAX < curPixelDouble.getRed()) {
					MAX = curPixelDouble.getRed();
				}
				if (MAX < curPixelDouble.getGreen()) {
					MAX = curPixelDouble.getGreen();
				}
				if (MAX < curPixelDouble.getBlue()) {
					MAX = curPixelDouble.getBlue();
				}
			}
		}
		double[] tab = new double[2];
		tab[0] = MIN;
		tab[1] = MAX;
		System.out.println("MIN:" + tab[0] + "MAX:" + tab[1] );
		return tab;


	}

}

