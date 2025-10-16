package com.donnedesang.service;

import com.donnedesang.model.Donor;
import com.donnedesang.model.Receiver;
import java.util.List;

public class MatchService {

    private DonorService donorService = new DonorService();
    private ReceiverService receiverService = new ReceiverService();

    public void matchDonorToReceiver(Donor donor, Receiver receiver) {
        if(donor.getStatus().equals("DISPONIBLE") && !receiver.isSatisfied()) {
            donor.setStatus("NON_DISPONIBLE");
            receiver.addDonor(donor);

            if(receiver.getDonors().size() >= receiver.getRequiredPouches()) {
                receiver.setSatisfied(true);
            }

            donorService.updateDonor(donor);
            receiverService.updateReceiver(receiver);
        }
    }

    public List<Donor> getAvailableDonors() {
        return donorService.getAllDonors().stream()
                .filter(d -> d.getStatus().equals("DISPONIBLE"))
                .toList();
    }

    public List<Receiver> getPendingReceivers() {
        return receiverService.getAllReceivers().stream()
                .filter(r -> !r.isSatisfied())
                .toList();
    }
}