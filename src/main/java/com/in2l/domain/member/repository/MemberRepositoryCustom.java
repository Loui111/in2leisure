package com.in2l.domain.member.repository;

import com.in2l.domain.member.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {
  //  List<Member> getMember();
  Member findByName(String name);

  Optional<Member> findByIdWithDeleteFlagFalse(Long id);

  Optional<List<Member>> findAllWithoutDelete();

  Optional<List<Member>> findAllWithDeleteFlagOnly();
}
