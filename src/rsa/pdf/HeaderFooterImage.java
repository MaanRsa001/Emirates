
package rsa.pdf;

import java.awt.Color;
import java.io.IOException;

import org.apache.logging.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;

public class HeaderFooterImage extends PdfPageEventHelper
{
	private static final Logger logger = LogUtil.getLogger(HeaderFooterImage.class);
	private transient String urlPath;
	private transient String cols;
	private transient String urlPathFooter;
	private transient String disText;
	private transient PdfPTableCreation tableCreation;
	private transient Image headerImageRoyal;
	private transient Image footerImageRoyal;
	private transient String fontPath;
	private transient String createDate;
	private transient PdfTemplate total;
	final static transient private String INVDRAFT = "INVALID DRAFT";
	public String getImagePath() {
		return urlPath;
	}

	public void setImagePath(final String urlPath) {
		this.urlPath = urlPath;
	}

	public String getCols() {
		return this.cols;
	}

	public void setCols(final String cols) {
		this.cols = cols;
	}

	public String getFooterImagePath() {
		return urlPathFooter;
	}

	public void setFooterImagePath(final String urlPathFooter) {
		this.urlPathFooter = urlPathFooter;
	}

	public String getDisplayText() {
		return disText;
	}

	public void setDisplayText(final String disText) {
		this.disText = disText;
	}
	
	/*** Other Country Footer***/

	private String policyOn;
	private String policyAt;
	private String entered;
	private String approved;
	private String signedImage;
	private String stampImage;
	private String cid;
	private String BrokerCompany;
	private String braAddress;
	private String issuerName;
	private String debitPDF;
	private String usrModeController;
	private String stampStatus;
	
	private transient Image signImageRoyal;
	private transient Image stampImageRoyal;

	public void setUsrModeController(final String usrModeController){
		this.usrModeController = usrModeController;
	}

	public void setStampStatus(final String stampStatus){
		this.stampStatus = stampStatus;
	}

	public void setIssuerName(final String issuerName){
		this.issuerName = issuerName;
	}

	public void setPolicyOn(final String policyOn){
		this.policyOn = policyOn;
	}

	public void setPolicyAt(final String policyAt){
		this.policyAt = policyAt;
	}

	public void setEntered(final String entered){
		this.entered = entered;
	}

	public void setApproved(final String approved){
		this.approved = approved;
	}

	public void setSignedImage(final String signedImage){
		this.signedImage = signedImage;
	}

	public void setStampImage(final String stampImage){
		this.stampImage = stampImage;
	}

	public void setCid(final String cid){
		this.cid = cid;
	}

	public String getCid(){
		return this.cid;
	}

	public void setBrokerCompany(final String BrokerCompany){
		this.BrokerCompany = BrokerCompany;
	}

	public String getBrokerCompany(){
		return this.BrokerCompany;
	}

	public void setBraAddress(final String braAddress){
		this.braAddress = braAddress;
	}

	public void setDebitPDF(final String debitPDF){
		this.debitPDF = debitPDF;
	}

	public String getBraAddress(){
		return this.braAddress;
	}

	public String getPolicyOn(){
		return this.policyOn;
	}

	public String getPolicyAt(){
		return this.policyAt;
	}

	public String getEntered(){
		return this.entered;
	}

	public String getApproved(){
		return this.approved;
	}

	public String getSignedImage(){
		return this.signedImage;
	}

	public String getIssuerName(){
		return this.issuerName;
	}

	public String getStampImage(){
		return this.stampImage;
	}

	public String getDebitPDF(){
		return this.debitPDF;
	}

	public String getStampStatus(){
		return this.stampStatus;
	}

	public String getUsrModeController(){
		return this.usrModeController;
	}
	
	/*** Other Country Footer Setter and Getter Methods***/

