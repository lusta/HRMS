package com.hrms.daoImpl;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class ReportDaoImpl {

	public boolean GenerateReport( String firstName, String lastName, String identityNumber,
			String title, String reportTitle, String description ) {
		boolean status = false;
		
		  try {
			  	File file = new File("C:/Users/Thabiso/Documents/Report/"+reportTitle+".pdf");
			  	FileOutputStream fileout = new FileOutputStream(file);
			  	Document document = new Document();
			  	PdfWriter.getInstance(document, fileout);
			  	document.addAuthor("Me");
			  	document.addTitle("My iText Test");

			  	document.open();
		  
			  	Chunk chunk = new Chunk("The Company");
			  	Font font = new Font(Font.COURIER);
			  	font.setStyle(Font.UNDERLINE);
			  	font.setStyle(Font.ITALIC);
			  	chunk.setFont(font);
			  	chunk.setBackground(Color.CYAN);
			  	document.add(chunk);

			  	Paragraph paragraph = new Paragraph();
			  	Paragraph paragraph2 = new Paragraph();
			  	paragraph.add(reportTitle);
			  	paragraph.setAlignment(Element.ALIGN_CENTER);
			  	document.add(paragraph);
			  	List list = new List(true, 15);
			  	list.add("Title           :"+title);
			  	list.add("First Name      :"+firstName);
			  	list.add("Last Name       :"+lastName);		   
			  	list.add("Identity number :"+identityNumber);  	
			  	list.add("Description");
			  	document.add(list);
			  	
			  	paragraph2.add(description);
			  	document.add(paragraph2);		  	
		        System.out.println("Ha ba shwe...");
		        document.close();
		        status = true;
		        
		  } catch (FileNotFoundException e) {
			  e.printStackTrace();
		  } catch (DocumentException e) {
			  e.printStackTrace();
		  }
		return status;
	}
}
