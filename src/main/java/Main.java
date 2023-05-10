import DTOs.CarDTO;
import auto_data_net.AutoDataNetMain;
import auto_data_net.ContentParser;
import auto_data_net.Controller;
import auto_data_net.Envi;
import cars_data_com.CarsDataComMain;
import carspecs_us.CarSpecsUSMain;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Constants;
import helpers.DateTimeFormattedString;
import helpers.Helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static final String CarUrlsSourceFile = "/cars/cars-data-com/waitingPagesUrl.txt";

    private static final String ImageUrlsSourceFile = "/cars/cars-data-com/waitingImagesUrl.txt";

    private static List<CarDTO> cars = new ArrayList<>();


    public static void main(String[] args) throws IOException {


        AutoDataNetMain autoDataNet = new AutoDataNetMain(new Controller(new Helpers(), new ContentParser(new Helpers())));
        List<CarDTO> autoDataNetCars = autoDataNet.getCarsList();
        CarsDataComMain carsDataComMain = new CarsDataComMain(new cars_data_com.Controller(new Helpers(), new cars_data_com.ContentParser(new Helpers())));
        List<CarDTO> carsDataComCars = carsDataComMain.getCarsList();
        CarSpecsUSMain carSpecsUs = new CarSpecsUSMain(new carspecs_us.Controller(new Helpers(), new carspecs_us.ContentParser(new Helpers())));
        List<CarDTO> carSpecsUsCars = carSpecsUs.getCarsList();
        List<CarDTO> allCars = new ArrayList <>();
        allCars.addAll(autoDataNetCars);
        allCars.addAll(carsDataComCars);
        allCars.addAll(carSpecsUsCars);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(allCars);

        new Helpers().writeToFile(json, Constants.targetAllCarJsonDirNamePrefix + "/" + DateTimeFormattedString.now() + ".json");

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


