package com.emsi.services;

import com.emsi.dao.GamesDao;
import com.emsi.dao.impl.GamesDaoImp;
import com.emsi.entities.Games;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

public class GamesService {

        private final GamesDao gamesDao = new GamesDaoImp();

        public List<Games> findAll() {
            return gamesDao.findAll();
        }
    public Games getGamesById(int id) {
        return gamesDao.findById(id);
    }

        public void save(Games games) {
            gamesDao.insert(games);
        }

        public void update(Games games) {
            try {
                if (games != null) {
                    gamesDao.update(games);
                     System.out.println(games);
                    System.out.println("Games updated successfully.");
                } else {
                    throw new IllegalArgumentException("Error updating astronaut: Games cannot be null.");
                }
            } catch (Exception e) {
                System.err.println("Error updating games: " + e.getMessage());
            }
        }

        public void remove(Games games) {
            try {
                if (games != null) {
                    gamesDao.deleteById(games.getId());
                    System.out.println("Games removed successfully.");
                } else {
                    throw new IllegalArgumentException("Error removing games: Astronaut cannot be null.");
                }
            } catch (Exception e) {
                System.err.println("Error removing games: " + e.getMessage());
            }
        }

    public void importFromExcel(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String plateforme = row.getCell(2).getStringCellValue();
                double prix = (double) row.getCell(3).getNumericCellValue();
                int note = (int) row.getCell(4).getNumericCellValue();
                String type = (String) row.getCell(5).getStringCellValue();
                String developpeur = row.getCell(6).getStringCellValue();

                Games games = new Games(id, name, plateforme,prix, note, type, developpeur);
                gamesDao.insert(games);
            }

            fis.close();

            System.out.println("Data imported from Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error importing data from Excel: " + e.getMessage());
        }
    }

    public void importFromText(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                int id = Integer.parseInt(values[0]);
                String name = values[1];
                String plateforme = values[2];
                double prix = Double.parseDouble(values[3]);
                int note = Integer.parseInt(values[4]);
                String type = values[5];
                String developpeur = values[6];

                Games games = new Games(id, name, plateforme,prix, note, type, developpeur);
                gamesDao.insert(games);
            }

            reader.close();

            System.out.println("Data imported from text file successfully.");
        } catch (IOException e) {
            System.err.println("Error importing data from text file: " + e.getMessage());
        }
    }

    public void importFromJson(String jsonFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Games> games = objectMapper.readValue(new File(jsonFilePath), new TypeReference<List<Games>>() {});

            for (Games game : games) {
                gamesDao.insert(game);
            }

            System.out.println("Data imported from JSON: " + jsonFilePath);
        } catch (IOException e) {
            System.err.println("Error importing data from JSON: " + e.getMessage());
        }
    }

    public void exportToExcel(String filePath) {
        try {
            List<Games> games = gamesDao.findAll();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Games");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Name");
            headerRow.createCell(2).setCellValue("Plateforme");
            headerRow.createCell(3).setCellValue("Prix");
            headerRow.createCell(4).setCellValue("Note");
            headerRow.createCell(5).setCellValue("Type");
            headerRow.createCell(6).setCellValue("Devellopeur");

            int rowNum = 1;
            for (Games games1 : games) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue(games1.getId());
                dataRow.createCell(1).setCellValue(games1.getName());
                dataRow.createCell(2).setCellValue(games1.getPlateforme());
                dataRow.createCell(3).setCellValue(games1.getPrix());
                dataRow.createCell(4).setCellValue(games1.getNote());
                dataRow.createCell(5).setCellValue(games1.getType());
                dataRow.createCell(6).setCellValue(games1.getDeveloppeur());
            }

            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);

            fos.close();

            System.out.println("Data exported to Excel successfully.");
        } catch (IOException e) {
            System.err.println("Error exporting data to Excel: " + e.getMessage());
        }
    }

    public void exportToText(String filePath) {
        try {
            List<Games> Gamess = gamesDao.findAll();

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (Games games : Gamess) {
                String sb = games.getId() + "," +
                        games.getName() + "," +
                        games.getPlateforme() + "," +
                        games.getPrix() + "," +
                        games.getNote() + "," +
                        games.getType() + "," +
                        games.getDeveloppeur() + "\n";
                writer.write(sb);
            }

            writer.close();

            System.out.println("Data exported to text file successfully.");
        } catch (IOException e) {
            System.err.println("Error exporting data to text file: " + e.getMessage());
        }
    }
    public void exportToJson(String filePath) {
        try {
            List<Games> Games = gamesDao.findAll();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(Games);

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(json);
            writer.close();

            System.out.println("Data exported to JSON file successfully.");
        } catch (IOException e) {
            System.err.println("Error exporting data to JSON file: " + e.getMessage());
        }
    }


}


