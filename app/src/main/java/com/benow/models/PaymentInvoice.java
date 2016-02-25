package com.benow.models;

public class PaymentInvoice {

    private Double amountPayable;
    private EcommOrder ecommOrder;

    /**
     * @return The amountPayable
     */
    public Double getAmountPayable() {
        return amountPayable;
    }

    /**
     * @param amountPayable The amountPayable
     */
    public void setAmountPayable(Double amountPayable) {
        this.amountPayable = amountPayable;
    }

    /**
     * @return The ecommOrder
     */
    public EcommOrder getEcommOrder() {
        return ecommOrder;
    }

    /**
     * @param ecommOrder The ecommOrder
     */
    public void setEcommOrder(EcommOrder ecommOrder) {
        this.ecommOrder = ecommOrder;
    }


}