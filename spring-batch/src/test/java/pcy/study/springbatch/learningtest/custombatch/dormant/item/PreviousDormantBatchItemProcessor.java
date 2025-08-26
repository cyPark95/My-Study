package pcy.study.springbatch.learningtest.custombatch.dormant.item;

import org.springframework.stereotype.Component;
import pcy.study.springbatch.learningtest.batch.item.ItemProcessor;
import pcy.study.springbatch.user.User;

import java.time.LocalDate;

@Component
public class PreviousDormantBatchItemProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) {
        LocalDate targetDate = LocalDate.now()
                .minusYears(1)
                .plusDays(7);

        if(targetDate.equals(user.getLoginAt().toLocalDate())) {
            return user;
        }

        return null;
    }
}
