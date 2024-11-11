/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import db.DBBroker;
import domain.Angazovanje;
import domain.Korisnik;
import domain.Predmet;
import domain.Profesor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.KorisnikRepository;

/**
 *
 * @author Anđela
 */
public class Controller {
    private final KorisnikRepository korisnikRepository;
    private List<Korisnik> listaKorisnika;
    private DBBroker dbb;
    private static Controller controller;
    private Controller(){
        korisnikRepository = new KorisnikRepository();
        listaKorisnika = new ArrayList<>();
        listaKorisnika = korisnikRepository.getListaKorisnika();
        dbb = new DBBroker();
        
    }

    public static Controller getController() {
        if(controller == null){
            controller = new Controller();
        }
        return controller;
    }

    public List<Korisnik> getListaKorisnika() {
        return korisnikRepository.getListaKorisnika();
    }

    public List<Predmet> getListaPredmeta() throws SQLException {
        try {
            return dbb.getListaPredmeta();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    
    public List<Profesor> getListaProfesora() throws SQLException {
        try {
            return dbb.getListaProfesora();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void dodajProfesora(Profesor p) throws Exception {
        try {
            dbb.dodajProfesora(p);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public List<Angazovanje> vratiListuAngazovanja(Profesor p) throws Exception{
        try{
            return dbb.vratiListuAngazovanja(p);
        }catch(Exception ex){
            throw ex;
        }
    }

    public boolean obrisiAngazovanjeIzBaze(Angazovanje a) throws Exception {
        try{
            return dbb.obrisiAngazovanjeIzBaze(a);
        }catch(Exception ex){
            throw ex;
        }
    }

    public void dodajAngazovanje(Angazovanje a) throws Exception {
        try{
            dbb.dodajAngazovanje(a);
        }catch(Exception ex){
            throw ex;
        }
    }

    public Long dodajNoviPredmet(Predmet p) throws Exception {
        try{
            return dbb.dodajNoviPredmet(p);
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public boolean proveriAngazovanjaProfesora(Long id){
        return dbb.proveriBrojAngazovanjaProfesora(id);
    }
    
    
}
