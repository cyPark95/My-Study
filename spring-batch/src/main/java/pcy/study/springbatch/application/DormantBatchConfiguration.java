package pcy.study.springbatch.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pcy.study.springbatch.batch.*;
import pcy.study.springbatch.user.User;

@Configuration
public class DormantBatchConfiguration {

    @Bean
    public Job dormantBatchTaskLet(
            JobExecutionListener jobExecutionListener,
            ItemReader<User> itemReader,
            ItemProcessor<User, User> itemProcessor,
            ItemWriter<User> itemWriter
    ) {
        return Job.builder()
                .jobExecutionListener(jobExecutionListener)
                .itemReader(itemReader)
                .itemProcessor(itemProcessor)
                .itemWriter(itemWriter)
                .build();
    }
}
