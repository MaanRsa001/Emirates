package rsa.pdf;

import java.awt.Color;

import org.apache.poi.hssf.record.RecalcIdRecord;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import com.maan.common.exception.BaseException;

public class PdfPTableCreation {
	private transient PdfPTable pdfpTable;

	public void setTable(final int cols) {
		this.pdfpTable = new PdfPTable(cols);
		this.pdfpTable.setWidthPercentage(100);
		this.pdfpTable.getDefaultCell().setHorizontalAlignment(
				Element.ALIGN_LEFT);
	}

	public PdfPTable getTable() {
		return this.pdfpTable;
	}

	public void setTableSpacingBefore(final float sapce) {
		this.pdfpTable.setSpacingBefore(sapce);
	}

	public void setTableSpacing(final float sapce) {
		this.pdfpTable.setSpacingBefore(sapce);
	}

	public void insertCell(final String data, final Font font,
			final int border, final int colspan, final int align)
			throws BaseException {
		try{
		final PdfPCell pdfpCell = new PdfPCell(new Paragraph(data, font));
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		if (align == 0) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		} else if (align == 1) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		} else if (align == 5) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			pdfpCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		}else if (align == 6) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		}
		else {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		this.pdfpTable.addCell(pdfpCell);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void insertCell(final Image image, final int border,
			final int colspan, final int align) throws BaseException {
		final PdfPCell pdfpCell = new PdfPCell(image);
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		if (align == 0) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		} else if (align == 1) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		} else {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		this.pdfpTable.addCell(pdfpCell);
	}
	public void insertCellMiddle(final Image image, final int border,
			final int colspan, final int align) throws BaseException {
		final PdfPCell pdfpCell = new PdfPCell(image);
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		if (align == 0) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		} else if (align == 1) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		} else {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pdfpCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		}
		this.pdfpTable.addCell(pdfpCell);
	}

	public void insertCell(final PdfPTable cellTable, final int border,
			final int colspan, final int align) throws BaseException {
		try{
		final PdfPCell pdfpCell = new PdfPCell(cellTable);
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		if (align == 0) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		} else if (align == 1) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		} else {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		this.pdfpTable.addCell(pdfpCell);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void insertCell(final int border, final int colspan)
			throws BaseException {
		final PdfPCell pdfpCell = new PdfPCell(new Paragraph(""));
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		this.pdfpTable.addCell(pdfpCell);
	}

	public void insertCell(final String data, final Font font,
			final int border, final int colspan, final int align,
			final String background) throws BaseException {
		final PdfPCell pdfpCell = new PdfPCell(new Paragraph(data, font));
		pdfpCell.setBorder(border);
		pdfpCell.setBackgroundColor(Color.BLACK);
		pdfpCell.setColspan(colspan);
		if (align == 0) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		} else if (align == 1) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		} else {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		this.pdfpTable.addCell(pdfpCell);
	}

	public void insertCellPad(final String data, final Font font,
			final int border, final int colspan, final int align)
			throws BaseException {
		final PdfPCell pdfpCell = new PdfPCell(new Paragraph(data, font));
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		if (align == 0) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		} else if (align == 1) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		} else {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		pdfpCell.setPaddingLeft(15);
		this.pdfpTable.addCell(pdfpCell);
	}

	public void insertCellPad(final String data, final Font font,
			final int border, final int colspan, final int align,
			final float padLimit) throws BaseException {
		final PdfPCell pdfpCell = new PdfPCell(new Paragraph(data, font));
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		if (align == 0) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		} else if (align == 1) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		} else {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		pdfpCell.setPaddingLeft(padLimit);
		this.pdfpTable.addCell(pdfpCell);
	}

	public void insertCell(final PdfPTable cellTable, final int border,
			final int colspan, final int align, final float lpadLimit,
			final float rpadLimit) throws BaseException {
		final PdfPCell pdfpCell = new PdfPCell(cellTable);
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		if (align == 0) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		} else if (align == 1) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		} else {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		pdfpCell.setPaddingLeft(lpadLimit);
		pdfpCell.setPaddingRight(rpadLimit);
		this.pdfpTable.addCell(pdfpCell);
	}
	public void insertCell(final PdfPTable cellTable, final int border,
			final int colspan, final int align, final int valign) throws BaseException {
		final PdfPCell pdfpCell = new PdfPCell(cellTable);
		pdfpCell.setBorder(border);
		pdfpCell.setColspan(colspan);
		if (align == 0) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		} else if (align == 1) {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		} else {
			pdfpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}if(valign==0){
			pdfpCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		}
		this.pdfpTable.addCell(pdfpCell);
	}
	
}