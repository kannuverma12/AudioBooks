package com.kv.audible;

import com.kv.audible.texttospeech.TTSSampleNew;
import java.io.BufferedReader;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class ConvertTextToSound {

  static List<NameValuePair> headers;

  public static void main(String[] args) {
    headers = new ArrayList<NameValuePair>();
    final ConvertTextToSound recorder = new ConvertTextToSound();
    String searchQuery = "We’re going to use a sample data set from the UC Irvine Machine Learning Reposi";
    Integer pageNumber = 1;
    recorder.convertToSound (searchQuery, pageNumber);
  }

  public static void convertToSound(String pageText, Integer pageNumber) {
    try {
      String token = null;

      headers.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded"));
      headers.add(new BasicNameValuePair("Ocp-Apim-Subscription-Key", "0bb5bf28e29e474cb765b28b4a370adf"));

      token = makePost("https://api.cognitive.microsoft.com/sts/v1.0/issueToken", headers, null);

      headers.clear();

      headers.add(new BasicNameValuePair("Content-type", "audio/wav"));
      headers.add(new BasicNameValuePair("codec", "audio/pcm"));
      headers.add(new BasicNameValuePair("samplerate", "44100"));
      headers.add(new BasicNameValuePair("Authorization","Bearer "+token));
      String searchQuery = "";

      long c = System.currentTimeMillis();
//      searchQuery = "We’re going to use a sample data set from the UC Irvine Machine Learning Reposi‐ tory, which is a fantastic source for interesting (and free) data sets for research and education. The data set we’ll analyze was curated from a record linkage study per‐ formed at a German hospital in 2010, and it contains several million pairs of patient records that were matched according to several different criteria, such as the patient’s name (first and last), address, and birthday. Each matching field was assigned a numerical score from 0.0 to 1.0 based on how similar the strings were, and the data was then hand-labeled to identify which pairs represented the same person and which did not. The underlying values of the fields that were used to create the data set were removed to protect the privacy of the patients. Numerical identifiers, the match scores for the fields, and the label for each pair (match versus nonmatch) were pub‐ lished for use in record linkage research.";
//      searchQuery += "We’re going to use a sample data set from the UC Irvine Machine Learning Reposi‐ tory, which is a fantastic source for interesting (and free) data sets for research and education. The data set we’ll analyze was curated from a record linkage study per‐ formed at a German hospital in 2010, and it contains several million pairs of patient records that were matched according to several different criteria, such as the patient’s name (first and last), address, and birthday. Each matching field was assigned a numerical score from 0.0 to 1.0 based on how similar the strings were, and the data was then hand-labeled to identify which pairs represented the same person and which did not. The underlying values of the fields that were used to create the data set were removed to protect the privacy of the patients. Numerical identifiers, the match scores for the fields, and the label for each pair (match versus nonmatch) were pub‐ lished for use in record linkage research.";
//      searchQuery += "We’re going to use a sample data set from the UC Irvine Machine Learning Reposi‐ tory, which is a fantastic source for interesting (and free) data sets for research and education. The data set we’ll analyze was curated from a record linkage study per‐ formed at a German hospital in 2010, and it contains several million pairs of patient records that were matched according to several different criteria, such as the patient’s name (first and last), address, and birthday. Each matching field was assigned a numerical score from 0.0 to 1.0 based on how similar the strings were, and the data was then hand-labeled to identify which pairs represented the same person and which did not. The underlying values of the fields that were used to create the data set were removed to protect the privacy of the patients. Numerical identifiers, the match scores for the fields, and the label for each pair (match versus nonmatch) were pub‐ lished for use in record linkage research.";
//
//      searchQuery += "We’re going to use a sample data set from the UC Irvine Machine Learning Reposi‐ tory, which is a fantastic source for interesting (and free) data sets for research and education. The data set we’ll analyze was curated from a record linkage study per‐ formed at a German hospital in 2010, and it contains several million pairs of patient records that were matched according to several different criteria, such as the patient’s name (first and last), address, and birthday. Each matching field was assigned a numerical score from 0.0 to 1.0 based on how similar the strings were, and the data was then hand-labeled to identify which pairs represented the same person and which did not. The underlying values of the fields that were used to create the data set were removed to protect the privacy of the patients. Numerical identifiers, the match scores for the fields, and the label for each pair (match versus nonmatch) were pub‐ lished for use in record linkage research.";
//      searchQuery += "We’re going to use a sample data set from the UC Irvine Machine Learning Reposi‐ tory, which is a fantastic source for interesting (and free) data sets for research and education. The data set we’ll analyze was curated from a record linkage study per‐ formed at a German hospital in 2010, and it contains several million pairs of patient records that were matched according to several different criteria, such as the patient’s name (first and last), address, and birthday. Each matching field was assigned a numerical score from 0.0 to 1.0 based on how similar the strings were, and the data was then hand-labeled to identify which pairs represented the same person and which did not. The underlying values of the fields that were used to create the data set were removed to protect the privacy of the patients. Numerical identifiers, the match scores for the fields, and the label for each pair (match versus nonmatch) were pub‐ lished for use in record linkage research.";
//      searchQuery += "We’re going to use a sample data set from the UC Irvine Machine Learning Reposi‐ tory, which is a fantastic source for interesting (and free) data sets for research and education. The data set we’ll analyze was curated from a record linkage study per‐ formed at a German hospital in 2010, and it contains several million pairs of patient records that were matched according to several different criteria, such as the patient’s name (first and last), address, and birthday. Each matching field was assigned a numerical score from 0.0 to 1.0 based on how similar the strings were, and the data was then hand-labeled to identify which pairs represented the same person and which did not. The underlying values of the fields that were used to create the data set were removed to protect the privacy of the patients. Numerical identifiers, the match scores for the fields, and the label for each pair (match versus nonmatch) were pub‐ lished for use in record linkage research.";
      //searchQuery += "We’re going to use a sample data set from the UC Irvine Machine Learning Reposi‐ tory, which is a fantastic source for interesting (and free) data sets for research and education. The data set we’ll analyze was curated from a record linkage study per‐ formed at a German hospital in 2010, and it contains several million pairs of patient records that were matched according to several different criteria, such as the patient’s name (first and last), address, and birthday. Each matching field was assigned a numerical score from 0.0 to 1.0 based on how similar the strings were, and the data was then hand-labeled to identify which pairs represented the same person and which did not. The underlying values of the fields that were used to create the data set were removed to protect the privacy of the patients. Numerical identifiers, the match scores for the fields, and the label for each pair (match versus nonmatch) were pub‐ lished for use in record linkage research.";
      // searchQuery += "We’re going to use a sample data set from the UC Irvine Machine Learning Reposi‐ tory, which is a fantastic source for interesting (and free) data sets for research and education. The data set we’ll analyze was curated from a record linkage study per‐ formed at a German hospital in 2010, and it contains several million pairs of patient records that were matched according to several different criteria, such as the patient’s name (first and last), address, and birthday. Each matching field was assigned a numerical score from 0.0 to 1.0 based on how similar the strings were, and the data was then hand-labeled to identify which pairs represented the same person and which did not. The underlying values of the fields that were used to create the data set were removed to protect the privacy of the patients. Numerical identifiers, the match scores for the fields, and the label for each pair (match versus nonmatch) were pub‐ lished for use in record linkage research.";

      searchQuery = pageText;

      if(searchQuery!=null && !searchQuery.equals("")) {
        System.out.println("\nsearchQuery for api: "+searchQuery+"\n");
        StringBuffer convertToVoice = new StringBuffer();
        convertToVoice.append ( searchQuery );

        if(!convertToVoice.toString().equals("")  || convertToVoice.toString().length() != 0){
          System.out.println("Converting to voice...");
          //System.out.println("Snippet = "+convertToVoice.toString()+" \nlength : "+convertToVoice.length());
          //if(convertToVoice.length() > 250)
            //convertToVoice.setLength(250);
          System.out.println("Snippet = "+convertToVoice.toString()+" \nlength : "+convertToVoice.length());
          TTSSampleNew.tts(convertToVoice.toString(), pageNumber);
        }

      }else{
        System.out.println("Audio is not recognizable. Please try again...");
      }
    }catch (Exception e){
      e.printStackTrace();
    }

  }



  public static String makePost(String url,List<NameValuePair> headList,File data){
    try
    {
      HttpClient httpclient = new DefaultHttpClient();

      URIBuilder builder = new URIBuilder(url);
      URI uri = builder.build();
      HttpPost request = new HttpPost(uri);

      for(int x=0;headList!=null && x<headList.size();x++) {
        request.setHeader(headList.get(x).getName(), headList.get(x).getValue());
      }
	           /*request.setHeader("Content-Type", "application/x-www-form-urlencoded");
	           request.setHeader("Ocp-Apim-Subscription-Key", "5187c84ce7164289bd6cbc9b9e13ca52");*/
      //System.out.println("after setting of header in func");


      // Request body
      if(data!=null && data.length()>0)
      {
        //StringEntity reqEntity = new StringEntity(data);
        FileEntity reqEntity=new FileEntity(data);
        request.setEntity(reqEntity);
      }

      HttpResponse response = httpclient.execute(request);
      HttpEntity entity = response.getEntity();
      //System.out.println("after getting of response");

      if (entity != null) {
        String sResp = EntityUtils.toString(entity);
        //System.out.println("resp\n"+sResp);
        return sResp;
      }

    } catch (Exception e) {
      System.out.println("caught exception inside MAKE POST");
      e.printStackTrace();
    }
    return null;
  }


  public static String makeGet(String url,List<NameValuePair> headList){
    try
    {
      System.out.println("inside get for URL: "+url);


      URL obj = new URL(url);
      HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

      // optional default is GET
      con.setRequestMethod("GET");
      for(int x=0; headList!=null && x<headList.size() ;x++)
      {
        con.setRequestProperty(headList.get(x).getName(), headList.get(x).getValue());
      }

      //add request header
      //con.setRequestProperty("User-Agent", USER_AGENT);

      int responseCode = con.getResponseCode();

      System.out.println("[Get]Response Code : " + responseCode);

      BufferedReader in = new BufferedReader(
          new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

      //print result
      System.out.println("[Get]response from textual web search:\n"+response.toString());
      return response.toString();

    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return null;
  }
}