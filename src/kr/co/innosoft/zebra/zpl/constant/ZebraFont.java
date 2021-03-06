package kr.co.innosoft.zebra.zpl.constant;

public enum ZebraFont {

	ZEBRA_ZERO("0"), ZEBRA_A("A"), ZEBRA_B("B"), ZEBRA_C("C"), ZEBRA_D("D"), ZEBRA_F("F"), ZEBRA_G("G"), ZEBRA_ONE("1"), ZEBRA_K("K");

	String letter;

	private ZebraFont(String letter) {
		this.letter = letter;
	}

	/**
	 * @return the letter
	 */
	public String getLetter() {
		return letter;
	}

	/**
	 * Fonction use for preview to find an equivalent font compatible with Graphic2D
	 *
	 * @param zebraFont zebraFont
	 * @return String
	 */
	public static String findBestEquivalentFontForPreview(ZebraFont zebraFont) {
		return "Arial";
	}

}
