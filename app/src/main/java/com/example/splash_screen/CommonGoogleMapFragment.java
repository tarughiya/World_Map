package com.example.splash_screen;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class CommonGoogleMapFragment extends Fragment implements LocationListener, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback{
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private LocationRequest mLocationRequest;
    private LocationManager locationManager;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    Marker makerEvent=null;
    String latitudeValue ;
    String longitudeValue ;
    String mapTypeValue = "normal";
    Button normal,satellite,hybrid;

    View view;
    private Context context= getContext();

    public static CommonGoogleMapFragment newInstance(String latValue,String longValue) {
        Bundle args = new Bundle();
        CommonGoogleMapFragment fragment = new CommonGoogleMapFragment();
        fragment.setArguments(args);
        fragment.latitudeValue = latValue;
        fragment.longitudeValue = longValue;
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.map, container, false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        isLocationEnabled();
        if (!isLocationEnabled()) {
            final Dialog dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(R.layout.dialog_location);
            dialog.findViewById(R.id.button_ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    dialog.dismiss();
                }
            });
            dialog.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
        mapFragment.getMapAsync(this);
        findView();
        return view;
    }

    protected boolean isLocationEnabled() {
        String le = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) getActivity().getSystemService(le);
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
    private void findView() {

        normal = view.findViewById(R.id.normal);
        satellite = view.findViewById(R.id.satellite);
        hybrid = view.findViewById(R.id.hybrid);
        normal.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
        satellite.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
        hybrid.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));

//        fragment11 = (MapFragment)getChildFragmentManager().findFragmentById(R.id.map);
//        fragment11.getMapAsync(this);

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapTypeValue = "normal";
              //  callMap();
                onNormalMap();
                normal.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
                satellite.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
                hybrid.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));

            }
        });
        satellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapTypeValue = "satellite";
               // callMap();
                onSatelliteMap();
                satellite.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
                normal.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
                hybrid.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));

            }
        });
        hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapTypeValue = "hybrid";
                //callMap();
                onHybridMap();
                hybrid.setBackgroundColor(getActivity().getResources().getColor(R.color.blue));
                satellite.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
                normal.setBackgroundColor(getActivity().getResources().getColor(R.color.gray));
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

      /*  LatLng sydney = new LatLng(finallat, finallong);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Find Location."));*/
      Toast.makeText(getActivity(),"enter",Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setMyLocationEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        Double eventLocatonLat= Double.valueOf(latitudeValue);
        Double eventLocatonLong= Double.valueOf(longitudeValue);
        LatLng latLngtwo = new LatLng(eventLocatonLat, eventLocatonLong);
        makerEvent=mMap.addMarker(new MarkerOptions().position(new
                LatLng(eventLocatonLat,eventLocatonLong)).icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngtwo));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(5));

      /*  MapRadar mapRadar = new MapRadar(mMap, latLngtwo, MapsActivity.this);
        mapRadar.withDistance(2000);
        mapRadar.withOuterCircleStrokeColor(Color.parseColor("#fccd29"));
        mapRadar.withOuterCircleStrokewidth(7);
        mapRadar.withOuterCircleTransparency(0.4f);
        mapRadar.withClockWiseAnticlockwise(true);      //enable both side rotation
        mapRadar.withClockwiseAnticlockwiseDuration(2);
        //withClockwiseAnticlockwiseDuration(duration), here duration denotes how much cycles should animation makes in
        //one direction
        mapRadar.withOuterCircleFillColor(Color.parseColor("#12000000"));
        mapRadar.withRadarColors(Color.parseColor("#00fccd29"), Color.parseColor("#fffccd29"));
        //withRadarColors() have two parameters, startColor and tailColor respectively
        //startColor should start with transparency, here 00 in front of fccd29 indicates fully transparent
        //tailColor should end with opaqueness, here f in front of fccd29 indicates fully opaque
        mapRadar.withRadarSpeed(5); //controls radar speed
        mapRadar.withRadarTransparency(0.4f);
        mapRadar.startRadarAnimation(); */     //in onMapReadyCallBack


    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if(getActivity() !=null) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation=location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(getActivity().getApplicationContext(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }
    public void onNormalMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void onSatelliteMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }
    public void onHybridMap() {
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

}