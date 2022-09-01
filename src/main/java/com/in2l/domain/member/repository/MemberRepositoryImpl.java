package com.in2l.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

  private final JPAQueryFactory jpaQueryFactory;

//  @Override
//  public List<Member> getMemberList(){
//
//  }
  //TODO: createMember시 validation 필요?

}

//  @Override//실제 여긴데??
//  public List<Post> getList(PostSearch postSearch) {
//
//    EntityManager entityManager = null;
//    new JPAQueryFactory(entityManager);
//
//    QPost qPost = new QPost("p");
//
////    Post findpost = (Post) jpaQueryFactory
////        .selectFrom(QPost.post)
////        .limit(postSearch.getSize())
////        .offset(postSearch.getOffset())
////        .orderBy(QPost.post.id.desc())
////        .fetch();
//    return jpaQueryFactory.selectFrom(QPost.post)
//        .limit(postSearch.getSize())
//        .offset(postSearch.getOffset())
////        .offset( (postSearch.getPage()-1) * postSearch.getSize())  //매전 이러는거 귀찮으니 postSearchdp 구현하자.
//        .orderBy(QPost.post.id.desc())
//        .fetch();
//  }
//}
