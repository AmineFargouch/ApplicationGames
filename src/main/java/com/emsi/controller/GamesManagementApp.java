package com.emsi.controller;
import com.emsi.entities.Games;
import com.emsi.services.GamesService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class GamesManagementApp extends Application {

    private GamesService gamesService = new GamesService();
    private ObservableList<Games> gamesList = FXCollections.observableArrayList();

    private TableView<Games> tableView;
    private TextField idField;
    private TextField nameField;
    private TextField plateformeField;
    private TextField prixField;
    private TextField noteField;
    private TextField typeField;
    private TextField developpeurField;

    public static void main(String[] args) {
        launch(args);
    }

    public void launchApp() throws Exception {
        Stage primaryStage = new Stage();
        start(primaryStage);

        // Create form fields
        idField = new TextField();
        nameField = new TextField();
        plateformeField = new TextField();
        prixField = new TextField();
        noteField = new TextField();
        typeField = new TextField();
        developpeurField = new TextField();

        // Create buttons
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button importButton = new Button("Import");
        Button exportButton = new Button("Export");

        // Set button actions
        addButton.setOnAction(event -> addGames());
        updateButton.setOnAction(event -> updateGames());
        deleteButton.setOnAction(event -> deleteGames());
        importButton.setOnAction(event -> importData());
        exportButton.setOnAction(event -> exportData());

        // Create table columns
        TableColumn<Games, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Games, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Games, String> PlateformeColumn = new TableColumn<>("Plateforme");
        TableColumn<Games, Integer> PrixColumn = new TableColumn<>("Prix");
        TableColumn<Games, Integer> NoteColumn = new TableColumn<>("Note");
        TableColumn<Games, Integer> TypeColumn = new TableColumn<>("Type");
        TableColumn<Games, String> DeveloppeurColumn = new TableColumn<>("Developpeur");

        // Configure column mapping
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PlateformeColumn.setCellValueFactory(new PropertyValueFactory<>("plateforme"));
        PrixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        NoteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        DeveloppeurColumn.setCellValueFactory(new PropertyValueFactory<>("developpeur"));

        // Create table view
        tableView = new TableView<>();
        tableView.setItems(gamesList);
        tableView.getColumns().addAll(idColumn, nameColumn, PlateformeColumn, PrixColumn, NoteColumn, TypeColumn, DeveloppeurColumn);

        // Create form layout
        GridPane formLayout = new GridPane();
        formLayout.setPadding(new Insets(10));
        formLayout.setHgap(10);
        formLayout.setVgap(5);
        formLayout.addRow(0, new Label("ID:"), idField);
        formLayout.addRow(1, new Label("Name:"), nameField);
        formLayout.addRow(2, new Label("plateforme:"), plateformeField);
        formLayout.addRow(3, new Label("prix:"), prixField);
        formLayout.addRow(4, new Label("note:"), noteField);
        formLayout.addRow(5, new Label("type:"), typeField);
        formLayout.addRow(6, new Label("developpeur:"), developpeurField);
        formLayout.addRow(7, addButton, updateButton, deleteButton);
        formLayout.addRow(8, importButton, exportButton);
        // Create root layout
        VBox rootLayout = new VBox(tableView, formLayout);
        VBox.setVgrow(tableView, Priority.ALWAYS);

        // Create scene
        Scene scene = new Scene(rootLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Load initial data
        loadGames();
    }

    private void loadGames() {
        List<Games> gamess = gamesService.findAll();
        gamesList.clear();
        gamesList.addAll(gamess);
    }

    private void addGames() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String plateforme = plateformeField.getText();
        double prix = Double.parseDouble(prixField.getText());
        int note = Integer.parseInt(noteField.getText());
        String type = typeField.getText();
        String developpeur = developpeurField.getText();

        Games games = new Games(id, name, plateforme, prix, note, type, developpeur);
        gamesService.save(games);
        gamesList.add(games);

        clearFormFields();
    }

    private void updateGames() {
        Games selectedGames = tableView.getSelectionModel().getSelectedItem();
        if (selectedGames != null) {
            String idText = idField.getText();
            if (!idText.isEmpty() && idText.matches("\\d+")) { // Vérification de la validité du texte
                int id = Integer.parseInt(idText);
                String name = nameField.getText();
                String plateforme = plateformeField.getText();
                double prix = Double.parseDouble(prixField.getText());
                int note = Integer.parseInt(noteField.getText());
                String type = typeField.getText();
                String developpeur = developpeurField.getText();

                selectedGames.setId(id);
                selectedGames.setName(name);
                selectedGames.setPlateforme(plateforme);
                selectedGames.setPrix(prix);
                selectedGames.setNote(note);
                selectedGames.setType(type);
                selectedGames.setDeveloppeur(developpeur);

                gamesService.update(selectedGames);
                loadGames();

                clearFormFields();
            } else {
                System.err.println("Invalid ID format");
            }
        }
    }



    private void deleteGames() {
        List<Games> selectedGamesList = new ArrayList<>(tableView.getSelectionModel().getSelectedItems());
        for (Games selectedGames : selectedGamesList) {
            gamesService.remove(selectedGames);
        }

        // Mise à jour de l'affichage
        loadGames();

        clearFormFields();
    }




    private void importData() {
        supprimerTous();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            String filePath = file.getAbsolutePath();
            String extension = getFileExtension(file.getName());

            switch (extension) {
                case "json":
                    gamesService.importFromJson(filePath);
                    break;
                case "xls":
                    gamesService.importFromExcel(filePath);
                    break;
                case "txt":
                    gamesService.importFromText(filePath);
                    break;
                default:
                    System.err.println("Unsupported file format: " + extension);
            }


            loadGames();


        }
    }

    private void exportData() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            String filePath = file.getAbsolutePath();
            String extension = getFileExtension(file.getName());

            switch (extension) {
                case "json":
                    gamesService.exportToJson(filePath);
                    break;
                case "xls":
                    gamesService.exportToExcel(filePath);
                    break;
                case "txt":
                    gamesService.exportToText(filePath);
                    break;
                default:
                    System.err.println("Unsupported file format: " + extension);
            }
        }
    }


    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }

    private void clearFormFields() {
        idField.clear();
        nameField.clear();
        plateformeField.clear();
        prixField.clear();
        noteField.clear();
        typeField.clear();
        developpeurField.clear();
    }
    public List<Games> GetAll(){
            return gamesService.findAll();
    }
    public void supprimerTous(){
        for (Games games:GetAll())
            gamesService.remove(games);

    }
}
