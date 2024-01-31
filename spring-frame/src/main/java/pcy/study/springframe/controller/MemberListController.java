package pcy.study.springframe.controller;

import jakarta.servlet.ServletException;
import pcy.study.springframe.domain.Member;
import pcy.study.springframe.front.ModelView;
import pcy.study.springframe.repository.MemberRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MemberListController implements Controller {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.put("members", members);
        return mv;
    }
}
