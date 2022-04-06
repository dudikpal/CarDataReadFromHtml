package cars_data_com;

public class Regex {

    public static final String regexManufacturer = "(?<=span itemprop=\\\"name\\\">).*?(?=</span>\\s*</a>\\s*<meta itemprop=\\\"position\\\" content=\\\"2\\\")";

    public static final String regexManufacturerWithType = "(?<=span itemprop=\\\"name\\\">).*?(?=\\s\\d+</span>\\s*<meta itemprop=\\\"position\\\" content=\\\"5\\\")";

    public static final String regexYear = "\\d+(?=</span>\\s*</a>\\s*<meta itemprop=\\\"position\\\" content=\\\"4\\\")";

    public static final String regexDoorsAndBody = "(?<=Body Type:</td><td class=\\\"col-6\\\">).*?(?=</td>)";

    public static final String regexDoors = "\\d+";

    public static final String regexBody = "(?<=,\\s).*";

    public static final String regexSeats = "(?<=Number Of Seats:</td><td class=\\\"col-6\\\">)\\d";

    public static final String regexDriveWheel = "(?<=Drive Wheel :</td><td class=\\\"col-6 grey\\\">).*?(?=</td>)";

    public static final String regexFuelType = "(?<=Fuel Type:</td><td class=\\\"col-6 grey\\\">).*?(?=</td)";

    public static final String regexPower = "(?<=Power:</td><td class=\\\"col-6\\\">).*?(?=</td)";

    public static final String regexPowerKW = ".*(?=\\skw)";

    public static final String regexPowerHP = "(?<=\\().*(?=\\shp)";

    public static final String regexMaxTorque = "(?<=Max Torque:</td><td class=\\\"col-6\\\">).*(?=\\snm)";

    public static final String regexTopSpeed = "(?<=Top Speed:</td><td class=\\\"col-6 grey\\\">).*(?=\\skm)";

    public static final String regexAcceleration = "(?<=0-100 Km / H:</td><td class=\\\"col-6\\\">).*(?=\\ss)";

    public static final String regexWeight = "(?<=Curb Weight:</td><td class=\\\"col-6 grey\\\">)\\d+";

    public static final String regexLength = "(?<=Length:</td><td class=\\\"col-6 grey\\\">)\\d+";

    public static final String regexWidth = "(?<=Width:</td><td class=\\\"col-6\\\">)\\d+";

    public static final String regexHeight = "(?<=Height:</td><td class=\\\"col-6 grey\\\">)\\d+";

    public static final String regexGroundClearance = "(?<=Ground Clearance:</td><td class=\\\"col-6 grey\\\">)\\d+";

    public static final String regexABS = "(?<=ABS:</td><td class=\\\"col-6\\\">)\\w+";

    public static final String regexTractionControl = "(?<=Traction Control:</td><td class=\\\"col-6\\\">)\\w+";

    public static final String regexLogoUrl = "(?<=<img src=\\\").*(?=\\\" width=\\\"80\\\" height=\\\"80\\\" alt=)";

    public static final String regexEngineCapacity = "(?<=Engine Capacity:<\\/td><td class=\\\"col-6 grey\\\">)\\d+";

    public static final String regexId = "[\\w-]+$";

    public static final String regexCarPageUrl = "(?<=<link rel=\\\"canonical\\\" href=\\\").*(?=\\\"\\s/>)";

    public static final String regex1stGear = "(?<=1st Gear:</td><td class=\\\"col-6 grey\\\">).*?(?=:1<\\/td>)";

    public static final String regex2ndGear = "(?<=2nd Gear:</td><td class=\\\"col-6\\\">).*?(?=:1<\\/td>)";

    public static final String regex3rdGear = "(?<=3rd Gear:</td><td class=\\\"col-6 grey\\\">).*?(?=:1<\\/td>)";

    public static final String regex4thGear = "(?<=4th Gear:</td><td class=\\\"col-6\\\">).*?(?=:1<\\/td>)";

    public static final String regex5thGear = "(?<=5th Gear:</td><td class=\\\"col-6 grey\\\">).*?(?=:1<\\/td>)";

    public static final String regex6thGear = "(?<=6th Gear:</td><td class=\\\"col-6\\\">).*?(?=:1<\\/td>)";

    public static final String regexFinalDrive = "(?<=Final Drive:</td><td class=\\\"col-6 grey\\\">).*?(?=:1<\\/td>)";

    public static final String regexFuelTankCapacity = "(?<=Fuel Tank Capacity:</td><td class=\\\"col-6\\\">).*(?=\\sl<)";

    //public static final String regex = "";
}
