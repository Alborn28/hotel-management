package hotel_management.reservation;

import hotel_management.room.RoomSize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class ReservationRequest {
    @NotNull
    private LocalDate start;

    @NotNull
    private LocalDate end;

    @NotEmpty
    @Email
    private String email;

    private UUID roomId;
    private RoomSize roomSize;

    private ReservationRequest() { }

    public ReservationRequest(LocalDate start, LocalDate end, String email, UUID roomId, RoomSize roomSize) {
        this.start = start;
        this.end = end;
        this.email = email;
        this.roomId = roomId;
        this.roomSize = roomSize;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getEmail() {
        return email;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public RoomSize getRoomSize() {
        return roomSize;
    }
}
