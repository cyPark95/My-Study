package pcy.study.springframe.repository;

import lombok.Getter;
import pcy.study.springframe.domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    @Getter
    private static final MemberRepository instance = new MemberRepository();
    private static final Map<Long, Member> store = new HashMap<>();

    private static long sequence = 0L;

    private MemberRepository() {
    }

    public void save(Member member) {
        member.setSequence(++sequence);
        store.put(member.getId(), member);
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
