package com.example.kenstate.personvisitstatusupdate;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.kenstate.personvisitstatusupdate.Constants.Baseurl;

public class PersonDetails extends Activity {
    Bundle bundle;
    private ProgressDialog pDialog;
    private String url = null;
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_DATA = "data";
    private int success;
    private PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defaulters);
        new  FetchPersonDetails().execute();


    }
    private class FetchPersonDetails extends AsyncTask<String, String, String> {
        JSONObject response,responsesss;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Display progress bar
            pDialog = new ProgressDialog(PersonDetails.this);
            pDialog.setMessage("Loading Data.. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            HttpJsonParser jsonParser = new HttpJsonParser();
            url=Baseurl+"defaultedclients.php";
            response = jsonParser.makeHttpRequest(url, "GET", null);
            try {
                success = response.getInt(KEY_SUCCESS);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;


        }

        protected void onPostExecute(String result) {
            pDialog.dismiss();
            runOnUiThread(new Runnable() {
                public void run() {

                    ListView listView = (ListView) findViewById(R.id.defaulterslist);
                    if (success == 1) try {

                        final JSONArray personArray = response.getJSONArray(KEY_DATA);
                        final List<Person> personList = new ArrayList<>();
                        //Populate the personDetails list from response
                        for (int i = 0; i < personArray.length(); i++) {
                            Person  ClientDetails = new Person();
                            JSONObject personObj = personArray.getJSONObject(i);
                            ClientDetails.setCccno(personObj.getString("CCCno"));
                            ClientDetails.setMflcode(personObj.getString("mflcode"));
                            ClientDetails.setFacilityname(personObj.getString("facility_name"));
                            ClientDetails.setTca(personObj.getString("last_tca"));
                            personList.add(ClientDetails);
                        }
                        //Create an adapter with the personDetails List and set it to the LstView
                        adapter = new PersonAdapter(personList, getApplicationContext());
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view,
                                                    int position, long id) {
                                // TODO Auto-generated method stub

                                try {
                                   String SelectedString = personArray.get(position).toString();
                                    JSONObject job = new JSONObject(SelectedString);
                                   String mflcode = job.getString("mflcode");
                                   String facility  = job.getString("facility_name");
                                  String tca    = job.getString("last_tca");
                                  String cccno    = job.getString("CCCno");

                                    Toast.makeText(PersonDetails.this,
                                            SelectedString,
                                            Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(PersonDetails.this,
                                            PersonTrace.class);
                                    intent.putExtra("mflcode", mflcode);
                                    intent.putExtra("facility", facility);
                                    intent.putExtra("tca", tca);
                                    intent.putExtra("cccno", cccno);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    else {
                        Toast.makeText(PersonDetails.this,
                                "Some error occurred while loading data",
                                Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }



}