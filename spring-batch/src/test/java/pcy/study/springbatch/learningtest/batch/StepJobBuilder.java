package pcy.study.springbatch.learningtest.batch;

import pcy.study.springbatch.learningtest.batch.job.JobExecutionListener;
import pcy.study.springbatch.learningtest.batch.step.Step;

import java.util.ArrayList;
import java.util.List;

public class StepJobBuilder {

    private List<Step> steps;
    private JobExecutionListener jobExecutionListener;

    public StepJobBuilder start(Step step) {
        steps = new ArrayList<>();
        steps.add(step);
        return this;
    }

    public StepJobBuilder next(Step step) {
        steps.add(step);
        return this;
    }

    public StepJobBuilder listener(JobExecutionListener jobExecutionListener) {
        this.jobExecutionListener = jobExecutionListener;
        return this;
    }

    public StepJob build() {
        return new StepJob(jobExecutionListener, steps);
    }
}
