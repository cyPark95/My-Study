package pcy.study.springbatch.application.dormant;

import org.springframework.stereotype.Component;
import pcy.study.springbatch.batch.ItemProcessor;
import pcy.study.springbatch.user.User;

import java.time.LocalDate;

@Component
public class PreDormantBatchItemProcessor implements ItemProcessor<User, User> {

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
