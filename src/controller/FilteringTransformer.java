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
 * C'est cette classe qui attribut le filtre selon selection graphique
 * <p>Title: FilteringTransformer</p>
 * <p>Description: ... (AbstractTransformer)</p>
 * <p>Copyright: Copyright (c) 2004 Sébastien Bois, Eric Paquette</p>
 * <p>Company: (ÉTS) - École de Technologie Supérieure</p>
 * @author unascribed
 * @version $Revision: 1.6 $
 */
public class FilteringTransformer extends AbstractTransformer{
	
	private double filterMatrix[][] = null;
	PaddingStrategy paddingStrategie;
	ImageClampStrategy imageClampStrategie;
	double sigmaGaussien;
	
	//Filter filter = new MeanFilter3x3(paddingStrategie, imageClampStrategie);
	
	//Ce filtre dispose de tout les paramètres (Padding, clamp, la gestion des filtres doit se faire soit dans FILTER ou GestionnaireFiltre
	//On renvoie seulement l'image traitee
	Filter filter = new GestionnaireFiltre(new PaddingZeroStrategy(), new ImageClampStrategy());

	boolean mirror = false;
	/**
	 * Affiche les valeurs de la matrice graphique
	 * @param _coordinates
	 * @param _value
	 */
	public void updateKernel(Coordinates _coordinates, float _value) {
		filterMatrix = new double [3][3];
		System.out.println("[" + (_coordinates.getColumn() - 1) + "]["
                                   + (_coordinates.getRow() - 1) + "] = " 
                                   + _value);
		filterMatrix[_coordinates.getColumn() - 1][_coordinates.getRow() - 1]= _value;
		System.out.println((_coordinates.getColumn() - 1) +" "+(_coordinates.getRow() - 1 )+" "+ _value);
		System.out.println("k");
		
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
	public int getID() { return ID_FILTER; }

	/**
	 * Procedure qui permet d'ajuster le type de gestion des bordures
	 * selon la selection a l ecran
	 * Si typeBorder est != de Mirror on applique PaddingZeroStrategy
	 * Sinon on applique PaddingMirror
	 * @param string
	 */
	public void setBorder(String typeBorder) {
		System.out.println(typeBorder);
		if(!typeBorder.equals("Mirror")){
			filter.setPaddingStrategy(new PaddingZeroStrategy());
		}
		else{
			filter.setPaddingStrategy(new PaddingMiror());
		}		
	}

	/**
	 * @param string
	 */
	public void setClamp(String clampStrategy) {
		System.out.println(clampStrategy);
		if(clampStrategy.equals("Abs and normalize to 255")){
			//filter.setImageConversionStrategy(conversionStrategy);
		}
		else if(clampStrategy.equals("Abs and normalize 0 to 255")){
			//filter.setImageConversionStrategy(conversionStrategy);
		}
		else if(clampStrategy.equals("Normalize 0 to 255")){
			//filter.setImageConversionStrategy(conversionStrategy);
		}
		else{
			//Clamp 0...255
			filter.setImageConversionStrategy(new ImageClampStrategy());
		}
	}
	
	public void setSigmaGaussien (String sigmaRecu) {
		System.out.println("Set valeur Sigma" + sigmaRecu);
		//Affect Sigma à super Filter
		filter.setSigmaGaussien(new Double(sigmaRecu));
		
	}
	
	
	
}
