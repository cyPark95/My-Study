package pcy.study.springbatch.leaningtest.simple.application.dormant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pcy.study.springbatch.email.EmailProvider;
import pcy.study.springbatch.leaningtest.simple.batch.ItemWriter;
import pcy.study.springbatch.user.User;

@Component
@RequiredArgsConstructor
public class PreDormantBatchItemWriter implements ItemWriter<User> {

    private final EmailProvider emailProvider;

    @Override
    public void write(User user) {
        emailProvider.send(
                user.getEmail(),
                "곧 휴먼 계정으로 전환됩니다.",
                "휴먼 계정으로 전환을 원치 않으신다면 1주일 내에 로그인 해주세요."
        );
    }
}
