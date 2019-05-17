package com.ocr.nicolas.escalade.consumer.impl.dao;

import com.ocr.nicolas.escalade.consumer.impl.rowmapper.BookingRowMapper;
import com.ocr.nicolas.escalade.model.bean.Reservation;
import com.ocr.nicolas.escalade.model.exception.BookingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.ocr.nicolas.escalade.consumer.contract.dao.BookingDao;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;

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


    /**
     * For get a list of ask booking in progress
     *
     * @param pUserId -> user id
     * @return
     */
    @Override
    public List<Reservation> getListBookingAskForOneUser(int pUserId) {
        String vSQL
                = "SELECT * FROM public.reservation"
                + " JOIN topopapier ON reservation.topopapier_id = topopapier.id"
                + "  WHERE utilisateur_id = :utilisateur_id"
                + "   AND statusreservation = true";


        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();
        vParams.addValue("utilisateur_id", pUserId, Types.INTEGER);

        RowMapper<Reservation> vRowMapperBooking = new BookingRowMapper();

        List<Reservation> vListBooking = vJdbcTemplate.query(vSQL, vParams, vRowMapperBooking);

        return  vListBooking;
    }


    /**
     * fors ask a list with bokking in progress
     *
     * @return -> List of booking in progress
     */
    @Override
    public List<Reservation> getListBookingInProgress() {
        String vSQL
                = "SELECT * FROM public.reservation"
                + " JOIN topopapier ON reservation.topopapier_id = topopapier.id"
                + "  WHERE statusreservation = true";

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDatasource());

        RowMapper<Reservation> vRowMapperBooking = new BookingRowMapper();

        List<Reservation> vListBooking = vJdbcTemplate.query(vSQL,vRowMapperBooking);

        return vListBooking;

    }



}
