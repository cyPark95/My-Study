package pcy.study.springbatch.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pcy.study.springbatch.batch.ItemProcessor;
import pcy.study.springbatch.batch.ItemReader;
import pcy.study.springbatch.batch.ItemWriter;
import pcy.study.springbatch.batch.Job;
import pcy.study.springbatch.user.User;

@Configuration
public class DormantBatchConfiguration {

    @Bean
    public Job dormantBatchTaskLet(
//            DormantBatchTaskLet dormantBatchTaskLet,
//            DormantBatchJobExecutionListener dormantBatchJobExecutionListener
            ItemReader<User> itemReader,
            ItemProcessor<User, User> itemProcessor,
            ItemWriter<User> itemWriter
    ) {
//        return new Job(
//                dormantBatchTaskLet,
//                dormantBatchJobExecutionListener
//        );

        return Job.builder()
                .itemReader(itemReader)
                .itemProcessor(itemProcessor)
                .itemWriter(itemWriter)
                .build();
    }
}
