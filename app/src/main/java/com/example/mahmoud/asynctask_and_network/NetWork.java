/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mahmoud.asynctask_and_network;

import java.io.*;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author mahmoud
 */
public class NetWork {
/*
    public static void main(String[] args) {
        NetWork myNetwork = new NetWork();
        String result = myNetwork.callURL("http://localhost/json.php");
        JSONArray myArray = myNetwork.GetJSONArray(result);
      try {
          for (int i = 0; i < myArray.length(); i++) {
              JSONObject object = myArray.getJSONObject(i);
              int id = object.getInt("ID");
              String name = object.get("Name").toString();
              // String City = object.get("City").toString();

              System.out.println(id + " " + name);
          }
      }catch (Exception ex){

      }
        System.out.println("=======");
        System.out.println(result);

    }
*/
    public String callURL(String myStringURL) {
        StringBuilder sb = new StringBuilder();
        // String sb="";
        // to convert String to URL
        URL url = null;
        //to open Connection with URL
        URLConnection urlConn = null;

        //to get InputStrem From Connection 
        InputStreamReader in = null;
        // to connect inputStrem with bufferedReader
        BufferedReader bufferedReader = null;

        try {
            //1  convert String to URL
            url = new URL(myStringURL);
            //2  Open Connection
            urlConn = url.openConnection();
            //3 check if there are newtork found or not 

            if (urlConn != null && urlConn.getInputStream() != null) {
                // 4 if there are connectio found make connection opent for just 1 minute
                urlConn.setReadTimeout(60 * 1000);
                // 5 get inputStrem from connection with the default charset for URL
                in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
                // 6 connect inputStrem with bufferedReader
                bufferedReader = new BufferedReader(in);
                // 7  Read Data from bufferReader while that we not arrived to final char (-1)
                String cp;
                /*    while ((cp = bufferedReader.readLine()) != null) {
                 sb.append( cp);
                 //    sb.a
                 //sb+=""+((char) cp);
                 }*/
                int x;
                while ((x = in.read()) != -1) {
                    sb.append((char) x);
                }
                bufferedReader.close();
            }

            in.close();
        } catch (Exception e) {

        }

        return sb.toString();

    }

    public JSONArray GetJSONArray(String jsonString) {

        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {

        }
        return jsonArray;

    }
/*
    public JSONObject GetJsonObject(String jsonString) {

        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(jsonString);
        } catch (JSONException e) {

        }
        return jsonObj;

    }*/

}
