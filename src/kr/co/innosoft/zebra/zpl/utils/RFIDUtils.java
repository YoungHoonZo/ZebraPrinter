package kr.co.innosoft.zebra.zpl.utils;

public class RfidUtils {

	/**
	 * Hex code to Ascii code converter
	 * @param hexStr Hex string
	 * @return
	 */
	private static String hexToAscii(String hexStr) {
        StringBuilder output = new StringBuilder("");

        for (int i = 0; i < hexStr.length(); i += 2) {
            String str = hexStr.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }

	/**
	 * Ascii code to Hex code converter
	 * @param asciiStr Ascii string
	 * @return
	 */
    private static String asciiToHex(String asciiStr) {
        char[] chars = asciiStr.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char ch : chars) {
            hex.append(Integer.toHexString((int) ch));
        }

        return hex.toString();
    }
}
