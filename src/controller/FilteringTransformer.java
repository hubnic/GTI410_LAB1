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
	
	Filter filter = new MeanFilter3x3(new PaddingZeroStrategy(), new ImageClampStrategy());
	
	// IL FAUT MODIFIER LE PADDING ZERO STREATEGY
	Filter filtreMoyen = new FiltreMoyen(new PaddingZeroStrategy(), new ImageClampStrategy());
	Filter filtreGaussien = new FiltreGaussien(new PaddingZeroStrategy(), new ImageClampStrategy());
	Filter filtreSobel = new FiltreSobel(new PaddingZeroStrategy(), new ImageClampStrategy());
	Filter filtreLaplacien = new FiltreLaplacien(new PaddingZeroStrategy(), new ImageClampStrategy());

	boolean mirror = false;
	float sigmaGaussien;
	/**
	 * Affiche les valeurs de la matrice graphique
	 * @param _coordinates
	 * @param _value
	 */
	public void updateKernel(Coordinates _coordinates, float _value) {
		System.out.println("[" + (_coordinates.getColumn() - 1) + "]["
                                   + (_coordinates.getRow() - 1) + "] = " 
                                   + _value);
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
				
			//AJOUT
				if (mirror){
					System.out.println("Le traitement se fait en Miror");
					BorderReflexion mirrorBorder= new BorderReflexion();
					ImageX currentImageMiror = mirrorBorder.Reflexion(currentImage);
					System.out.println("Taille de ImageMiror "+ currentImageMiror.getImageWidth() +" "+currentImageMiror.getImageHeight());
					System.out.println("Taille de Image "+ currentImage.getImageWidth() +" "+currentImage.getImageHeight());
				}
				
				//Agir selon les filtres
				switch (getID()) {
				 case 0:
					 System.out.println("0");
                 break;
				 case 1:
					 System.out.println("1");
                 break;
				 case 2:
					 System.out.println("2");
	                 break;
				 case 3: 
					 System.out.println("3");
	                 break;
				 case 4: 
					 System.out.println("4");
	                 break;
				}
				//FIN AJOUT
				//MODIFICATION
				ImageDouble filteredImage = filter.filterToImageDouble(currentImage);
				ImageX filteredDisplayableImage = filter.getImageConversionStrategy().convert(filteredImage);
				//FIN DE MODIFICATION4
				
				
				currentImage.beginPixelUpdate();
				
				for (int i = 0; i < currentImage.getImageWidth(); ++i) {
					for (int j = 0; j < currentImage.getImageHeight(); ++j) {
						currentImage.setPixel(i, j, filteredDisplayableImage.getPixelInt(i, j));
					}
				}
				currentImage.endPixelUpdate();
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see controller.AbstractTransformer#getID()
	 */
	public int getID() { return ID_FILTER; }

	/**
	 * @param string
	 */
	public void setBorder(String string) {
		System.out.println(string);
		mirror = true;
		
	}

	/**
	 * @param string
	 */
	public void setClamp(String string) {
		System.out.println(string);
	}
	
	public void setSigmaGaussien (String object) {
		System.out.println("Set valeur Sigma" + object);

	}
	
}
