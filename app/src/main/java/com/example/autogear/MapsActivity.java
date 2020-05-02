package com.example.autogear;


import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String TAG ;
    Address myAddress ;
    Address myAddress1 ;
    Intent getMapIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        myAddress = null;
        myAddress1 = null;
        TAG = "error";
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getMapIntent = getIntent();
        String location = getMapIntent.getStringExtra("start");
        List<Address> addressList = null;
        MarkerOptions markerOptions = new MarkerOptions();
        Log.d("location = ", location);

        if (!location.equals("")) {
            Geocoder geocoder = new Geocoder(getApplicationContext());
            try {
                addressList = geocoder.getFromLocationName(location, 5);

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addressList != null) {
                for (int i = 0; i < addressList.size(); i++) {
                    myAddress = addressList.get(i);
                    LatLng latLng = new LatLng(myAddress.getLatitude(), myAddress.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                }
            }

        }

        String destination = getMapIntent.getStringExtra("end");

        List<Address> destinationList = null;

        if (!destination.equals("")) {
            Geocoder geocoder = new Geocoder(getApplicationContext());
            try {
                destinationList = geocoder.getFromLocationName(destination, 5);

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (destinationList != null) {
                for (int i = 0; i < destinationList.size(); i++) {
                    myAddress1 = destinationList.get(i);
                    LatLng latLng1 = new LatLng(myAddress1.getLatitude(), myAddress1.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng1).title(destination));

                }
            }
        }

//        LatLng barcelona = new LatLng(41.385064, 2.173403);
//        mMap.addMarker(new MarkerOptions().position(barcelona).title("Marker in Barcelona"));
//
//        LatLng madrid = new LatLng(40.416775, -3.70379);
//        mMap.addMarker(new MarkerOptions().position(madrid).title("Marker in Madrid"));

        //Define list to get all latlng for the route

        List<LatLng> path = new ArrayList();


        //Execute Directions API request
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDKmoA5ADgtJPM_EyXXOhItIRCN7KczvpY")
                .build();
        DirectionsApiRequest req = DirectionsApi.getDirections(context, myAddress.getLatitude() + "," + myAddress.getLongitude(), myAddress1.getLatitude() + "," + myAddress1.getLongitude());
        try {
            DirectionsResult res = req.await();

            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.length > 0) {
                DirectionsRoute route = res.routes[0];

                if (route.legs != null) {
                    for (int i = 0; i < route.legs.length; i++) {
                        DirectionsLeg leg = route.legs[i];
                        if (leg.steps != null) {
                            for (int j = 0; j < leg.steps.length; j++) {
                                DirectionsStep step = leg.steps[j];
                                if (step.steps != null && step.steps.length > 0) {
                                    for (int k = 0; k < step.steps.length; k++) {
                                        DirectionsStep step1 = step.steps[k];
                                        EncodedPolyline points1 = step1.polyline;
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                            for (com.google.maps.model.LatLng coord1 : coords1) {
                                                path.add(new LatLng(coord1.lat, coord1.lng));
                                            }
                                        }
                                    }
                                } else {
                                    EncodedPolyline points = step.polyline;
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        List<com.google.maps.model.LatLng> coords = points.decodePath();
                                        for (com.google.maps.model.LatLng coord : coords) {
                                            path.add(new LatLng(coord.lat, coord.lng));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }

        //Draw the polyline
        if (path.size() > 0) {
            PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(10);
            mMap.addPolyline(opts);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myAddress1.getLatitude(), myAddress1.getLongitude()), 6));
    }
}
