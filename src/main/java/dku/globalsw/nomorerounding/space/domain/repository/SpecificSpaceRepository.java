package dku.globalsw.nomorerounding.space.domain.repository;

import dku.globalsw.nomorerounding.lot.domain.entity.Lot;
import dku.globalsw.nomorerounding.space.domain.entity.SpecificSpace;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificSpaceRepository extends JpaRepository<SpecificSpace, Long> {
    Optional<SpecificSpace> findBySpaceRowAndSpaceColumnAndLot(Integer spaceRow, Integer spaceColumn, Lot lot);
}
