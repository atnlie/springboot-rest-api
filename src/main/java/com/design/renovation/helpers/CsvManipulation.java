package com.design.renovation.helpers;

import com.design.renovation.models.entities.Contact;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvManipulation {
  private static final String TYPE = "text/csv";

  public static boolean hasCSVFormat(MultipartFile file) {
    return TYPE.equals(file.getContentType());
  }

  public static List<Contact> csvToContacts(InputStream inputStream) {
    try {
      BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//      CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.builder().setDelimiter(";").build());
      CSVParser csvParser = new CSVParser(fileReader,
          CSVFormat.DEFAULT.builder().setDelimiter(";")
              .setSkipHeaderRecord(true)
              .setIgnoreSurroundingSpaces(true)
              .setIgnoreHeaderCase(true)
              .build());
      List<Contact> contacts = new ArrayList<>();
      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
        if((csvRecord.getRecordNumber() > 1)) { // assume skip header doesn't works
          Contact contact = new Contact();
          contact.setId(Long.parseLong(csvRecord.toList().get(0)));
          contact.setName(csvRecord.toList().get(1));
          contact.setEmail(csvRecord.toList().get(2));
          contacts.add(contact);
        }
      }
      csvParser.close();
      return contacts;
    } catch (IOException err) {
      throw new RuntimeException("Fail to parse CSV file " + err.getMessage());
    }
  }
}
