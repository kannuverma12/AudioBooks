package com.kv.audible.texttospeech;
import com.kv.audible.utils.Constants;
import java.io.File;
import java.io.FileOutputStream;

import java.util.Objects;

public class TTSSampleNew {

  public static void tts(String textToSynthesize, Integer pageNUmber) {
    String outputFormat = AudioOutputFormat.Riff24Khz16BitMonoPcm;
    String deviceLanguage = "en-US";
    String genderName = "Female";
    String voiceName = "Microsoft Server Speech Text to Speech Voice (en-US, Guy24KRUS)";
    FileOutputStream fstream = null;
    try{
      byte[] audioBuffer = TTSServiceNew.Synthesize(textToSynthesize, outputFormat, deviceLanguage, genderName, voiceName);

      String outputWave = Constants.AUDIO_FILE_PATH + Constants.AUDIO_FILE_NAME + pageNUmber + "." + Constants.AUDIO_FILE_EXTENSION;
      File outputAudio = new File(outputWave);
      fstream = new FileOutputStream(outputAudio);
      fstream.write(audioBuffer);

    }catch(Exception e){
      e.printStackTrace();
    }finally {
      try {
        if(Objects.nonNull(fstream)) {
          fstream.flush ( );
          fstream.close ( );
        }
      }catch (Exception e){
        e.printStackTrace ();
      }
    }
  }

}
