package a4jedi;

public class PictureImpl extends AnyPicture implements Picture {

	private Pixel[][] _pixels;

	public PictureImpl(int width, int height) {
		super.setWidth(width);
		super.setHeight(height);
		_pixels = new Pixel[height][width];

		for (int i = 0; i < super.getHeight(); i++) {
			for (int j = 0; j < super.getWidth(); j++) {
				_pixels[i][j] = new ColorPixel(0.5, 0.5, 0.5);
			}
		}
	}

	public void setPixel(int x, int y, Pixel p) {
		if (p != null) {
			_pixels[y][x] = p;
		} else {
			throw new RuntimeException("Pixel p cannot have null as the value");
		}
	}

	public Pixel getPixel(int x, int y) {
		return _pixels[y][x];
	}

	public int countRange(double low, double high) {
		int count = 0;
		if (low <= 1.0 && low >= 0.0 && high <= 1.0 && high >= 0.0) {
			for (int i = 0; i < super.getHeight(); i++) {
				for (int j = 0; j < super.getWidth(); j++) {
					if (_pixels[i][j].getIntensity() <= high && _pixels[i][j].getIntensity() >= low) {
						count++;
					}
				}
			}
			return count;
		} else {
			throw new RuntimeException("Low and high must be between 0 and 1");
		}
	}

	public void print() {
		for (int i = 0; i < super.getHeight(); i++) {
			for (int j = 0; j < super.getWidth(); j++) {
				if (i == super.getWidth() - 1) {
					System.out.println(_pixels[i][j].getChar());
				} else {
					System.out.print(_pixels[i][j].getChar());
				}
			}
		}
	}

}
