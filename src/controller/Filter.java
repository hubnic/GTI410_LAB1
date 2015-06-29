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

import model.*;

/**
 * <p>Title: Filter</p>
 * <p>Description: Abstract class, created to handle both ImageX and ImageDouble filtering
 * <p>Copyright: Copyright (c) 2003 Colin Barré-Brisebois, Eric Paquette</p>
 * <p>Company: ETS - École de Technologie Supérieure</p>
 * @author unascribed
 * @version $Revision: 1.8 $
 */
public class Filter {
	protected PaddingStrategy paddingStrategy;
	protected ImageConversionStrategy conversionStrategy;
	double sigma;
	private double filterMatrix[][] = new double[3][3];
	
	public Filter(PaddingStrategy paddingStrategy, ImageConversionStrategy conversionStrategy) {
		setPaddingStrategy(paddingStrategy);
		setImageConversionStrategy(conversionStrategy);
	}
	
	public ImageDouble filterToImageDouble(ImageX image) {
		return null;
	}
	
	public ImageDouble filterToImageDouble(ImageDouble image) {
		return null;
	}

	public ImageX filterToImageX(ImageX image) {
		return null;
	}
	
	public ImageX filterToImageX(ImageDouble image) {
		return null;
	}

	public void setPaddingStrategy(PaddingStrategy strategy) {
		this.paddingStrategy = strategy;		
	}
	
	public void setImageConversionStrategy(ImageConversionStrategy conversionStrategy) {
		this.conversionStrategy = conversionStrategy;
	}
	
	public PaddingStrategy getPaddingStrategy() {
		return paddingStrategy;
	}
	
	public ImageConversionStrategy getImageConversionStrategy() {
		return conversionStrategy;
	}
	
	public void setMatrice(double[][] filterRecu) {
		filterMatrix[0][0]=filterRecu[0][0];
		filterMatrix[0][1]=filterRecu[0][1];
		filterMatrix[0][2]=filterRecu[0][2];
		filterMatrix[1][0]=filterRecu[1][0];
		filterMatrix[1][1]=filterRecu[1][1];
		filterMatrix[1][2]=filterRecu[1][2];
		filterMatrix[2][0]=filterRecu[2][0];
		filterMatrix[2][1]=filterRecu[2][1];
		filterMatrix[2][2]=filterRecu[2][2];
		System.out.println("Je suis dans le setter de la Matrice FIlter");
		System.out.println(filterRecu[0][0]);
		System.out.println(filterRecu[0][1]);
		System.out.println(filterRecu[0][2]);
		System.out.println(filterRecu[1][0]);
		System.out.println(filterRecu[1][1]);
		System.out.println(filterRecu[1][2]);
		System.out.println(filterRecu[2][0]);
		System.out.println(filterRecu[2][1]);
		System.out.println(filterRecu[2][2]);
	}
	public void setMatriceIndividuelle(int x, int y, float value) {
		filterMatrix[x][y] = value;
	}
	public double[][] getMatrice() {
//		System.out.println("Je suis dans le getter la Matrice Filter");
//		System.out.println(filterMatrix[0][0]);
//		System.out.println(filterMatrix[0][1]);
//		System.out.println(filterMatrix[0][2]);
//		System.out.println(filterMatrix[1][0]);
//		System.out.println(filterMatrix[1][1]);
//		System.out.println(filterMatrix[1][2]);
//		System.out.println(filterMatrix[2][0]);
//		System.out.println(filterMatrix[2][1]);
//		System.out.println(filterMatrix[2][2]);
		return this.filterMatrix;
	}

	
}
