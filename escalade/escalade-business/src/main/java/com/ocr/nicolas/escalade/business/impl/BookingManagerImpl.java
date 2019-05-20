package com.ocr.nicolas.escalade.business.impl;

import com.ocr.nicolas.escalade.business.contract.BookingManager;
import com.ocr.nicolas.escalade.business.contract.TopoPapierManager;
import com.ocr.nicolas.escalade.consumer.contract.dao.BookingDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.TopoPapierDao;
import com.ocr.nicolas.escalade.consumer.contract.dao.UserDao;
import com.ocr.nicolas.escalade.model.bean.Commentaire;
import com.ocr.nicolas.escalade.model.bean.Reservation;
import com.ocr.nicolas.escalade.model.bean.Topopapier;
import com.ocr.nicolas.escalade.model.bean.Utilisateur;
import com.ocr.nicolas.escalade.model.exception.BookingException;
import com.ocr.nicolas.escalade.model.exception.TopoPapierException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;

@Named
public class BookingManagerImpl extends AbstractManager implements BookingManager {

    @Inject
    private BookingDao bookingDao;

    @Inject
    private TopoPapierDao topoPapierDao;

    @Inject
    private UserDao userDao;



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

    /**
     * For accept booking transaction and write email for booking validation
     *
     * @param pTopoPapier -> paper Topo for accept booking
     * @param pBooking -> booking bean
     */
    @Override
    public void acceptBookingAndRelationShip(Topopapier pTopoPapier, Reservation pBooking) {

        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Topopapier newTopoPaper = new Topopapier();

                try {
                    //1:Paper topo disponibilite -> false
                    topoPapierDao.changeAvailabilityTopoPapier(pTopoPapier, pTopoPapier.getId());

                    //2:Paper topo demandereservation -> false
                    topoPapierDao.changeBookingRequest(pTopoPapier, pTopoPapier.getId());

                    //3:Booking statusreservation -> false
                    bookingDao.changeBookingStatus(pBooking);

                    //4: set pTopoPaper
                    newTopoPaper = topoPapierDao.getOnlyOneTopopaper(pTopoPapier.getId());

                    //get string(email) of lending user
                    String vEmailTransaction = userDao.getUserBean(newTopoPaper);

                    //write on booking new e-mail
                    bookingDao.changeEmailOnBooking(vEmailTransaction, pBooking);

                } catch (TopoPapierException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * For get a list with booking ok
     *
     * @param pUserId -> User ID
     * @return
     */
    @Override
    public List<Reservation> getListBookingOK(int pUserId) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        List<Reservation> vListBooking = vTransactionTemplate.execute(transactionStatus -> {

            List<Reservation> vListBookingTransaction = null;
            vListBookingTransaction = bookingDao.getListBookingOK(pUserId);

            return  vListBookingTransaction;
        });
        return  vListBooking;

    }

}
