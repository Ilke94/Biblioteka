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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milos
 */
public class Trener implements Serializable, OpstiDomenskiObjekat {

    private int trenerId;
    private String jmbg;
    private String ime;
    private String prezime;
    private String ulica;
    private String broj;
    private String username;
    private String password;
    private Mesto mesto;
    private Licenca licenca;

    public Trener() {
    }

    public Trener(int trenerId, String ime, String prezime) {
        this.trenerId = trenerId;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Trener(int trenerId, String ime, String prezime, String jmbg, String ulica, String broj, Mesto mesto, Licenca licenca) {
        this.trenerId = trenerId;
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.ulica = ulica;
        this.broj = broj;
        this.mesto = mesto;
        this.licenca = licenca;
    }

    public Trener(int trenerId, String jmbg, String ime, String prezime, String ulica, String broj, String username, String password, Mesto mesto, Licenca licenca) {
        this.trenerId = trenerId;
        this.jmbg = jmbg;
        this.ime = ime;
        this.prezime = prezime;
        this.ulica = ulica;
        this.broj = broj;
        this.username = username;
        this.password = password;
        this.mesto = mesto;
        this.licenca = licenca;
    }

    public int getTrenerId() {
        return trenerId;
    }

    public void setTrenerId(int trenerId) {
        this.trenerId = trenerId;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public Licenca getLicenca() {
        return licenca;
    }

    public void setLicenca(Licenca licenca) {
        this.licenca = licenca;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trener other = (Trener) obj;
        return Objects.equals(this.username, other.username);
    }

    @Override
    public String vratiNazivTabele() {
        return "trener";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return String.format("NULL,'%s','%s','%s','%s','%s','%s','%s',%d,%d",
                jmbg, ime, prezime, ulica, broj, username, password, mesto.getId(), licenca.getSifra());
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> listaTrenera = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String jmbg = rs.getString("jmbg");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String ulica = rs.getString("ulica");
                String broj = rs.getString("broj");
                String username = rs.getString("username");
                String pass = rs.getString("pass");
                int mesto_id = rs.getInt("id");
                int postanskiBroj = rs.getInt("ptt");
                String nazivMesta = rs.getString("mesto");
                int licenca_id = rs.getInt("sifra");
                String nazivLicence = rs.getString("licenca");
                Mesto mesto = new Mesto(mesto_id, postanskiBroj, nazivMesta);
                Licenca licenca = new Licenca(licenca_id, nazivLicence);
                Trener trener = new Trener(id, jmbg, ime, prezime, ulica, broj, username, pass, mesto, licenca);
                listaTrenera.add(trener);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTrenera;
    }

    @Override
    public String vratiJoin() {
        return " trener t\n"
                + "INNER JOIN mesto m ON t.mesto_id=m.id\n"
                + "INNER JOIN licenca l ON t.licenca_id=l.sifra";
    }

    @Override
    public String vratiSelect() {
        return " t.id,jmbg,ime,prezime,ulica,broj,username,pass,m.id,m.ptt AS ptt,m.naziv AS mesto,l.sifra,l.naziv AS licenca\n";
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "ime=" + "'" + this.getIme() + "'" + "," + " prezime=" + "'" + this.getPrezime() + "'" + ","
                + " jmbg=" + "'" + this.getJmbg() + "'" + "," + " ulica=" + "'" + this.getUlica() + "'" + ","
                + " broj=" + "'" + this.getBroj() + "'" + ","
                + " mesto_id=" + this.getMesto().getId() + "," + " licenca_id=" + getLicenca().getSifra();
    }

    @Override
    public String vratiWhere() {
        return "id=" + this.getTrenerId();
    }

}
