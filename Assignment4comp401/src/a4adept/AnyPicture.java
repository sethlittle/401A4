package a4adept;

import a4adept.SubPictureImpl;
import a4adept.SubPicture;
import a4adept.Pixel;
import a4adept.PictureImpl;
import a4adept.TransparentPixel;
import a4adept.GrayPixel;
import a4adept.Picture;
import a4adept.TransparentColorPixel;
import a4adept.ColorPixel;

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

}
