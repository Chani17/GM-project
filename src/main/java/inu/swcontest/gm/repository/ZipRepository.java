package inu.swcontest.gm.repository;

import inu.swcontest.gm.entity.Zip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZipRepository extends JpaRepository<Zip, Long> {
    List<Zip> findByMemberEmail(String email);
}
