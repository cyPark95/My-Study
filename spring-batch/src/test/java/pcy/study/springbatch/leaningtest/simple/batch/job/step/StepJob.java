package pcy.study.springbatch.leaningtest.simple.batch.job.step;

import org.springframework.stereotype.Component;
import pcy.study.springbatch.leaningtest.simple.batch.job.AbstractJob;
import pcy.study.springbatch.leaningtest.simple.batch.job.JobExecutionListener;

import java.util.List;

@Component
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
