package pcy.study.sns.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberReadService {

    private final MemberRepository memberRepository;

    public MemberDto getMember(Long id) {
        var member = memberRepository.findById(id)
                .orElseThrow();
        return toDto(member);
    }

    private MemberDto toDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getBirthday()
        );
    }
}
