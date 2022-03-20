package carspecs_us;

import DTOs.CarDTO;
import helpers.Helpers;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ContentParser {

    private Helpers helpers;

    private final String regexId = "[\\w-]+$";
    private final String regexManufacturer = "((?<=<div class=\\\"pure-u-1 pure-u-md-17-24\\\">\\n\\t\\t<a href=\\\"/\\\">Cars</a> &gt;\n" +
            "\t\t<a href=\\\"/cars/).*(?=</a))";
    private final String regexManufacturerWithType = "(?<=<div class=\\\"pure-u-1 pure-u-md-17-24\\\">\n" +
            "\t\t<a href=\\\"/\\\">Cars</a> &gt;\n" +
            "\t\t<a href=\\\"/cars/)[\\w\\W]*(?=</a> &gt;\n" +
            "\t\t<a href=)";
    private final String regexAcceleration = "(?<=/h4>\\n).*(?=\\ssec<)";
    private final String regexSeats = "(?<=Passenger Capacity</h4>\\n)\\d+(?=\\s<)";
    private final String regexDriveWheel = "(?<=Drive type</h4>\\n).*?(?=\\s*<)";
    private final String regexDoors = "(?<=Passenger Doors</h4>\\n).*?(?=\\s*<)";
    private final String regexEngineType = "(?<=Engine type</h4>\\n).*?(?=\\s*<)";
    private final String regexFuelType = "(?<=Fuel Type:</td><td class=\\\"col-6 grey\\\">).*?(?=</td)";
    private final String regexMaxTorque = "(?<=Torque</h4>\\n).*?(?=\\sft-lbs*)";
    private final String regexPowerHP = "(?<=Horsepower</h4>\\n).*?(?=\\shp)";
    private final String regexTopSpeed = "(?<=Top Speed:</td><td class=\\\"col-6 grey\\\">).*(?=\\skm)";
    private final String regexWeight = "(?<=Curb weight</h4>\\n).*?(?=\\slbs)";
    private final String regexWidth = "(?<=Width</h4>\\n).*?(?=<br)";
    private final String regexLength = "(?<=Length</h4>\\n).*?(?=in.)";
    private final String regexHeight = "(?<=Height</h4>\\n).*?(?=in.)";
    private final String regexYear = "^\\d+";
    private final String regexGroundClearance = "(?<=Ground clearance</h4>\\n).*?(?=in.)";
    private final String regexABS = "ABS";
    private final String regexTractionControl = "Traction";
    private final String regexLogoUrl = "(?<=<img src=\\\").*(?=\\\" width=\\\"80\\\" height=\\\"80\\\" alt=)";
    private final String regexEngineCapacity = "(?<=engine size</h4>\\n).*?(?=L)";
    private final String regexCarPageUrl = "(?<=<link rel=\\\"canonical\\\" href=\\\").*(?=\\\">)";

    private final String regex1stGear = "(?<=First Gear Ratio</h4>\\n).*?(?=<br)";
    private final String regex2ndGear = "(?<=Second Gear Ratio</h4>\\n).*?(?=<br)";
    private final String regex3rdGear = "(?<=Third Gear Ratio</h4>\\n).*?(?=<br)";
    private final String regex4thGear = "(?<=Fourth Gear Ratio</h4>\\n).*?(?=<br)";
    private final String regex5thGear = "(?<=Fifth Gear Ratio</h4>\\n).*?(?=<br)";
    private final String regex6thGear = "(?<=Sixth Gear Ratio</h4>\\n).*?(?=<br)";
    private final String regexFinalDrive = "(?<=Final Drive Axle Ratio</h4>\\n).*?(?=<br)";
    private final String regexFuelTankCapacity = "(?<=Fuel tank capacity</h4>\\n).*?(?=\\sgal.<br)";
    //private final String regex = "";

    public CarDTO parsedData(String fileName, String content, String imageUrl) {

        CarDTO car = new CarDTO();

        car.setId("c_" + parseData(fileName, regexId));

        String manufacturer = parseData(parseData(content, regexManufacturer), "(?<=>).*");
        car.getManufacturer().setValue(manufacturer);

        String type = parseData(parseData(content, regexManufacturerWithType), "(?<=\\\">).*$");

        String title = parseData(content, "(?<=title>).*(?=</title>)");
        String frontRemoved = title.replace(manufacturer + " " + type, "")
                .replace(" Base ", "");
        String typeFull = type + " " + parseTypeSpec(frontRemoved);

        car.getType().setValue(typeFull);
        car.getCountry().setValue("N/A");

        String acceleration = parseData(content, regexAcceleration).replace(",", ".");
        car.getAcceleration().setValue(tryParseToNumber(acceleration).doubleValue());

        car.getSeats().setValue(tryParseToNumber(parseData(content, regexSeats)).intValue());
        String driveWheel = parseData(content, regexDriveWheel);

        if (driveWheel.toLowerCase().matches(".*four.*|.*all.*")) {
            driveWheel = "4WD";
        } else if (driveWheel.toLowerCase().matches(".*front.*")) {
            driveWheel = "FWD";
        } else if (driveWheel.toLowerCase().matches(".*rear.*")) {
            driveWheel = "RWD";
        }
        car.getDriveWheel().setValue(driveWheel);

        int doors = tryParseToNumber(parseData(content, regexDoors)).intValue();
        car.getDoors().setValue(doors);

        String body = getBody(title);
        car.getBody().setValue(body.replace("&Eacute;", "e"));

        car.getEngineType().setValue(parseData(content, regexEngineType));
        car.getFuelType().setValue(parseData(content, regexFuelType));

        int torque = tryParseToNumber(parseData(content, regexMaxTorque)).intValue();
        torque *= 1.3558;
        car.getMaxTorque().setValue(torque);

        int powerHP = tryParseToNumber(parseData(content, regexPowerHP)).intValue();
        int powerKW = (int) (powerHP * 0.745699872);
        car.getPowerKW().setValue(powerKW);
        car.getPowerHP().setValue(powerHP);

        car.getYear().setValue(tryParseToNumber(parseData(title, regexYear)).intValue());
        car.getTopSpeed().setValue(tryParseToNumber(parseData(content, regexTopSpeed)).intValue());

        int weightKG = (int) (tryParseToNumber(
                parseData(content, regexWeight)
                        .replace(",", "")).intValue() * 0.45359237);
        car.getWeight().setValue(weightKG);

        car.getWidth().setValue((int) (tryParseToNumber(parseData(content, regexWidth)).intValue() * 25.4));
        car.getLength().setValue((int) (tryParseToNumber(parseData(content, regexLength)).intValue() * 25.4));
        car.getHeight().setValue((int) (tryParseToNumber(parseData(content, regexHeight)).intValue() * 25.4));
        car.getGroundClearance().setValue((int)(tryParseToNumber(parseData(content, regexGroundClearance)).intValue() * 25.4));

        car.getABS().setValue(parseData(content, regexABS).equals("ABS") ? "yes" : "N/A");
        car.getTractionControl().setValue(parseData(content, regexTractionControl).equals("Traction") ? "yes" : "N/A");
        car.getImageUrl().setValue(imageUrl);
        car.getLogoURL().setValue(parseData(content, regexLogoUrl));
        car.getEngineCapacity().setValue((int)(tryParseToNumber(parseData(content, regexEngineCapacity)).doubleValue() * 1000));
        car.getCarPageUrl().setValue(parseData(content, regexCarPageUrl));
        car.getObjectPositionHorizontal().setValue("0vh");
        car.getObjectPositionVertical().setValue("0vh");
        car.getObjectWidth().setValue("100%");
        car.getObjectHeight().setValue("100%");
        car.getGear1st().setValue(tryParseToNumber(parseData(content, regex1stGear).replace(",", ".")).doubleValue());
        car.getGear2nd().setValue(tryParseToNumber(parseData(content, regex2ndGear).replace(",", ".")).doubleValue());
        car.getGear3rd().setValue(tryParseToNumber(parseData(content, regex3rdGear).replace(",", ".")).doubleValue());
        car.getGear4th().setValue(tryParseToNumber(parseData(content, regex4thGear).replace(",", ".")).doubleValue());
        car.getGear5th().setValue(tryParseToNumber(parseData(content, regex5thGear).replace(",", ".")).doubleValue());
        car.getGear6th().setValue(tryParseToNumber(parseData(content, regex6thGear).replace(",", ".")).doubleValue());
        car.getFinalDrive().setValue(tryParseToNumber(parseData(content, regexFinalDrive).replace(",", ".")).doubleValue());
        car.getFuelTankCapacity().setValue((int)(tryParseToNumber(parseData(content, regexFuelTankCapacity)).doubleValue() * 3.785411784));

        return car;
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
                .trim();

        System.out.println(frontRemoved);
        return frontRemoved;
    }


    private Number tryParseToNumber(String number) {

        return helpers.tryParseToNumber(number);
    }


    private String parseData(String content, String regex) {

        return helpers.parseData(content, regex).trim();
    }
}
