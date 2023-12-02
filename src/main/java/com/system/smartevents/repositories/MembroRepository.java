
package com.system.smartevents.repositories;

import com.system.smartevents.models.MembroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroRepository extends JpaRepository<MembroModel,Integer> {
}