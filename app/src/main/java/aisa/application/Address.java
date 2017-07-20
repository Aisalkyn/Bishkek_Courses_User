package aisa.application;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import aisa.application.adapters.CustomGrid;
import aisa.application.models.Branches;
import aisa.application.models.Categories;

/**
 * Created by admin on 7/17/17.
 */

public class Address extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, LocationListener {

    private LatLng position;
    private GoogleMap mMap;
    private List<Branches> branches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapClick(LatLng point) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().icon(
                BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_location))
                .position(position = point));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        if (mMap != null) {
            Location location = getLocation();
            if (location != null) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)).position(position).title("Current location"));
                CameraPosition myPosition = new CameraPosition.Builder().target(latLng).zoom(14).bearing(90).tilt(30).build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(myPosition));
            }
        }
    }

    public Location getLocation() {
        Location location = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }else
        try {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            mMap.setMyLocationEnabled(true);
            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                if (isNetworkEnabled) {


                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            100,
                            10000, this);
                Log.d("Network", "Network Enabled");
                if (locationManager != null) {
                    location = locationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        position = new LatLng(location.getLatitude(), location.getLongitude());
                    }
                }
            }
            // if GPS Enabled get lat/long using GPS Services
            if (isGPSEnabled) {
                if (location == null) {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            100,
                            10000, this);
                    Log.d("GPS", "GPS Enabled");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            position = new LatLng(location.getLatitude(), location.getLongitude());
                        }
                    }
                }
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return location;
}


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            getLocation();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(List<Branches> branches) {
        this.branches = branches;
        EventBus.getDefault().unregister(this);
    }

}
