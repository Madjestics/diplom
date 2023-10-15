package diplom.buisness;

import diplom.data.exceptions.FilesException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Метод для работы с файлами (считывания, записи)
 */
public class FileService {

    public List<Double> importDataMas(String file) throws FileNotFoundException {
        List<Double> values = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                values.add(Double.valueOf(line));
            }
            return values;
        } catch (IOException e) {
            throw new FilesException("Ошибка чтения файлов");
        }
    }

}
