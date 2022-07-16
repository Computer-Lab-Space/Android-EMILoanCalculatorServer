package com.computerlabspace.emiloancaluculatorserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class EMICalculatorService extends Service {
    public EMICalculatorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return service;
    }

    EMIAidlInterface.Stub service = new EMIAidlInterface.Stub() {
        @Override
        public double calculateEMI(long principalAmount, long downPayment, float interestRate, int tenure) throws RemoteException {
            principalAmount = principalAmount - downPayment;
            interestRate = interestRate/(12 * 100); // per month interest rate
            double emi = (principalAmount * interestRate * Math.pow((1 + interestRate), tenure))
                    / (Math.pow((1 + interestRate), tenure) - 1);
            return emi;
        }
    };
}