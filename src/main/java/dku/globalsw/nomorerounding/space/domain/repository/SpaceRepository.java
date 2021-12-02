package dku.globalsw.nomorerounding.space.domain.repository;

import dku.globalsw.nomorerounding.lot.domain.entity.Lot;
import dku.globalsw.nomorerounding.space.domain.entity.Space;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceRepository extends JpaRepository<Space, String> {

    Boolean existsByUserId(Long userId);

    Boolean existsBySpaceRowAndSpaceColumnAndLot(Integer spaceRow, Integer spaceColumn, Lot lot);

    Optional<Space> findByUserId(Long userId);
}
