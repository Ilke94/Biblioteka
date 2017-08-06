/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Milos
 */
public class Trening implements Serializable, OpstiDomenskiObjekat {

    private int brojTreninga;
    private Date datum;
    private int duzinaTrajanja;
    private String cilj;
    private String sadrzaj;
    private Trener trener;
    private Igrac igrac;
    private VrstaTreninga vrstaTreninga;

    public Trening() {
    }

    public Trening(int brojTreninga, Date datum, int duzinaTrajanja, String cilj, String sadrzaj, Trener trener, Igrac igrac, VrstaTreninga vrstaTreninga) {
        this.brojTreninga = brojTreninga;
        this.datum = datum;
        this.duzinaTrajanja = duzinaTrajanja;
        this.cilj = cilj;
        this.sadrzaj = sadrzaj;
        this.trener = trener;
        this.igrac = igrac;
        this.vrstaTreninga = vrstaTreninga;
    }

    public VrstaTreninga getVrstaTreninga() {
        return vrstaTreninga;
    }

    public void setVrstaTreninga(VrstaTreninga vrstaTreninga) {
        this.vrstaTreninga = vrstaTreninga;
    }

    public int getBrojTreninga() {
        return brojTreninga;
    }

    public void setBrojTreninga(int brojTreninga) {
        this.brojTreninga = brojTreninga;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getDuzinaTrajanja() {
        return duzinaTrajanja;
    }

    public void setDuzinaTrajanja(int duzinaTrajanja) {
        this.duzinaTrajanja = duzinaTrajanja;
    }

    public String getCilj() {
        return cilj;
    }

    public void setCilj(String cilj) {
        this.cilj = cilj;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Igrac getIgrac() {
        return igrac;
    }

    public void setIgrac(Igrac igrac) {
        this.igrac = igrac;
    }

    @Override
    public String vratiNazivTabele() {
        return "trening";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("NULL,%d,%d,'%s',%d,'%s','%s',%d",
                trener.getTrenerId(), igrac.getId(), sdf.format(datum), duzinaTrajanja, cilj, sadrzaj, vrstaTreninga.getVrstaTreningaId());
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiJoin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String vratiSelect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
