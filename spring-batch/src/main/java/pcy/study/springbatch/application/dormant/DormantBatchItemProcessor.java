package pcy.study.springbatch.application.dormant;

import org.springframework.stereotype.Component;
import pcy.study.springbatch.batch.ItemProcessor;
import pcy.study.springbatch.user.User;

import java.time.LocalDate;

@Component
public class DormantBatchItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User item) {
        boolean isDormantTarget = LocalDate.now()
                .minusDays(365)
                .isAfter(item.getLoginAt().toLocalDate());

        if(!isDormantTarget) {
            return null;
        }

        item.changeDormant();
        return item;
    }
}
