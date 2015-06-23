package controller;

public class FiltreGaussien extends Filter{
	private double sigma;
	private double filterMatrix[][] = null;
	public FiltreGaussien(PaddingStrategy paddingStrategy,
			ImageConversionStrategy conversionStrategy) {
		super(paddingStrategy, conversionStrategy);
		// TODO Auto-generated constructor stub
		filterMatrix = new double[3][3];
		
	}
	
	/**
	 * Permet d initialiser la valeur de sigma permettant les calculs
	 * @param sigmaRecu
	 */
	void setSigma(double sigmaRecu){
		this.sigma = sigmaRecu;
	}

}
