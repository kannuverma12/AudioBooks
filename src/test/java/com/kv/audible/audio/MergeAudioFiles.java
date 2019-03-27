package com.kv.audible.audio;

import com.kv.audible.utils.Constants;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;

public class MergeAudioFiles {

  public void mergeAudioFiles(Integer start, Integer end){
    try{

      String wavFile1 = Constants.AUDIO_FILE_PATH + Constants.AUDIO_FILE_NAME + start + "." + Constants.AUDIO_FILE_EXTENSION;
      String wavFile2 = Constants.AUDIO_FILE_PATH + Constants.AUDIO_FILE_NAME + end + "." + Constants.AUDIO_FILE_EXTENSION;

      FileInputStream fistream1 = new FileInputStream (wavFile1);  // first source file
      FileInputStream fistream2 = new FileInputStream(wavFile2);//second source file
      SequenceInputStream sistream = new SequenceInputStream(fistream1, fistream2);
      FileOutputStream fostream = new FileOutputStream("D://merge1.mp3");//destinationfile

      int temp;

      while( ( temp = sistream.read() ) != -1)
      {
        // System.out.print( (char) temp ); // to print at DOS prompt
        fostream.write(temp);   // to write to file
      }
      fostream.close();
      sistream.close();
      fistream1.close();
      fistream2.close();
    }catch(Exception e){
      e.printStackTrace ();
    }finally {

    }


  }

}
