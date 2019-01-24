package a4jedi;

public class GammaCorrect implements PixelTransformation {

	private double _gamma;

	public GammaCorrect(double gamma) {
		if (gamma == 0) {
			throw new IllegalArgumentException("Gamma cannot be 0");
		}
		_gamma = gamma;
	}

	@Override
	public Pixel transform(Pixel p) {
		if (p == null) {
			throw new IllegalArgumentException("Pixel p cannot be null");
		}
		double oldr = p.getRed();
		double oldb = p.getBlue();
		double oldg = p.getGreen();

		double newr = Math.pow(oldr, 1.0 / _gamma);
		double newb = Math.pow(oldb, 1.0 / _gamma);
		double newg = Math.pow(oldg, 1.0 / _gamma);

		return new ColorPixel(newr, newg, newb);
	}

}
