package com.loadrv.dbdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.loadrv.dbdemo.model.adapter.RVAdapter;
import com.loadrv.dbdemo.pojo.ChannelList;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ProgressDialog pDialog;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String url ="https://www.googleapis.com/youtube/v3/search?part=id,snippet&maxResults=20&channelId=UCCq1xDJMBRF61kiOgU90_kw&key=AIzaSyBRLPDbLkFnmUv13B-Hq9rmf0y7q8HOaVs";
    List<ChannelList> rlist = new ArrayList<>();
    RVAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        madapter = new RVAdapter(this,rlist);
        mRecyclerView.setAdapter(madapter);
        sendRequest();

    }

    private void sendRequest() {

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();


// Creating volley request obj
        JsonObjectRequest mchannelReq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                       // hidePDialog();
                        pDialog.dismiss();;

                        try {
                            JSONObject response1 = new JSONObject(response.toString());
                            JSONArray list = response1.getJSONArray("items");    // you have done this wrong


                            // parsing json
                            for(int i = 0; i<list.length();i++) {
                                try {
                                    JSONObject obj = list.getJSONObject(i);

                                    ChannelList mchannel = new ChannelList();
                                    mchannel.setTitle(obj.getJSONObject("snippet").getString("title")); // this will work, but its not the good way

                                    // the better way will be like this below

                                    JSONObject snippetJSONObject = obj.getJSONObject("snippet");
                                    mchannel.setDescription(snippetJSONObject.getString("description"));
                                    mchannel.setDatetime(snippetJSONObject.getString("publishedAt"));
                                    String img = snippetJSONObject.getJSONObject("thumbnails").getJSONObject("default").getString("url");

                                    mchannel.setThumbnailurl(img);

                                    // adding movie to movies array
                                    rlist.add(mchannel);
                                    Log.d("rlist",""+rlist);

                                } catch(Exception e) {
                                    e.printStackTrace();
                                }

                            }



                        } catch(Exception e) {
                            e.printStackTrace();
                        }



                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        madapter.notifyDataSetChanged();

                       /* Gson gson = new Gson();
                        String jsonoutput = response.toString();
                        Type listType = new TypeToken<List<RoyList>>(){}.getType();
                        List<RoyList> posts = gson.fromJson(jsonoutput, listType);
                        Log.d("gsondd", "gsonposts"+posts);*/
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
               // hidePDialog();
                pDialog.dismiss();
            }
        });

        RequestQueue requestueue = Volley.newRequestQueue(this);
        requestueue.add(mchannelReq);
    }
}