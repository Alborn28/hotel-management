package hotel_management.room;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String number;

    private RoomSize size;

    private Room() {}

    public Room(String number, RoomSize size) {
        this.number = number;
        this.size = size;
    }

    public UUID getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public RoomSize getSize() {
        return size;
    }
}
