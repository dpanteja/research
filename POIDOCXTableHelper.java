package com.dandi.api.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.util.ResourceUtils;
public class POIDOCXTableHelper {
	
	public static void main(String[] args)throws IOException, URISyntaxException {
		modifyAndCopy();
	}

	public static void modifyAndCopy() throws IOException, FileNotFoundException, URISyntaxException {
		String fileName = "F:\\StartSmall.docx";
		
		ReadableByteChannel in = Channels.newChannel(ResourceUtils.getURL("classpath:StartSmall.docx").openStream());

		InputStream fis = Channels.newInputStream(in);

		XWPFDocument document = new XWPFDocument(fis);
		List<XWPFParagraph> paragraphs = document.getParagraphs();
		
		for (int x=0; x<paragraphs.size();x++)
		{
			XWPFParagraph paragraph = paragraphs.get(x);
			
			if(paragraph.getText().length() != 0)
			{
				XWPFRun run=paragraph.createRun();
				
				if(paragraph.getText().contains("Name"))
					run.setText(" Dandapani");
				if(paragraph.getText().contains("Project ID"))
					run.setText(" Lets Start Fun");
			}
		}
		
		List<XWPFTable> tables = document.getTables();
		for (int x=0; x<tables.size();x++)
		{
			XWPFTable table = tables.get(x);
			List<XWPFTableRow> tableRows = table.getRows();
			//tableRows.remove(x);
			for (int r=0; r<tableRows.size();r++)
			{
				System.out.println("Row "+ (r+1)+ ":");
				XWPFTableRow tableRow = tableRows.get(r);
				List<XWPFTableCell> tableCells = tableRow.getTableCells();
				for (int c=0; c<tableCells.size();c++)
				{
					System.out.print("Column "+ (c+1)+ ": ");
					XWPFTableCell tableCell = tableCells.get(c);
					//tableCell.setText("TAE");
					String tableCellVal = tableCell.getText();
					if ((c+1)==2){
						
						if (tableCellVal!=null){
							if (tableCellVal.length()>0){
								 char c1 = tableCellVal.charAt(0);
								 String s2 = "-TEST";
								 char c2 = s2.charAt(0);
								 String test = tableCell.getText().replace(tableCellVal,s2);
								 tableCell.setText(test);
							}else{
								tableCell.setText("NULL");
							}
						}
					}
					System.out.println("tableCell.getText(" + (c) + "):" + tableCellVal);
				}
			}
			System.out.println("\n");
		}
		OutputStream out = new FileOutputStream(fileName);
		document.write(out);
		out.close();
	}
}