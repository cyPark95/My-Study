package pcy.study.springbatch.learningtest.custombatch.step;

import org.springframework.context.annotation.Bean;
import pcy.study.springbatch.learningtest.batch.job.Job;
import pcy.study.springbatch.learningtest.batch.job.JobExecutionListener;
import pcy.study.springbatch.learningtest.batch.step.Step;
import pcy.study.springbatch.learningtest.batch.StepJobBuilder;
import pcy.study.springbatch.learningtest.custombatch.dormant.item.*;

public class StepJobConfiguration {

    @Bean
    public Job dormantBatchJob(
            JobExecutionListener jobExecutionListener,
            Step previousDormantBatchStep,
            Step dormantBatchStep
    ) {
        return new StepJobBuilder()
                .start(previousDormantBatchStep)
                .next(dormantBatchStep)
                .listener(jobExecutionListener)
                .build();
    }

    @Bean
    public Step previousDormantBatchStep(
            DormantBatchItemReader itemReader,
            PreviousDormantBatchItemProcessor itemProcessor,
            PreviousDormantBatchItemWriter itemWriter
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
