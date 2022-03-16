package carspecs_us;

public enum CarTypes {

    Sedan("Sedan"),
    Coupe("Coupe"),
    Convertible("Convertible"),
    Hatchback("Hatchback"),
    Wagon("Wagon"),
    Truck("Truck"),
    SUV("SUV"),
    Minivan("Minivan"),
    Van("Van");

    public String type;

    CarTypes(String type) {
        this.type = type;
    }
}
