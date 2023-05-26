package cars_data_com;

import DTOs.CarDTO;
import constants.Constants;
import helpers.Helpers;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ContentParser {

    private Helpers helpers;


    public CarDTO parseData(String fileName, String content, String imageUrl) {

        CarDTO car = new CarDTO();

        car.setId("c_" + parseData(fileName, Regex.regexId));

        String manufacturer = parseData(content, Regex.regexManufacturer);
        car.setManufacturer(manufacturer);
        String type = parseData(content, Regex.regexManufacturerWithType).replace(manufacturer + " ", "");
        car.setType(type);

        String acceleration = parseData(content, Regex.regexAcceleration).replace(",", ".");
        car.setAcceleration(tryParseToNumber(acceleration).doubleValue());

        String doorsAndBody = parseData(content, Regex.regexDoorsAndBody);
        int doors = tryParseToNumber(parseData(doorsAndBody, Regex.regexDoors)).intValue();
        String body = parseData(doorsAndBody, Regex.regexBody);
        car.setDoors(doors);
        car.setBody(convertBodyType(body));

        String power = parseData(content, Regex.regexPower);
        car.setPowerKW(tryParseToNumber(parseData(power, Regex.regexPowerKW)).intValue());
        car.setPowerHP(tryParseToNumber(parseData(power, Regex.regexPowerHP)).intValue());

        int weight = tryParseToNumber(parseData(content, Regex.regexWeight)).intValue();
        int width = tryParseToNumber(parseData(content, Regex.regexWidth)).intValue();
        int height = tryParseToNumber(parseData(content, Regex.regexHeight)).intValue();
        car.setWeight(weight);
        car.setWidth(width);
        car.setHeight(height);
        car.setCornering(Constants.calculatecornering(width, height, weight));

        car.setDriveWheel(getDriveWheel(content));
        car.setSeats(tryParseToNumber(parseData(content, Regex.regexSeats)).intValue());
        car.setCountry(Constants.country);
        car.setFuelType(parseData(content, Regex.regexFuelType));
        car.setMaxTorque(tryParseToNumber(parseData(content, Regex.regexMaxTorque)).intValue());
        car.setYear(tryParseToNumber(parseData(content, Regex.regexYear)).intValue());
        car.setTopSpeed(tryParseToNumber(parseData(content, Regex.regexTopSpeed)).intValue());
        car.setLength(tryParseToNumber(parseData(content, Regex.regexLength)).intValue());
        car.setGroundClearance(tryParseToNumber(parseData(content, Regex.regexGroundClearance)).intValue());
        car.setAbs(parseData(content, Regex.regexABS));
        car.setTractionControl(parseData(content, Regex.regexTractionControl));
        car.setImageUrl(imageUrl);
        car.setLogoURL(Constants.logoUrl);
        car.setEngineCapacity(tryParseToNumber(parseData(content, Regex.regexEngineCapacity)).intValue());
        car.setCarPageUrl(parseData(content, Regex.regexCarPageUrl));
        car.setObjectPositionHorizontal(Constants.objectPositionHorizontal);
        car.setObjectPositionVertical(Constants.objectPositionVertical);
        car.setObjectWidth(Constants.objectWidth);
        car.setObjectHeight(Constants.objectHeight);
        car.setGear1st(tryParseToNumber(parseData(content, Regex.regex1stGear).replace(",", ".")).doubleValue());
        car.setGear2nd(tryParseToNumber(parseData(content, Regex.regex2ndGear).replace(",", ".")).doubleValue());
        car.setGear3rd(tryParseToNumber(parseData(content, Regex.regex3rdGear).replace(",", ".")).doubleValue());
        car.setGear4th(tryParseToNumber(parseData(content, Regex.regex4thGear).replace(",", ".")).doubleValue());
        car.setGear5th(tryParseToNumber(parseData(content, Regex.regex5thGear).replace(",", ".")).doubleValue());
        car.setGear6th(tryParseToNumber(parseData(content, Regex.regex6thGear).replace(",", ".")).doubleValue());
        car.setFinalDrive(tryParseToNumber(parseData(content, Regex.regexFinalDrive).replace(",", ".")).doubleValue());
        car.setFuelTankCapacity((tryParseToNumber(parseData(content, Regex.regexFuelTankCapacity)).intValue()));

        return car;
    }

    private String getDriveWheel(String content) {

        String driveWheel = parseData(content, Regex.regexDriveWheel);

        if (driveWheel.equals("front+rear")) {

            driveWheel = "AWD";

        } else if (driveWheel.equals("front")) {

            driveWheel = "FWD";

        } else if (driveWheel.equals("rear")) {

            driveWheel = "RWD";

        }
        return driveWheel;
    }


    private String convertBodyType(String bodyType) {

        String result = bodyType
                .replaceAll("&[Ee]acute;", "e")
                .replaceAll("[Ss]tation wagon", "Estate");

        return result
                .substring(0, 1).toUpperCase()
                .concat(result.substring(1));
    }


    private Number tryParseToNumber(String number) {

        return helpers.tryParseToNumber(number);
    }


    private String parseData(String content, String regex) {

        return helpers.parseData(content, regex);
    }
}
