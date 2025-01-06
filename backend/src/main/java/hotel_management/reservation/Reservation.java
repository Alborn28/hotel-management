package hotel_management.reservation;

import hotel_management.room.Room;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(nullable = false)
    private String email;

    @Column(name = "start_date", nullable = false)
    private LocalDate start;

    @Column(name = "end_date", nullable = false)
    private LocalDate end;

    private Reservation() {}

    public Reservation(Room room, String email, LocalDate start, LocalDate end) {
        this.room = room;
        this.email = email;
        this.start = start;
        this.end = end;
    }

    public UUID getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
