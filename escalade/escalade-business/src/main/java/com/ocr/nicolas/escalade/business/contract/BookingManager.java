package com.ocr.nicolas.escalade.business.contract;

import com.ocr.nicolas.escalade.model.bean.Reservation;
import com.ocr.nicolas.escalade.model.bean.Topopapier;

import java.util.List;

public interface BookingManager {

    void writeBooking(Reservation pReservation);
    List<Reservation> getListBookingAskForOneUser(int pUserId);
    List<Reservation> getListAllTopoPapierWithBookingRequest(int pUserId);
    void acceptBookingAndRelationShip(Topopapier pTopoPapier, Reservation pBooking);
    List<Reservation> getListBookingOK(int pUserId);

}
