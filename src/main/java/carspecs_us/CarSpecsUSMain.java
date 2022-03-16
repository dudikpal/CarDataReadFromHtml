package carspecs_us;

import DTOs.CarDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.DateTimeFormattedString;
import helpers.Helpers;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class CarSpecsUSMain {

    private Controller controller;

    private final String carUrlsSourceFile = "/cars/carspecs-us/waitingPagesUrl.txt";

    private final String imageUrlsSourceFile = "/cars/carspecs-us/waitingImagesUrl.txt";


    public static void main(String[] args) throws IOException {

        CarSpecsUSMain main = new CarSpecsUSMain(new Controller(new Helpers(), new ContentParser(new Helpers())));

        List<CarDTO> cars = main.controller.getCars(main.carUrlsSourceFile, main.imageUrlsSourceFile);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cars);

        System.out.println(json);
        main.controller.getHelpers().writeToFile(json, "src/main/resources/cars/carspecs-us/" + DateTimeFormattedString.now() + ".json");
    }
}
