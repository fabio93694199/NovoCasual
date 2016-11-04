package com.example.ti.novocasual;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

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
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Apontamentos extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference databaseReference,banco,banco1;
    private ArrayList<Usuario> lstUsuario = new ArrayList<>();

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

    //////////test//////////////

    protected void testDeLista(ArrayList<Usuario> us){
        lstUsuario = us;
    }
    ///////////////////////////

    protected class Localizar implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private GoogleApiClient m_api;
        int cont = 0;

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

            final LatLng latLng = location();

            databaseReference = FirebaseDatabase.getInstance().getReference();
            banco = databaseReference.child("brasil").child("RioGrandeDoSul");

            banco.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.getChildrenCount()==0){
                        //se tá vazio o banco sai do método de busca
                    }else{
                        //para cada usuario no banco, cria um objeto e add numa lista...
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                            Usuario use = postSnapshot.getValue(Usuario.class);
                            lstUsuario.add(use);
                            cont+=1;
                        }
                    }
                    // somente após os dados chegarem de fato...
                    for (int i=0; i< lstUsuario.size(); i++){

                        Double lt = Double.parseDouble(lstUsuario.get(i).getLat());
                        Double lg = Double.parseDouble(lstUsuario.get(i).getLng());

                        mMap.addMarker(new MarkerOptions().position(new LatLng(lt,lg)).title(lstUsuario.get(i).getNome()).snippet(lstUsuario.get(i).getEmail()));
                        //mMap.addMarker(new MarkerOptions().position(new LatLng(lt,lg)).title(Integer.toString(lstUsuario.size())).snippet(Integer.toString(cont)));

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mMap.animateCamera(cameraPosition(latLng, 15, 0, 0));
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
