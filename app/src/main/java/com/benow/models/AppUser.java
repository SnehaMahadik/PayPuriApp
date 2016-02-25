package com.benow.models;

import java.util.HashMap;
import java.util.Map;

public class AppUser {

    private String username;
    private String password;
    private String email;
    private String mobileNumber;
    private String parentUsername;
    private String referralCode;
    private Object otp;
    private Boolean mobileVerified;
    private Boolean emailVerified;
    private Object bankAccountRefNumber;
    private Object bankAccountMaskedNumber;
    private Object bankAccountIfsc;
    private Object mmid;
    private Object bankAccountName;
    private Object bankAccountAeba;
    private Object appUserVirtualAddresses;
    private Links Links;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber The mobileNumber
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * @return The parentUsername
     */
    public String getParentUsername() {
        return parentUsername;
    }

    /**
     * @param parentUsername The parentUsername
     */
    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    /**
     * @return The referralCode
     */
    public String getReferralCode() {
        return referralCode;
    }

    /**
     * @param referralCode The referralCode
     */
    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    /**
     * @return The otp
     */
    public Object getOtp() {
        return otp;
    }

    /**
     * @param otp The otp
     */
    public void setOtp(Object otp) {
        this.otp = otp;
    }

    /**
     * @return The mobileVerified
     */
    public Boolean getMobileVerified() {
        return mobileVerified;
    }

    /**
     * @param mobileVerified The mobileVerified
     */
    public void setMobileVerified(Boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    /**
     * @return The emailVerified
     */
    public Boolean getEmailVerified() {
        return emailVerified;
    }

    /**
     * @param emailVerified The emailVerified
     */
    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    /**
     * @return The bankAccountRefNumber
     */
    public Object getBankAccountRefNumber() {
        return bankAccountRefNumber;
    }

    /**
     * @param bankAccountRefNumber The bankAccountRefNumber
     */
    public void setBankAccountRefNumber(Object bankAccountRefNumber) {
        this.bankAccountRefNumber = bankAccountRefNumber;
    }

    /**
     * @return The bankAccountMaskedNumber
     */
    public Object getBankAccountMaskedNumber() {
        return bankAccountMaskedNumber;
    }

    /**
     * @param bankAccountMaskedNumber The bankAccountMaskedNumber
     */
    public void setBankAccountMaskedNumber(Object bankAccountMaskedNumber) {
        this.bankAccountMaskedNumber = bankAccountMaskedNumber;
    }

    /**
     * @return The bankAccountIfsc
     */
    public Object getBankAccountIfsc() {
        return bankAccountIfsc;
    }

    /**
     * @param bankAccountIfsc The bankAccountIfsc
     */
    public void setBankAccountIfsc(Object bankAccountIfsc) {
        this.bankAccountIfsc = bankAccountIfsc;
    }

    /**
     * @return The mmid
     */
    public Object getMmid() {
        return mmid;
    }

    /**
     * @param mmid The mmid
     */
    public void setMmid(Object mmid) {
        this.mmid = mmid;
    }

    /**
     * @return The bankAccountName
     */
    public Object getBankAccountName() {
        return bankAccountName;
    }

    /**
     * @param bankAccountName The bankAccountName
     */
    public void setBankAccountName(Object bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    /**
     * @return The bankAccountAeba
     */
    public Object getBankAccountAeba() {
        return bankAccountAeba;
    }

    /**
     * @param bankAccountAeba The bankAccountAeba
     */
    public void setBankAccountAeba(Object bankAccountAeba) {
        this.bankAccountAeba = bankAccountAeba;
    }

    /**
     * @return The appUserVirtualAddresses
     */
    public Object getAppUserVirtualAddresses() {
        return appUserVirtualAddresses;
    }

    /**
     * @param appUserVirtualAddresses The appUserVirtualAddresses
     */
    public void setAppUserVirtualAddresses(Object appUserVirtualAddresses) {
        this.appUserVirtualAddresses = appUserVirtualAddresses;
    }

    /**
     * @return The Links
     */
    public Links getLinks() {
        return Links;
    }

    /**
     * @param Links The _links
     */
    public void setLinks(Links Links) {
        this.Links = Links;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
