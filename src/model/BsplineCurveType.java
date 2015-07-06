package model;

import java.awt.Point;
import java.util.List;

public class BsplineCurveType extends CurveType {

	public BsplineCurveType(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getNumberOfSegments(int numberOfControlPoints) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfControlPointsPerSegment() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ControlPoint getControlPoint(List controlPoints, int segmentNumber,
			int controlPointNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point evalCurveAt(List controlPoints, double t) {
		// TODO Auto-generated method stub
		return null;
	}

}
