package hotel_management.reservation;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("reservation-v1")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation makeReservation(@RequestBody @Valid ReservationRequest reservationRequest) {
        if (null != reservationRequest.getRoomId()) {
            return reservationService.bookRoom(reservationRequest.getRoomId(), reservationRequest.getStart(), reservationRequest.getEnd());
        } else if (null != reservationRequest.getRoomSize()) {
            return reservationService.bookRoomOfSize(reservationRequest.getRoomSize(), reservationRequest.getStart(), reservationRequest.getEnd());
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "You must provide either roomId or roomSize.");
        }
    }
}
