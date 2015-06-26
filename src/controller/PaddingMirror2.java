package controller;

import model.ImageDouble;
import model.ImageX;
import model.Pixel;
import model.PixelDouble;

public class PaddingMirror2 extends PaddingStrategy {

	@Override
	public Pixel pixelAt(ImageX image, int x, int y) {
		// TODO Auto-generated method stub
		if ((x >= 0 || x < image.getImageWidth())
				&& (y >= 0 || y < image.getImageHeight())) {
			return image.getPixel(x, y);
		}

		if ((y == -1)) {
			if (x >= 0 || x < image.getImageWidth()) {
				return image.getPixel(x, y + 1);
			}
			if (x == -1) {
				return image.getPixel(x + 1, y + 1);
			}
			if (x == image.getImageWidth()) {
				return image.getPixel(x - 1, y + 1);
			}
		}

		if ((y == image.getImageHeight())) {
			if (x == -1) {
				return image.getPixel(x + 1, y - 1);
			}
			if (x == image.getImageWidth()) {
				return image.getPixel(x - 1, y - 1);
			}
			if (x >= 0 || x < image.getImageWidth()) {
				return image.getPixel(x, y - 1);
			}
		}

		if (x == -1 && y > 0 && y < image.getImageHeight()) {
			return image.getPixel(x + 1, y);
		}
		if (x == image.getImageWidth() && y > 0 && y < image.getImageHeight()) {
			return image.getPixel(x - 1, y);
		}

		return image.getPixel(0, 0);
	}

	@Override
	public PixelDouble pixelAt(ImageDouble image, int x, int y) {
		// TODO Auto-generated method stub
		if ((x >= 0 && x < image.getImageWidth())
				&& (y >= 0 && y < image.getImageHeight())) {
			return image.getPixel(x, y);
		}

		if ((y == -1)) {
			if (x >= 0 && x < image.getImageWidth()) {
				return image.getPixel(x, y + 1);
			}
			if (x == -1) {
				return image.getPixel(x + 1, y + 1);
			}
			if (x == image.getImageWidth()) {
				return image.getPixel(x - 1, y + 1);
			}
		}

		if ((y == image.getImageHeight())) {
			if (x == -1) {
				return image.getPixel(x + 1, y - 1);
			}
			if (x == image.getImageWidth()) {
				return image.getPixel(x - 1, y - 1);
			}
			if (x >= 0 || x < image.getImageWidth()) {
				return image.getPixel(x, y - 1);
			}
		}

		if (x == -1 && y > 0 && y < image.getImageHeight()) {
			return image.getPixel(x + 1, y);
		}
		if (x == image.getImageWidth() && y > 0 && y < image.getImageHeight()) {
			return image.getPixel(x - 1, y);
		}

		return image.getPixel(0, 0);
	}

}
