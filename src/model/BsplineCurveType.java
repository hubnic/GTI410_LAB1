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
 * <p>Title: BSplineCurveType</p>
 * <p>Description: ... (CurveType)</p>
 * <p>Copyright: Copyright (c) 2004 Eric Paquette</p>
 * <p>Company: (ÉTS) - École de Technologie Supérieure</p>
 * @author Eric Paquette
 * @version $Revision: 1.3 $
 */
public class BsplineCurveType extends CurveType {

	public BsplineCurveType(String name) {
		super(name);
		double t2 = 0;
	}
	/* (non-Javadoc)
	 * @see model.CurveType#getNumberOfSegments(int)
	 */
	public int getNumberOfSegments(int numberOfControlPoints) {
		if (numberOfControlPoints < 4)
			return 0;
		if (numberOfControlPoints == 4)
			return 1;
		return (numberOfControlPoints - 3);
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
		
		int controlPointIndex = segmentNumber * 1 + controlPointNumber;
		return (ControlPoint)controlPoints.get(controlPointIndex);

	}

	/* (non-Javadoc)
	 * @see model.CurveType#evalCurveAt(java.util.List, double)
	 */

	public Point evalCurveAt(List controlPoints, double t) {
		List tVector = Matrix.buildRowVector4(t*t*t, t*t, t, 1);

		System.out.println("Point -3 "+((ControlPoint)controlPoints.get(0)).getCenter().x+" "+((ControlPoint)controlPoints.get(0)).getCenter().y);
		System.out.println("Point -2 "+((ControlPoint)controlPoints.get(1)).getCenter().x+" "+((ControlPoint)controlPoints.get(1)).getCenter().y);
		System.out.println("Point -1 "+((ControlPoint)controlPoints.get(2)).getCenter().x+" "+((ControlPoint)controlPoints.get(2)).getCenter().y);
		System.out.println("Point  i "+((ControlPoint)controlPoints.get(3)).getCenter().x+" "+((ControlPoint)controlPoints.get(3)).getCenter().y);

		List gVector = Matrix.buildColumnVector4(
				((ControlPoint)controlPoints.get(0)).getCenter(),
				((ControlPoint)controlPoints.get(1)).getCenter(),
				((ControlPoint)controlPoints.get(2)).getCenter(),
				((ControlPoint)controlPoints.get(3)).getCenter());
		Point p = Matrix.eval(tVector, matrix, gVector);
		p.x=p.x/6;
		p.y=p.y/6;
		return p;
	}

	private List BSplineMatrix =
			/**Matrix.buildMatrix4(-1.0/6.0,  3.0/6.0, -3.0/6.0, 1.0/6.0,
								3.0/6.0, -6.0/6.0,  3.0/6.0, 0.0,
								-3.0/6.0,  0,  3.0/6.0, 0,
								1.0/6.0,  4.0/6.0,  1.0/6.0, 0);**/
	Matrix.buildMatrix4(-1,  3, -3, 1,
			3, -6,  3, 0,
			-3,  0,  3, 0,
			1,  4,  1, 0);

	private List matrix = BSplineMatrix;
}
