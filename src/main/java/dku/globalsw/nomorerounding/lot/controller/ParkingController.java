package dku.globalsw.nomorerounding.lot.controller;

import dku.globalsw.nomorerounding.lot.dto.request.LotRequest;
import dku.globalsw.nomorerounding.lot.dto.response.LotResponse;
import dku.globalsw.nomorerounding.lot.service.LotService;
import dku.globalsw.nomorerounding.space.dto.SpaceRequest;
import dku.globalsw.nomorerounding.space.service.SpaceService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parking")
public class ParkingController {

    private final LotService lotService;
    private final SpaceService spaceService;

    @PostMapping("/makeLot")
    public ResponseEntity<LotResponse> makeLot(@RequestBody LotRequest lotRequest) {
        return ResponseEntity.ok().body(lotService.makeLot(lotRequest));
    }

    @GetMapping("/search/{floor}")
    public ResponseEntity<LotResponse> search(@PathVariable("floor") String floor) {
        return ResponseEntity.ok().body(lotService.search(floor));
    }

    @PostMapping("/park")
    public ResponseEntity<LotResponse> park(@RequestBody SpaceRequest spaceRequest, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(spaceService.park(spaceRequest, httpServletRequest));
    }

    @DeleteMapping("/unpark")
    public ResponseEntity<LotResponse> unpark(@RequestBody SpaceRequest spaceRequest, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(spaceService.unpark(spaceRequest, httpServletRequest));
    }
}
