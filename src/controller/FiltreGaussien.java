package controller;

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
 * Classe permettant d'appliquer le Filtre Gaussien
 *
 */
public class FiltreGaussien extends Filter {
	private double filterMatrix[][] = null;

	/**
	 * Default constructor.
	 * @param paddingStrategy PaddingStrategy used
	 * @param conversionStrategy ImageConversionStrategy used
	 */
	public FiltreGaussien(PaddingStrategy paddingStrategy,
						 ImageConversionStrategy conversionStrategy) {
		super(paddingStrategy, conversionStrategy);
		filterMatrix = new double[3][3];

		filterMatrix[0][0] = 2.0/174.0;
		filterMatrix[1][0] = 14.0/174.0;
		filterMatrix[2][0] = 2.0/174.0;
		filterMatrix[0][1] = 14.0/174.0;
		filterMatrix[1][1] = 100.0/174.0;
		filterMatrix[2][1] = 14.0/174.0;
		filterMatrix[0][2] = 2.0/174.0;
		filterMatrix[1][2] = 14.0/174.0;
		filterMatrix[2][2] = 12.0/174.0;
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
		double result = 0;

		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				newPixel = new PixelDouble();

				//*******************************
				// RED
				for (int i = 0; i <= 2; i++) {
					for (int j = 0; j <= 2; j++) {

						//System.out.println("filtre entree" + filterMatrix[i][j]);
						result += filterMatrix[i][j] * getPaddingStrategy().pixelAt(image,
								x + (i - 1),
								y + (j - 1)).getRed();
					}
				}
				newPixel.setRed(result);
				result = 0;

				//*******************************
				// Green
				for (int i = 0; i <= 2; i++) {
					for (int j = 0; j <= 2; j++) {
						result += filterMatrix[i][j] * getPaddingStrategy().pixelAt(image,
								x+(i-1),
								y+(j-1)).getGreen();
					}
				}

				newPixel.setGreen(result);
				result = 0;

				//*******************************
				// Blue
				for (int i = 0; i <= 2; i++) {
					for (int j = 0; j <= 2; j++) {
						result += filterMatrix[i][j] * getPaddingStrategy().pixelAt(image,
								x+(i-1),
								y+(j-1)).getBlue();
					}
				}

				newPixel.setBlue(result);
				result = 0;

				//*******************************
				// Alpha - Untouched in this filter
				newPixel.setAlpha(getPaddingStrategy().pixelAt(image, x,y).getAlpha());

				//*******************************
				// Done
				newImage.setPixel(x, y, newPixel);
			}
		}

		return newImage;
	}

	
	/**
	 * Permet d initialiser la valeur de sigma permettant les calculs
	 * @param sigmaRecu
	 */
	void setSigma(double sigmaRecu){
		this.sigma = sigmaRecu;
	}

}
