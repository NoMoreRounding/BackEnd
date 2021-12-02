package dku.globalsw.nomorerounding.lot.domain.repository;

import dku.globalsw.nomorerounding.lot.domain.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotRepository extends JpaRepository<Lot, String> {

}
