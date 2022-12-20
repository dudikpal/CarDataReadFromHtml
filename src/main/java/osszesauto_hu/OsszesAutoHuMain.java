package osszesauto_hu;

import DTOs.CarDTO;
import auto_data_net.Envi;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Constants;
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

        List <CarDTO> cars = main.getCarsList();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cars);
        System.out.println(json);
        main.controller.getHelpers().writeToFile(json, Constants.targetDirNamePrefix + Envi.dirName + "/" + DateTimeFormattedString.now() + ".json");
    }

    public List<CarDTO> getCarsList() {
        return controller.getCars(carUrlsSourceFile, imageUrlsSourceFile);
    }
}
