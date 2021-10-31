package de.programming.challenge;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This controller takes over the work with files such as reading the data from a file
 * and working with this data such as returning the smallest value from a column.
 *
 * @author cananaucristian
 */
public class FileController {

    /**
     * Read the data from a file and returns the smallest value from a column.
     *
     * @param filePath Path location of the file.
     * @param firstColumn First column name as String.
     * @param secondColumn Second column name as String.
     * @param resultColumn Name of the column that should return the result.
     * @param isAbsoluteDifference Should the negative number be converted into a positive one.
     * @return Returns the smallest value as String from a column.
     * @throws IOException
     */
    public String getSmallestColumnValueFromFileData(String filePath,
                                                     String firstColumn,
                                                     String secondColumn,
                                                     String resultColumn,
                                                     boolean isAbsoluteDifference) throws IOException {
        FilesService filesService = new FilesService();

        List<Map<String, Object>> fileData = filesService.getFileData(filePath);

        int minIndex = NumberUtil.minIndex(getDifferenceBetweenTwoColumnsAsList(fileData, firstColumn, secondColumn, isAbsoluteDifference));

        return (String) fileData.get(minIndex).get(resultColumn);
    }

    /**
     * Returns the difference between two columns from a map in a list.
     *
     *
     * @param fileData The data from a File as a list with a map.
     * @param firstColumn First column name as String.
     * @param secondColumn Second column name as String.
     * @param isAbsoluteDifference Should the negative number be converted into a positive one.
     * @return Returns a list with integers. The integers are the difference between two column.
     */
    private List<Integer> getDifferenceBetweenTwoColumnsAsList(List<Map<String, Object>> fileData,
                                                               String firstColumn,
                                                               String secondColumn,
                                                               boolean isAbsoluteDifference) {
        return fileData.stream()
                .map(d -> NumberUtil.getDifferenceBetweenTwoNumbers(Integer.parseInt((String) d.get(firstColumn)), Integer.parseInt((String) d.get(secondColumn)), isAbsoluteDifference))
                .collect(Collectors.toList());
    }
}
