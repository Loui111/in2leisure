package com.in2l.domain.member.repository;

import com.in2l.domain.member.domain.Member;
import com.in2l.domain.member.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Member findByName(String name) {
    return jpaQueryFactory.selectFrom(QMember.member)
        .where(QMember.member.name.eq(name))
        .fetchOne();
  }

  @Override
  public Optional<Member> findByIdWithDeleteFlagFalse(Long id) {
    return Optional.ofNullable(jpaQueryFactory.selectFrom(QMember.member)
        .where(QMember.member.deleteFlag.eq(Boolean.FALSE))
        .where(QMember.member.id.eq(id))
        .fetchOne());
  }

  @Override
  public Optional<List<Member>> findAllWithoutDelete(){
    return Optional.ofNullable(jpaQueryFactory.selectFrom(QMember.member)
        .where(QMember.member.deleteFlag.eq(Boolean.FALSE))
        .fetch());
  }
}
