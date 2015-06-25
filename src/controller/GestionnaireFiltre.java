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

	void setTypeFiltre(int numFiltre){
		System.out.println("Update FIlte");
		switch (numFiltre) {
		case 1: // Mean filter
		{
			Filter filter = new MeanFilter3x3(this.getPaddingStrategy(), this.getImageConversionStrategy());
		} 
		break;
		case 2: // Gaussian filter
		{
			Filter filter = new FiltreGaussien(this.getPaddingStrategy(), this.getImageConversionStrategy());
			
		} 
		break;
		case 3: // 4-Neighbour Laplacian
		{
			Filter filter = new FiltreLaplacien(this.getPaddingStrategy(), this.getImageConversionStrategy());
		} 
		break;
		case 4: // 8-Neighbour Laplacian
		{
		} 
		break;
		case 5: // Prewitt Horiz ====> MEDIAN****************************************************************
		{
			Filter filter = new FiltreMedian(this.getPaddingStrategy(), this.getImageConversionStrategy());
		} 
		break;
		case 6: // Prewitt Vert
		{

		} 
		break;
		case 7: // Sobel Horiz 
		{
			Filter filter = new FiltreSobel(this.getPaddingStrategy(), this.getImageConversionStrategy());
		} 
		break;
		case 8: // Sobel Vert
		{
			Filter filter = new FiltreSobel(this.getPaddingStrategy(), this.getImageConversionStrategy());
		} 
		break;
		case 9: // Roberts 45 degrees
		{

		} 
		break;
		case 10: // Roberts -45 degrees
		{

		} 
		break;
		case 0: // Custom
		default:
		{
			// Do nothing
		}
		break;
	}
	}

}
