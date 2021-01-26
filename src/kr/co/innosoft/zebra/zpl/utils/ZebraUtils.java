package kr.co.innosoft.zebra.zpl.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.standard.PrinterName;

import kr.co.innosoft.zebra.zpl.model.ZebraLabel;
import kr.co.innosoft.zebra.zpl.model.ZebraPrintException;
import kr.co.innosoft.zebra.zpl.model.ZebraPrintNotFoundException;

/**
 * Utilities to print zpl
 *
 * @author ttropard
 *
 */
public class ZebraUtils {

	/**
	 * Function to print code Zpl to network zebra
	 *
	 * @param zpl code Zpl to print
	 * @param ip ip adress
	 * @param port port
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(String zpl, String ip, int port) throws ZebraPrintException {
		Socket clientSocket = null;
		try {
			try {
				clientSocket = new Socket(ip, port);
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				outToServer.writeBytes(zpl);
				clientSocket.close();
			} finally {
				if (clientSocket != null) {
					clientSocket.close();
				}
			}
		} catch (IOException e1) {
			throw new ZebraPrintException("Cannot print label on this printer : " + ip + ":" + port, e1);
		}
	}

	/**
	 * Function to print code Zpl to network zebra
	 *
	 * @param zebraLabel code Zpl to print
	 * @param ip ip adress
	 * @param port port
	 * @param charSet character set
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(String zpl, String ip, int port, String charSet) throws ZebraPrintException {
		Socket clientSocket = null;
		try {
			try {
				clientSocket = new Socket(ip, port);
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//				outToServer.writeBytes(zpl);
				outToServer.write(zpl.getBytes(charSet));
				clientSocket.close();
			} finally {
				if (clientSocket != null) {
					clientSocket.close();
				}
			}
		} catch (IOException e1) {
			throw new ZebraPrintException("Cannot print label on this printer : " + ip + ":" + port, e1);
		}
	}

	/**
	 * Function to print code Zpl to local zebra(usb)
	 *
	 * @param zpl code Zpl to print
	 * @param printerName name of the selected printer
	 * @param charSet Character Set
	 * @throws ZebraPrintException
	 *             if zpl could not be printed
	 */

	public static void printZpl(String zpl, String printerName, String charSet) throws ZebraPrintException {
		try {

			PrintService psZebra = null;
			String sPrinterName = null;
			PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);

			for (int i = 0; i < services.length; i++) {
				PrintServiceAttribute attr = services[i].getAttribute(PrinterName.class);
				sPrinterName = ((PrinterName) attr).getValue();
				if (sPrinterName.toLowerCase().indexOf(printerName.toLowerCase()) >= 0) {
					psZebra = services[i];
					break;
				}
			}

			if (psZebra == null) {
				throw new ZebraPrintNotFoundException("Zebra printer not found : " + printerName);
			}
			DocPrintJob job = psZebra.createPrintJob();

			byte[] by = zpl.getBytes(charSet);
			DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
			Doc doc = new SimpleDoc(by, flavor, null);
			job.print(doc, null);

		} catch (PrintException e) {
			throw new ZebraPrintException("Cannot print label on this printer : " + printerName, e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Function to print zebraLabel
	 *
	 * @param zebraLabel zebraLabel
	 * @param ip ipadress
	 * @param port port
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(ZebraLabel zebraLabel, String ip, int port) throws ZebraPrintException {
		printZpl(zebraLabel.getZplCode(), ip, port);
	}

	/**
	 * Function to print zebraLabel
	 *
	 * @param zebraLabel zebraLabel
	 * @param ip ipadress
	 * @param port port
	 * @param charSet Character Set
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(ZebraLabel zebraLabel, String ip, int port, String charSet) throws ZebraPrintException {
		printZpl(zebraLabel.getZplCode(), ip, port, charSet);
	}

	/**
	 * Function to print zebraLabel
	 *
	 * @param zebraLabel zebraLabel
	 * @param printerName printerName
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(ZebraLabel zebraLabel, String printerName) throws ZebraPrintException {
		printZpl(zebraLabel.getZplCode(), printerName, "UTF-8");
	}

	/**
	 * Fonction to print zebraLabel
	 *
	 * @param zebraLabel  zebraLabel
	 * @param printerName printerName
	 * @param charSet Character Set
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(ZebraLabel zebraLabel, String printerName, String charSet) throws ZebraPrintException {
		printZpl(zebraLabel.getZplCode(), printerName, charSet);
	}

	/**
	 * Fonction to print multiple zebraLabel to network printer
	 *
	 * @param zebraLabels list of zebra labels
	 * @param ip ip adress
	 * @param port port
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(List<ZebraLabel> zebraLabels, String ip, int port) throws ZebraPrintException {
		StringBuilder zpl = new StringBuilder();
		for (ZebraLabel zebraLabel : zebraLabels) {
			zpl.append(zebraLabel.getZplCode());
		}
		printZpl(zpl.toString(), ip, port);
	}

	/**
	 * Fonction to print multiple zebraLabel to network printer
	 *
	 * @param zebraLabels list of zebra labels
	 * @param ip ip adress
	 * @param port port
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(List<ZebraLabel> zebraLabels, String ip, int port, String charSet) throws ZebraPrintException {
		StringBuilder zpl = new StringBuilder();
		for (ZebraLabel zebraLabel : zebraLabels) {
			zpl.append(zebraLabel.getZplCode());
		}
		printZpl(zpl.toString(), ip, port, charSet);
	}

	/**
	 * Fonction to print multiple zebraLabel to local printer
	 *
	 * @param zebraLabels list of zebra labels
	 * @param printerName printerName
	 *
	 * @throws ZebraPrintException if zpl could not be printed
	 */
	public static void printZpl(List<ZebraLabel> zebraLabels, String printerName) throws ZebraPrintException {
		StringBuilder zpl = new StringBuilder();
		for (ZebraLabel zebraLabel : zebraLabels) {
			zpl.append(zebraLabel.getZplCode());
		}
		printZpl(zpl.toString(), printerName, "UTF-8");
	}

	/**
	 * Fonction to print multiple zebraLabel to local printer
	 *
	 * @param zebraLabels list of zebra labels
	 * @param printerName printerName
	 * @param charSet Character Set
	 *
	 * @throws ZebraPrintException
	 *             if zpl could not be printed
	 */
	public static void printZpl(List<ZebraLabel> zebraLabels, String printerName, String charSet) throws ZebraPrintException {
		StringBuilder zpl = new StringBuilder();
		for (ZebraLabel zebraLabel : zebraLabels) {
			zpl.append(zebraLabel.getZplCode());
		}
		printZpl(zpl.toString(), printerName, charSet);
	}
}
