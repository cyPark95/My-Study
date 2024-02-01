package pcy.study.springframe.controller;

import jakarta.servlet.ServletException;
import pcy.study.springframe.domain.Member;
import pcy.study.springframe.repository.MemberRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MemberListController implements ModelController {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        model.put("members", members);
        return "members";
    }
}
