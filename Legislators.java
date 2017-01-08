package com.example.pavan.tryapp;

import java.io.Serializable;

public class Legislators implements Serializable{
    private String title, email, chamber, phone, startT, endT,office,
            state,fax,birthday, fname, lname, party, stateName, district, image, fb, twt, website;

    public Legislators(String title, String email, String chamber,
                       String phone, String startT, String endT, String office,
                       String state, String fax, String birthday,String fname, String lname,
                       String party, String stateName, String district, String image, String fb, String twt, String website) {
       this.title = title;
        this.email = email;
        this.chamber = chamber;
        this.phone = phone;
        this.startT = startT;
        this.endT = endT;
        this.office = office;
        this.state = state;
        this.fax = fax;
        this.birthday = birthday;
        this.fname = fname;
        this.lname = lname;
        this.party = party;
        this.stateName = stateName;
        this.district = district;
        this.image = image;
        this.fb = fb;
        this.twt = twt;
        this.website = website;
    }

    public String getTwt() {
        if(twt.equals(null))
            return "N.a";
        else
        return twt;
    }

    public void setTwt(String twt) {
        this.twt = twt;
    }

    public String getWebsite() {
        if(website.equals("null"))
            return "N.a";
        else
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFb() {
        if(fb.equals(null))
            return "N.a";
        else
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStartT() {
        return startT;
    }

    public void setStartT(String startT) {
        this.startT = startT;
    }

    public String getEndT() {
        return endT;
    }

    public void setEndT(String endT) {
        this.endT = endT;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public String getParty()
    {
        return party;
    }

    public void setParty(String party)
    {
        this.party = party;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getImage() {
        return "https://theunitedstates.io/images/congress/original/"+image+".jpg";
    }

    public void setImage(String image) {
        this.image = image;
    }

}