	public PdfPTable getHeaderImageTable()throws BaseException
	{
			tableCreation = new PdfPTableCreation();
			try
			{
				headerImageRoyal  = Image.getInstance(getImagePath());
			}
			catch (Exception e) 
			{
					logger.info(e);
			}
			//headerImageRoyal.scaleToFit(500,150);
			//headerImageRoyal.scaleToFit(400,100);
			headerImageRoyal.scaleToFit(500,60);
			headerImageRoyal.setDpi(300,300);
			
			//headerImageRoyal.scaleToFit(173.25f,46.5f);
			//headerImageRoyal.setDpi(300,300);
			tableCreation.setTable(8);
			//tableCreation.insertCell(Rectangle.NO_BORDER, 8);
			//tableCreation.insertCell(Rectangle.NO_BORDER, 8);
			//tableCreation.insertCell(Rectangle.NO_BORDER, 8);
			tableCreation.insertCell(headerImageRoyal, Rectangle.NO_BORDER, 8, 0);
			BaseFont arialFont=null;
			try {
				arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
				Font fontTextNormal = new Font(arialFont, 8, Font.NORMAL);
				//tableCreation.insertCell(" COMPANY", fontTextNormal, Rectangle.NO_BORDER,8, 1);
			} catch (DocumentException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			tableCreation.setTableSpacing(1f);
			return tableCreation.getTable();
	}

	public void onEndPage(final PdfWriter writer, final Document document)
	{
        try
		{  
			BaseFont arialFont=null;
			tableCreation = new PdfPTableCreation();
			final Rectangle page = document.getPageSize();
        	final PdfPTable head = new PdfPTable(1);

        	final PdfPCell cell = new PdfPCell(getHeaderImageTable());
			
			cell.setBorder(Rectangle.NO_BORDER);
            head.addCell(cell);
			head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			if("153".equals(cid) || "14".equals(cid)){
				head.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head.getTotalHeight()-30,writer.getDirectContent());
			}else{
				head.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head.getTotalHeight()-10,writer.getDirectContent());
			}
			//Footer
			//if("1".equals(cid) || "173".equals(cid))
			{
				arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
				Font fontTextNormal = new Font(arialFont, 8, Font.NORMAL);
				final PdfPTable foot = new PdfPTable(1);
				
				final PdfPTable pageNo = new PdfPTable(2);
				Paragraph para=new Paragraph(String.format("Page %d of ", writer.getPageNumber()), fontTextNormal);
				PdfPCell cell2 = new PdfPCell(para);
				cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell2.setBorder(Rectangle.NO_BORDER);
                pageNo.addCell(cell2);
                PdfPCell cell3 = new PdfPCell(Image.getInstance(total));
                cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell3.setBorder(Rectangle.NO_BORDER);
                pageNo.addCell(cell3);
                pageNo.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pageNo.setTotalWidth(100);
                pageNo.writeSelectedRows(0, -1, page.getWidth()-document.rightMargin()-pageNo.getTotalWidth()+38, document.bottomMargin()+foot.getTotalHeight()+pageNo.getTotalHeight()-12,writer.getDirectContent());
				
				footerImageRoyal = Image.getInstance(getFooterImagePath());
				//footerImageRoyal.scaleAbsolute(500,60);
				footerImageRoyal.scaleToFit(500,60);
				//footerImageRoyal.scaleAbsolute(1032.75f,63f);
				footerImageRoyal.setDpi(300,300);
				final PdfPCell cell1 = new PdfPCell(footerImageRoyal);
				cell1.setBorder(Rectangle.NO_BORDER);
				foot.addCell(cell1);
				
				foot.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
				foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin()-12,writer.getDirectContent());
			}
			
			final PdfContentByte pdfConByte = writer.getDirectContent();
			pdfConByte.saveState();
			final PdfGState gstate = new PdfGState();
            gstate.setFillOpacity(0.3f);
            gstate.setStrokeOpacity(0.3f);
            final BaseFont helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
			
			/*if(disText.equalsIgnoreCase(INVDRAFT)||disText.equalsIgnoreCase("DRAFT")){
				disText = "QUOTATION ONLY";
			}*/
			if (writer.getPageNumber() <= 3)
			{
				pdfConByte.setGState(gstate);
				pdfConByte.setColorFill(Color.pink);
				pdfConByte.beginText();
				pdfConByte.setFontAndSize(helv, 50);
				pdfConByte.showTextAligned(Element.ALIGN_CENTER, disText.replaceAll("INVALID", "TEST") , document.getPageSize().getWidth() / 2, document.getPageSize().getHeight() / 2+20, 45);
				pdfConByte.endText();
			}
        }
        catch (Exception e){
        	logger.info(e);
        }
    }
     
    public void onStartPage(final PdfWriter writer1, final Document document) 
	{
		try
		{
			document.add(new Paragraph("\n\n"));
		}
		catch (Exception e)
		{
			logger.info(e);
		} 
    }

	public String getFontPath() {
		return fontPath;
	}

	public void setFontPath(final String fontPath) {
		this.fontPath = fontPath;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void onOpenDocument(PdfWriter writer, Document document) {
        total = writer.getDirectContent().createTemplate(30, 16);
    }
	 public void onCloseDocument(PdfWriter writer, Document document) {
		 BaseFont arialFont=null;
		 try {
			arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
			Font fontTextNormal = new Font(arialFont, 8, Font.NORMAL);
	        ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(" "+String.valueOf(writer.getPageNumber() - 1),fontTextNormal),0, 6, 0);
		}catch (Exception e) {
			e.printStackTrace();
		}
     }
} // Class
