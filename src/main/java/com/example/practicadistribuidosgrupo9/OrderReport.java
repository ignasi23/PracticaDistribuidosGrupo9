package com.example.practicadistribuidosgrupo9;

import java.util.ArrayList;
import java.util.List;

public class OrderReport {
    private String userName;
    private String orderID;
    private String reportMsg;


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


}
