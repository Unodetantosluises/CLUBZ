package com.rungroup.web.repository;

import com.rungroup.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByName(String url);
    @Query("SELECT e FROM Event e WHERE LOWER(e.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Event> searchEvents(@Param("query") String query);

    List<Event> findAllByOrderByCreatedOnAsc();
}
