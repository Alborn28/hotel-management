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

    @Column(name = "start_date", nullable = false)
    private LocalDate start;

    @Column(name = "end_date", nullable = false)
    private LocalDate end;

    public Reservation() {}

    public Reservation(LocalDate start, LocalDate end, Room room) {
        this.start = start;
        this.end = end;
        this.room = room;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public Room getRoom() {
        return room;
    }
}
