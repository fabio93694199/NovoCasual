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

//public class Apontamentos extends SupportMapFragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

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
                //LatLng apontamento3 = new LatLng(tete1,tete2);
                //-30.1409121,-51.1303575
                //-30.1409601,-51.1301575
                //-30.1410601,-51.1294799
                //-30.141267,-51.128761
                //-30.141123, -51.130382
                //-30.140694, -51.130084
                //-30.141135, -51.130183
                //-30.141027, -51.130095
                mMap.addMarker(new MarkerOptions().position(apontamento1).title("Apontado no(a) IFRS").snippet("Campus Retinga\nPorto Alegre\nRio Grande do Sul"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(apontamento1, 15));

                mMap.addMarker(new MarkerOptions().position(apontamento2).title("Apontado no(a) IFRS").snippet("Campus Retinga\nPorto Alegre\nRio Grande do Sul"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(apontamento2, 15));

                //mMap.addMarker(new MarkerOptions().position(apontamento3).title("Apontado no(a) IFRS").snippet("Campus Retinga\nPorto Alegre\nRio Grande do Sul"));
                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(apontamento3, 15));
*/
        mMap.getUiSettings().setMapToolbarEnabled(false);
        new Localizar().execute();
        mMap.setMyLocationEnabled(true);

    }


    protected class Localizar implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener , GoogleMap.InfoWindowAdapter {
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
            try {
                new Thread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mMap.addMarker(new MarkerOptions().position(new LatLng(-30.1409601,-51.1301575)).title("Maria A.").snippet("Campus Retinga\nPorto Alegre\nRio Grande do Sul"));
            try {
                new Thread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mMap.addMarker(new MarkerOptions().position(new LatLng(-30.1410601,-51.1294799)).title("Pedro B.").snippet("Campus Retinga\nPorto Alegre\nRio Grande do Sul"));
            try {
                new Thread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mMap.addMarker(new MarkerOptions().position(new LatLng(-30.141267,-51.128761)).title("João C.").snippet("Campus Retinga\nPorto Alegre\nRio Grande do Sul"));
            try {
                new Thread().sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mMap.addMarker(new MarkerOptions().position(new LatLng(-30.141123, -51.130382)).title("Paula D.").snippet("Campus Retinga\nPorto Alegre\nRio Grande do Sul"));

            mMap.animateCamera(cameraPosition(latLng,15,0,0));
            //m_map.moveCamera(cameraPosition(latLng,15,0,0));

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

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            View v =null;
            /*
            View v = getLayoutInflater(Bundle.EMPTY).inflate(R.layout.marcador_personalizado,null);

            TextView nome_noMapa = (TextView) v.findViewById(R.id.IDnomeNoMapa);
            TextView idade_noMapa = (TextView) v.findViewById(R.id.IDidadeNoMapa);
            TextView genero_noMapa = (TextView) v.findViewById(R.id.IDgeneroNoMapa);
            TextView interesse_noMapa = (TextView) v.findViewById(R.id.IDinteresseNoMapa);

            LatLng ll = new LatLng(-30.141027, -51.130095);
            marker.setPosition(ll);
            marker.setTitle("Gasparzinho");
            nome_noMapa.setText(     "Nome......: "+marker.getTitle());
            idade_noMapa.setText(    "Idade.....: 30 anos");
            genero_noMapa.setText(   "Genero....: Homen");
            interesse_noMapa.setText("Interesse.: Novas amizades");
            return v;
            */
            return  v;
        }

        //////////////////////////////////////////////////////////////////////////////

    } // Fim da Classe Localizar

    /////////////////////////////////////////////////////////////////////////////////

} // Fim da Classe Apontamento
