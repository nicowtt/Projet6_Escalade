package com.ocr.nicolas.escalade.consumer.contract.dao;

import com.ocr.nicolas.escalade.model.bean.Reservation;
import com.ocr.nicolas.escalade.model.exception.BookingException;

import java.util.List;

public interface BookingDao {

    void writeBooking(Reservation pReservation) throws BookingException;
    List<Reservation> getListBookingAskForOneUser(int pUserId);
}
