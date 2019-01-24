package a4jedi;

import a4jedi.Picture;
import a4jedi.Pixel;
import a4jedi.SubPicture;
import a4jedi.SubPictureImpl;

public class TransformedPicture extends AnyPicture implements Picture {

	private Picture _source;
	private PixelTransformation _xform;

	public TransformedPicture(Picture source, PixelTransformation xform) {
		if (source == null || xform == null) {
			throw new IllegalArgumentException("One of the arguments is null");
		}

		_source = source;
		_xform = xform;

	}

	@Override
	public int getWidth() {
		return _source.getWidth();
	}

	@Override
	public int getHeight() {
		return _source.getHeight();
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		throw new UnsupportedOperationException("SetPixel is weird");
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("The x and y values must be positive");
		}
		if (x > _source.getWidth() || y > _source.getHeight()) {
			throw new IllegalArgumentException("The x and y values must be within the Picture");
		}
		return _xform.transform(_source.getPixel(x, y));
	}

	@Override
	public int countRange(double low, double high) {
		if (low < 0.0 || low > 1.0 || high < 0.0 || high > 1.0) {
			throw new IllegalArgumentException("low and high must be between 0 and 1");
		}
		return _source.countRange(low, high);
	}

	@Override
	public void print() {
		_source.print();
	}

	@Override
	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		return new SubPictureImpl(this, xOffset, yOffset, width, height);
	}

}
