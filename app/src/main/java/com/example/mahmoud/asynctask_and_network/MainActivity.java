package com.example.mahmoud.asynctask_and_network;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements homeFragment.MyListener {
    ArrayList<String> myList = new ArrayList<String>();
    splashScreen splashScreen;
    homeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    new MyTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void FillData() {
        homeFragment.FillArrayList(myList);
    }

    public class MyTask extends AsyncTask<Void, Void, Void> {
        NetWork myNetWork = new NetWork();

        @Override
        protected void onPreExecute() {
            //  super.onPreExecute();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            splashScreen = new splashScreen();
            transaction.add(R.id.MyFreeSpace, splashScreen);
            transaction.commit();
        }


        @Override
        protected Void doInBackground(Void... params) {
            String result = myNetWork.callURL("http://coders.ps/json.php");
            JSONArray myArray = myNetWork.GetJSONArray(result);
            try {
                Thread.sleep(3000);
                for (int i = 0; i < myArray.length(); i++) {
                    JSONObject object = myArray.getJSONObject(i);
                    //  int id = object.getInt("ID");
                    String name = object.get("Name").toString();
                    // String City = object.get("City").toString();
                    myList.add(name);
                    //    System.out.println(id + " " + name);
                }
            } catch (Exception ex) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //   super.onPostExecute(aVoid);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            homeFragment = new homeFragment();
            transaction.replace(R.id.MyFreeSpace, homeFragment);
            transaction.commit();

        }
    }
}
