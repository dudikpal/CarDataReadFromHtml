package carspecs_us;

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

        String manufacturer = parseData(parseData(content, Regex.regexManufacturer), "(?<=>).*");
        car.setManufacturer(manufacturer);

        String type = parseData(parseData(content, Regex.regexManufacturerWithType), "(?<=\\\">).*$");
        String title = parseData(content, "(?<=title>).*(?=</title>)");
        String frontRemoved = title.replace(manufacturer + " " + type, "")
                .replace(" Base ", "");
        String typeFull = type + " " + parseTypeSpec(frontRemoved);
        car.setType(typeFull);

        String acceleration = parseData(content, Regex.regexAcceleration).replace(",", ".");
        car.setAcceleration(tryParseToNumber(acceleration).doubleValue());

        int powerHP = tryParseToNumber(parseData(content, Regex.regexPowerHP)).intValue();
        int powerKW = (int) (powerHP * Constants.hpToKw);
        car.setPowerKW(powerKW);
        car.setPowerHP(powerHP);

        int weight = tryParseToNumber(parseData(content, Regex.regexWeight)).intValue();
        int width = tryParseToNumber(parseData(content, Regex.regexWidth)).intValue();
        int height = tryParseToNumber(parseData(content, Regex.regexHeight)).intValue();
        car.setWeight(weight);
        car.setWidth(width);
        car.setHeight(height);
        car.setCornering(Constants.calculatecornering(width, height, weight));

        car.setFuelType(parseData(content, Regex.regexFuelType));
        car.setBody(getBody(title).replace("&Eacute;", "e"));
        car.setMaxTorque(getTorque(content));
        car.setDoors(tryParseToNumber(parseData(content, Regex.regexDoors)).intValue());
        car.setDriveWheel(getDriveWheel(content));
        car.setSeats(tryParseToNumber(parseData(content, Regex.regexSeats)).intValue());
        car.setCountry(Constants.country);
        car.setYear(tryParseToNumber(parseData(title, Regex.regexYear)).intValue());
        car.setTopSpeed(tryParseToNumber(parseData(content, Regex.regexTopSpeed)).intValue());
        car.setLength((int) (tryParseToNumber(parseData(content, Regex.regexLength)).intValue() * Constants.inchToMm));
        car.setGroundClearance((int)(tryParseToNumber(parseData(content, Regex.regexGroundClearance)).intValue() * Constants.inchToMm));
        car.setAbs(parseData(content, Regex.regexABS).equals("ABS") ? "yes" : "N/A");
        car.setTractionControl(parseData(content, Regex.regexTractionControl).equals("Traction") ? "yes" : "N/A");
        car.setImageUrl(imageUrl);
        car.setLogoURL(Constants.logoUrl);
        car.setEngineCapacity((int)(tryParseToNumber(parseData(content, Regex.regexEngineCapacity)).doubleValue() * 1000));
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
        car.setFuelTankCapacity((int) (tryParseToNumber(parseData(content, Regex.regexFuelTankCapacity)).doubleValue() * Constants.galToLiter));

        return car;
    }


    private int getWeightKG(String content) {

        int weightKG = (int) (tryParseToNumber(
                parseData(content, Regex.regexWeight)
                        .replace(",", "")).intValue() * Constants.lbsToKg);

        return weightKG;
    }


    private int getTorque(String content) {

        int torque = tryParseToNumber(parseData(content, Regex.regexMaxTorque)).intValue();
        torque *= Constants.ftlbsToNm;

        return torque;
    }


    private String getDriveWheel(String content) {

        String driveWheel = parseData(content, Regex.regexDriveWheel);

        if (driveWheel.toLowerCase().matches(".*four.*|.*all.*")) {

            driveWheel = "AWD";

        } else if (driveWheel.toLowerCase().matches(".*front.*")) {

            driveWheel = "FWD";

        } else if (driveWheel.toLowerCase().matches(".*rear.*")) {

            driveWheel = "RWD";
        }

        return driveWheel;
    }


    private String getBody(String title) {

        for (CarTypes carType : CarTypes.values()) {

            if (title.contains(carType.type)) {

                return carType.type;
            }
        }

        return "N/A";
    }


    private String parseTypeSpec(String frontRemoved) {

        for (CarTypes carType : CarTypes.values()) {

            frontRemoved = frontRemoved.replace(carType.type, "");
        }

        frontRemoved = frontRemoved
                // remove transmission data
                .replaceAll(" [Ss]equential| [Ss]hift| [Co]ntrol| [Aa]uto| [Mm]anual| \\d+-spd", "")
                // remove drive wheel
                .replaceAll("( AWD| FWD| RWD)", "")
                // remove doors
                .replaceAll("(\s?\\d+dr\s?|\\d\\W+[Dd]oor| FrontTrak)", "")
                // remove year
                .replaceAll("^\\d+\s+", "")
                // remove minus sign
                .replaceAll("\s*-\s*", "\s")
                // remove year and others
                .replaceAll("(\\d{4}|w/OD|FFV|R/T|\\d\\.\\dL)", "")
                //remove whitespaces
                .replaceAll("^\s*", "")
                .replaceAll("\s+", " ")
                .trim();

        return frontRemoved;
    }


    private Number tryParseToNumber(String number) {

        return helpers.tryParseToNumber(number);
    }


    private String parseData(String content, String regex) {

        return helpers.parseData(content, regex).trim();
    }
}
