package hotel_management.reservation;

import hotel_management.room.RoomSize;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class ReservationRequest {
    @NotNull
    private LocalDate start;
    @NotNull
    private LocalDate end;
    private UUID roomId;
    private RoomSize roomSize;

    public ReservationRequest() { }

    public ReservationRequest(LocalDate start, LocalDate end, UUID roomId, RoomSize roomSize) {
        this.start = start;
        this.end = end;
        this.roomId = roomId;
        this.roomSize = roomSize;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public RoomSize getRoomSize() {
        return roomSize;
    }
}
