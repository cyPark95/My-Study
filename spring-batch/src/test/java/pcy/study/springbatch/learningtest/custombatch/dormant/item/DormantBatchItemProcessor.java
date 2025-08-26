package pcy.study.springbatch.learningtest.custombatch.dormant.item;

import org.springframework.stereotype.Component;
import pcy.study.springbatch.learningtest.batch.item.ItemProcessor;
import pcy.study.springbatch.user.User;

import java.time.LocalDate;

@Component
public class DormantBatchItemProcessor implements ItemProcessor<User, User> {

    private static final int DORMANT_PERIOD_DAYS = 365;

    @Override
    public User process(User user) {
        LocalDate dormantDate = LocalDate.now()
                .minusDays(DORMANT_PERIOD_DAYS);

        if(!user.isDormantSince(dormantDate)) {
            return null;
        }

        user.changeDormant();
        return user;
    }
}
