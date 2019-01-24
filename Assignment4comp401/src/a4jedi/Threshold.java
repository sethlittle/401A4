package a4jedi;

public class Threshold implements PixelTransformation {

	private double _threshold;

	public Threshold(double threshold) {
		if (threshold < 0.0 || threshold > 1.0) {
			throw new IllegalArgumentException("The threshold must be between 0 and 1");
		}
		_threshold = threshold;
	}

	@Override
	public Pixel transform(Pixel p) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel p cannot be null");
		}

		if (p.getIntensity() > _threshold) {
			return new GrayPixel(1.0);
		} else {
			return new GrayPixel(0.0);
		}
	}

}
