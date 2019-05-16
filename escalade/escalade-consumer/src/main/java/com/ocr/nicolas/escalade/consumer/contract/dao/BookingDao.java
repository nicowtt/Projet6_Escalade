package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Reservation;
import com.ocr.nicolas.escalade.model.exception.BookingException;

public interface BookingDao {

    void writeBooking(Reservation pReservation) throws BookingException;
}
