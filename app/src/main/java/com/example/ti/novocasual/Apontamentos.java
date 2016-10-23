package com.example.ti.novocasual;

import android.location.Location;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Apontamentos extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
/*
                LatLng apontamento1 = new LatLng(-30.1412498, -51.1298999);
                LatLng apontamento2 = new LatLng(-30.1402498, -51.1288999);

                mMap.addMarker(new MarkerOptions().position(apontamento1).title("Apontado no(a) IFRS").snippet("Campus Retinga\nPorto Alegre\nRio Grande do Sul"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(apontamento1, 15));
*/
        mMap.getUiSettings().setMapToolbarEnabled(false);
        new Localizar().execute();
        mMap.setMyLocationEnabled(true);

    }


    protected class Localizar implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private GoogleApiClient m_api;

        public Localizar(){
            super();
        }

        protected void execute(){
            m_api = new GoogleApiClient.Builder(getActivity())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            m_api.connect();
        }

        @Override
        public void onConnected(Bundle bundle){
            //
            LatLng latLng = location();

            mMap.addMarker(new MarkerOptions().position(latLng).title("Eu").snippet("29 anos"));
            mMap.animateCamera(cameraPosition(latLng,15,0,0));
            m_api.disconnect();
        }

        @Override
        public void onConnectionSuspended(int i){

        }

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult){

        }

        public CameraUpdate cameraPosition(LatLng latLng, float zoom, float tilt, float bearing){
            CameraPosition.Builder builder = new CameraPosition.Builder();

            CameraPosition position = builder.target(latLng)
                    .zoom(zoom)
                    .tilt(tilt)
                    .bearing(bearing)
                    .build();

            return CameraUpdateFactory.newCameraPosition(position);
        }

        public LatLng location(){
            Location loc = LocationServices.FusedLocationApi.getLastLocation(m_api);

            return new LatLng(loc.getLatitude(),loc.getLongitude());
        }
        //////////////////////////////////////////////////////////////////////////////

    } // Fim da Classe Localizar
    /////////////////////////////////////////////////////////////////////////////////
} // Fim da Classe Apontamento
