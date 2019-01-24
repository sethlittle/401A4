package a4adept;

import a4adept.Picture;
import a4adept.Pixel;
import a4adept.SubPicture;
import a4adept.AnyPicture;
import a4adept.SubPictureImpl;
import a4adept.PictureImpl;

public class VerticalStackPicture extends AnyPicture implements Picture {

	private Picture _top;
	private Picture _bottom;

	public VerticalStackPicture(Picture top, Picture bottom) {
		if (top == null || bottom == null) {
			throw new IllegalArgumentException("Neither picture can be null");
		}
		if (top.getWidth() != bottom.getWidth()) {
			throw new IllegalArgumentException("The widths of the pictures must be the same");
		}

		_top = top;
		_bottom = bottom;
		super.setWidth(top.getWidth());
		super.setHeight(top.getHeight() + bottom.getHeight());

	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		if (p == null) {
			throw new IllegalArgumentException("The pixel cannot be null");
		}
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("x and y should be positive");
		}
		if (x > super.getWidth() || y > super.getHeight()) {
			throw new IllegalArgumentException("Out of bounds");
		}

		if (y < _top.getHeight()) {
			_top.setPixel(y, x, p);
		} else {
			_bottom.setPixel(y - _top.getHeight(), x, p);
		}

	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("x and y should be positive");
		}
		if (x > super.getWidth() || y > super.getHeight()) {
			throw new IllegalArgumentException("Out of bounds");
		}

		if (y < _top.getHeight()) {
			return _top.getPixel(y, x);
		} else {
			return _bottom.getPixel(y - _top.getHeight(), x);
		}

	}

	@Override
	public int countRange(double low, double high) {
		int count = 0;
		for (int i = 0; i < super.getHeight(); i++) {
			for (int j = 0; j < super.getWidth(); j++) {
				if (i < _top.getHeight()) {
					if (_top.getPixel(i, j).getIntensity() <= high || _top.getPixel(i, j).getIntensity() >= low) {
						count++;
					}
				} else {
					if (_bottom.getPixel(i - _top.getHeight(), j).getIntensity() <= high
							|| _bottom.getPixel(i, j).getIntensity() >= low) {
						count++;
					}
				}
			}
		}
		return count;
	}

	@Override
	public void print() {
		_top.print();
		_bottom.print();
	}

}
