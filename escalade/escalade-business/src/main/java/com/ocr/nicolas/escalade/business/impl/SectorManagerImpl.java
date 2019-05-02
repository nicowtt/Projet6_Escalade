package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.SectorManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.SectorDao;
import com.ocr.nicolas.escalade.model.bean.secteur.Secteur;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class SectorManagerImpl implements SectorManager {

    @Inject
    private SectorDao sectorDao;


    /**
     * for get sectors List on Site
     *
     * @param pSite -> numero de site
     * @return liste de secteur
     */
    @Override
    public List<Secteur> getListOneSector(int pSite){

        List<Secteur> vSecteur = new ArrayList<>();
        vSecteur = sectorDao.getListOneSector(pSite);

        return vSecteur;
    }
}
