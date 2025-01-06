package hotel_management.room;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("room-v1")
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public Iterable<Room> listRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Room> getRoom(@PathVariable(value = "id") UUID id) {
        return roomRepository.findById(id);
    }

    @PostMapping
    public Room createRoom(@RequestBody @Valid Room room) {
        return roomRepository.save(room);
    }
}
