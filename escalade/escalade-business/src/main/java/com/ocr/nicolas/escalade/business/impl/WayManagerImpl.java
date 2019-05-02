package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.WayManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.WayDao;
import com.ocr.nicolas.escalade.model.bean.voie.Voie;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class WayManagerImpl implements WayManager {

    @Inject
    private WayDao wayDao;


    /**
     *  for get Ways List on Site
     *
     * @param pSite
     * @return
     */
    @Override
    public List<Voie> getListAllWaysForOneSite (int pSite) {
        List<Voie> vVoie = new ArrayList<>();
        vVoie = wayDao.getListAllWaysForOneSite(pSite);

        return vVoie;
    }


    /**
     * for get ways List on sectors
     *
     * @param pSecteur -> id of sector
     * @return Ways List
     */
    @Override
    public List<Voie> getListVoie(int pSecteur) {
        List<Voie> vVoie = new ArrayList<>();
        vVoie = wayDao.getListVoie(pSecteur);

        return vVoie;
    }


}
