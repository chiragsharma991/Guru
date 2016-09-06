package com.example.genie7.demo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView lvGuruname;
    ArrayList<ModelClassGuru> mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvGuruname = (ListView) findViewById(R.id.lv_guruname);

        new getmunijilist().execute();

        lvGuruname.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), GuruDescriptionActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("name", mlist.get(position).getName());
                bundle.putString("description", mlist.get(position).getDescription());
                bundle.putString("largeimagepath", mlist.get(position).getProfile());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    public Activity getActivity() {
        return this;
    }

    class getmunijilist extends AsyncTask<Boolean, Void, ArrayList<ModelClassGuru>> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            pd = new ProgressDialog(getApplicationContext());
//            pd.setMessage("loading....");
//            pd.show();
        }

        @Override
        protected ArrayList<ModelClassGuru> doInBackground(Boolean... params) {

            JSONObject obj = new JSONObject();
            try {

                String url = getResources().getString(R.string.getmunijilist);
                JSONObject resobj = RestJsonClient.connect(url);
                System.out.println("resobj 1" + resobj);

                mlist = new ArrayList<ModelClassGuru>();

                JSONArray arryObject = resobj.getJSONArray("adsData");

                for (int i = 0; i < arryObject.length(); i++) {
                    ModelClassGuru list = new ModelClassGuru();
                    JSONObject jsonObject = arryObject.getJSONObject(i);

                    list.setName(jsonObject.getString("name"));
                    list.setProfile(jsonObject.getString("largeimagepath"));
                    list.setDescription(jsonObject.getString("description"));

                    mlist.add(list);

                }


            } catch (JSONException e) {

                e.printStackTrace();
            }


            return mlist;
        }

        protected void onPostExecute(ArrayList<ModelClassGuru> eventModels) {


            super.onPostExecute(eventModels);
//            pd.dismiss();
            try {
                lvGuruname.setAdapter(new CustomAdapterGuru(MainActivity.this, mlist));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
