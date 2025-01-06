package hotel_management.reservation;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/reservation-v1")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation makeReservation(@RequestBody @Valid ReservationRequest reservationRequest) {
        if (null != reservationRequest.getRoomId()) {
            return reservationService.bookRoom(reservationRequest);
        } else if (null != reservationRequest.getRoomType()) {
            return reservationService.bookRoomOfType(reservationRequest);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "You must provide either roomId or roomType.");
        }
    }
}
