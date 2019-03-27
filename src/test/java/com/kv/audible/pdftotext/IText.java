package com.kv.audible.pdftotext;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.kv.audible.texttospeech.TTSSampleNew;

public class IText {

  public static void main(String... args){
    test ();
  }

  public static void test(){
    try {
      PdfReader reader = new PdfReader ("/Users/karanverma/Downloads/RS_BOOK1.pdf");

      int n = reader.getNumberOfPages();
      System.out.println("Total Pages = " + n);

      String convertToText = "";
      for(int i=2;i<=n;i++){
        System.out.println("Getting Page = " + i);
        String pageText = PdfTextExtractor.getTextFromPage(reader, i);

        convertToText += pageText;
        if(i%5 == 0){
          System.out.println("Page = " + i + ", text length = " + convertToText.length());
          TTSSampleNew.tts(convertToText, i);
          convertToText = "";
        }
      }
      reader.close();
    } catch (Exception e) {
      e.printStackTrace ();
      System.out.println(e);
    }
  }

}
