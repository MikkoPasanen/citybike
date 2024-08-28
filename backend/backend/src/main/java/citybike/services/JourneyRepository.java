package citybike.services;

import citybike.entity.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey, Integer> {
    @Query(value = "SELECT * FROM journey LIMIT :limit", nativeQuery = true)
    List<Journey> findLimited(@Param("limit") int limit);
}
