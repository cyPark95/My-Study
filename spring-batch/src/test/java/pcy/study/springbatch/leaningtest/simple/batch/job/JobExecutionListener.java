package pcy.study.springbatch.leaningtest.simple.batch.job;

public interface JobExecutionListener {

    void beforeJob(JobExecution jobExecution);

    void afterJob(JobExecution jobExecution);
}
