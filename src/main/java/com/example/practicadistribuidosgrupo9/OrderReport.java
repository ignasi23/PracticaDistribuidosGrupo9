package com.example.practicadistribuidosgrupo9;


public class OrderReport {
    private String userName;
    private String orderID;
    private String reportMsg;
    private boolean reportSolved = false;

    public OrderReport(String userName, String orderID, String reportMsg) {
        this.userName = userName;
        this.orderID = orderID;
        this.reportMsg = reportMsg;

    }
    public String getUserName() {
        return userName;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getReportMsg() {
        return reportMsg;
    }

    public void setReportSolved(boolean b){
        this.reportSolved = b;
    }
    public boolean getReportSolved(){
        return this.reportSolved;
    }

}
