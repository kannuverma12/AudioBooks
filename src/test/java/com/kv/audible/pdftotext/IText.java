package com.kv.audible.pdftotext;

//import com.itextpdf.kernel.pdf.PdfReader;
//import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class IText {

  public static void main(String... args){
    test ();
  }

  public static void test(){
    try {
      PdfReader reader = new PdfReader ("/Users/karanverma/Documents/KV_PAYTM.pdf");
      int n = reader.getNumberOfPages();

      String str= PdfTextExtractor.getTextFromPage(reader, 2); //Extracting the content from a particular page.
      System.out.println(str);
      reader.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}
