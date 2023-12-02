
package com.system.smartevents.repositories;

import com.system.smartevents.models.AgenciaModel;
import com.system.smartevents.models.AgendamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoModel, Integer> {
}
