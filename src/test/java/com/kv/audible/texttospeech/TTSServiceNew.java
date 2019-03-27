package com.kv.audible.texttospeech;

import com.kv.audible.utils.ByteArray;
import com.kv.audible.utils.Constants;
import com.kv.audible.utils.HttpsConnection;
import java.io.DataOutputStream;
import java.io.InputStream;

import javax.net.ssl.HttpsURLConnection;

public class TTSServiceNew {

  private static String ttsServiceUri = "https://westus.tts.speech.microsoft.com/cognitiveservices/v1";

  public static byte[] Synthesize(String textToSynthesize, String outputFormat, String locale, String genderName, String voiceName) throws Exception {

    Authentication auth = new Authentication(Constants.KEY2);
    String accessToken = auth.GetAccessToken();

    HttpsURLConnection webRequest = HttpsConnection.getHttpsConnection(ttsServiceUri);
    webRequest.setDoInput(true);
    webRequest.setDoOutput(true);
    webRequest.setConnectTimeout(5000);
    webRequest.setReadTimeout(30000);
    webRequest.setRequestMethod("POST");

    webRequest.setRequestProperty("Content-Type", "application/ssml+xml");
    webRequest.setRequestProperty("X-Microsoft-OutputFormat", outputFormat);
    webRequest.setRequestProperty("Authorization", "Bearer " + accessToken);
    webRequest.setRequestProperty("X-Search-AppId", "07D3234E49CE426DAA29772419F436CA");
    webRequest.setRequestProperty("X-Search-ClientID", "1ECFAE91408841A480F00935DC390960");
    webRequest.setRequestProperty("User-Agent", "TTSAndroid");
    webRequest.setRequestProperty("Accept", "*/*");

    String body = XmlDom.createDom(locale, genderName, voiceName, textToSynthesize);
    byte[] bytes = body.getBytes();
    webRequest.setRequestProperty("content-length", String.valueOf(bytes.length));
    webRequest.connect();

    DataOutputStream dop = new DataOutputStream(webRequest.getOutputStream());
    dop.write(bytes);
    dop.flush();
    dop.close();

    InputStream inSt = webRequest.getInputStream();
    ByteArray ba = new ByteArray();

    int rn2 = 0;
    int bufferLength = 4096;
    byte[] buf2 = new byte[bufferLength];
    while ((rn2 = inSt.read(buf2, 0, bufferLength)) > 0) {
      ba.cat(buf2, 0, rn2);
    }

    inSt.close();
    webRequest.disconnect();

    return ba.getArray();
  }
}
