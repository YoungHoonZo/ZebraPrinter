package kr.co.innosoft.zebra.zpl.model.element;

import kr.co.innosoft.zebra.zpl.constant.ZebraFont;
import kr.co.innosoft.zebra.zpl.constant.ZebraRotation;
import kr.co.innosoft.zebra.zpl.model.PrinterOptions;
import kr.co.innosoft.zebra.zpl.model.ZebraElement;
import kr.co.innosoft.zebra.zpl.utils.ZplUtils;

/**
 * Element to set a font and size (explain in dot).
 * 
 * This command is apply only on the next element (in zebraElements list).
 * 
 * This command could be use when your font and PPP is not yet implemented
 * 
 * ZPL Command : ^A
 * 
 * @author ttropard
 * 
 */
public class ZebraAFontElement extends ZebraElement {

	private ZebraFont zebraFont;

	private ZebraRotation zebraRotation = ZebraRotation.NORMAL;

	private int dotHeigth;
	private int dotsWidth;

	/**
	 * Use this constructor if you want to change the font of the next element.
	 * 
	 * @param zebraFont
	 *            zebra font
	 */
	public ZebraAFontElement(ZebraFont zebraFont) {
		super();
		this.zebraFont = zebraFont;
	}

	/**
	 * Constructor to set font and size of the next element
	 * 
	 * @param zebraFont
	 *            font zebra
	 * @param dotHeigth
	 *            height explain in dots
	 * @param dotsWidth
	 *            height explain in dots
	 */
	public ZebraAFontElement(ZebraFont zebraFont, int dotHeigth, int dotsWidth) {
		super();
		this.zebraFont = zebraFont;
		this.dotHeigth = dotHeigth;
		this.dotsWidth = dotsWidth;
	}

	/**
	 * Constructor to use if you want have non-horizontal text.
	 * 
	 * @param zebraFont
	 *            font zebra
	 * @param zebraRotation
	 *            text rotation
	 * @param dotHeigth
	 *            height explain in dots
	 * @param dotsWidth
	 *            height explain in dots
	 */
	public ZebraAFontElement(ZebraFont zebraFont, ZebraRotation zebraRotation, int dotHeigth, int dotsWidth) {
		super();
		this.zebraFont = zebraFont;
		this.zebraRotation = zebraRotation;
		this.dotHeigth = dotHeigth;
		this.dotsWidth = dotsWidth;
	}

	/* (non-Javadoc)
	 * @see fr.w3blog.zpl.model.ZebraElement#getZplCode(fr.w3blog.zpl.model.PrinterOptions)
	 */
	@Override
	public String getZplCode(PrinterOptions printerOptions) {
		return ZplUtils.zplCommandSautLigne(String.format("A%s", zebraFont.getLetter()), zebraRotation.getLetter(), dotHeigth, dotsWidth).toString();
	}
}
