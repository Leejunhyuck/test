package org.motivators.wavy_project.user.repository;

import org.motivators.wavy_project.user.domain.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> {

}
