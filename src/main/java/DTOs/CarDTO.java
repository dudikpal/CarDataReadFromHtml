package DTOs;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
public class CarDTO {

    private String id;

    private int rq;

    private String level;

    private String manufacturer;

    private String type;

    private Integer year;

    private String country;

    private Integer topSpeed;

    private Double acceleration;

    private Integer powerHP;

    private Integer cornering;

    private Integer groundClearance;

    private String abs;

    private String tractionControl;

    private Integer weight;

    private Integer doors;

    private String body;

    private Integer seats;

    private String driveWheel;

    private String fuelType;

    private Integer fuelTankCapacity;

    private Integer engineCapacity;

    private Integer powerKW;

    private Integer maxTorque;

    private Integer length;

    private Integer width;

    private Integer height;

    private String imageUrl;

    private String logoURL;

    private String carPageUrl;

    private String objectPositionHorizontal;

    private String objectPositionVertical;

    private String objectWidth;

    private String objectHeight;

    private Double gear1st;

    private Double gear2nd;

    private Double gear3rd;

    private Double gear4th;

    private Double gear5th;

    private Double gear6th;

    private Double finalDrive;

    private String createdAt = getDateNow();

    private String updatedAt = getDateNow();

    private String getDateNow() {
        return Instant.now().toString();
    }
}