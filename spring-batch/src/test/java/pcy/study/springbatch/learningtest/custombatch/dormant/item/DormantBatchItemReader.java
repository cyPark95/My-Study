package pcy.study.springbatch.learningtest.custombatch.dormant.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import pcy.study.springbatch.learningtest.batch.item.ItemReader;
import pcy.study.springbatch.user.User;
import pcy.study.springbatch.user.UserRepository;

@Component
@RequiredArgsConstructor
public class DormantBatchItemReader implements ItemReader<User> {

    private final UserRepository userRepository;
    private int pageNo = 0;

    @Override
    public User read() {
        PageRequest pageRequest = PageRequest.of(pageNo++, 1, Sort.by("id").ascending());
        Page<User> page = userRepository.findAll(pageRequest);

        if (page.isEmpty()) {
            pageNo = 0;
            return null;
        }

        return page.getContent().getFirst();
    }
}
