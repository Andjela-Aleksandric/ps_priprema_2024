/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import domain.Korisnik;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anđela
 */
public class KorisnikRepository {
    private List<Korisnik> listaKorisnika;

    public KorisnikRepository() {
        listaKorisnika = new ArrayList<>();
        listaKorisnika.add(new Korisnik("Andja", "Andjic", "aa@gmail.com", "andja"));
        listaKorisnika.add(new Korisnik("Djika", "Andjic", "dja@gmail.com", "djika"));
    }

    public void setListaKorisnika(List<Korisnik> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }

    public List<Korisnik> getListaKorisnika() {
        return listaKorisnika;
    }
    
    
    
}
