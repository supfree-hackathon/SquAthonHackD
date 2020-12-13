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

        Stores ecofriendly_solutions = new Stores("ECOFRIENDLY SOLUTIONS",37.98459006043951, 23.733062742151237);
        Stores lidl = new Stores("Lidl Αιγάλεω",37.99907474435186, 23.667714167654946);
        Stores lidl2 = new Stores("Lidl Καλλιθέα",37.96137433874252, 23.697235065581285);
        Stores lidl3 = new Stores("Lidl Ζωγράφου",37.98022756493354, 23.76087630216657);
        Stores livgreen = new Stores("Living Green S.A.",37.984359214333544, 23.736956419710456);
        Stores yogashop = new Stores("Simply Green - Yoga Shop",37.97730147606124, 23.726677068824703);

        storelist.add(lidl);
        storelist.add(lidl2);
        storelist.add(lidl3);
        storelist.add(livgreen);
        storelist.add(yogashop);
        storelist.add(ecofriendly_solutions);

        nameslist.add(lidl.getName());
        nameslist.add(lidl2.getName());
        nameslist.add(lidl3.getName());
        nameslist.add(livgreen.getName());
        nameslist.add(yogashop.getName());
        nameslist.add(ecofriendly_solutions.getName());

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
