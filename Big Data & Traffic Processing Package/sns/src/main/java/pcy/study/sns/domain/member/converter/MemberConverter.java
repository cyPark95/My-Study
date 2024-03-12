package pcy.study.sns.domain.member.converter;

import org.springframework.stereotype.Component;
import pcy.study.sns.domain.member.dto.MemberDto;
import pcy.study.sns.domain.member.entity.Member;

@Component
public class MemberConverter {

    public MemberDto toDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getBirthday()
        );
    }
}
