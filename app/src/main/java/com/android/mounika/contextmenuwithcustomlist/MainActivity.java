package com.android.mounika.contextmenuwithcustomlist;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.jar.Attributes;

import static android.view.LayoutInflater.*;


public class MainActivity extends AppCompatActivity {

    private static final boolean TODO = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.listcustom);
        String Name[] = {"mounika", "Haseena", "Rajiya", "akhila", "Brahmini"};
        String Number[] = {"1234567891", "2345678910", "3456789101", "4567891011", "56789101112"};
        myAdapter adapter = new myAdapter(Name, Number);
        listView.setAdapter(adapter);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String Number = myAdapter.getItem(info.position).toString();
        if (item.getTitle() == "Call") {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + Number));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)

                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return TODO;
            }
            startActivity(callIntent);
        } else if (item.getTitle() == "SendSMS") {
            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", Number);
            startActivity(smsIntent);
            return true;
        }
    }


        static class myAdapter extends BaseAdapter {
            String Name[] = null;
            static String[] Number = null;

            myAdapter(String[] Name, String Number[]) {
                this.Name = Name;
                this.Number = Number;
            }

            @Override
            public int getCount() {
                return Name.length;

            }

            public static Object getItem(int position) {

                return Number[position];
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = from(MainActivity.this);
                View view = inflater.inflate(R.layout.activity_customlist, null);
                TextView textView = (TextView) view.findViewById(R.id.textView);
                String item1 = Name[position];
                textView.setText(item1);
                TextView textView1 = (TextView) view.findViewById(R.id.textView2);
                String item2 = Number[position];
                textView1.setText(item2);

                return view;
            }

        }

    }


