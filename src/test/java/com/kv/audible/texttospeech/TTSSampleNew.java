package com.kv.audible.texttospeech;
import java.io.File;
import java.io.FileOutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class TTSSampleNew {

  public static void tts(String textToSynthesize) {
    //String textToSynthesize = "This is a demo to call microsoft text to speech service in java.";
    String outputFormat = AudioOutputFormat.Riff24Khz16BitMonoPcm;
    String deviceLanguage = "en-US";
    String genderName = "Male";
    String voiceName = "Microsoft Server Speech Text to Speech Voice (en-US, Guy24KRUS)";

    try{
      byte[] audioBuffer = TTSServiceNew.Synthesize(textToSynthesize, outputFormat, deviceLanguage, genderName, voiceName);

      // write the pcm data to the file
      String outputWave = "/Users/karanverma/Documents/backups/git/AudioBooks/converted.wav";
      File outputAudio = new File(outputWave);
      FileOutputStream fstream = new FileOutputStream(outputAudio);
      fstream.write(audioBuffer);
      fstream.flush();
      fstream.close();


      // specify the audio format
      AudioFormat audioFormat = new AudioFormat(
          AudioFormat.Encoding.PCM_SIGNED,
          24000,
          16,
          1,
          1 * 2,
          24000,
          false);

      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(outputWave));

      DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
          audioFormat, AudioSystem.NOT_SPECIFIED);
      SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem
          .getLine(dataLineInfo);
      sourceDataLine.open(audioFormat);
      sourceDataLine.start();
      System.out.println("start to play the wave:");
      /*
       * read the audio data and send to mixer
       */
      int count;
      byte tempBuffer[] = new byte[4096];
      while ((count = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) >0) {
        sourceDataLine.write(tempBuffer, 0, count);
      }

      sourceDataLine.drain();
      sourceDataLine.close();
      audioInputStream.close();

    }catch(Exception e){
      e.printStackTrace();
    }
  }

}
