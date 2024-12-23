package com.main.invenmbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.invenmbe.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
