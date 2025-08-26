package pcy.study.springbatch.learningtest.custombatch.tasklet;

import org.springframework.context.annotation.Bean;
import pcy.study.springbatch.learningtest.batch.job.Job;
import pcy.study.springbatch.learningtest.batch.job.JobExecutionListener;
import pcy.study.springbatch.learningtest.custombatch.dormant.item.DormantBatchItemProcessor;
import pcy.study.springbatch.learningtest.custombatch.dormant.item.DormantBatchItemReader;
import pcy.study.springbatch.learningtest.custombatch.dormant.item.DormantBatchItemWriter;

public class TaskletJobConfiguration {

    @Bean
    public Job dormantBatchJob(
            JobExecutionListener jobExecutionListener,
            DormantBatchItemReader itemReader,
            DormantBatchItemProcessor itemProcessor,
            DormantBatchItemWriter itemWriter
    ) {
        return TaskletJob.builder()
                .jobExecutionListener(jobExecutionListener)
                .itemReader(itemReader)
                .itemProcessor(itemProcessor)
                .itemWriter(itemWriter)
                .build();
    }
}
