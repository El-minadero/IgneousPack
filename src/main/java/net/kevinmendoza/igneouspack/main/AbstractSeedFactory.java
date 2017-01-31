package net.kevinmendoza.igneouspack.main;

public abstract class AbstractSeedFactory {
	private static long SEED = 1;
	public static void setSeed(long seed) { SEED=seed; }
}
