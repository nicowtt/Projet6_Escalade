package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.model.bean.Reservation;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.exception.BookingException;
import com.ocr.nicolas.escalade.model.exception.SectorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ocr.nicolas.escalade.consumer.contract.dao.BookingDao;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.sql.Types;

@Named
public class BookingDaoImpl extends  AbstractDAoImpl implements BookingDao {

    static final Log logger = LogFactory.getLog(BookingDaoImpl.class);


    /**
     * For write new booking of paper topo
     * @param pReservation -> bbooking bean
     * @param pUserId -> user id
     * @throws BookingException
     */
    @Override
    public void writeBooking(Reservation pReservation) throws BookingException {
        String vSQL
                = "INSERT INTO public.reservation (statusreservation, topopapier_id, utilisateur_id)"
                + " VALUES (:statusreservation, :topopapier_id, :utilisateur_id)";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());
        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("statusreservation", pReservation.isStatusReservation());
        vParams.addValue("topopapier_id", pReservation.getTopoPapier_id());
        vParams.addValue("utilisateur_id", pReservation.getUtilisateur_id());

        try {
            vJdbcTemplate.update(vSQL, vParams);
        } catch (DuplicateKeyException vEx) {
            logger.debug("La reservation existe deja !");
            //return for user
            throw new BookingException("La reservation existe deja !");
        }
    }



}
