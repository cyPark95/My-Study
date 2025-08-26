package pcy.study.springbatch.learningtest.batch;

import pcy.study.springbatch.learningtest.batch.job.JobExecutionListener;
import pcy.study.springbatch.learningtest.batch.step.Step;

import java.util.List;

public class StepJob extends AbstractJob {

    private final List<Step> steps;

    public StepJob(JobExecutionListener jobExecutionListener, List<Step> steps) {
        super(jobExecutionListener);
        this.steps = steps;
    }

    @Override
    public void doExecute() {
        steps.forEach(Step::execute);
    }
}
