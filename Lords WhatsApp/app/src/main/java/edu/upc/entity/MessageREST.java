package edu.upc.entity;


import android.util.Log;

import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



import org.json.JSONTokener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;


public class MessageREST {

    public static void REST_create(String server, String port, DMessages message) {
        try {
            // create URL object with url:
            // http://server_ip:port/Netbeans_project_name/webresources/
            //   package_name.message
            // open url connection

            //URL url = new URL("http://10.0.2.2:29547/D_Dispositius/webresources/practica2.dmessages/from");
            URL url = new URL("http://"+server+":"+port+"/D_Dispositius/webresources/practica2.dmessages/");

            HttpURLConnection ucon = (HttpURLConnection)url.openConnection();
            ucon.setConnectTimeout(8000);

            // set POST request method, DoInput and DoOutput
            // set "application/json" MIME type for both sending and receiving
            try {
                ucon.setRequestMethod("POST");
            } catch (ProtocolException e) {
                System.out.println("Failed to set to POST");
                e.printStackTrace();
            }

            ucon.setDoOutput(true);
            ucon.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            ucon.setRequestProperty("Accept", "application/json");

            JSONObject messageAsJson = new JSONObject();
            messageAsJson.put("content", message.getContent());
            messageAsJson.put("date", message.getDate());
            messageAsJson.put("userSender", message.getUserSender());
            messageAsJson.put("id", message.getId());
            System.out.println(message.toString());

            String urlParameters = messageAsJson.toString();

            DataOutputStream writter = new DataOutputStream(ucon.getOutputStream());
            writter.writeBytes(urlParameters);
            writter.flush();
            writter.close();

            ucon.connect();
            int tmp = ucon.getResponseCode();
            Log.i("Tests", "TMP is " + tmp);
            if(tmp == HttpURLConnection.HTTP_OK)
            {
                Log.i("Tests", "Connection ok");
            }
            else
            {
                Log.i("Tests", "Connection failed");
            }

            // open BufferedReader attached to url InputStream
            // read response and optionally print to System.out
            //TODO ...

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<DMessages> REST_retrieveFromDate(String server, String port, Date date, String nick) {
        try {
            // create URL object with url:
            // http://server_ip:port/Netbeans_project_name/webresources/
            //   package_name.message/from
            // open url connection

            //10.0.2.2
            //8080

            URL url = new URL("http://"+server+":"+port+"/D_Dispositius/webresources/practica2.dmessages/from");
            //URL url = new URL("http://10.0.2.2:29547/D_Dispositius/webresources/practica2.dmessages/from");
            HttpURLConnection ucon = (HttpURLConnection)url.openConnection();
            ucon.setConnectTimeout(8000);

            // set POST request method, DoInput and DoOutput
            // set "application/json" MIME type for both sending and receiving
            try {
                ucon.setRequestMethod("POST");
            } catch (ProtocolException e) {
                System.out.println("Failed to set to POST");
                e.printStackTrace();
            }
            ucon.setDoInput(true);
            ucon.setDoOutput(true);
            ucon.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            ucon.setRequestProperty("Accept", "application/json");


            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+01:00");

            JSONObject dateAsObj = new JSONObject();
            dateAsObj.put("date", date);
            System.out.println(dateAsObj.toString());

            String urlParameters = dateAsObj.toString();


            DataOutputStream writter = new DataOutputStream(ucon.getOutputStream());
            writter.writeBytes(urlParameters);
            writter.flush();
            writter.close();

            ucon.connect();


            int tmp = ucon.getResponseCode();
            Log.i("Tests", "TMP is " + tmp);
            if(tmp == HttpURLConnection.HTTP_OK)
            {
                Log.i("Tests", "Connection ok");
            }
            else
            {
                Log.i("Tests", "Connection failed");
            }

            ArrayList<DMessages> messages = new ArrayList<>();

            BufferedReader in = new BufferedReader(new InputStreamReader(ucon.getInputStream()));
            for(JsonElement el: new JsonParser().parse(in).getAsJsonArray())
            {
                messages.add(new Gson().fromJson(el, DMessages.class));
            }


            return messages;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public DMessages readMessage(JsonReader reader) throws IOException {
        String content = null;
        Date date = null;
        String sender = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "content":
                    content = reader.nextString();
                    break;
                case "user_sender":
                    sender = reader.nextString();
                    break;
                case "date":
                    date = new Date();
                    date.parse(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return new DMessages(0, content, sender, date);
    }
}