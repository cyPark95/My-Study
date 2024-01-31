package pcy.study.springframe.controller;

import jakarta.servlet.ServletException;
import pcy.study.springframe.domain.Member;
import pcy.study.springframe.front.ModelView;
import pcy.study.springframe.repository.MemberRepository;

import java.io.IOException;
import java.util.Map;

public class MemberSaveController implements Controller {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) throws ServletException, IOException {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result");
        mv.put("member", member);
        return mv;
    }
}
