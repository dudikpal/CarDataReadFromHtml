package cars_data_com;

import DTOs.CarDTO;
import helpers.Helpers;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
public class Controller {

    private Helpers helpers;

    private ContentParser contentParser;


    public List<CarDTO> getCars(String pageUrlsFileName, String imageUrlsFileName) {

        List<String> dataLinks = getDataLinks(pageUrlsFileName);
        List<String> imageLinks = getImageLinks(imageUrlsFileName);
        List<CarDTO> cars = new ArrayList<>();

        Pattern p = Pattern.compile(".*\\/(?=\\w+\\.\\w*)");
        Matcher m = p.matcher(pageUrlsFileName);
        m.find();
        String dirName = "src/main/resources" + m.group() + "/files/";

        for (int i = 0; i < dataLinks.size(); i++) {

            String url = dataLinks.get(i);
            String regexfileName = "(?<=en/).*";
            String fileName = helpers.createFileName(url, regexfileName).replace("-specs/", "_");

            // eles, webrol olvas
            //String htmlContent = getHtmlAllContent(url);
            //cars.add(contentParser.parsedData(fileName, htmlContent, imageLinks.get(i)));

            // tesztre, fileba irja a html contentet autonkent
            //helpers.writeToFile(htmlContent, "./src/main/resources/cars/cars-data-com/files/" + fileName);

            // tesztre, filebol olvas
            cars.add(contentParser.parsedData(fileName, helpers.readDatasFromFile(dirName, fileName), imageLinks.get(i)));
        }

        return cars;
    }


    public String getHtmlAllContent(String uri) {

        String firstPartHtml = helpers.getHtmlContent(uri + "/tech");
        String secondPartHtml = helpers.getHtmlContent(uri + "/sizes");
        String thirdPartHtml = helpers.getHtmlContent(uri + "/options");

        return firstPartHtml + secondPartHtml + thirdPartHtml;
    }


    public List<String> getDataLinks(String fileName) {

        return helpers.readUrlsFromFile(getPageUrlInputStream(fileName));
    }


    public List<String> getImageLinks(String fileName) {

        return helpers.readImageUrlsFromFile(getImageUrlInputStream(fileName));
    }


    private InputStream getPageUrlInputStream(String pageUrlsFileName) {

        return CarsDataComMain.class.getResourceAsStream(pageUrlsFileName);
    }


    private InputStream getImageUrlInputStream(String imageUrlsFileName) {

        return CarsDataComMain.class.getResourceAsStream(imageUrlsFileName);
    }
}
