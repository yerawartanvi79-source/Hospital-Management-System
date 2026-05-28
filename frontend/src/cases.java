import java.util.*;

abstract class Patient {

    String name;
    int age;
    String bloodGroup;
    String status;

    Patient(String name, int age, String bloodGroup) {

        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.status = "Admitted";
    }

    abstract double calculateFee(int days);
}

class RegularPatient extends Patient {

    RegularPatient(String name, int age, String bloodGroup) {
        super(name, age, bloodGroup);
    }

    @Override
    double calculateFee(int days) {

        return days * 500;
    }
}

class IcuPatient extends Patient {

    IcuPatient(String name, int age, String bloodGroup) {
        super(name, age, bloodGroup);
    }

    @Override
    double calculateFee(int days) {

        return days * 2000;
    }
}

class RoomSlot {

    int roomNumber;
    Patient patient;

    RoomSlot(int roomNumber) {

        this.roomNumber = roomNumber;
    }

    boolean isEmpty() {

        return patient == null;
    }
}

class Hospital {

    ArrayList<RoomSlot> rooms =
            new ArrayList<>();

    double totalRevenue = 0;

    Hospital(int totalRooms) {

        for (int i = 1; i <= totalRooms; i++) {

            rooms.add(
                    new RoomSlot(100 + i)
            );
        }
    }

    String admitPatient(Patient patient) {

        for (RoomSlot room : rooms) {

            if (room.isEmpty()) {

                room.patient = patient;

                return "Patient admitted to Room "
                        + room.roomNumber;
            }
        }

        return "No Rooms Available!";
    }

    String dischargePatient(
            String name,
            int days
    ) {

        for (RoomSlot room : rooms) {

            if (!room.isEmpty()
                    &&
                    room.patient.name.equalsIgnoreCase(name)) {

                double totalBill =
                        room.patient.calculateFee(days);

                totalRevenue += totalBill;

                room.patient.status =
                        "Discharged";

                String msg =
                        "Patient : " + name +
                        "\nRoom : " + room.roomNumber +
                        "\nBill : ₹" + totalBill +
                        "\nStatus : Discharged";

                room.patient = null;

                return msg;
            }
        }

        return "Patient not found!";
    }
}