package kz.iitu.intercitybustransportation.controller;

import kz.iitu.intercitybustransportation.dto.StopDTO;
import kz.iitu.intercitybustransportation.service.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stops")
public class StopController {

    private final StopService stopService;

    @Autowired
    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @GetMapping("/{id}")
    public StopDTO getStop(@PathVariable Long id) {
        return stopService.getStop(id);
    }

    @GetMapping
    public List<StopDTO> getAllStops() {
        return stopService.getAllStops();
    }

    @PostMapping
    public StopDTO createStop(@RequestBody StopDTO stopDto) {
        return stopService.createStop(stopDto);
    }

    @PutMapping("/{id}")
    public StopDTO updateStop(@PathVariable Long id, @RequestBody StopDTO stopDto) {
        return stopService.updateStop(id, stopDto);
    }

    @DeleteMapping("/{id}")
    public void deleteStop(@PathVariable Long id) {
        stopService.deleteStop(id);
    }
}
