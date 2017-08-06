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
public class Licenca implements Serializable, OpstiDomenskiObjekat {

    private int sifra;
    private String naziv;

    public Licenca() {
    }

    public Licenca(int sifra, String naziv) {
        this.sifra = sifra;
        this.naziv = naziv;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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
        final Licenca other = (Licenca) obj;
        if (this.sifra != other.sifra) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "licenca";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "";
    }

    @Override
    public String vratiSelect() {
        return "*";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> listaLicenci = new ArrayList<>();
        try {
            while (rs.next()) {
                int sifra = rs.getInt("sifra");
                String naziv = rs.getString("naziv");
                Licenca licenca = new Licenca(sifra, naziv);
                listaLicenci.add(licenca);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaLicenci;
    }

    @Override
    public String vratiJoin() {
        return vratiNazivTabele();
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiWhere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
