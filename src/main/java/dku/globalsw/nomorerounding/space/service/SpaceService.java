package dku.globalsw.nomorerounding.space.service;

import dku.globalsw.nomorerounding.space.domain.exception.InvalidParkingException;
import dku.globalsw.nomorerounding.lot.domain.entity.Lot;
import dku.globalsw.nomorerounding.auth.service.AuthService;
import dku.globalsw.nomorerounding.lot.service.LotService;
import dku.globalsw.nomorerounding.space.domain.entity.Space;
import dku.globalsw.nomorerounding.space.domain.entity.SpecificSpace;
import dku.globalsw.nomorerounding.space.domain.enums.SpaceTypeChecker;
import dku.globalsw.nomorerounding.space.domain.repository.SpaceRepository;
import dku.globalsw.nomorerounding.space.domain.repository.SpecificSpaceRepository;
import dku.globalsw.nomorerounding.space.dto.SpaceMapper;
import dku.globalsw.nomorerounding.space.dto.SpaceRequest;
import dku.globalsw.nomorerounding.user.domain.entity.User;
import dku.globalsw.nomorerounding.lot.dto.response.LotResponse;
import dku.globalsw.nomorerounding.space.domain.exception.DuplicatedSpaceException;
import dku.globalsw.nomorerounding.space.domain.exception.InvalidSpaceException;
import dku.globalsw.nomorerounding.space.domain.exception.InvalidSpaceTypeException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;
    private final SpecificSpaceRepository specificSpaceRepository;
    private final LotService lotService;
    private final AuthService authService;

    public Space save(Space space) {
        return spaceRepository.save(space);
    }

    public LotResponse park(SpaceRequest spaceRequest, HttpServletRequest httpServletRequest) {

        User user = authService.findUserByHttpServlet(httpServletRequest);

        String floor = spaceRequest.getFloor();

        Lot lot = lotService.findById(floor);

        SpecificSpace specificSpace = specificSpaceRepository.findBySpaceRowAndSpaceColumnAndLot(
            spaceRequest.getSpaceRow(),
            spaceRequest.getSpaceColumn(), lot).orElse(null);

        if (specificSpace != null) {
            if (!SpaceTypeChecker.checker(specificSpace.getSpaceType(), user.getCar())) {
                throw new InvalidSpaceTypeException();
            }
        }

        if (spaceRepository.existsByUserId(user.getId())) {
            throw new DuplicatedSpaceException("이미 주차한 유저입니다.");
        }

        if (spaceRepository.existsBySpaceRowAndSpaceColumnAndLot(spaceRequest.getSpaceRow(),
            spaceRequest.getSpaceColumn(), lot)) {
            throw new DuplicatedSpaceException("이미 주차된 자리입니다.");
        }

        Space space = SpaceMapper.INSTANCE.requestToEntity(spaceRequest, user.getId());
        Space savedSpace = save(space);

        savedSpace.setLot(lot);

        lotService.addSpace(lot, space);

        return lotService.toResponse(lot);
    }

    public LotResponse unpark(SpaceRequest spaceRequest, HttpServletRequest httpServletRequest) {

        User user = authService.findUserByHttpServlet(httpServletRequest);

        String floor = spaceRequest.getFloor();

        Lot lot = lotService.findById(floor);

        Space space = spaceRepository.findByUserId(user.getId())
            .orElseThrow(InvalidSpaceException::new);

        spaceRepository.delete(space);

        return lotService.toResponse(lot);
    }
}
