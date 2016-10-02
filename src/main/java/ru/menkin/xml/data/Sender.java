package ru.menkin.xml.data;

public class Sender {
    private Contact contact;
    private EDMS EDMS;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public ru.menkin.xml.data.EDMS getEDMS() {
        return EDMS;
    }

    public void setEDMS(ru.menkin.xml.data.EDMS EDMS) {
        this.EDMS = EDMS;
    }
}
