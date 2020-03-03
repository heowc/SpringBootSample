package com.heowc.service;

import com.heowc.domain.Member;
import com.heowc.domain.MemberRepository;
import com.heowc.domain.MemberRequest;
import com.heowc.domain.NotFoundMemberIdException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultPasswordChangingService implements PasswordChangingService {

    private final MemberRepository repository;
    private final ApplicationEventPublisher publisher;

    public DefaultPasswordChangingService(MemberRepository repository, ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @Transactional
    @Override
    public void changePassword(MemberRequest memberRequest) {
        final Member member = repository.findById(memberRequest.getId())
                                        .orElseThrow(NotFoundMemberIdException::new);
        member.changePassword(memberRequest.getPassword(), publisher);
    }
}
