package pcy.study.springbatch.leaningtest.simple.batch.job;

import lombok.Getter;

@Getter
public enum BatchStatus {

    STARTED,
    FAILED,
    COMPLETED
}
