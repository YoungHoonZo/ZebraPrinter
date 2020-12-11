package kr.co.innosoft.zebra.zpl.model.element;

import kr.co.innosoft.zebra.zpl.model.PrinterOptions;
import kr.co.innosoft.zebra.zpl.model.ZebraElement;

/**
 * Object use if you want add Zpl Code not supported by this library
 * 
 * @author ttropard
 * 
 */
public class ZebraNativeZpl extends ZebraElement {

	private String zplCode;

	public ZebraNativeZpl(String zplCode) {
		super();
		this.zplCode = zplCode;
		this.defaultDrawGraphic = false;
	}

	/* (non-Javadoc)
	 * @see fr.w3blog.zpl.model.ZPLElement#getZplCode()
	 */
	@Override
	public String getZplCode(PrinterOptions printerOptions) {
		return zplCode;
	}
}
