package Package;

public class Stopwatch {
	private long start;

	public Stopwatch() {
	}

	public void start() {
		start = System.currentTimeMillis();
	}

	public double stop() {
		long currentTime = System.currentTimeMillis();
		return (currentTime - start);
	}
}