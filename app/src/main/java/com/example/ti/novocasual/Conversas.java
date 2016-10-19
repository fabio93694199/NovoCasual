package com.example.ti.novocasual;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Conversas extends Fragment {

    private DatabaseReference databaseReference,banco,banco1,refBanco,pessoasEncontradas,refObjeto;
    private ListView listPessoasEncontradas;
    private ArrayList<String> listaDeUsuarios = new ArrayList<>();

    public Conversas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_conversas, container, false);
        ////////////////////////////////////////////////////////////////////////////////////////////
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //refObjeto = databaseReference.child("brasil").child("RioGrandeDoSul").child("9e9KNRfXbWecUr1iNNWpgTMl5Qb2");
        refObjeto = databaseReference.child("brasil").child("RioGrandeDoSul");
        listPessoasEncontradas = (ListView) v.findViewById(R.id.IDlistadepessoasEncontradas);

        final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, listaDeUsuarios);
        listPessoasEncontradas.setAdapter(adaptador);

        refObjeto.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    //lst.add(String.valueOf(dsp.getKey())); //add o resultado no array list
                    listaDeUsuarios.add(String.valueOf(dsp.getValue()));
                }
                /*
                for(String dado:listaDeUsuarios){
                    Toast.makeText(getContext(),dado, LENGTH_LONG).show();
                }*/
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////
        return v;
    }

}
