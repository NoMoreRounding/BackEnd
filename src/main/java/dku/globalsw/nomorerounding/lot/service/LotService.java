package dku.globalsw.nomorerounding.lot.service;

import dku.globalsw.nomorerounding.lot.domain.entity.Lot;
import dku.globalsw.nomorerounding.lot.domain.repository.LotRepository;
import dku.globalsw.nomorerounding.lot.dto.mapper.LotMapper;
import dku.globalsw.nomorerounding.lot.dto.request.LotRequest;
import dku.globalsw.nomorerounding.lot.dto.response.LotResponse;
import dku.globalsw.nomorerounding.space.domain.entity.Space;
import dku.globalsw.nomorerounding.space.domain.entity.SpecificSpace;
import dku.globalsw.nomorerounding.space.dto.SpaceMapper;
import dku.globalsw.nomorerounding.space.dto.SpecificSpaceMapper;
import dku.globalsw.nomorerounding.space.dto.SpaceResponse;
import dku.globalsw.nomorerounding.space.dto.SpecificSpaceResponse;
import dku.globalsw.nomorerounding.lot.domain.exception.DuplicatedFloorException;
import dku.globalsw.nomorerounding.lot.domain.exception.InvalidFloorException;
import dku.globalsw.nomorerounding.space.domain.repository.SpecificSpaceRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LotService {

    private final LotRepository lotRepository;
    private final SpecificSpaceRepository specificSpaceRepository;

    public Lot addSpace(Lot lot, Space space) {
        lot.getSpace().add(space);
        return lotRepository.save(lot);
    }

    public LotResponse makeLot(LotRequest lotRequest) {

        LotResponse lotResponse = null;

        if (lotRepository.findById(lotRequest.getFloor()).isPresent()) {
            throw new DuplicatedFloorException();
        }

        Lot lot = LotMapper.INSTANCE.requestToEntity(lotRequest);
        Lot savedLot = lotRepository.save(lot);

        List<SpecificSpace> specificSpaces = SpecificSpaceMapper.INSTANCE.requestToEntity(
            lotRequest.getSpecificSpaceRequests());

        List<SpecificSpace> specificSpaceList = new ArrayList<>();

        for (SpecificSpace e : specificSpaces) {
            SpecificSpace save = specificSpaceRepository.save(e);
            save.setLot(savedLot);
            specificSpaceList.add(specificSpaceRepository.save(save));
        }

        lotResponse = LotMapper.INSTANCE.toDto(savedLot);
        lotResponse.setSpecificSpaceResponses(
            SpecificSpaceMapper.INSTANCE.toDto(specificSpaceList));

        return lotResponse;
    }

    public Lot findById(String floor) {
        return lotRepository.findById(floor).orElseThrow(InvalidFloorException::new);
    }

    public LotResponse search(String floor) {
        LotResponse lotResponse = null;

        Lot lot = findById(floor);

        return toResponse(lot);
    }

    public LotResponse toResponse(Lot lot) {
        LotResponse lotResponse = LotMapper.INSTANCE.toDto(lot);

        List<SpaceResponse> spaceResponses = SpaceMapper.INSTANCE.toDto(lot.getSpace());

        List<SpecificSpaceResponse> specificSpaceResponses = SpecificSpaceMapper.INSTANCE.toDto(
            lot.getSpecificSpace());

        lotResponse.setSpaceResponses(spaceResponses);
        lotResponse.setSpecificSpaceResponses(specificSpaceResponses);

        return lotResponse;
    }
}
