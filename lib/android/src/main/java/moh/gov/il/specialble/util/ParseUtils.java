package moh.gov.il.specialble.util;

import android.content.Context;

import moh.gov.il.crypto.Constants;
import moh.gov.il.crypto.Contact;
import moh.gov.il.crypto.MatchResponse;
import moh.gov.il.crypto.utilities.BytesUtils;
import moh.gov.il.crypto.utilities.Hex;
import moh.gov.il.specialble.db.DBClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hagai on 17/05/2020.
 */
public class ParseUtils {

    public static final String INFECTED = "days";
    public static final String START_DAY = "startDay";

    public static String infectedDbToJson(Map<Integer, Map<Integer, ArrayList<byte[]>>> infectedDb) {
        JSONObject root = new JSONObject();
        JSONArray rootInfected = new JSONArray();

        try {
            Object[] keySetArray = infectedDb.keySet().toArray();
            Arrays.sort(keySetArray); // Make sure the keys are in ascending order
            int today = (int) keySetArray[keySetArray.length - 1];
            int startDay = today - moh.gov.il.crypto.Constants.NUM_OF_DAYS; // We subtract 14 from today, because we want to go 15 days back and today is the 15th day.
            root.put(START_DAY, startDay);
            for (int i = startDay; i <= today; i++) {

                Map<Integer, ArrayList<byte[]>> epochs = infectedDb.get(i);
                JSONArray rootInfectedEpochs = new JSONArray();
                if (epochs != null) {
                    Object[] epochKeySetArray = epochs.keySet().toArray();
                    for (int x = 0; x < moh.gov.il.crypto.Constants.NUM_OF_EPOCHS; x++) {
                        int epocKey = -1;
                        if (x < epochKeySetArray.length) {
                            epocKey = (int) epochKeySetArray[x];
                        }
                        ArrayList<byte[]> ephs = epochs.get(epocKey);
                        JSONArray rootInfectedEpochsInnerLevel = new JSONArray();

                        if (ephs != null) {
                            for (int j = 0; j < ephs.size(); j++) {
                                String converted = Hex.toHexString(ephs.get(j), null);
                                rootInfectedEpochsInnerLevel.put(converted);
                            }
                        }
                        rootInfectedEpochs.put(rootInfectedEpochsInnerLevel);
                    }
                } else {
                    for (int x = 0; x < Constants.NUM_OF_EPOCHS; x++) {
                        JSONArray rootInfectedEpochsInnerLevel = new JSONArray();
                        rootInfectedEpochs.put(rootInfectedEpochsInnerLevel);
                    }
                }
                rootInfected.put(rootInfectedEpochs);
            }
            root.put(INFECTED, rootInfected);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return root.toString();
    }

    public static Map<Integer, Map<Integer, ArrayList<byte[]>>> extractInfectedDbFromJson(String epochs, Context applicationContext) {
        Map<Integer, Map<Integer, ArrayList<byte[]>>> infectedDb = new HashMap<>();
        try {
            JSONObject jsonRes = null;

            if (epochs != null)
                jsonRes = new JSONObject(epochs);
//            else
//                jsonRes = new JSONObject(loadJSONFromAsset(applicationContext)); ///for testing

            if(jsonRes != null) {

                JSONArray infected = jsonRes.getJSONArray(INFECTED);
                int startDay = jsonRes.getInt(START_DAY);

                for (int i = 0; i < infected.length(); i++, startDay++) {
                    infectedDb.put(startDay, new HashMap<Integer, ArrayList<byte[]>>());
                    JSONArray epochsArray = infected.getJSONArray(i);

                    for (int j = 0; j < epochsArray.length(); j++) {
                        JSONArray eph = epochsArray.getJSONArray(j);
                        infectedDb.get(startDay).put(j, new ArrayList<byte[]>());
                        for (int k = 0; k < eph.length(); k++) {
                            String epoc = eph.getString(k);
                            byte[] epocBytes = Hex.hexStringToByteArray(epoc);
                            infectedDb.get(startDay).get(j).add(epocBytes);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return infectedDb;
    }

//    public static String loadJSONFromAsset(Context ctx) {
//        String json = null;
//        try {
//            InputStream is = ctx.getResources().openRawResource(R.raw.infected);//ctx.getAssets().open("infected.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }

    public static void loadDatabase(Context ctx, String jsonArray) {
        String json = null;
        try {
            JSONArray dbArray = new JSONArray();
            if (jsonArray != null && jsonArray.length() > 0) {
                dbArray = new JSONArray(jsonArray);
            }
//            else {
//
//                InputStream is = ctx.getResources().openRawResource(R.raw.outputcontacts);
//                int size = is.available();
//                byte[] buffer = new byte[size];
//                is.read(buffer);
//                is.close();
//                json = new String(buffer, "UTF-8");
//
//                dbArray = new JSONArray(json);
//            }

            for (int i = 0; i < dbArray.length(); i++)
            {
                JSONObject jo = dbArray.getJSONObject(i);

                byte[] otherEphemeralId = Hex.hexStringToByteArray(jo.getString("ephemeral_id"));
                byte[] rssi = BytesUtils.numToBytes(jo.getInt("rssi"),4);
                byte[] ownLocation = Hex.hexStringToByteArray(jo.getString("geohash"));
                int time = jo.getInt("timestamp");
                double lat = 0;
                double lon = 0;
                if(jo.has("lat"))
                     lat = jo.getDouble("lat");
                if(jo.has("lon"))
                    lon = jo.getDouble("lon");
                DBClient.getInstance(ctx).storeContact(new Contact(otherEphemeralId, rssi, time, ownLocation, lat, lon));
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String parseResultToJson(List<MatchResponse> matches)
    {
        JSONArray result = new JSONArray();
        try
        {
            for (MatchResponse match : matches)
            {
                result.put(match.toJsonObject());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return result.toString();

    }

}
