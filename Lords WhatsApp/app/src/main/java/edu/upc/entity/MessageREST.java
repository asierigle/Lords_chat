package edu.upc.entity;

import android.util.JsonReader;
import android.util.Log;
/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
*/
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class MessageREST {

    public static void REST_create(String server, String port, DMessages message) {
        try {
            // create URL object with url:
            // http://server_ip:port/Netbeans_project_name/webresources/
            //   package_name.message
            // open url connection

            //URL url = new URL("http://citmalumnes.upc.es:3306/Dispositius/webresources/practica2.D_Messages/from");
            URL url = new URL("http://localhost:1527/Dispositius/webresources/practica2.D_Messages/from");
            HttpURLConnection ucon = (HttpURLConnection)url.openConnection();

            // set POST request method, DoInput and DoOutput
            // set "application/json" MIME type for both sending and receiving

            ucon.setRequestMethod("POST");
            ucon.setRequestProperty("entity", message.toString());


            ucon.setRequestProperty("text/plain", "application/json; charset=utf-8");
            ucon.setDoInput(false);
            ucon.setDoOutput(true);

            // open PrintWriter attached to url OutputStream
            // create a GsonBuilder with date format
            //   "yyyy-MM-dd'T'HH:mm:ss+02:00"
            // print message as json text with toJson
            // connect to url

            OutputStream output = ucon.getOutputStream();


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

            //URL url = new URL("http://"+server+":"+port+"/D_Dispositius/webresources/practica2.DMessages/from");
            URL url = new URL("http://10.0.2.2:29547/D_Dispositius/webresources/practica2.dmessages/from");
            HttpURLConnection ucon = (HttpURLConnection)url.openConnection();
            ucon.setConnectTimeout(8000);

            // set POST request method, DoInput and DoOutput
            // set "application/json" MIME type for both sending and receiving
            ucon.setRequestMethod("POST");
            ucon.setDoInput(true);
            ucon.setDoOutput(true);
            ucon.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            ucon.setRequestProperty("Accept", "application/json");


            // open PrintWriter attached to url OutputStream
            // create a GsonBuilder with date format
            //   "yyyy-MM-dd'T'HH:mm:ss+02:00"
            // print message with date as json text with toJson
            // connect to url

            PrintWriter writter = new PrintWriter(new OutputStreamWriter(ucon.getOutputStream()));

           /* GsonBuilder builder = new GsonBuilder();
            builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00");
            Gson gson = builder.create();
            gson.toJson(date, writter);*/

            ucon.connect();

            // open BufferedReader attached to url InputStream
            // read response as List<Message> and optionally print to System.out
            // there are two alternatives:
            //   - distinguish between LocalMessage and RemoteMessage and
            //     fill list accordingly. Read section
            //     'Serializing and Deserializing Collection
            //      with Objects of Arbitrary Types'
            //     in Gson user's guide



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

            JsonReader reader = new JsonReader(new InputStreamReader(ucon.getInputStream()));

            reader.beginArray();
            while (reader.hasNext()) {
                // Leer objeto
                messages.add(readMessage(reader));
            }
            reader.endArray();

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