package citybike.services;

import citybike.entity.Journey;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyRepository extends JpaRepository<Journey, Integer> {

    @NonNull
    Page<Journey> findAll(@NonNull Pageable pageable);
}
