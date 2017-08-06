/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milos
 */
public class Igrac implements Serializable, OpstiDomenskiObjekat {

    private int id;
    private String ime;
    private String prezime;
    private String jmbg;
    private String ulica;
    private String broj;
    private Mesto mesto;
    private Uzrast uzrast;
    private Trener trener;
    private boolean status;

    public Igrac() {
    }

    public Igrac(int id, String ime, String prezime, String jmbg, String ulica, String broj, Mesto mesto, Uzrast uzrast, Trener trener) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto = mesto;
        this.uzrast = uzrast;
        this.trener = trener;
        this.status = true;
    }

    public Igrac(int id, String ime, String prezime, String jmbg, String ulica, String broj, Mesto mesto, Uzrast uzrast, Trener trener, boolean status) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto = mesto;
        this.uzrast = uzrast;
        this.trener = trener;
        this.status = status;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public Uzrast getUzrast() {
        return uzrast;
    }

    public void setUzrast(Uzrast uzrast) {
        this.uzrast = uzrast;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String vratiJoin() {
        return "igrac i INNER JOIN mesto m ON i.mesto_id=m.id\n"
                + "INNER JOIN uzrast u ON i.uzrast_id=u.sifra\n"
                + "INNER JOIN trener t ON i.trener_id=t.id";
    }

    @Override
    public String vratiNazivTabele() {
        return "igrac";
    }

    @Override
    public String vratiSelect() {
        return " i.id,i.ime,i.prezime,i.jmbg,i.ulica,i.broj,status_igraca,m.id AS mesto_id,m.naziv AS mesto,u.sifra,u.naziv AS uzrast,t.id AS trener_id,t.ime AS ime_trenera,t.prezime AS prezime_trenera\n";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("NULL,'%s','%s','%s','%s','%s',%d,%d,%d,%d",
                ime, prezime, jmbg, ulica, broj, status ? 1 : 0, mesto.getId(), uzrast.getSifra(), trener.getTrenerId());
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> listaIgraca = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String jmbg = rs.getString("jmbg");
                String ulica = rs.getString("ulica");
                String broj = rs.getString("broj");
                boolean status = rs.getBoolean("status_igraca");
                int mesto_id = rs.getInt("mesto_id");
                String nazivMesta = rs.getString("mesto");
                int uzrast_id = rs.getInt("sifra");
                String nazivUzrasta = rs.getString("uzrast");
                int trener_id = rs.getInt("trener_id");
                String imeTrenera = rs.getString("ime_trenera");
                String prezimeTrenera = rs.getString("prezime_trenera");
                Mesto mesto = new Mesto(mesto_id, nazivMesta);
                Uzrast uzrast = new Uzrast(uzrast_id, nazivUzrasta);
                Trener trener = new Trener(trener_id, imeTrenera, prezimeTrenera);
                Igrac igrac = new Igrac(id, ime, prezime, jmbg, ulica, broj, mesto, uzrast, trener);
                igrac.setStatus(status);
                listaIgraca.add(igrac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaIgraca;
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "ime=" + "'" + this.getIme() + "'" + "," + " prezime=" + "'" + this.getPrezime() + "'" + ","
                + " jmbg=" + "'" + this.getJmbg() + "'" + "," + " ulica=" + "'" + this.getUlica() + "'" + ","
                + " broj=" + "'" + this.getBroj() + "'" + "," + " status_igraca=" + isStatus() + ","
                + " mesto_id=" + this.getMesto().getId() + "," + " trener_id="
                + this.getTrener().getTrenerId() + "," + " uzrast_id=" + getUzrast().getSifra();

    }

    @Override
    public String vratiWhere() {
        return "id=" + this.getId();
    }

}
