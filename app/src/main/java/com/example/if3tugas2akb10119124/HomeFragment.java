package com.example.if3tugas2akb10119124;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import if3tugas2akb10119124.R;

//10119124, Primarazaq Noorshalih Putra Hilmana, IF-3
public class HomeFragment extends Fragment {

    // Inisialisasi Variabel
    FusedLocationProviderClient client;
    private GoogleMap map;
    ArrayList<LatLng> arrayList = new ArrayList<LatLng>();
    LatLng Resto1 = new LatLng(-6.915744007347544, 107.69937679877464);
    LatLng Resto2 = new LatLng(-6.915439461714694, 107.69942662643575);
    LatLng Resto3 = new LatLng(-6.914715130852964, 107.69920773488322);
    LatLng Resto4 = new LatLng(-6.918548392835638, 107.6984239440672);
    LatLng Resto5 = new LatLng(-6.918006448379032, 107.70202012582328);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inisialisasi View
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Inisialisasi map Fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        //Inisialisasi location client
        client = LocationServices.getFusedLocationProviderClient(getActivity());

        // Menambahkan data ke dalam arraylist
        arrayList.add(Resto1);
        arrayList.add(Resto2);
        arrayList.add(Resto3);
        arrayList.add(Resto4);
        arrayList.add(Resto5);

        //async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //When map is loaded
                map = googleMap;
                //Add the favorite name of restaurant nearby
                map.addMarker(new MarkerOptions().position(Resto1).title("Warung Nyi Oneng"));
                map.addMarker(new MarkerOptions().position(Resto2).title("Ayam Penyet Geprek"));
                map.addMarker(new MarkerOptions().position(Resto3).title("Rumah Makan Melati Indah"));
                map.addMarker(new MarkerOptions().position(Resto4).title("Rumah Makan Bundo"));
                map.addMarker(new MarkerOptions().position(Resto5).title("Ayam Goreng Kaori"));
                for (int i=0;i<arrayList.size();i++){
                    map.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                    map.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
                }
            }
        });

        // Cek Kondisi
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Jika diizinkan
            getCurrentLocation();
        }
        else {
            // Jika tidak diizinkan
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION }, 100);
        }

        // mengembalikkan view
        return view;
    }


    @SuppressLint("MissingPermission")
    private void getCurrentLocation()
    {
        // Inisialisasi map fragment
        SupportMapFragment mapFragment=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        // inisialisasi Location manager
        LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        // Cek kondisi
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // ketika gps aktif, maka dapatkan lokasi terkini
            client.getLastLocation().addOnCompleteListener(
                    task -> {
                        // Inisialisasi lokasi
                        Location location = task.getResult();
                        // Cek kondisi
                        if (location != null) {
                            // mengisi longitude dan Latitude jika location tidak kosong
                            mapFragment.getMapAsync(googleMap -> {
                                LatLng lokasi = new LatLng(location.getLatitude(),location.getLongitude());
                                MarkerOptions tanda = new MarkerOptions().position(lokasi).title("Lokasi Anda");
                                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasi,17));
                                googleMap.addMarker(tanda);
                            });
                        }
                        else {
                            // Jika location kosong
                            LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(10000).setFastestInterval(1000).setNumUpdates(1);

                            LocationCallback locationCallback = new LocationCallback() {
                                @Override
                                public void
                                onLocationResult(@NonNull LocationResult locationResult)
                                {
                                    mapFragment.getMapAsync(googleMap -> {
                                        LatLng lokasi = new LatLng(location.getLatitude(),location.getLongitude());
                                        MarkerOptions options = new MarkerOptions().position(lokasi).title("Lokasi Sekarang");
                                        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasi,17));
                                        googleMap.addMarker(options);
                                    });
                                }
                            };

                            // Request location updates
                            client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                        }
                    });
        }
        else {
            // Jika gps belum aktif
            startActivity(
                    new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}