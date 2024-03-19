package pcy.study.sns.domain.member.repository;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import pcy.study.sns.common.RepositoryTest;
import pcy.study.sns.domain.member.entity.Member;
import pcy.study.sns.util.MemberFixtureFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
class MemberJdbcRepositoryTest {

    private MemberRepository memberRepository;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @BeforeEach
    void setUp() {
        memberRepository = new MemberJdbcRepository(namedParameterJdbcTemplate);
    }

    @Test
    @DisplayName("ID로 회원 조회")
    void findById() {
        // given
        Member member = MemberFixtureFactory.create();
        Long id = memberRepository.save(member).getId();

        // when
        Optional<Member> result = memberRepository.findById(id);

        // then
        assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("빈 ID 목록으로 회원 목록 조회")
    void findAllByIdIn_emptyIds() {
        // given
        List<Long> ids = List.of();

        // when
        List<Member> result = memberRepository.findAllByIdIn(ids);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("ID 목록으로 회원 목록 조회")
    void findAllByIdIn() {
        // given
        int size = 10;
        EasyRandom easyRandom = MemberFixtureFactory.get();
        List<Long> ids = IntStream.range(0, size)
                .parallel()
                .mapToObj(i -> {
                    Member member = easyRandom.nextObject(Member.class);
                    return memberRepository.save(member).getId();
                })
                .toList();

        // when
        List<Member> result = memberRepository.findAllByIdIn(ids);

        // then
        assertEquals(size, result.size());
    }

    @Test
    @DisplayName("회원 저장")
    void save() {
        // given
        Member member = MemberFixtureFactory.create();

        // when
        Member result = memberRepository.save(member);

        // then
        assertNotNull(result.getId());
    }

}
