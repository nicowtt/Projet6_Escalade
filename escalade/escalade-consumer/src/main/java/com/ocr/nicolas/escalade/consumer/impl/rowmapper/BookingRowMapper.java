package com.ocr.nicolas.escalade.consumer.impl.rowmapper;

import com.ocr.nicolas.escalade.model.bean.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingRowMapper implements RowMapper<Reservation> {

    @Override
    public Reservation mapRow(ResultSet resultSet, int i) throws SQLException {
        Reservation vBooking = new Reservation(resultSet.getInt("id"));
        vBooking.setStatusReservation(resultSet.getBoolean("statusreservation"));
        vBooking.setTopoPapier_id(resultSet.getInt("topopapier_id"));
        vBooking.setUtilisateur_id(resultSet.getInt("utilisateur_id"));

        return vBooking;
    }
}
