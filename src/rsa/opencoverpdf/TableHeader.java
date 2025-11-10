package rsa.opencoverpdf;

import java.awt.Color;

import org.apache.logging.log4j.Logger;

import rsa.pdf.PdfPTableCreation;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
public class TableHeader extends PdfPageEventHelper
{
	private static final Logger logger = LogUtil.getLogger(TableHeader.class);
	
	final transient private PdfPTableCreation tableCreation;
	private transient int page = 0;
	private transient String openCoverNo;
	private transient String clientName;
	private transient String certificateNo;
	private transient String urlPath;
	private transient String currency;
	private transient String fontPath;
	private transient String displayMode;
	private transient String currencyType;
	
	/**
	 * @return the currencyType
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	/**
	 * @param currencyType the currencyType to set
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	/**
	 * @return the displayMode
	 */
	public String getDisplayMode() {
		return displayMode;
	}
	/**
	 * @param displayMode the displayMode to set
	 */
	public void setDisplayMode(String displayMode) {
		this.displayMode = displayMode;
	}
	public String getImagePath()
	{
		return urlPath;
	}
	public void setImagePath(final String urlPath)
	{
		this.urlPath=urlPath;					
	}
	public String getOpenCoverNo()
	{
		return openCoverNo;
	}
	public void setOpenCoverNo(final String openCoverNo)
	{
		this.openCoverNo=openCoverNo;					
	}
	public String getClientName()
	{
		return clientName;
	}
	public void setClientName(final String clientName)
	{
		this.clientName=clientName;					
	}
	public String getCurrency() {
		return this.currency;
	}
	public void setCurrency(final String currency) {
		this.currency = currency;
	}
	public String getCertificateNo()
	{
		return certificateNo;
	}
	public void setCertificateNo(final String certificateNo)
	{
		this.certificateNo=certificateNo;					
	}
	public TableHeader()
	{
		super();
		tableCreation = new PdfPTableCreation();
	}
	public PdfPTable getHeaderTable() throws BaseException
	{
			BaseFont arialFont = null;
			try
			{
				arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			}
			catch (Exception e) 
			{
					logger.info(e);
			}
				//////////// Font Declaration ////////////
			Font font1;
			font1 = new Font(arialFont, 8, Font.BOLD,Color.BLACK);
			Font font2;
			font2 = new Font(arialFont, 8, Font.BOLD,Color.BLACK);
			Font font3;
			font3 = new Font(arialFont, 10, Font.BOLD,Color.BLACK);
				//font3.setStyle(Font.UNDERLINE);
				/*PdfPTable innertable1;
				tableCreation.setTable(1);
				tableCreation.insertCell("Markers & Numbers", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Packing Description\nPacking Type\nNo. of Packages\nPackage No.", font2, Rectangle.BOX, 1, 0);
				innertable1 = tableCreation.getTable();
				
				tableCreation.setTable(1);
				tableCreation.insertCell("Voyage", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("From\n\nTo\nTranshipment", font2, Rectangle.BOX, 1, 0);
				PdfPTable innertable2;
				innertable2 = tableCreation.getTable();

				tableCreation.setTable(11);
				
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 10, 0);
				tableCreation.insertCell("Page\b"+page, font2, Rectangle.NO_BORDER, 1, 1);
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 3, 0);
				tableCreation.insertCell("Marine Declaration Under Open Cover No.", font2, Rectangle.NO_BORDER, 3, 0);
				tableCreation.insertCell(openCoverNo, font2, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell("Currency :", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("("+getCurrency()+")", font1, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell("Bordereaux No :", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell(certificateNo, font1, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell("", font1, Rectangle.NO_BORDER, 8, 0);
				tableCreation.insertCell("Client Name :", font2, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell(clientName, font1, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell("", font3, Rectangle.NO_BORDER, 8, 0);
				
				tableCreation.insertCell("Date of\nIssue", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Declaration No.\nCertificate No.\n\nInvoice No.", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("BL/AWB/CN NO.\n&. Date\nL.C. No.\nBank Interest", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell(innertable1, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Mode of Transit\nVessel Name\nSailing Date", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell(innertable2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Interests", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Sum Insured\nValuation Basis\nLoading/Addl. (L)\nF.C. Sum Insured\nExchange Rate", font2, Rectangle.BOX, 1, 1);
				tableCreation.insertCell("", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Rate(%)", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Premium", font2, Rectangle.BOX, 1, 0);*/
				tableCreation.setTable(15);
				if("draftModeMultiple".equalsIgnoreCase(displayMode)){
					tableCreation.insertCell("List attaching to and forming part of Quote No "+certificateNo, font1,Rectangle.NO_BORDER, 15, 0);
				}else{
					tableCreation.insertCell("List attaching to and forming part of Policy No "+certificateNo, font1,Rectangle.NO_BORDER, 15, 0);
				}
				tableCreation.insertCell("\n", font2,Rectangle.NO_BORDER, 15, 0);
				tableCreation.insertCell("Sno.", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Ref. No.", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Order No.", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Interest / Description", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("L/C NO. / Bank", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Conveyance", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Vessel Name / Sailing Date", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Voyage From", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Voyage To", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Invoice Value", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Currency", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Incoterms", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("ROE", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("BOV", font2, Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Sum Insured ("+currencyType+")", font2, Rectangle.BOX, 1, 0);
				try {
					tableCreation.getTable().setWidths(new float[] {5f, 10f, 10f, 15f, 10f, 10f, 12f, 12f, 10f, 12f, 12f, 12f, 5f, 10f, 12f});
				} catch (DocumentException e) {
					
					e.printStackTrace();
				}
				return tableCreation.getTable();
	}

	public void onEndPage(final PdfWriter writer, final Document document)
	{
        try
		{
			//page++;
			page = writer.getPageNumber();
            Rectangle page = document.getPageSize();
            PdfPTable head;
            head = new PdfPTable(1);
			
		/*	PdfPCell cell = new PdfPCell(getHeaderImageTable());
			cell.setBorder(Rectangle.NO_BORDER);
            head.addCell(cell);
			head.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
			head.writeSelectedRows(0, -1, document.leftMargin(), page.height() - document.topMargin() + head.getTotalHeight()-10, writer.getDirectContent());*/
			//
            PdfPCell cell1;
            cell1 = new PdfPCell(getHeaderTable());
			cell1.setBorder(Rectangle.NO_BORDER);
            head.addCell(cell1);
			head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			head.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head.getTotalHeight() - 80, writer.getDirectContent());
			//
		/*	PdfPTable foot = new PdfPTable(1);
			//jpg.scaleAbsolute(500,60);
			//jpg.setAbsolutePosition(document.leftMargin(), -15);
			//jpg.scaleAbsoluteWidth(1050f);
			//jpg.scaleAbsoluteHeight(90f);
			//jpg.setDpi(300,300);
			foot.addCell(cell);
			foot.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin() + 20);
			foot.writeSelectedRows(0, -1, 10, document.bottomMargin(), writer.getDirectContent());*/
        }
        catch (Exception e)
		{
            throw new ExceptionConverter(e);
        }
	}
	public void onStartPage(final PdfWriter writer, final Document document)
	{
		try
		{
			BaseFont arialFont = null;
			try
			{
				arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			}
			catch (Exception e) 
			{
					logger.info(e);
			}
				//////////// Font Declaration ////////////
			Font font1 = new Font(arialFont, 8, Font.BOLD);
			document.add(new Paragraph("\n\n"));
			tableCreation.setTable(1);
			tableCreation.insertCell("", font1,Rectangle.NO_BORDER, 1, 0);
			tableCreation.insertCell("", font1,Rectangle.NO_BORDER, 1, 0);
			document.add(tableCreation.getTable());
			
		}
		catch (Exception e)
		{
			throw new ExceptionConverter(e);
		}
	}
	public String getFontPath() {
		return fontPath;
	}
	public void setFontPath(final String fontPath) {
		this.fontPath = fontPath;
	}
}