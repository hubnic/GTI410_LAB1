package controller;

public class GestionnaireFiltre extends Filter{

	double sigma;
	
	//Filter filtreMoyen = new FiltreMoyen(new PaddingZeroStrategy(), new ImageClampStrategy());
	//Filter filtreGaussien = new FiltreGaussien(new PaddingZeroStrategy(), new ImageClampStrategy());
	//Filter filtreSobel = new FiltreSobel(new PaddingZeroStrategy(), new ImageClampStrategy());
	//Filter filtreLaplacien = new FiltreLaplacien(new PaddingZeroStrategy(), new ImageClampStrategy());
	
	public GestionnaireFiltre(PaddingStrategy paddingStrategy,
			ImageConversionStrategy conversionStrategy) {
		super(paddingStrategy, conversionStrategy);
		// TODO Auto-generated constructor stub
	}


}
