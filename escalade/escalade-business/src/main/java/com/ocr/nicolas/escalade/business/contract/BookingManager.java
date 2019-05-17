package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.Reservation;

import java.util.List;

public interface BookingManager {

    void writeBooking(Reservation pReservation);
    List<Reservation> getListBookingAskForOneUser(int pUserId);
}
