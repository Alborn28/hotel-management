package hotel_management.reservation;

import hotel_management.room.Room;
import hotel_management.room.RoomRepository;
import hotel_management.room.RoomSize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isEmpty()) {
            // TODO Throw error that room doesn't exist
        }

        boolean roomIsBooked = reservationRepository.roomIsBooked(roomId, start, end);
        if (roomIsBooked) {
            // TODO Throw error that room is booked
            return new Reservation();
        }

        Reservation reservation = new Reservation(start, end, room.get());
        return reservationRepository.save(reservation);
    }

    public Reservation bookRoomOfSize(RoomSize roomSize, LocalDate start, LocalDate end) {
        /*
            Check if a room of given size is available
            Create reservation
         */
        return new Reservation();
    }
}
