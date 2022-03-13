import DTOs.CarDTO;
import cars_data_com.ContentParser;

import java.util.ArrayList;
import java.util.List;


public class Main {

    private static final String CarUrlsSourceFile = "/cars/cars-data-com/waitingPagesUrl.txt";

    private static final String ImageUrlsSourceFile = "/cars/cars-data-com/waitingImagesUrl.txt";

    private static List<CarDTO> cars = new ArrayList<>();


    public static void main(String[] args) {

        // read data from download waiting file, and write to local file
        /*for (String url : readUrlsFromFile()) {

            String fileName = createFileNameToSave(url);
            String htmlContent = getHtmlAllContent(url);

            writeToFile(htmlContent, fileName);
        }*/

        // read datas from local content file
        /*ContentParser cp = new ContentParser();
        List<String> fileNames = getFileNames();
        List<String> imagesUrl = readImageUrlsFromFile();

        for (int i = 0; i < fileNames.size(); i++) {

            CarDTO car = cp.parsedDatas(fileNames.get(i), imagesUrl.get(i));

            cars.add(car);
        }
        System.out.println(cars);*/
    }












}


