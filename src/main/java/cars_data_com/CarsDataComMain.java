package cars_data_com;

import DTOs.CarDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.DateTimeFormattedString;
import helpers.Helpers;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class CarsDataComMain {

    private Controller controller;

    private final String carUrlsSourceFile = "/cars/cars-data-com/waitingPagesUrl.txt";

    private final String imageUrlsSourceFile = "/cars/cars-data-com/waitingImagesUrl.txt";


    public static void main(String[] args) throws IOException {

        CarsDataComMain main = new CarsDataComMain(new Controller(new Helpers(), new ContentParser(new Helpers())));

        List<CarDTO> cars = main.controller.getCars(main.carUrlsSourceFile, main.imageUrlsSourceFile);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cars);

        System.out.println(json);
        main.controller.getHelpers().writeToFile(json, "src/main/resources/cars/cars-data-com/" + DateTimeFormattedString.now() + ".json");
    }
}
