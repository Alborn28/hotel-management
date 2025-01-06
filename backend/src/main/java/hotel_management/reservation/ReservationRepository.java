package hotel_management.reservation;

import hotel_management.room.Room;
import hotel_management.room.RoomSize;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends CrudRepository<Reservation, UUID> {
    @Query("SELECT COUNT(id) > 0 FROM Reservation res " +
            "WHERE res.room.id = ?1 " +
            "AND res.start <= ?3 AND res.end >= ?2 ")
    boolean roomIsBooked(UUID roomId, LocalDate start, LocalDate end);

    @Query("SELECT room from Room room " +
            "WHERE room.size = ?1 " +
            "AND room.id NOT IN (" +
            "    SELECT res.room.id FROM Reservation res " +
            "    WHERE res.start <= ?3 AND res.end >= ?2 " +
            ")")
    List<Room> availableRoomsOfSize(RoomSize roomSize, LocalDate start, LocalDate end, Pageable pageable);
}
