package constants;

public class Constants {

    public static final String logoUrl = "placeholder.png";

    public static final String imageUrl = "placeholder.png";

    public static final String objectPositionHorizontal = "center";

    public static final String objectPositionVertical = "";

    public static final String objectWidth = "100%";

    public static final String objectHeight = "100%";

    public static final String country = "N/A";

    public static final Double hpToKw = 0.745699872;

    public static final Double ftlbsToNm = 1.3558;

    public static final Double lbsToKg = 0.45359237;

    public static final Double galToLiter = 3.785411784;

    public static final Double inchToMm = 25.4;

    public static final String sourceDirNamePrefix = "/cars/";

    public static final String targetDirNamePrefix = "src/main/resources/cars/";

    public static final String pageUrlSourceFile = "/waitingPagesUrl.txt";

    public static final String imageUrlSourceFile = "/waitingImagesUrl.txt";

    public static final String imageDirNamePrefix = "assets/img/cars/";

    public static final String imageFileExtension = ".webp";

    public static int calculatecornering(int width, int height, int weight) {

        double msToKmh = 3.6;
        double gravity = 9.81;
        int cornerRadiusMeter = 20;
        double widthMeter = width / 1000;
        double heightMeter = height / 1000;

        return (int)(Math.sqrt((cornerRadiusMeter * gravity * (widthMeter / 2)) / (heightMeter / 4)) * msToKmh);
    }
}
