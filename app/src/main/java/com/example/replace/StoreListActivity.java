package com.example.replace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StoreListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> nameslist;
    private ArrayList<Stores> storelist;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storelist);

        nameslist = new ArrayList<String>();
        storelist = new ArrayList<Stores>();
        listView = (ListView) findViewById(R.id.listview);

        Stores lidl = new Stores("Lidl Athina",37.97701,23.69480);
        Stores lidl2 = new Stores("Lidl Athina2",37.96334,23.69686);

        storelist.add(lidl);
        storelist.add(lidl2);

        nameslist.add(lidl.getName());
        nameslist.add(lidl2.getName());

        arrayAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameslist);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(StoreListActivity.this,"clicked item:"+i+" "+nameslist.get(i).toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StoreListActivity.this, MapsActivity.class);
                intent.putExtra("Name",storelist.get(i).getName());
                intent.putExtra("Lat",storelist.get(i).getLan());
                intent.putExtra("Lon",storelist.get(i).getLon());
                startActivity(intent);
            }
        });

    }
}
