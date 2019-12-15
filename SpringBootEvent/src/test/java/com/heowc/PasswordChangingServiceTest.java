package com.heowc;

import com.heowc.domain.*;
import com.heowc.service.PasswordChangingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PasswordChangingServiceTest {

    @Autowired
    private PasswordChangingService service;

    @Autowired
    private MemberRepository repository;

    @Test
    void notFoundMemberId() {
        // given
        repository.save(new Member("heowc1992", "1234"));

        // when-then
        assertThatThrownBy(() -> {
            MemberRequest memberRequest = new MemberRequest("heowc", "1234");
            service.changePassword(memberRequest);
        }).isInstanceOf(NotFoundMemberIdException.class);
    }

    @Test
    void doesNotMatchedPassword() {
        // given
        repository.save(new Member("heowc1992", "1234"));

        // when-then
        assertThatThrownBy(() -> {
            MemberRequest memberRequest = new MemberRequest("heowc1992", "123");
            service.changePassword(memberRequest);
        }).isInstanceOf(PasswordNotMatchingException.class);
    }

    @Test
    void changePassword() {
        // given
        Member member = new Member("heowc1992", "1234");
        repository.save(member);

        // when
        service.changePassword(new MemberRequest("heowc1992", "1234"));

        // then
        Member changedMember = repository.findById("heowc1992").orElse(null);
        assertThat(changedMember).isNotNull();
        assertThat(changedMember.getPassword()).doesNotMatch("1234");
    }

}
