package com.example.pavan.tryapp;

import java.io.Serializable;


public class Bills implements Serializable{
    private String bill_id, bill_type, short_title, introduced_on, sponsor, chamber, status, congressUrl, version_status, bill_url;

    public Bills(String bill_id, String bill_type, String short_title, String introduced_on, String sponsor, String chamber, String status, String congressUrl, String version_status, String bill_url) {
        this.bill_id = bill_id;
        this.bill_type = bill_type;
        this.short_title = short_title;
        this.introduced_on = introduced_on;
        this.sponsor = sponsor;
        this.chamber = chamber;
        this.status = status;
        this.congressUrl = congressUrl;
        this.version_status = version_status;
        this.bill_url = bill_url;
    }

    public String getBill_type() {
        return bill_type;
    }

    public void setBill_type(String bill_type) {
        this.bill_type = bill_type;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCongressUrl() {
        return congressUrl;
    }

    public void setCongressUrl(String congressUrl) {
        this.congressUrl = congressUrl;
    }

    public String getVersion_status() {
        return version_status;
    }

    public void setVersion_status(String version_status) {
        this.version_status = version_status;
    }

    public String getBill_url() {
        return bill_url;
    }

    public void setBill_url(String bill_url) {
        this.bill_url = bill_url;
    }

    public String getBill_id() {
        return bill_id;
    }

    public void setBill_id(String bill_id) {
        this.bill_id = bill_id;
    }

    public String getShort_title() {
        return short_title;
    }

    public void setShort_title(String short_title) {
        this.short_title = short_title;
    }

    public String getIntroduced_on() {
        return introduced_on;
    }

    public void setIntroduced_on(String introduced_on) {
        this.introduced_on = introduced_on;
    }
}
