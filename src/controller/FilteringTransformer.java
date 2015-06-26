/*
   This file is part of j2dcg.
   j2dcg is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2 of the License, or
   (at your option) any later version.
   j2dcg is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   You should have received a copy of the GNU General Public License
   along with j2dcg; if not, write to the Free Software
   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package controller;

import java.awt.event.MouseEvent;
import java.util.List;

import model.ImageDouble;
import model.ImageX;
import model.Shape;

/**
 * cette classe qui attribut le filtre selon selection graphique
 * <p>Title: FilteringTransformer</p>
 * <p>Description: ... (AbstractTransformer)</p>
 * <p>Copyright: Copyright (c) 2004 Sébastien Bois, Eric Paquette</p>
 * <p>Company: (ÉTS) - École de Technologie Supérieure</p>
 * @author unascribed
 * @version $Revision: 1.6 $
 */
public class FilteringTransformer extends AbstractTransformer{
	
	private double filterMatrix[][] = null;
	double sigmaGaussien;
	//Ce filtre dispose de tout les paramètres (Padding, clamp, la gestion des filtres doit se faire soit dans FILTER ou GestionnaireFiltre
	//On renvoie seulement l'image traitee
	
	//Filter filter = new GestionnaireFiltre(new PaddingZeroStrategy(), new ImageClampStrategy());
	Filter filter = new FiltreCustom(new PaddingZeroStrategy(), new ImageClampStrategy());

	/**
	 * Affiche et recupere les valeurs de la matrice graphique
	 * les valeurs sont affectees a filterMatrix
	 * @param _coordinates
	 * @param _value
	 */
	public void updateKernel(Coordinates _coordinates, float _value) {
		filterMatrix = new double [3][3];
		System.out.println("[" + (_coordinates.getColumn() - 1) + "]["
                                   + (_coordinates.getRow() - 1) + "] = " 
                                   + _value);
		filterMatrix[_coordinates.getColumn() - 1][_coordinates.getRow() - 1] = _value;
		filter.setMatriceIndividuelle(_coordinates.getColumn() - 1, _coordinates.getRow() - 1, _value);
		//filter.setMatrice(this.filterMatrix);
		//System.out.println((_coordinates.getColumn() - 1) +" "+(_coordinates.getRow() - 1 )+" "+ _value);
		//System.out.println("k");
		//filter.setMatrice(filterMatrix);
//		System.out.println(filterMatrix[0][0]);
//		System.out.println(filterMatrix[0][1]);
//		System.out.println(filterMatrix[0][2]);
//		System.out.println(filterMatrix[1][0]);
//		System.out.println(filterMatrix[1][1]);
//		System.out.println(filterMatrix[1][2]);
//		System.out.println(filterMatrix[2][0]);
//		System.out.println(filterMatrix[2][1]);
//		System.out.println(filterMatrix[2][2]);
	}
		
	/**
	 * Evenement après un click de la souris
	 * @param e
	 * @return
	 */
	protected boolean mouseClicked(MouseEvent e){
		List intersectedObjects = Selector.getDocumentObjectsAtLocation(e.getPoint());
		if (!intersectedObjects.isEmpty()) {			
			Shape shape = (Shape)intersectedObjects.get(0);			
			if (shape instanceof ImageX) {		
				
				ImageX currentImage = (ImageX)shape;
				//Debut traitement de l'image après le clic
				ImageDouble filteredImage = filter.filterToImageDouble(currentImage);
				ImageX filteredDisplayableImage = filter.getImageConversionStrategy().convert(filteredImage);
				//FIN DE traitement
				
				//Debut du reaffichage de l image
				currentImage.beginPixelUpdate();
				
				for (int i = 0; i < currentImage.getImageWidth(); ++i) {
					for (int j = 0; j < currentImage.getImageHeight(); ++j) {
						currentImage.setPixel(i, j, filteredDisplayableImage.getPixelInt(i, j));
					}
				}
				currentImage.endPixelUpdate();
				
				//Fin update image
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see controller.AbstractTransformer#getID()
	 */
	public int getID() {
		System.out.println("ID DU FILTRE "+ID_FILTER);
		return ID_FILTER; }

	/**
	 * Procedure qui permet d'ajuster le type de gestion des bordures
	 * selon la selection a l ecran
	 * Si typeBorder est != de Mirror on applique PaddingZeroStrategy
	 * Sinon on applique PaddingMirror 
	 * (Liaison avec FilterKernelPanel)
	 * @param string
	 */
	public void setBorder(String typeBorder) {
		System.out.println(typeBorder);
		if(typeBorder.equals("Mirror")){
			filter.setPaddingStrategy(new PaddingMiror());
			
		}
		else if(!typeBorder.equals("Mirror")){
			filter.setPaddingStrategy(new PaddingZeroStrategy());
		}		
	}

	/**
	 * Permet d'affecter l image clamp strategy selon la selection faite sur le 
	 * panneau graphique (Liaison avec FilterKernelPanel)
	 * @param string
	 */
	public void setClamp(String clampStrategy) {
		System.out.println(clampStrategy);
		if(clampStrategy.equals("Abs and normalize to 255")){
			//Clamp Abs and normalize to 255
			filter.setImageConversionStrategy(new ImageClampAbsNormalizeTo255());
		}
		else if(clampStrategy.equals("Abs and normalize 0 to 255")){
			//Clamp Abs and normalize 0 to 255
			filter.setImageConversionStrategy(new ImageClampAbsNormalize0To255());
		}
		else if(clampStrategy.equals("Normalize 0 to 255")){
			//Clamp Normalize 0 to 255
			filter.setImageConversionStrategy(new ImageClampNormalize0To255());
		}
		else if(clampStrategy.equals("Clamp 0...255")){
			//Clamp 0...255
			filter.setImageConversionStrategy(new ImageClampStrategy());
		}
	}
	
	public void setSigmaGaussien (String sigmaRecu) {
		System.out.println("Set valeur Sigma" + sigmaRecu);
		//Affect Sigma à super Filter
		filter.setSigmaGaussien(new Double(sigmaRecu));
		
	}
	
	public void setTypeFiltre(int numFiltre){
		System.out.println("JE SUIS ICI");
		PaddingStrategy padactuel = filter.getPaddingStrategy();  
		ImageClampStrategy imageConvertionActuel = (ImageClampStrategy) filter.getImageConversionStrategy();
		
		
		switch (numFiltre) {
		case 1: // Mean filter
		{
			filter = new MeanFilter3x3(padactuel, imageConvertionActuel);
		} 
		break;
		case 2: // Gaussian filter
		{
			filter = new FiltreGaussien(padactuel, imageConvertionActuel);
			
		} 
		break;
		case 3: // 4-Neighbour Laplacian
		{
			filter = new FiltreLaplacien(padactuel, imageConvertionActuel);
		} 
		break;
		case 4: // 8-Neighbour Laplacian
		{
		} 
		break;
		case 5: // Prewitt Horiz ====> MEDIAN****************************************************************
		{
			filter = new FiltreMedian(padactuel, imageConvertionActuel);
		} 
		break;
		case 6: // Prewitt Vert
		{

		} 
		break;
		case 7: // Sobel Horiz 
		{
		 filter = new FiltreSobel(padactuel, imageConvertionActuel);
		} 
		break;
		case 8: // Sobel Vert
		{
			filter = new FiltreSobel(padactuel, imageConvertionActuel);
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
		{
			
			filter = new FiltreCustom(padactuel, imageConvertionActuel);
			filter.setMatrice(this.filterMatrix);
		} 
		break;
		default:
		{
			// Do nothing
		}
		break;
	}
	}
	
	
}
