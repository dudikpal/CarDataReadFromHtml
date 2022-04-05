package cars_data_com;

import DTOs.CarDTO;
import DTOs.DataDTO;
import constants.Constants;
import helpers.Helpers;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ContentParser {

    private Helpers helpers;

    private final String regexManufacturer = "(?<=span itemprop=\\\"name\\\">).*?(?=</span>\\s*</a>\\s*<meta itemprop=\\\"position\\\" content=\\\"2\\\")";
    private final String regexManufacturerWithType = "(?<=span itemprop=\\\"name\\\">).*?(?=\\s\\d+</span>\\s*<meta itemprop=\\\"position\\\" content=\\\"5\\\")";
    private final String regexYear = "\\d+(?=</span>\\s*</a>\\s*<meta itemprop=\\\"position\\\" content=\\\"4\\\")";

    private final String regexDoorsAndBody = "(?<=Body Type:</td><td class=\\\"col-6\\\">).*?(?=</td>)";
    private final String regexDoors = "\\d+";
    private final String regexBody = "(?<=,\\s).*";

    private final String regexSeats = "(?<=Number Of Seats:</td><td class=\\\"col-6\\\">)\\d";
    private final String regexDriveWheel = "(?<=Drive Wheel :</td><td class=\\\"col-6 grey\\\">).*?(?=</td>)";
    private final String regexEngineType = "(?<=motor Type:</td><td class=\\\"col-6\\\">).*?(?=</td)";
    private final String regexFuelType = "(?<=Fuel Type:</td><td class=\\\"col-6 grey\\\">).*?(?=</td)";

    private final String regexPower = "(?<=Power:</td><td class=\\\"col-6\\\">).*?(?=</td)";
    private final String regexPowerKW = ".*(?=\\skw)";
    private final String regexPowerHP = "(?<=\\().*(?=\\shp)";

    private final String regexMaxTorque = "(?<=Max Torque:</td><td class=\\\"col-6\\\">).*(?=\\snm)";
    private final String regexTopSpeed = "(?<=Top Speed:</td><td class=\\\"col-6 grey\\\">).*(?=\\skm)";
    private final String regexAcceleration = "(?<=0-100 Km / H:</td><td class=\\\"col-6\\\">).*(?=\\ss)";
    private final String regexWeight = "(?<=Curb Weight:</td><td class=\\\"col-6 grey\\\">)\\d+";
    private final String regexLength = "(?<=Length:</td><td class=\\\"col-6 grey\\\">)\\d+";
    private final String regexWidth = "(?<=Width:</td><td class=\\\"col-6\\\">)\\d+";
    private final String regexHeight = "(?<=Height:</td><td class=\\\"col-6 grey\\\">)\\d+";
    private final String regexGroundClearance = "(?<=Ground Clearance:</td><td class=\\\"col-6 grey\\\">)\\d+";
    private final String regexABS = "(?<=ABS:</td><td class=\\\"col-6\\\">)\\w+";
    private final String regexTractionControl = "(?<=Traction Control:</td><td class=\\\"col-6\\\">)\\w+";
    private final String regexLogoUrl = "(?<=<img src=\\\").*(?=\\\" width=\\\"80\\\" height=\\\"80\\\" alt=)";
    private final String regexEngineCapacity = "(?<=Engine Capacity:<\\/td><td class=\\\"col-6 grey\\\">)\\d+";
    private final String regexId = "[\\w-]+$";
    private final String regexCarPageUrl = "(?<=<link rel=\\\"canonical\\\" href=\\\").*(?=\\\"\\s/>)";
    private final String regex1stGear = "(?<=1st Gear:</td><td class=\\\"col-6 grey\\\">).*?(?=:1<\\/td>)";
    private final String regex2ndGear = "(?<=2nd Gear:</td><td class=\\\"col-6\\\">).*?(?=:1<\\/td>)";
    private final String regex3rdGear = "(?<=3rd Gear:</td><td class=\\\"col-6 grey\\\">).*?(?=:1<\\/td>)";
    private final String regex4thGear = "(?<=4th Gear:</td><td class=\\\"col-6\\\">).*?(?=:1<\\/td>)";
    private final String regex5thGear = "(?<=5th Gear:</td><td class=\\\"col-6 grey\\\">).*?(?=:1<\\/td>)";
    private final String regex6thGear = "(?<=6th Gear:</td><td class=\\\"col-6\\\">).*?(?=:1<\\/td>)";
    private final String regexFinalDrive = "(?<=Final Drive:</td><td class=\\\"col-6 grey\\\">).*?(?=:1<\\/td>)";
    private final String regexFuelTankCapacity = "(?<=Fuel Tank Capacity:</td><td class=\\\"col-6\\\">).*(?=\\sl<)";
    //private final String regex = "";


    public CarDTO parsedData(String fileName, String content, String imageUrl) {

        CarDTO car = new CarDTO();

        car.setId("c_" + parseData(fileName, regexId));

        String manufacturer = parseData(content, regexManufacturer);
        car.setManufacturer(manufacturer);

        String type = parseData(content, regexManufacturerWithType).replace(manufacturer + " ", "");
        car.setType(type);
        car.setCountry(Constants.country);

        String acceleration = parseData(content, regexAcceleration).replace(",", ".");
        car.setAcceleration(tryParseToNumber(acceleration).doubleValue());

        car.setSeats(Integer.parseInt(parseData(content, regexSeats)));
        String driveWheel = parseData(content, regexDriveWheel);

        if (driveWheel.equals("front+rear")) {
            driveWheel = "4WD";
        } else if (driveWheel.equals("front")) {
            driveWheel = "FWD";
        } else if (driveWheel.equals("rear")) {
            driveWheel = "RWD";
        }
        car.setDriveWheel(driveWheel);

        String doorsAndBody = parseData(content, regexDoorsAndBody);
        int doors = tryParseToNumber(parseData(doorsAndBody, regexDoors)).intValue();
        String body = parseData(doorsAndBody, regexBody);
        car.setDoors(doors);
        car.setBody(convertBodyType(body));


        car.setFuelType(parseData(content, regexFuelType));
        car.setMaxTorque(tryParseToNumber(parseData(content, regexMaxTorque)).intValue());

        String power = parseData(content, regexPower);
        car.setPowerKW(tryParseToNumber(parseData(power, regexPowerKW)).intValue());
        car.setPowerHP(tryParseToNumber(parseData(power, regexPowerHP)).intValue());

        car.setYear(tryParseToNumber(parseData(content, regexYear)).intValue());
        car.setTopSpeed(tryParseToNumber(parseData(content, regexTopSpeed)).intValue());
        car.setWeight(tryParseToNumber(parseData(content, regexWeight)).intValue());
        car.setLength(tryParseToNumber(parseData(content, regexLength)).intValue());
        car.setWidth(tryParseToNumber(parseData(content, regexWidth)).intValue());
        car.setHeight(tryParseToNumber(parseData(content, regexHeight)).intValue());
        car.setGroundClearance(tryParseToNumber(parseData(content, regexGroundClearance)).intValue());
        car.setAbs(parseData(content, regexABS));
        car.setTractionControl(parseData(content, regexTractionControl));
        car.setImageUrl(imageUrl);
        car.setLogoURL(Constants.logoUrl);
        car.setEngineCapacity(tryParseToNumber(parseData(content, regexEngineCapacity)).intValue());
        car.setCarPageUrl(parseData(content, regexCarPageUrl));
        car.setObjectPositionHorizontal(Constants.objectPositionHorizontal);
        car.setObjectPositionVertical(Constants.objectPositionVertical);
        car.setObjectWidth(Constants.objectWidth);
        car.setObjectHeight(Constants.objectHeight);
        car.setGear1st(tryParseToNumber(parseData(content, regex1stGear).replace(",", ".")).doubleValue());
        car.setGear2nd(tryParseToNumber(parseData(content, regex2ndGear).replace(",", ".")).doubleValue());
        car.setGear3rd(tryParseToNumber(parseData(content, regex3rdGear).replace(",", ".")).doubleValue());
        car.setGear4th(tryParseToNumber(parseData(content, regex4thGear).replace(",", ".")).doubleValue());
        car.setGear5th(tryParseToNumber(parseData(content, regex5thGear).replace(",", ".")).doubleValue());
        car.setGear6th(tryParseToNumber(parseData(content, regex6thGear).replace(",", ".")).doubleValue());
        car.setFinalDrive(tryParseToNumber(parseData(content, regexFinalDrive).replace(",", ".")).doubleValue());
        car.setFuelTankCapacity((tryParseToNumber(parseData(content, regexFuelTankCapacity)).intValue()));

        return car;
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
