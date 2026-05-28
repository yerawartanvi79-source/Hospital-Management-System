public class PatientData {

    private String name;
    private int age;
    private String blood;
    private String type;
    private int room;
    private String status;

    // ================= CONSTRUCTOR =================

    public PatientData(
            String name,
            int age,
            String blood,
            String type,
            int room,
            String status
    ) {

        this.name = name;
        this.age = age;
        this.blood = blood;
        this.type = type;
        this.room = room;
        this.status = status;
    }

    // ================= GETTERS =================

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBlood() {
        return blood;
    }

    public String getType() {
        return type;
    }

    public int getRoom() {
        return room;
    }

    public String getStatus() {
        return status;
    }
}