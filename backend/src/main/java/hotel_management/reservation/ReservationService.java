package hotel_management.reservation;

import hotel_management.room.Room;
import hotel_management.room.RoomRepository;
import hotel_management.room.RoomType;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            RoomRepository roomRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    public Reservation bookRoom(ReservationRequest request) {
        return bookRoom(request.getRoomId(), request.getEmail(), request.getStart(), request.getEnd());
    }

    public Reservation bookRoom(UUID roomId, String email, LocalDate start, LocalDate end) {
        validateReservationDates(start, end);

        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "room.does.not.exist");
        }

        boolean roomIsBooked = reservationRepository.roomIsBooked(roomId, start, end);
        if (roomIsBooked) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "room.is.occupied");
        }

        Reservation reservation = new Reservation(room.get(), email, start, end);
        return reservationRepository.save(reservation);
    }

    public Reservation bookRoomOfType(ReservationRequest request) {
        return bookRoomOfType(request.getRoomType(), request.getEmail(), request.getStart(), request.getEnd());
    }

    public Reservation bookRoomOfType(RoomType roomType, String email, LocalDate start, LocalDate end) {
        validateReservationDates(start, end);

        List<Room> availableRooms = reservationRepository.availableRoomsOfType(roomType, start, end, PageRequest.ofSize(1));
        if (availableRooms.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "no.available.rooms.of.given.type");
        }

        Room room = availableRooms.getFirst();
        Reservation reservation = new Reservation(room, email, start, end);
        return reservationRepository.save(reservation);
    }

    private void validateReservationDates(LocalDate start, LocalDate end) {
        if (end.isBefore(start)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "end.date.cannot.be.before.start.date");
        }
    }
}
