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

package model;

import java.awt.Point;
import java.util.List;

/**
 * <p>Title: HermiteMatrixype</p>
 * <p>Description: ... (CurveType)</p>
 * <p>Copyright: Copyright (c) 2004 Eric Paquette</p>
 * <p>Company: (ÉTS) - École de Technologie Supérieure</p>
 * @author Eric Paquette
 * @version $Revision: 1.3 $
 */
public class HermiteCurveType extends CurveType {

	public HermiteCurveType(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see model.CurveType#getNumberOfSegments(int)
	 */
	public int getNumberOfSegments(int numberOfControlPoints) {
		if (numberOfControlPoints >= 4) {
			return (numberOfControlPoints - 1) / 3;
		} else {
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see model.CurveType#getNumberOfControlPointsPerSegment()
	 */
	public int getNumberOfControlPointsPerSegment() {
		return 4;
	}

	/* (non-Javadoc)
	 * @see model.CurveType#getControlPoint(java.util.List, int, int)
	 */
	public ControlPoint getControlPoint(
			List controlPoints,
			int segmentNumber,
			int controlPointNumber) {
		int controlPointIndex = segmentNumber * 3 + controlPointNumber;
		return (ControlPoint)controlPoints.get(controlPointIndex);
	}

	/* (non-Javadoc)
	 * @see model.CurveType#evalCurveAt(java.util.List, double)
	 */
	public Point evalCurveAt(List controlPoints, double t) {
		ControlPoint r1 = new ControlPoint((((ControlPoint)controlPoints.get(2)).getCenter().getX() - ((ControlPoint)controlPoints.get(0)).getCenter().getX()),
				(((ControlPoint)controlPoints.get(2)).getCenter().getY() - ((ControlPoint)controlPoints.get(0)).getCenter().getY()));
		
		ControlPoint r2 = new ControlPoint((((ControlPoint)controlPoints.get(3)).getCenter().getX() - ((ControlPoint)controlPoints.get(1)).getCenter().getX()),
				(((ControlPoint)controlPoints.get(3)).getCenter().getY() - ((ControlPoint)controlPoints.get(1)).getCenter().getY()));


		System.out.println("Point 1  (" + ((ControlPoint)controlPoints.get(0)).getCenter().getX()+","+((ControlPoint)controlPoints.get(0)).getCenter().getY()+")");
		System.out.println("Controle 1  (" + ((ControlPoint)controlPoints.get(2)).getCenter().getX()+","+((ControlPoint)controlPoints.get(2)).getCenter().getY()+")");
		System.out.println("Point R1  (" + r1.getCenter().getX()+","+r1.getCenter().getY()+")");
		System.out.println("Point 4  (" + ((ControlPoint)controlPoints.get(1)).getCenter().getX()+","+((ControlPoint)controlPoints.get(1)).getCenter().getY()+")");
		System.out.println("Controle 4  (" + ((ControlPoint)controlPoints.get(3)).getCenter().getX()+","+((ControlPoint)controlPoints.get(3)).getCenter().getY()+")");
		System.out.println("Point R2  (" + r2.getCenter().getX()+","+r2.getCenter().getY()+")");

		List tVector = Matrix.buildRowVector4(t*t*t, t*t, t, 1);
		List gVector = Matrix.buildColumnVector4(((ControlPoint) controlPoints.get(0)).getCenter(),
				((ControlPoint) controlPoints.get(1)).getCenter(),
				(r1.getCenter()),
				(r2.getCenter()));
		Point p = Matrix.eval(tVector, matrix, gVector);
		return p;
	}

	private List HermiteMatrix =
			Matrix.buildMatrix4(2,  -2, 1, 1,
					-3, 3,  -2, -1,
					0,  0,  1, 0,
					1,  0,  0, 0);

	private List matrix = HermiteMatrix;
}
