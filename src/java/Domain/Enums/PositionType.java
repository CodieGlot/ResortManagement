package Domain.Enums;

public enum PositionType {
    Receptionist(0),
    Server(1),
    Specialist(2),
    Supervisor(3),
    Manager(4),
    Director(5);

    private final int index;

    PositionType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static PositionType fromIndex(int index) {
        for (PositionType type : PositionType.values()) {
            if (type.getIndex() == index) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid PositionType index: " + index);
    }
}
