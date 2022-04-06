package auto_data_net;

import DTOs.CarDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Constants;
import helpers.DateTimeFormattedString;
import helpers.Helpers;
import lombok.AllArgsConstructor;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class AutoDataNetMain {

    private Controller controller;

    private final String carUrlsSourceFile = Constants.sourceDirNamePrefix + Envi.dirName + Constants.pageUrlSourceFile;

    private final String imageUrlsSourceFile = Constants.sourceDirNamePrefix + Envi.dirName + Constants.imageUrlSourceFile;


    public static void main(String[] args) throws IOException {

        AutoDataNetMain main = new AutoDataNetMain(new Controller(new Helpers(), new ContentParser(new Helpers())));

        List <CarDTO> cars = main.controller.getCars(main.carUrlsSourceFile, main.imageUrlsSourceFile);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cars);

        main.controller.getHelpers().writeToFile(json, Constants.targetDirNamePrefix + Envi.dirName + "/" + DateTimeFormattedString.now() + ".json");
    }
}
