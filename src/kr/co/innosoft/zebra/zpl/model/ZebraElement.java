package kr.co.innosoft.zebra.zpl.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import kr.co.innosoft.zebra.zpl.utils.ZplUtils;

public abstract class ZebraElement {

	/**
	 * x-axis location (in dots)
	 */
	protected Integer positionX;
	/**
	 * y-axis location (in dots)
	 */
	protected Integer positionY;

	/**
	 * Will draw a default box on the graphic if drawGraphic method is not overload
	 *
	 */
	protected boolean defaultDrawGraphic = true;

	/**
	 * @return the positionX
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * @param positionX the positionX to set
	 * @return ZebraElement
	 */
	public ZebraElement setPositionX(int positionX) {
		this.positionX = positionX;
		return this;
	}

	/**
	 * @return the positionY
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * @param positionY the positionY to set
	 * @return ZebraElement
	 */
	public ZebraElement setPositionY(int positionY) {
		this.positionY = positionY;
		return this;
	}

	/**
	 *
	 * @param printerOptions printerOptions
	 * @return String
	 */
	public String getZplCode(PrinterOptions printerOptions) {
		return "";
	}

	/**
	 * Function used by child class if you want to set position before draw your element.
	 *
	 * @return String
	 */
	protected String getZplCodePosition() {

		StringBuffer zpl = new StringBuffer("");
		if (positionX != null && positionY != null) {
			zpl.append(ZplUtils.zplCommand("FT", positionX, positionY));
		}
		return zpl.toString();
	}

	/**
	 * Used to draw label preview.
	 * This method should be overloader by child class.
	 *
	 * Default draw a rectangle
	 *
	 * @param printerOptions printerOptions
	 *            TODO
	 * @param graphic graphic
	 */
	public void drawPreviewGraphic(PrinterOptions printerOptions, Graphics2D graphic) {
		if (defaultDrawGraphic) {
			int top = 0;
			int left = 0;
			if (positionX != null) {
				left = Math.round((positionX / printerOptions.getZebraPPP().getDotByMm()) * 10);
			}
			if (positionY != null) {
				top = Math.round((positionY / printerOptions.getZebraPPP().getDotByMm()) * 10);
			}
			graphic.setColor(Color.BLACK);
			graphic.drawRect(left, top, 100, 20);
			drawTopString(graphic, new Font("Arial", Font.BOLD, 11), "Default", left, top);
		}
	}

	/**
	 * Function to draw Element, based on top position.
	 *
	 * Default drawString write text on vertical middle (Zebra not)
	 *
	 * @param graphic graphic
	 * @param font font
	 * @param text text
	 * @param positionX positionX
	 * @param positionY positionY
	 */
	protected void drawTopString(Graphics2D graphic, Font font, String text, int positionX, int positionY) {
		graphic.setFont(font);
		FontMetrics fm = graphic.getFontMetrics(font);
		Rectangle2D rect = fm.getStringBounds(text, graphic);
		int textHeight = (int) (rect.getHeight());
		positionY = positionY + textHeight;
		graphic.drawString(text, positionX, positionY); // Draw the string.
	}
}
