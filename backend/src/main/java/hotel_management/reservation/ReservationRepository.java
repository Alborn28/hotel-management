package hotel_management.reservation;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface ReservationRepository extends CrudRepository<Reservation, UUID> {
    @Query("SELECT COUNT(id) > 0 FROM Reservation r " +
            "WHERE r.room.id = ?1 " +
            "AND r.start <= ?3 AND r.end >= ?2 ")
    boolean roomIsBooked(UUID roomId, LocalDate start, LocalDate end);
}
