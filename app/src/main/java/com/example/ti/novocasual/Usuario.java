package com.example.ti.novocasual;

import android.content.Context;

/**
 * Created by ti on 11/10/16.
 */

public class Usuario {
    public static String TOKEN = "com.example.ti.novocasual.TOKEN";

    private String id;
    private String nome;
    private String email;
    private String senha;

    public Usuario(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    /*
        public void gerarCryptSenha(){
            senha = CryptWithMD5.cryptWithMD5(senha);
        }
    */
    public String getTokenSP(Context context){
        //String token = ClassePadrao.getSP(context,TOKEN);
        //return (token);
        return ("");
    }

    public void saveTokenSP(Context context,String token){
        //ClassePadrao.saveSP(context,TOKEN,token);
    }

    public void saveDB (){
        //Firebase firebase = ClassePadrao.getFirebase();
        //firebase.child("usuarios").child(getId());
        setId(null);
        setSenha(null);
        //firebase.setValue(this);
    }
}

