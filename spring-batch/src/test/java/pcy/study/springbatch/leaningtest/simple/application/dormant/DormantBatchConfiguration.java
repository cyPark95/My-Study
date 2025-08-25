package pcy.study.springbatch.leaningtest.simple.application.dormant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pcy.study.springbatch.leaningtest.simple.batch.job.Job;
import pcy.study.springbatch.leaningtest.simple.batch.job.JobExecutionListener;
import pcy.study.springbatch.leaningtest.simple.batch.job.step.Step;
import pcy.study.springbatch.leaningtest.simple.batch.job.step.StepJobBuilder;

@Configuration
public class DormantBatchConfiguration {

    @Bean
    public Job dormantBatchJob(
            JobExecutionListener jobExecutionListener,
            Step preDormantBatchStep,
            Step dormantBatchStep
    ) {
        return new StepJobBuilder()
                .start(preDormantBatchStep)
                .next(dormantBatchStep)
                .listener(jobExecutionListener)
                .build();
    }

    @Bean
    public Step preDormantBatchStep(
            DormantBatchItemReader itemReader,
            PreDormantBatchItemProcessor itemProcessor,
            PreDormantBatchItemWriter itemWriter
    ) {
        return Step.builder()
                .itemReader(itemReader)
                .itemProcessor(itemProcessor)
                .itemWriter(itemWriter)
                .build();
    }

    @Bean
    public Step dormantBatchStep(
            DormantBatchItemReader itemReader,
            DormantBatchItemProcessor itemProcessor,
            DormantBatchItemWriter itemWriter
    ) {
        return Step.builder()
                .itemReader(itemReader)
                .itemProcessor(itemProcessor)
                .itemWriter(itemWriter)
                .build();
    }
}
