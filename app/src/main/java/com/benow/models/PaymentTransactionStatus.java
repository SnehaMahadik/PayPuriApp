package com.benow.models;


public class PaymentTransactionStatus {

    private String errorCode;
    private String errorDescription;
    private String transactionStatus;

    /**
     * @return The errorCode
     */
    public Object getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode The errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return The errorDescription
     */
    public Object getErrorDescription() {
        return errorDescription;
    }

    /**
     * @param errorDescription The errorDescription
     */
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    /**
     * @return The transactionStatus
     */
    public String getTransactionStatus() {
        return transactionStatus;
    }

    /**
     * @param transactionStatus The transactionStatus
     */
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

}