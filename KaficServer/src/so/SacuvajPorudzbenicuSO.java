/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.InterfaceObjekat;
import domen.Porudzbenica;
import domen.StavkaPorudzbenice;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiOdgovor;

/**
 *
 * @author User
 */
public class SacuvajPorudzbenicuSO extends OpstaSistemskaOperacija {

    public SacuvajPorudzbenicuSO() {
        super();
    }

    @Override
    protected void proveriPreduslove(InterfaceObjekat domenskiObjekat) throws Exception {
    }

    @Override
    protected ServerskiOdgovor izvrsiOperaciju(InterfaceObjekat domenskiObjekat) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();

        try {
            int id = getDb().unesi(domenskiObjekat);
            Porudzbenica p = (Porudzbenica) domenskiObjekat;
            p.setPorudzbenicaID(id);
            for (StavkaPorudzbenice sp : p.getStavkePorudzbenice()) {
                sp.setPorudzbenica(p);
                getDb().unesi(sp);
            }
            so.setUspesno(true);
            so.setPoruka("Sistem je sacuvao porudzbenicu");
        } catch (Exception e) {
            Logger.getLogger(SacuvajPorudzbenicuSO.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Sistem nije sacuvao porudzbenicu");

        }
        return so;
    }
}
