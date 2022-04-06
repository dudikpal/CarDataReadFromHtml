package carspecs_us;

public class Regex {

    public static final String regexId = "[\\w-]+$";

    public static final String regexManufacturer = "((?<=<div class=\\\"pure-u-1 pure-u-md-17-24\\\">\\n\\t\\t<a href=\\\"/\\\">Cars</a> &gt;\n" +
            "\t\t<a href=\\\"/cars/).*(?=</a))";

    public static final String regexManufacturerWithType = "(?<=<div class=\\\"pure-u-1 pure-u-md-17-24\\\">\n" +
            "\t\t<a href=\\\"/\\\">Cars</a> &gt;\n" +
            "\t\t<a href=\\\"/cars/)[\\w\\W]*(?=</a> &gt;\n" +
            "\t\t<a href=)";

    public static final String regexAcceleration = "(?<=/h4>\\n).*(?=\\ssec<)";

    public static final String regexSeats = "(?<=Passenger Capacity</h4>\\n)\\d+(?=\\s<)";

    public static final String regexDriveWheel = "(?<=Drive type</h4>\\n).*?(?=\\s*<)";

    public static final String regexDoors = "(?<=Passenger Doors</h4>\\n).*?(?=\\s*<)";

    public static final String regexEngineType = "(?<=Engine type</h4>\\n).*?(?=\\s*<)";

    public static final String regexFuelType = "(?<=Fuel Type:</td><td class=\\\"col-6 grey\\\">).*?(?=</td)";

    public static final String regexMaxTorque = "(?<=Torque</h4>\\n).*?(?=\\sft-lbs*)";

    public static final String regexPowerHP = "(?<=Horsepower</h4>\\n).*?(?=\\shp)";

    public static final String regexTopSpeed = "(?<=Top Speed:</td><td class=\\\"col-6 grey\\\">).*(?=\\skm)";

    public static final String regexWeight = "(?<=Curb weight</h4>\\n).*?(?=\\slbs)";

    public static final String regexWidth = "(?<=Width</h4>\\n).*?(?=<br)";

    public static final String regexLength = "(?<=Length</h4>\\n).*?(?=in.)";

    public static final String regexHeight = "(?<=Height</h4>\\n).*?(?=in.)";

    public static final String regexYear = "^\\d+";

    public static final String regexGroundClearance = "(?<=Ground clearance</h4>\\n).*?(?=in.)";

    public static final String regexABS = "ABS";

    public static final String regexTractionControl = "Traction";

    public static final String regexLogoUrl = "(?<=<img src=\\\").*(?=\\\" width=\\\"80\\\" height=\\\"80\\\" alt=)";

    public static final String regexEngineCapacity = "(?<=engine size</h4>\\n).*?(?=L)";

    public static final String regexCarPageUrl = "(?<=<link rel=\\\"canonical\\\" href=\\\").*(?=\\\">)";

    public static final String regex1stGear = "(?<=First Gear Ratio</h4>\\n).*?(?=<br)";

    public static final String regex2ndGear = "(?<=Second Gear Ratio</h4>\\n).*?(?=<br)";

    public static final String regex3rdGear = "(?<=Third Gear Ratio</h4>\\n).*?(?=<br)";

    public static final String regex4thGear = "(?<=Fourth Gear Ratio</h4>\\n).*?(?=<br)";

    public static final String regex5thGear = "(?<=Fifth Gear Ratio</h4>\\n).*?(?=<br)";

    public static final String regex6thGear = "(?<=Sixth Gear Ratio</h4>\\n).*?(?=<br)";

    public static final String regexFinalDrive = "(?<=Final Drive Axle Ratio</h4>\\n).*?(?=<br)";

    public static final String regexFuelTankCapacity = "(?<=Fuel tank capacity</h4>\\n).*?(?=\\sgal.<br)";

    //public static final String regex = "";
}
