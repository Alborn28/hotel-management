package hotel_management.reservation;

import hotel_management.room.Room;
import hotel_management.room.RoomRepository;
import hotel_management.room.RoomSize;
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

    public Reservation bookRoom(UUID roomId, LocalDate start, LocalDate end) {
        validateReservationDates(start, end);

        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "The room does not exist.");
        }

        boolean roomIsBooked = reservationRepository.roomIsBooked(roomId, start, end);
        if (roomIsBooked) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "The room is occupied during the given time period.");
        }

        Reservation reservation = new Reservation(start, end, room.get());
        return reservationRepository.save(reservation);
    }

    public Reservation bookRoomOfSize(RoomSize roomSize, LocalDate start, LocalDate end) {
        validateReservationDates(start, end);

        List<Room> availableRooms = reservationRepository.availableRoomsOfSize(roomSize, start, end, PageRequest.ofSize(1));
        if (availableRooms.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "There are no available rooms of the requested size.");
        }

        Room room = availableRooms.getFirst();
        Reservation reservation = new Reservation(start, end, room);
        return reservationRepository.save(reservation);
    }

    private void validateReservationDates(LocalDate start, LocalDate end) {
        if (end.isBefore(start)) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "End date cannot be before start date.");
        }
    }
}
