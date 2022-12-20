package auto_data_net;

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

        String type = parseData(content, Regex.regexTypePart1) + " " + parseData(content, Regex.regexTypePart2);
        car.setType(type);

        String acceleration = parseData(content, Regex.regexAcceleration).replace(",", ".");
        car.setAcceleration(tryParseToNumber(acceleration).doubleValue());

        String driveWheel = parseData(content, Regex.regexDriveWheel);
        car.setDriveWheel(setDriveWheel(driveWheel));

        int doors = tryParseToNumber(parseData(content, Regex.regexDoors)).intValue();
        car.setDoors(doors);

        String body = parseData(content, Regex.regexBody);
        car.setBody(convertBodyType(body));

        int hp = tryParseToNumber(parseData(content, Regex.regexPowerHP)).intValue();
        car.setPowerHP(hp);
        car.setPowerKW((int) (hp * Constants.hpToKw));

        int weight = tryParseToNumber(parseData(content, Regex.regexWeight)).intValue();
        int width = tryParseToNumber(parseData(content, Regex.regexWidth)).intValue();
        int height = tryParseToNumber(parseData(content, Regex.regexHeight)).intValue();
        car.setWeight(weight);
        car.setWidth(width);
        car.setHeight(height);
        car.setCornering(Constants.calculatecornering(width, height, weight));

        setAssists(car, parseData(content, Regex.regexAssists));
        car.setSeats(Integer.parseInt(parseData(content, Regex.regexSeats)));
        car.setCountry(Constants.country);
        car.setFuelType(parseData(content, Regex.regexFuelType));
        car.setMaxTorque(tryParseToNumber(parseData(content, Regex.regexMaxTorque)).intValue());
        car.setYear(tryParseToNumber(parseData(content, Regex.regexYear)).intValue());
        car.setTopSpeed(tryParseToNumber(parseData(content, Regex.regexTopSpeed)).intValue());
        car.setLength(tryParseToNumber(parseData(content, Regex.regexLength)).intValue());
        car.setImageUrl(imageUrl);
        car.setLogoURL(Constants.logoUrl);
        car.setEngineCapacity(tryParseToNumber(parseData(content, Regex.regexEngineCapacity)).intValue());
        car.setFuelTankCapacity((tryParseToNumber(parseData(content, Regex.regexFuelTankCapacity)).intValue()));
        car.setCarPageUrl(parseData(content, Regex.regexCarPageUrl));
        car.setObjectPositionHorizontal(Constants.objectPositionHorizontal);
        car.setObjectPositionVertical(Constants.objectPositionVertical);
        car.setObjectWidth(Constants.objectWidth);
        car.setObjectHeight(Constants.objectHeight);
        car.setGroundClearance(-1);
        car.setGear1st(-1.0);
        car.setGear2nd(-1.0);
        car.setGear3rd(-1.0);
        car.setGear4th(-1.0);
        car.setGear5th(-1.0);
        car.setGear6th(-1.0);
        car.setFinalDrive(-1.0);

        return car;
    }


    private String setDriveWheel(String driveWheel) {

        if (driveWheel.startsWith("All wheel drive")) {

            driveWheel = "4WD";

        } else if (driveWheel.equals("Front wheel drive")) {

            driveWheel = "FWD";

        } else if (driveWheel.equals("Rear wheel drive")) {

            driveWheel = "RWD";
        }

        return driveWheel;
    }


    private void setAssists(CarDTO car, String assists) {

        setAbs(car, assists);
        setTc(car, assists);
    }


    private void setTc(CarDTO car, String assists) {

        if (assists.contains("Traction")) {

            car.setTractionControl("yes");

        } else {

            car.setTractionControl("N/A");
        }
    }


    private void setAbs(CarDTO car, String assists) {

        if (assists.contains("ABS")) {

            car.setAbs("yes");

        } else {

            car.setAbs("N/A");

        }
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
