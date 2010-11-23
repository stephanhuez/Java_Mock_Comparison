package shz.mock_comparison;

public class ImpossibleFactoryInstantiation extends RuntimeException {

	public ImpossibleFactoryInstantiation(String message) {
		super(message);
	}

	public ImpossibleFactoryInstantiation(String message, Throwable cause) {
		super(message, cause);
	}

	private static final long serialVersionUID = -525170596022630786L;

}
