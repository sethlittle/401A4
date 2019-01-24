package a4adept;

import a4adept.Picture;
import a4adept.Pixel;
import a4adept.SubPicture;
import a4adept.AnyPicture;
import a4adept.SubPictureImpl;
import a4adept.PictureImpl;

public class HorizontalStackPicture extends AnyPicture implements Picture {

	private Picture _left;
	private Picture _right;

	public HorizontalStackPicture(Picture left, Picture right) {
		if (left == null || right == null) {
			throw new IllegalArgumentException("Neither picture can be null");
		}
		if (left.getHeight() != right.getHeight()) {
			throw new IllegalArgumentException("The Pictures must have the same Height");
		}

		_left = left;
		_right = right;
		super.setWidth(left.getWidth() + right.getWidth());
		super.setHeight(left.getHeight());

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

		if (x < _left.getWidth()) {
			_left.setPixel(y, x, p);
		} else {
			_right.setPixel(y, x - _left.getWidth(), p);
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

		if (x < _left.getWidth()) {
			return _left.getPixel(x, y);
		} else {
			return _right.getPixel(x - _left.getWidth(), y);
		}

	}

	@Override
	public int countRange(double low, double high) {
		Picture output = new PictureImpl(super.getWidth(), super.getHeight());
		int count = 0;
		for (int i = 0; i < output.getHeight(); i++) {
			for (int j = 0; j < output.getWidth(); j++) {
				if (j < _left.getWidth()) {
					output.setPixel(j, i, _left.getPixel(j, i));
				} else {
					output.setPixel(j, i, _right.getPixel(j - _left.getWidth(), i));
				}
			}
		}

		for (int i = 0; i < output.getHeight(); i++) {
			for (int j = 0; j < output.getWidth(); j++) {
				if (output.getPixel(j, i).getIntensity() <= high || output.getPixel(j, i).getIntensity() >= low) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public void print() {
		Picture output = new PictureImpl(super.getWidth(), super.getHeight());

		for (int i = 0; i < output.getHeight(); i++) {
			for (int j = 0; j < output.getWidth(); j++) {
				if (j < _left.getWidth()) {
					output.setPixel(j, i, _left.getPixel(j, i));
				} else {
					output.setPixel(j, i, _right.getPixel(j - _left.getWidth(), i));
				}
			}
		}

		output.print();

	}

}
