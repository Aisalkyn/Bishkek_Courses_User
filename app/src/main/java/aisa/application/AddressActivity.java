package aisa.application;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import aisa.application.models.Branch;
import aisa.application.models.Course;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by admin on 7/17/17.
 */

public class AddressActivity extends AppCompatActivity implements
        OnMapReadyCallback, LocationListener {

    private static final int LOCATION_REQUEST = 100;
    private LatLng position;
    private GoogleMap mMap;
    private Realm realm;
    List<Branch> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setMap();
        realm = Realm.getDefaultInstance();
    }

    private RealmList<Branch> getDataFromDatabase(String title) {
        return realm.copyFromRealm(realm.where(Course.class)
                .equalTo("name", title).findFirst()).getBranches();
    }

    private void setMap() {
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String courseName = getIntent().getStringExtra("course_name");
        if(courseName != null && !courseName.isEmpty()){
            list = getDataFromDatabase(courseName);
            addPoints();
        }
    }

    private void addPoints() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(Branch branch: list){
            Marker marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_location)).position(
                    new LatLng(branch.getLatitude(), branch.getLongitude())));
            builder.include(marker.getPosition());
        }
        CameraUpdate updatePosition = CameraUpdateFactory.newLatLngBounds(builder.build(), 10);
        mMap.moveCamera(updatePosition);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST) {

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        position = new LatLng(location.getLatitude(), location.getLongitude());
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
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
