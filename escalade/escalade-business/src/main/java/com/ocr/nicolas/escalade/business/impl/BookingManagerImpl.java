package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.BookingManager;
import com.ocr.nicolas.escalade.business.contract.TopoPapierManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.BookingDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.TopoPapierDao;
import com.ocr.nicolas.escalade.model.bean.Commentaire;
import com.ocr.nicolas.escalade.model.bean.Reservation;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.exception.BookingException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class BookingManagerImpl extends AbstractManager implements BookingManager {

    @Inject
    private BookingDao bookingDao;

    @Inject
    private TopoPapierDao topoPapierDao;



    /**
     * For write new booking of paper topo
     * @param pReservation -> bean booking
     */
    @Override
    public void writeBooking(Reservation pReservation) {

        Topopapier newTopoPapier = new Topopapier();
        newTopoPapier.setDemandeReservation(true);

        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    bookingDao.writeBooking(pReservation);
                    //change all topoPapier (link to booking)-> demandeReservation = true
                    topoPapierDao.changeBookingRequest(newTopoPapier, pReservation.getTopoPapier_id());

                } catch (BookingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * For get list of asking booking in progress
     *
     * @param pUserId
     * @return
     */
    @Override
    public List<Reservation> getListBookingAskForOneUser(int pUserId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Reservation> vListBooking = vTransactionTemplate.execute(transactionStatus -> {

            List<Reservation> vListBookingTransaction = null;
            vListBookingTransaction = bookingDao.getListBookingAskForOneUser(pUserId);

            return  vListBookingTransaction;
        });
        return  vListBooking;
    }

    /**
     * For get List with booking request true
     *
     * @return
     */
    @Override
    public List<Reservation> getListAllTopoPapierWithBookingRequest(int pUserId) {

        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Reservation> vListBooking = vTransactionTemplate.execute(transactionStatus -> {

            List<Reservation> vListBookingTransaction = null;
            vListBookingTransaction = bookingDao.getListAllTopoPapierWithBookingRequest(pUserId);

            return vListBookingTransaction;
        });
        return vListBooking;
    }

}
