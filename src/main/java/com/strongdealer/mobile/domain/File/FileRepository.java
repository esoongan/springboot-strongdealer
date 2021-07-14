package com.strongdealer.mobile.domain.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findAllByCar_Id(Long id);
}
