package com.example.replace;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = MapsActivity.class.getName();

    private GoogleMap mMap;

    private List<Address> addresses;
    private Intent intent;
    private boolean showAddress;
    private double lat;
    private double lon;
    private String name;

    private TextView textAdr2;
    private TextView storeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        textAdr2 = (TextView) findViewById(R.id.textAdr2);
        storeName = (TextView) findViewById(R.id.storeName);

        intent = getIntent();
        name = intent.getStringExtra("Name");
        lat = intent.getDoubleExtra("Lat",0.00);
        lon = intent.getDoubleExtra("Lon",0.00);


        storeName.setText(name);
        //startIntentService(lat,lon);
        getAddress(lat,lon);
        initMap();

    }

    public void initMap(){
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //******************
        //SET MAP
        //******************
        mMap = googleMap;

        LatLng markerInfo = new LatLng(lat, lon);
        Marker mMarker = mMap.addMarker(new MarkerOptions().position(markerInfo));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerInfo, 17f));


        //If fetching address was successfull show address data
        if(showAddress){

            String address = addresses.get(0).getAddressLine(0); //0 to obtain first possible address
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();

            String title = address + ", " + city + ", " + state;

            mMarker.setTitle(title);

            textAdr2.setText(title + "\n" + country + ", " + postalCode);

        }
        else{
            mMarker.setTitle("Lat: "+lat+", Lon: "+lon);
        }
    }

    //Geocoder Location Address
    public void getAddress(double lat, double lon){
        String errorMessage = "";
        addresses=null;

        Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
        try{

            addresses = geocoder.getFromLocation(lat, lon, 1); //1 num of possible location returned

        } catch (IOException ioException) {
            // Catch network or other I/O problems.
            errorMessage = "service not available";
            Log.e(TAG, errorMessage, ioException);


        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            errorMessage = "invalid lat lon";
            Log.e(TAG, errorMessage + ". " +
                    "Latitude = " + lat +
                    ", Longitude = " +
                    lon, illegalArgumentException);
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = "No adrress found";
                Log.e(TAG, errorMessage);
            }
            //deliverResultToReceiver(Constants.FAILURE_RESULT, errorMessage);
        } else {

            showAddress = true;
            //Toast.makeText(addMarkerActivity.this, title, Toast.LENGTH_LONG).show();
        }

    }
}