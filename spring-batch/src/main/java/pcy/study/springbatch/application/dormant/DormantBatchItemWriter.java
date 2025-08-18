package pcy.study.springbatch.application.dormant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pcy.study.springbatch.batch.ItemWriter;
import pcy.study.springbatch.email.EmailProvider;
import pcy.study.springbatch.user.User;
import pcy.study.springbatch.user.UserRepository;

@Component
@RequiredArgsConstructor
public class DormantBatchItemWriter implements ItemWriter<User> {

    private final UserRepository userRepository;
    private final EmailProvider emailProvider;

    @Override
    public void write(User item) {
        userRepository.save(item);
        emailProvider.send(item.getEmail(), "휴먼 전환 안내 메일입니다.", "내용");
    }
}
