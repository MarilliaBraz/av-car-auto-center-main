package com.ads3.auto_center.core.repositories;

import com.ads3.auto_center.core.domains.BaseModel;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericRepository<E extends BaseModel> extends JpaRepository<E, Long> {
    Optional<E> findByIdAndAtivoTrue(Long id);
    Page<E> findAllByAtivoTrue(Pageable pageable);
}