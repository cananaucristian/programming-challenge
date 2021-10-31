package de.programming.challenge;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This service takes over the work with files such as reading in various file types
 * and outputting the data of the files.
 *
 * @author cananaucristian
 */
public class FilesService {
    private static final String JSON_FILE_EXTENSION = "json";

    /**
     * Read the file by his path and returns the file data.
     * The Map has the names of the columns and their values.
     *
     * @param filePath Path location of the file.
     * @return Returns the data of the file as a mapping in a list.
     * @throws IOException
     */
    public List<Map<String, Object>> getFileData(String filePath) throws IOException {
        File file = new File(filePath);

        List<Map<String, Object>> fileData = new ArrayList<>();

        if(JSON_FILE_EXTENSION.equals(getFileExtension(file.getName()))) {
            fileData.add(readObjectsFromJson(file));
        }else {
            fileData.addAll(readObjectsFromCsv(file));
        }

        return fileData;
    }

    /**
     * Read the data from a JSON file.
     *
     * @param file The JSON-File.
     * @return Returns a map with the column name as the key and its data as the value.
     * @throws IOException
     */
    private Map<String, Object> readObjectsFromJson(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);

        return mapper.readValue(file, mapType);
    }

    /**
     * Read the data from a CSV file.
     *
     * @param file The CSV-File.
     * @return Returns a map in a list with the column name as the key and its data as the value.
     * @throws IOException
     */
    private List<Map<String, Object>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<String, Object>> mappingIterator = csvMapper.readerFor(Map.class).with(csvSchema).readValues(file);

        return mappingIterator.readAll();
    }

    /**
     * @param fileName The name of the file.
     * @return Returns the file extension of the given filename.
     */
    private String getFileExtension(String fileName) {
        return Arrays.stream(fileName.split("\\.")).reduce((a, b) -> b).orElse(null);
    }
}
