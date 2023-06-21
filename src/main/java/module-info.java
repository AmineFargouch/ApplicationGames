module com.example.applicationgames {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires poi;
    requires poi.ooxml;
    requires com.fasterxml.jackson.core;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;

    opens com.emsi.entities;
    opens com.views to javafx.fxml;

    opens com.emsi to javafx.fxml;
    exports com.emsi;
    exports com.emsi.entities;
    exports com.emsi.dao;
    exports com.emsi.controller;
    exports com.emsi.services;
    exports com.emsi.test;

    opens com.emsi.controller to javafx.fxml;
}