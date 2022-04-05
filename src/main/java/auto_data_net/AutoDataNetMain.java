package auto_data_net;

import DTOs.CarDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.DateTimeFormattedString;
import helpers.Helpers;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class AutoDataNetMain {

    private Controller controller;

    private final String carUrlsSourceFile = "/cars/auto-data-net/waitingPagesUrl.txt";

    private final String imageUrlsSourceFile = "/cars/auto-data-net/waitingImagesUrl.txt";


    public static void main(String[] args) throws IOException {

        AutoDataNetMain main = new AutoDataNetMain(new Controller(new Helpers(), new ContentParser(new Helpers())));

        List <CarDTO> cars = main.controller.getCars(main.carUrlsSourceFile, main.imageUrlsSourceFile);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cars);

        main.controller.getHelpers().writeToFile(json, "src/main/resources/cars/auto-data-net/" + DateTimeFormattedString.now() + ".json");
    }
}
