package com.heowc.service;

import com.heowc.domain.Member;
import com.heowc.domain.MemberRepository;
import com.heowc.domain.MemberRequest;
import com.heowc.domain.NotFoundMemberIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultPasswordChangingService implements PasswordChangingService {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Transactional
    @Override
    public void changePassword(MemberRequest memberRequest) {
        Member member = repository.findById(memberRequest.getId()).orElseThrow(NotFoundMemberIdException::new);
        member.changePassword(memberRequest.getPassword(), publisher);
    }
}
