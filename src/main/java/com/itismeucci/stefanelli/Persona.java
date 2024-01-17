package com.itismeucci.stefanelli;

public class Persona {

    private String nome;
    private String cognome;
    private int eta;

    public Persona() {

        nome = "";
        cognome = "";
        eta = 0;
    }
    public Persona(String nome, String cognome, int eta) {
        //età negative diventano positive
        this.nome = nome;
        this.cognome = cognome;
        this.eta = Math.abs(eta);
    }

    public String getNome() {

        return nome;
    }

    public String getCognome() {

        return cognome;
    }

    public int getEta() {

        return eta;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Cognome: " + cognome + ", Età: " + eta;
    }
}
