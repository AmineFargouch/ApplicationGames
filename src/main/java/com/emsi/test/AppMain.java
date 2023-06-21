package com.emsi.test;

import com.emsi.entities.Games;
import com.emsi.services.UserService;

public class AppMain {
    public static void main(String[] args) {
      //  AstronauteService astronauteService = new AstronauteService();
        UserService userService = new UserService();

  /*      // Import data from Excel
        String excelFilePath = "src/main/resources/inputDataExcel.xls";
        astronauteService.importFromExcel(excelFilePath);

        // Import data from text file
        String textFilePath = "src/main/resources/inputData.txt";
        astronauteService.importFromText(textFilePath);

        // Import data from JSON file
        String jsonFilePath = "src/main/resources/inputDataJson.json";
        astronauteService.importFromJson(jsonFilePath);

        // Export data to Excel
        String excelExportPath = "src/main/resources/outputDataExcel.xls";
        astronauteService.exportToExcel(excelExportPath);
        // Export data to text file
        String textExportPath = "src/main/resources/outputDataText.txt";
        astronauteService.exportToText(textExportPath);
        // Export data to text file
        String jsonExportPath = "src/main/resources/OutputDataJson.json";
        astronauteService.exportToJson(jsonExportPath);

       // Create a new Astronaute
      Astronaute newAstronaute = new Astronaute(1, "John Doe", 2, "nasa", 75, 5, "7");
      astronauteService.save(newAstronaute);

       // Update an existing Astronaute
       Astronaute existingAstronaute = astronauteService.getAstronauteById(1);
        existingAstronaute.setName("John Doe");
        existingAstronaute.setAgence("NASA");
        existingAstronaute.setHeight(180);
        existingAstronaute.setWeight(75);
        existingAstronaute.setYearsOfExperience(5);
        existingAstronaute.setGrade("commando");

       astronauteService.update(existingAstronaute);
*/
        // Delete an Astronaute
        //Astronaute astronauteToDelete = astronauteService.getAstronauteById(169);;
        //astronauteService.remove(astronauteToDelete);

        // Print all Astronaute
//        System.out.println("All Astronaute:");
//        astronauteService.findAll().forEach(System.out::println);
        // Create a new Astronaute

    }
}
