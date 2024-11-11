/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import domain.Angazovanje;
import domain.Korisnik;
import domain.Predmet;
import domain.Profesor;
import domain.Zvanje;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Anđela
 */
public class DBBroker {
    private Konekcija konekcija;
    private Connection conn;
    
    
    public DBBroker() {
        konekcija = Konekcija.getKonekcija();
        conn = konekcija.getConn();
        
    }

    public List<Predmet> getListaPredmeta() throws SQLException{
        List<Predmet> lista = new ArrayList<>();
        String upit = "SELECT * FROM predmet";
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                Long id = rs.getLong("id");
                String naziv = rs.getString("naziv");
                String kod = rs.getString("kod");
                int espb = rs.getInt("espb");
                lista.add(new Predmet(id, naziv, kod, espb));
            }
            conn.commit();
        } catch (SQLException ex) {
            throw ex;
        }

        return lista;
    }

    public List<Profesor> getListaProfesora() throws SQLException {
        List<Profesor> lista = new ArrayList<>();
        String upit = "SELECT * FROM profesor";
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                lista.add(new Profesor(rs.getLong("id"), rs.getString("ime"), rs.getString("prezime"), rs.getString("email"), Zvanje.valueOf(rs.getString("zvanje"))));
            }
        } catch (SQLException ex) {
            throw ex;
        }

        return lista;
    }

    public void dodajProfesora(Profesor p) throws Exception {
        String upit = "INSERT INTO Profesor(ime,prezime,email,zvanje) VALUES(?,?,?,?)";
        try{
            PreparedStatement ps = conn.prepareStatement(upit);
            ps.setString(1, p.getIme());
            ps.setString(2, p.getPrezime());
            ps.setString(3, p.getEmail());
            ps.setString(4, String.valueOf(p.getZvanje()));
            ps.executeUpdate();
            conn.commit();
        }catch (Exception ex){
            throw ex;
        }
    }

    public List<Angazovanje> vratiListuAngazovanja(Profesor p) throws Exception {
        List<Angazovanje> lista = new ArrayList<>();
        String upit = "SELECT * FROM angazovanje a JOIN profesor prof ON a.profesor = prof.id JOIN predmet p "
                + "ON a.predmet = p.id WHERE prof.id = " + p.getId();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                Profesor profesor = new Profesor(rs.getLong("prof.id"), rs.getString("prof.ime"), rs.getString("prof.prezime"), rs.getString("prof.email"), Zvanje.valueOf(rs.getString("prof.zvanje")));
                Predmet predmet = new Predmet(rs.getLong("p.id"), rs.getString("p.naziv"), rs.getString("p.kod"), rs.getInt("p.espb"));
                lista.add(new Angazovanje(profesor, predmet, rs.getLong("a.id")));
            }
            conn.commit();
        } catch (Exception ex) {
            throw ex;
        }

        return lista;
    }

    public boolean obrisiAngazovanjeIzBaze(Angazovanje a) throws Exception {
        String upit = "DELETE FROM angazovanje WHERE id = " +a.getId();
        System.out.println(upit);
        try {
            Statement st = conn.createStatement();
            int affectedRows = st.executeUpdate(upit);
            if(affectedRows > 0){
                conn.commit();
                return true;
            }
            
        } catch (Exception ex) {
            throw ex;
        }
        return false;
    }

    public void dodajAngazovanje(Angazovanje a) throws Exception {
       
        String upit = "INSERT INTO angazovanje(profesor,predmet) VALUES(?,?)";
        try {
            if(proveriBrojAngazovanjaProfesora(a.getProfesor().getId())){
                PreparedStatement ps = conn.prepareStatement(upit);
                ps.setLong(1, a.getProfesor().getId());
                ps.setLong(2, a.getPredmet().getId());
                ps.executeUpdate();
                conn.commit();
            }else{
                throw new Exception("Broj angazovanja profesora je maksimalan (3)");
            }
        } catch (Exception ex) {
            throw ex;
        }
        
    }

    public boolean proveriBrojAngazovanjaProfesora(Long id){
        String upit = "SELECT COUNT(*) AS broj FROM angazovanje WHERE profesor = " + id;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(upit);
            int broj;
            if(rs.next()){
                broj = rs.getInt("broj");
            }
            else{
                broj = 0;
            }
            if(broj >= 3){
                return false;
            }else{
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public Long dodajNoviPredmet(Predmet p) throws Exception {
        String upit = "INSERT INTO predmet(naziv,kod,espb) VALUES(?,?,?)";
        Long id = Long.MAX_VALUE;
        try {
            PreparedStatement ps = conn.prepareStatement(upit,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNaziv());
            ps.setString(2, p.getKod());
            ps.setInt(3, p.getEspb()); 
            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                id = rs.getLong(1);
                }
                conn.commit();
                return id;
            }else{
                throw new Exception("Neuspesno dodavanje predmeta u bazu");
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
    
}
