package inu.swcontest.gm.repository;

import inu.swcontest.gm.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository {
    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
