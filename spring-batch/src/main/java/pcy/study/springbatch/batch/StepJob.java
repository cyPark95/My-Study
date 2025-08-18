package pcy.study.springbatch.batch;

import org.springframework.stereotype.Component;

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
