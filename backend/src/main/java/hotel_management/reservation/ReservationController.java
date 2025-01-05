package hotel_management.reservation;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("reservation-v1")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation makeReservation(@RequestBody ReservationRequest reservationRequest) {
        /*
            Validate here or in service?
            Make sure from and to are set
            To must be after from
            Can't book in the past?
         */
        if (null != reservationRequest.getRoomId()) {
            return reservationService.bookRoom(reservationRequest.getRoomId(), reservationRequest.getStart(), reservationRequest.getEnd());
        } else if (null != reservationRequest.getRoomSize()) {
            return reservationService.bookRoomOfSize(reservationRequest.getRoomSize(), reservationRequest.getStart(), reservationRequest.getEnd());
        } else {
            // TODO Throw error that you must provide roomId or roomSize
            return new Reservation();
        }
    }
}
