package carspecs_us;

import DTOs.CarDTO;
import helpers.Helpers;
import lombok.AllArgsConstructor;
import lombok.Data;
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
            String regexFileName = "(?<=cars\\/).*?(?=#.*|$)";
            String fileName = helpers.createFileName(url, regexFileName).replace("/", "_");

            // eles, webrol olvas
            //String htmlContent = getHtmlAllContent(url);
            //cars.add(contentParser.parsedData(fileName, htmlContent, imageLinks.get(i)));

            // tesztre, fileba irja a html contentet autonkent
            //getHelpers().writeToFile(htmlContent, "./src/main/resources/cars/auto-types-com/files/" + fileName);

            // tesztre, filebol olvas
            cars.add(contentParser.parsedData(fileName, helpers.readDatasFromFile(dirName, fileName), imageLinks.get(i)));
        }

        return cars;
    }


    public String getHtmlAllContent(String uri) {

        String html = helpers.getHtmlContent(uri);


        return html;
    }


    public List<String> getDataLinks(String fileName) {

        return helpers.readUrlsFromFile(getPageUrlInputStream(fileName));
    }


    public List<String> getImageLinks(String fileName) {

        return helpers.readImageUrlsFromFile(getImageUrlInputStream(fileName));
    }


    private InputStream getPageUrlInputStream(String pageUrlsFileName) {

        return CarSpecsUSMain.class.getResourceAsStream(pageUrlsFileName);
    }


    private InputStream getImageUrlInputStream(String imageUrlsFileName) {

        return CarSpecsUSMain.class.getResourceAsStream(imageUrlsFileName);
    }
}
