package osszesauto_hu;

import DTOs.CarDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import helpers.DateTimeFormattedString;
import helpers.Helpers;
import lombok.AllArgsConstructor;
import java.io.*;
import java.util.List;

@AllArgsConstructor
public class OsszesAutoHuMain {

    private Controller controller;

    private final String carUrlsSourceFile = "/cars/osszesauto-hu/waitingPagesUrl.txt";

    private final String imageUrlsSourceFile = "/cars/osszesauto-hu/waitingImagesUrl.txt";


    public static void main(String[] args) throws IOException {

        OsszesAutoHuMain main = new OsszesAutoHuMain(new Controller(new Helpers(), new ContentParser(new Helpers())));

        List<CarDTO> cars = main.controller.getCars(main.carUrlsSourceFile, main.imageUrlsSourceFile);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cars);

        System.out.println(json);
        main.controller.getHelpers().writeToFile(json, "src/main/resources/cars/osszesauto-hu/" + DateTimeFormattedString.now() + ".json");



    }
}
