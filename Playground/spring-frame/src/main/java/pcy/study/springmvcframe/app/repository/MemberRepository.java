package pcy.study.springmvcframe.app.repository;

import lombok.Getter;
import pcy.study.springmvcframe.app.domain.Member;

import java.util.*;

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

    public Member findByUsername(String username) {
        return store.values().stream()
                .filter(member -> member.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
