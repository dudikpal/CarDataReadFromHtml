package auto_data_net;

public class Regex {

    public static final String regexManufacturer = "(?<=position\": 2,\n" + "\t\t    \"name\": \")\\w+(?=\")";

    public static final String regexTypePart1 = "(?<=position\": 3,\n" + "\t\t    \"name\": \")\\w+(?=\")";

    public static final String regexTypePart2 = "(?<=position\": 5,\n" + "\t\t    \"name\": \").*(?=\\s\\()";

    public static final String regexYear = "(?<=position\": 4,\n" + "\t\t    \"name\": \")\\d+(?=\\s)";

    public static final String regexDoors = "(?<=th>Doors <\\/th><td>).*(?=\\s<\\/td)";

    public static final String regexBody = "(?<=Body type<\\/th><td>).*?((?=\\s*\\()|(?=\\s*<))";

    public static final String regexSeats = "(?<=Seats <\\/th><td>).*(?=\\s<\\/td)";

    public static final String regexDriveWheel = "(?<=Drive wheel <\\/th><td>).*(?=\\s<\\/td)";

    public static final String regexFuelType = "(?<=Fuel Type <\\/th><td>).*?(?=\\s?\\()";

    public static final String regexPowerHP = "(?<=Power <\\/th><td>).*?(?=\\s?H)";

    public static final String regexMaxTorque = "(?<=Torque <\\/th>\n" + "\t\t\t\t\t\t\t<td>)\\d+";

    public static final String regexTopSpeed = "(?<=Maximum speed <\\/th>\n" + "\t\t\t\t\t\t\t<td>)\\d+";

    public static final String regexAcceleration = "(?<=Acceleration 0 - 100 km\\/h<\\/th><td>).*(?=\\ssec)";

    public static final String regexWeight = "(?<=Kerb Weight <\\/th>\n" + "\t\t\t\t\t\t<td>)\\d+";

    public static final String regexLength = "(?<=Length <\\/th>\n" + "\t\t\t\t\t\t<td>)\\d+";

    public static final String regexWidth = "(?<=Width <\\/th>\n" + "\t\t\t\t\t\t<td>)\\d+";

    public static final String regexHeight = "(?<=Height <\\/th>\n" + "\t\t\t\t\t\t<td>)\\d+";

    public static final String regexAssists = "(?<=Assisting systems<\\/th><td>).*(?=<\\/td)";

    public static final String regexEngineCapacity = "(?<=Engine displacement <\\/th>\n" + "\t\t\t\t\t\t<td>)\\d+";

    public static final String regexId = "[\\w\\.-]+$";

    public static final String regexCarPageUrl = "(?<=<link rel=\\\"canonical\\\" href=\\\").*(?=\\\"\\s/>)";

    public static final String regexFuelTankCapacity = "(?<=Fuel tank capacity <\\/th>\n" + "\t\t\t\t\t\t\t<td>)\\d+";

    //private final String regex = "";
}
