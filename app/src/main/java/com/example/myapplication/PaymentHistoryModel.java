package com.example.myapplication;

public class PaymentHistoryModel  {
    String refno,date,mode,amount,remark;
    int status;
            PaymentHistoryModel(String refno,String date,int status,String mode,String amount,String remarl){
                this.refno=refno;
                this.date=date;
                this.status=status;
                this.mode=mode;
                this.amount=amount;
                this.remark=remarl;
            }
}
