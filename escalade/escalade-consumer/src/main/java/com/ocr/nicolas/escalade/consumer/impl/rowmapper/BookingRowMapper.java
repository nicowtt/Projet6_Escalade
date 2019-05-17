package com.ocr.nicolas.escalade.consumer.impl.rowmapper;

import com.ocr.nicolas.escalade.model.bean.Reservation;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
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

        //new bean"topopapier"
        Topopapier vTopoPapier = new Topopapier(resultSet.getInt("topopapier_id"));
        //set vTopoPapier -> to be completed if needed
        vTopoPapier.setNomTopo(resultSet.getString("nomtopo"));

        //bean "Topopapier" -> variable du bean "Booking"
        vBooking.setTopopapier(vTopoPapier);

        return vBooking;
    }
}
