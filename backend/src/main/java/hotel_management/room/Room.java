package hotel_management.room;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private Long number;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType type;

    private Room() {}

    public Room(Long number, RoomType type) {
        this.number = number;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public Long getNumber() {
        return number;
    }

    public RoomType getType() {
        return type;
    }
}
