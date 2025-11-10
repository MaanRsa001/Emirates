package rsa.opencoverpdf;

import java.awt.Color;

import rsa.pdf.PdfPTableCreation;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.common.exception.BaseException;
public class HeaderFooterImage extends PdfPageEventHelper
{
	private transient Image headerImage;
	private transient Image emptyImageBetter;
	private transient Image headerImageBetter;
	private transient String urlPath;
	private transient String urlPathFooter;
	private transient String disText;
	private transient String policyNo;
	private transient String pname;
	private transient String option;
	private transient String betterImagePath;
	private transient String pid;
	private transient String emptyImage;
	private transient PdfPTableCreation tableCreation;
	private transient String fontPath;
	public HeaderFooterImage()
	{
		tableCreation = new PdfPTableCreation();
	}
	public String getHeaderImagePath()
	{
		return urlPath;
	}
	public void setHeaderImagePath(final String urlPath)
	{
		this.urlPath=urlPath;					
	}
	public String getFooterImagePath()
	{
		return urlPathFooter;
	}
	public void setFooterImagePath(final String urlPathFooter)
	{
		this.urlPathFooter=urlPathFooter;					
	}
	public void setDisplayText(final String disText)
	{
		this.disText=disText;					
	}
	public String getDisplayText()
	{
			return disText;
	}
	public void setPolicyNo(final String policyNo)
	{
		this.policyNo=policyNo;					
	}
	public String getPolicyNo()
	{
			return policyNo;
	}
	public void setPname(final String pname)
	{
		this.pname=pname;					
	}
	public String getPname()
	{
			return pname;
	}
	public void setOption(final String option)
	{
		this.option=option;					
	}
	public String getOption()
	{
			return option;
	}
	public String getBetterImagePath()
	{
		return betterImagePath;
	}
	public void setBetterImagePath(final String betterImagePath)
	{
		this.betterImagePath=betterImagePath;					
	}
	public void setPid(final String pid)
	{
		this.pid = pid;
	}
	public String getPid()
	{
		return this.pid;
	}
	public void setEmptyImage(final String emptyImage)
	{
		this.emptyImage = emptyImage;
	}
	public String getEmptyImage()
	{
		return this.emptyImage;
	}
	public PdfPTable getHeaderTable()throws BaseException
	{
			/////////// Font Declaration ////////////
			BaseFont arialFont = null;
			try
			{
				//arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
				arialFont = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
			}
			catch (Exception e) 
			{
					e.printStackTrace();
			}
				final Font font1 = new Font(arialFont, 8, Font.BOLD);
				final Font font2 = new Font(arialFont, 8);
				final Font font3 = new Font(arialFont, 10, Font.BOLD);
			
				tableCreation.setTable(8);
				
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("Royal & Sun Alliance (Middle East) Ltd. E.C.", font1, Rectangle.NO_BORDER, 3, 0);
				
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("Office Court 2nd Floor Oud Metha Road", font2, Rectangle.NO_BORDER, 3, 0);

				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("P.O.Box 28648 Dubai - UAE", font2, Rectangle.NO_BORDER, 3, 0);

				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("Tel: +971 (04) 3029800", font2, Rectangle.NO_BORDER, 3, 0);

				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("Fax: +971 (04) 3029800", font2, Rectangle.NO_BORDER, 3, 0);

				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("Email: rsauae@ae.rsagroup.com", font2, Rectangle.NO_BORDER, 3, 0);

				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 5, 0);
				tableCreation.insertCell("Emirates email", font2, Rectangle.NO_BORDER, 3, 0);
			if(option.equalsIgnoreCase("PrintQuote"))
			{
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 8, 2);
				tableCreation.insertCell("HOME INSURANCE QUOTATION", font3, Rectangle.NO_BORDER, 8, 2);
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 8, 2);
				tableCreation.insertCell("", font2, Rectangle.NO_BORDER, 8, 2);
			}
				tableCreation.setTableSpacing(20f);
				return tableCreation.getTable();
	}
	public PdfPTable getHeaderImageTable()throws BaseException
	{
			BaseFont arialFont = null;
			try
			{
				headerImage  = Image.getInstance(getHeaderImagePath());
				//arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
				arialFont = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
			}
			catch (Exception e) 
			{
					e.printStackTrace();
			}
			final Font font2 = new Font(arialFont, 11);
			//headerImage.setAbsolutePosition(document.leftMargin(), 760);
			
			if(option.equalsIgnoreCase("PrintQuote")&&(pid.equals("28") || pid.equals("34")))
			{
				try
				{
					headerImageBetter  = Image.getInstance(getBetterImagePath());
					emptyImageBetter  = Image.getInstance(getEmptyImage());
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				tableCreation.setTable(2);
				headerImageBetter.scaleAbsolute(125,25);
				emptyImageBetter.scaleAbsolute(125,24);
				headerImageBetter.setDpi(300,100);
				emptyImageBetter.setDpi(300,100);
				tableCreation.insertCell(emptyImageBetter, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell(headerImageBetter, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell(emptyImageBetter, Rectangle.NO_BORDER, 2, 0);
				PdfPTable temp  = tableCreation.getTable();
				tableCreation.setTable(8);
				headerImage.scaleAbsolute(380,63);
				headerImage.setDpi(200,100);
				tableCreation.insertCell(temp, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell(headerImage, Rectangle.NO_BORDER, 6, 0);
				
				
			}
			else if(option.equalsIgnoreCase("BetterMail"))
			{
				
				
				try
				{
					headerImageBetter  = Image.getInstance(getBetterImagePath());
					emptyImageBetter  = Image.getInstance(getEmptyImage());
				}
				catch (Exception e)
				{
					System.out.println("royalbetermailtest....."+e.toString());
					e.printStackTrace();
				}
				tableCreation.setTable(2);
				headerImageBetter.scaleAbsolute(125,25);
				headerImageBetter.setDpi(300,100);
				tableCreation.insertCell(headerImageBetter, Rectangle.NO_BORDER, 2, 0);
				tableCreation.setTableSpacing(50f);
				PdfPTable temp  = tableCreation.getTable();

				tableCreation.setTable(8);
				headerImage.scaleAbsolute(130,90);
				headerImage.setDpi(200,100);
				tableCreation.insertCell(Rectangle.NO_BORDER, 6);
				tableCreation.insertCell(headerImage, Rectangle.NO_BORDER, 2, 0);
					tableCreation.insertCell(Rectangle.NO_BORDER, 8);
					tableCreation.insertCell(Rectangle.NO_BORDER, 8);
					tableCreation.insertCell(Rectangle.NO_BORDER, 8);
					tableCreation.insertCell(Rectangle.NO_BORDER, 8);
					tableCreation.insertCell(Rectangle.NO_BORDER, 8);
					tableCreation.insertCell(Rectangle.NO_BORDER, 8);
									
				tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.insertCell("BETTER HOMES HOME CONTENTS INSURANCE ESTIMATE", font2,Rectangle.NO_BORDER, 5, 2);
				tableCreation.insertCell(temp, Rectangle.NO_BORDER, 2, 0);
				
				
			}
			else
			{
				tableCreation.setTable(8);
				headerImage.scaleAbsolute(500,70);
				headerImage.setDpi(200,100);
				tableCreation.insertCell(headerImage, Rectangle.NO_BORDER, 8, 1);
			}
			
			tableCreation.setTableSpacing(1f);
			return tableCreation.getTable();
	}
    public void onEndPage(PdfWriter writer, Document document)
	{
        try
		{
			
			Rectangle page = document.getPageSize();
            PdfPTable head = new PdfPTable(1);
            PdfPTable head1 = new PdfPTable(1);
			
            PdfPCell cell = new PdfPCell(getHeaderImageTable());
			cell.setBorder(Rectangle.NO_BORDER);
            head.addCell(cell);
			head.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
            if(option.equalsIgnoreCase("BetterMail"))
				head.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head.getTotalHeight()-100,
                writer.getDirectContent());
			else
				head.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head.getTotalHeight()-30,
                writer.getDirectContent());
			
			
			
			//
			/*PdfPCell cellHead = new PdfPCell(getHeaderTable());
			cellHead.setBorder(Rectangle.NO_BORDER);
            head1.addCell(cellHead);*/
		if(option.equalsIgnoreCase("PrintQuote"))
		{
			head1.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			head1.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head1.getTotalHeight() - 105, writer.getDirectContent());
		}
		else
		{
			head1.setTotalWidth(page.getWidth() - document.leftMargin() - document.rightMargin());
			head1.writeSelectedRows(0, -1, document.leftMargin(), page.getHeight() - document.topMargin() + head1.getTotalHeight() - 105, writer.getDirectContent());
		}
			//
           /* PdfPTable foot = new PdfPTable(1);
            footerImage = Image.getInstance(getFooterImagePath());
			//footerImage.setAbsolutePosition(document.leftMargin(), -15);
			footerImage.scaleAbsolute(500,60);
			footerImage.setDpi(300,300);
			PdfPCell cell1 = new PdfPCell(footerImage);
			cell1.setBorder(Rectangle.NO_BORDER);
            foot.addCell(cell1);
			//foot.addCell(footererImage);
			foot.setTotalWidth(page.width() - document.leftMargin() - document.rightMargin());
            foot.writeSelectedRows(0, -1, document.leftMargin(), document.bottomMargin(),
                writer.getDirectContent());*/
			PdfContentByte cb = writer.getDirectContent();
			cb.saveState();
			//System.out.println("Draft b4 new");
			final PdfGState gstate = new PdfGState();
            gstate.setFillOpacity(0.3f);
            gstate.setStrokeOpacity(0.3f);
            final BaseFont helv = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
			//System.out.println("Draft b4 setting in Helper end");
			
			if (writer.getPageNumber() <= 3)
			{
				try{cb.setGState(gstate);
				cb.setColorFill(Color.pink);
				cb.beginText();
				cb.setFontAndSize(helv, 50);
//				cb.showTextAligned(Element.ALIGN_CENTER, disText , document.getPageSize().width() / 2, document.getPageSize().height() / 2+20, 45);
				cb.endText();
				//System.out.println("Draft after setting in Helper end");
				} catch (Exception e)
				{
		            e.printStackTrace();
		        }
			}
        }
        catch (Exception e)
		{
            throw new ExceptionConverter(e);
        }
    }
	public void onStartPage(PdfWriter writer, Document document)
	{
		BaseFont arialFont = null;
		try
		{
			//arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			arialFont = BaseFont.createFont("Helvetica", BaseFont.WINANSI, false);
		}
		catch (Exception e) 
		{
				e.printStackTrace();
		}
		Font font1 = new Font(arialFont, 12, Font.BOLD);
		font1.setStyle(Font.UNDERLINE);
		Font sfont1 = new Font(arialFont, 9, Font.BOLD);
		try
		{
			
			//document.add(new Paragraph("<u>Home Insurance Policy</u>"));
			//document.add(new Paragraph("Schedule forming part of Policy No:"+getPolicyNo()));
			if(!option.equalsIgnoreCase("PrintQuote"))
			{
				document.add(new Paragraph("\n\n\n\n\n\n"));
				tableCreation.setTable(3);
				//tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.insertCell(pname+"\bPOLICY", font1, Rectangle.NO_BORDER, 3, 2);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				tableCreation.insertCell(Rectangle.NO_BORDER, 3);
				
				if("Draft".equalsIgnoreCase(getDisplayText()))
					tableCreation.insertCell("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\bSchedule forming part of Quote No:\b\b\b"+getPolicyNo(), sfont1, Rectangle.NO_BORDER, 2, 0);
				
				else 
					tableCreation.insertCell("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\bSchedule forming part of Policy No:\b\b\b"+getPolicyNo(), sfont1, Rectangle.NO_BORDER, 2, 0);
				
				tableCreation.insertCell(Rectangle.NO_BORDER, 1);
				tableCreation.setTableSpacing(5f);
				document.add(tableCreation.getTable());
				document.add(new Paragraph("\n\n"));
			}
			else
				document.add(new Paragraph("\n\n\n\n\n\n\n\n"));
			
        }
		catch (Exception e)
		{
			throw new ExceptionConverter(e);
		}
	}
	public String getFontPath() {
		return fontPath;
	}
	public void setFontPath(String fontPath) {
		this.fontPath = fontPath;
	}
}
