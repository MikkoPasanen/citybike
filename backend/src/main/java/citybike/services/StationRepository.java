package citybike.services;

import citybike.entity.Station;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Integer> {
    @NonNull
    Page<Station> findAll(@NonNull Pageable pageable);
}
