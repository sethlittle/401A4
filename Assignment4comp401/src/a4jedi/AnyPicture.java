package a4jedi;

import a4jedi.SubPictureImpl;
import a4jedi.SubPicture;
import a4jedi.Pixel;
import a4jedi.PictureImpl;
import a4jedi.TransparentPixel;
import a4jedi.GrayPixel;
import a4jedi.Picture;
import a4jedi.TransparentColorPixel;
import a4jedi.ColorPixel;

public abstract class AnyPicture implements SubPicture {

	private int _width;
	private int _height;
	private int _xOffset;
	private int _yOffset;
	private Picture _source;

	public void setWidth(int width) {
		_width = width;
	}

	public void setHeight(int height) {
		_height = height;
	}

	@Override
	public int getWidth() {
		return _width;
	}

	@Override
	public int getHeight() {
		return _height;
	}

	@Override
	abstract public void setPixel(int x, int y, Pixel p);

	@Override
	abstract public Pixel getPixel(int x, int y);

	@Override
	abstract public int countRange(double low, double high);

	@Override
	abstract public void print();

	@Override
	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		return new SubPictureImpl(this, xOffset, yOffset, width, height);
	}

	public void setSource(Picture s) {
		_source = s;
	}

	@Override
	public Picture getSource() {
		return _source;
	}

	public void setXOffset(int xOffset) {
		_xOffset = xOffset;
	}

	public void setYOffset(int yOffset) {
		_yOffset = yOffset;
	}

	@Override
	public int getXOffset() {
		return _xOffset;
	}

	@Override
	public int getYOffset() {
		return _yOffset;
	}

	public TransformedPicture transform(PixelTransformation xform) {
		return new TransformedPicture(this, xform);
	}

}
