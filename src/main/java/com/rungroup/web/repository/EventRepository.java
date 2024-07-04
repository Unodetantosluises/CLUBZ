package com.rungroup.web.repository;

import com.rungroup.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByName(String url);
    @Query("SELECT e FROM Event e WHERE e.name LIKE CONCAT('%', :query, '%')")
    List<Event> searchEvents(String query);

    List<Event> findAllByOrderByCreatedOnAsc();
}
