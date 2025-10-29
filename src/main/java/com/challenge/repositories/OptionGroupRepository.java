package com.challenge.repositories;

import com.challenge.domain.model.OptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionGroupRepository extends JpaRepository<OptionGroup, Long> {
}