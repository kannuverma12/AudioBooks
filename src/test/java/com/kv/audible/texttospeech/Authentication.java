package com.kv.audible.texttospeech;

import com.kv.audible.utils.HttpsConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
import javax.net.ssl.HttpsURLConnection;

public class Authentication
{
  public static final String AccessTokenUri = "https://westus.api.cognitive.microsoft.com/sts/v1.0/issueToken";

  private String apiKey;
  private String accessToken;
  private Timer accessTokenRenewer;

  //Access token expires every 10 minutes. Renew it every 9 minutes only.
  private final int RefreshTokenDuration = 9 * 60 * 1000;
  private final String charsetName = "utf-8";
  private TimerTask nineMinitesTask = null;

  public Authentication(String apiKey)
  {
    this.apiKey = apiKey;

    this.accessToken = HttpPost(AccessTokenUri, this.apiKey);

    // renew the token every specified minutes
    accessTokenRenewer = new Timer();
    nineMinitesTask = new TimerTask(){
      public void run(){
        RenewAccessToken();
      }
    };

    accessTokenRenewer.schedule(nineMinitesTask, 0, RefreshTokenDuration);
  }

  public String GetAccessToken()
  {
    return this.accessToken;
  }

  private void RenewAccessToken()
  {
    String newAccessToken = HttpPost(AccessTokenUri, this.apiKey);
    //swap the new token with old one
    //Note: the swap is thread unsafe
    System.out.println("new access token: " + accessToken);
    this.accessToken = newAccessToken;
  }

  private String HttpPost(String AccessTokenUri, String apiKey)
  {
    InputStream inSt = null;
    HttpsURLConnection webRequest = null;

    //Prepare OAuth request
    try{
      webRequest = HttpsConnection.getHttpsConnection(AccessTokenUri);
      webRequest.setDoInput(true);
      webRequest.setDoOutput(true);
      webRequest.setConnectTimeout(5000);
      webRequest.setReadTimeout(5000);
      webRequest.setRequestMethod("POST");

      byte[] bytes = new byte[0];
      webRequest.setRequestProperty("content-length", String.valueOf(bytes.length));
      webRequest.setRequestProperty("Ocp-Apim-Subscription-Key", apiKey);
      webRequest.connect();

      DataOutputStream dop = new DataOutputStream(webRequest.getOutputStream());
      dop.write(bytes);
      dop.flush();
      dop.close();

      inSt = webRequest.getInputStream();
      InputStreamReader in = new InputStreamReader(inSt);
      BufferedReader bufferedReader = new BufferedReader(in);
      StringBuffer strBuffer = new StringBuffer();
      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        strBuffer.append(line);
      }

      bufferedReader.close();
      in.close();
      inSt.close();
      webRequest.disconnect();

      // parse the access token
      String token = strBuffer.toString();

      return token;
    }catch (Exception e){
      e.printStackTrace();
    }

    return null;
  }
}
