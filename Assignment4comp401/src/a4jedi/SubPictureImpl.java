package a4jedi;

public class SubPictureImpl extends AnyPicture implements SubPicture {

	private int _subW;
	private int _subH;

	public SubPictureImpl(Picture source, int xOffset, int yOffset, int width, int height) {
		if (source == null) {
			throw new IllegalArgumentException("The picture source cannot be null");
		}
		if (xOffset + width > source.getWidth() || yOffset + height > source.getHeight()) {
			throw new IllegalArgumentException("The subpicture subarea is greater than the picture source");
		}
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException("width and height must be positive");
		}
		if (xOffset < 0 || yOffset < 0) {
			throw new IllegalArgumentException("xOffset and yOffset must be positive");
		}

		super.setSource(source);
		_subW = width;
		_subH = height;
		super.setWidth(source.getWidth());
		super.setHeight(source.getHeight());
		super.setXOffset(xOffset);
		super.setYOffset(yOffset);

	}

	@Override
	public void setWidth(int width) {
		_subW = width;
	}

	@Override
	public void setHeight(int height) {
		_subH = height;
	}

	@Override
	public int getWidth() {
		return _subW;
	}

	@Override
	public int getHeight() {
		return _subH;
	}

	@Override
	public int countRange(double low, double high) {
		int count = 0;
		for (int i = 0; i < _subW; i++) {
			for (int j = 0; j < _subH; j++) {
				if (this.getSource().getPixel(i + super.getXOffset(), j + super.getYOffset()).getIntensity() <= high
						&& this.getSource().getPixel(i + super.getXOffset(), j + super.getYOffset())
								.getIntensity() >= low) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x > super.getWidth() || y > super.getHeight()) {
			throw new IllegalArgumentException("x and y must be less than the size of the Picture");
		}
		return this.getSource().getPixel(x + this.getXOffset(), y + this.getYOffset());
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		if (x > super.getWidth() || y > super.getHeight()) {
			throw new IllegalArgumentException("x and y must be less than the size of the Picture");
		}
		if (p == null) {
			throw new IllegalArgumentException("Pixel p cannot be null");
		}
		this.getSource().setPixel(x + this.getXOffset(), y + this.getYOffset(), p);
	}

	@Override
	public void print() {
		this.extract(super.getXOffset(), super.getYOffset(), super.getWidth(), super.getHeight()).print();
	}

}
