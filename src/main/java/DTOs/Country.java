package DTOs;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Country {

    GB("Great Britain"),
    ES("Spain"),
    DE("Germany"),
    HR("Hrvatski"),
    AT("Austria"),
    NL("Netherland"),
    AU("Australia"),
    SE("Sweden");

    private String name;

}
