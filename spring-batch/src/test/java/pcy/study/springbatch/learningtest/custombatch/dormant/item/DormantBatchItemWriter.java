package pcy.study.springbatch.learningtest.custombatch.dormant.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pcy.study.springbatch.email.EmailProvider;
import pcy.study.springbatch.learningtest.batch.item.ItemWriter;
import pcy.study.springbatch.user.User;
import pcy.study.springbatch.user.UserRepository;

@Component
@RequiredArgsConstructor
public class DormantBatchItemWriter implements ItemWriter<User> {

    private final UserRepository userRepository;
    private final EmailProvider emailProvider;

    @Override
    public void write(User user) {
        userRepository.save(user);
        emailProvider.send(user.getEmail(), "휴먼 전환 안내 메일입니다.", "내용");
    }
}
