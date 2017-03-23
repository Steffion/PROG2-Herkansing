package nl.Steffion.PROG2_herkansing;

public class Utils {
	/**
	 * Util for building strings.
	 *
	 * @param input
	 *            - array which needs to be added to each other
	 * @param startArg
	 *            - where to start
	 * @return Complete string
	 */
	public static String stringBuilder(String[] input, int startArg) {
		if ((input.length - startArg) <= 0) return null;
		
		StringBuilder sb = new StringBuilder(input[startArg]);
		for (int i = ++startArg; i < input.length; i++)
			sb.append(' ').append(input[i]);
		
		return sb.toString();
	}
}
