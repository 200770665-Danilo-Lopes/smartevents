
package com.system.smartevents.repositories;

import com.system.smartevents.models.EventoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<EventoModel, Integer> {
}