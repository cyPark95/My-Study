package pcy.study.springbatch.batch;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class JobExecution {

    private BatchStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public JobExecution() {
        this.status = BatchStatus.STARTED;
        this.startTime = LocalDateTime.now();
    }

    public void failed() {
        this.status = BatchStatus.FAILED;
    }

    public void completed() {
        this.status = BatchStatus.COMPLETED;
    }

    public void end() {
        this.endTime = LocalDateTime.now();
    }
}
