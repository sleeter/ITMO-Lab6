package org.example.utilities;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import org.example.data.*;

public class FileManager {
    private String filename;
    public FileManager(){}
    public FileManager(String filename) {
        this.filename = filename;
    }

    public void setFilename(String file) {
        this.filename = file;
    }
    private static List<String[]> toStringArray(LinkedHashSet<Organization> organizations) {
        List<String[]> records = new ArrayList<String[]>();

        Iterator<Organization> it = organizations.iterator();
        while (it.hasNext()) {
            Organization organization = it.next();
            records.add(new String[] {String.valueOf(organization.getId()), organization.getName(), String.valueOf(organization.getCoordinates().getX()),
                    String.valueOf(organization.getCoordinates().getY()), String.valueOf(organization.getCreationDate()), String.valueOf(organization.getAnnualTurnover()),
                    organization.getFullName(), String.valueOf(organization.getEmployeesCount()), String.valueOf(organization.getType()),
                    organization.getPostalAddress().getStreet()});
        }
        return records;
    }

    public void writeCollectionsOPENCSV(LinkedHashSet<Organization> organizations){
        try {
            File file = new File(filename);
            FileWriter outputFile = new FileWriter(file);
            CSVWriter csvWriter = new CSVWriter(outputFile);
            List<String[]> arr = toStringArray(organizations);
            for (String[] ar : arr){
                csvWriter.writeNext(ar);
            }
            csvWriter.close();
        }catch (java.io.IOException e){

        }

    }
    public LinkedHashSet<Organization> readCollectionsOPENCSV(){
        LinkedHashSet<Organization> organizations = new LinkedHashSet<>();
        try{
            FileReader filereader = new FileReader(new File(filename));
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                Integer id = Integer.parseInt(nextRecord[0]);
                String name = nextRecord[1];
                float x = Float.parseFloat(nextRecord[2]);
                Double y = Double.parseDouble(nextRecord[3]);
                Coordinates coordinates = new Coordinates(x, y);
                String date = nextRecord[4];
                LocalDate creationDate = LocalDate.parse(date);
                float annualTurnover = Float.parseFloat(nextRecord[5]);
                String fullName = nextRecord[6];
                Integer employeesCount = Integer.parseInt(nextRecord[7]);
                OrganizationType type = OrganizationType.getOrganizationType(nextRecord[8]);
                Address postalAddress = new Address(nextRecord[9]);
                Organization organization = new Organization(id, name, coordinates, creationDate, annualTurnover, fullName, employeesCount, type, postalAddress);
                organizations.add(organization);
            }
            return  organizations;
        }catch (FileNotFoundException e){

        }catch (com.opencsv.exceptions.CsvValidationException e){

        }catch (java.io.IOException e){

        }
        return new LinkedHashSet<>();
    }
}
