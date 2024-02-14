package ru.otus.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDateTime;

@ShellComponent
@RequiredArgsConstructor
public class UIShell {

    private final Job jobTransfer;

    private final JobLauncher launcher;

    @ShellMethod(value = "Start", key = "run")
    public String run() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        launcher.run(
                jobTransfer,
                new JobParametersBuilder().
                        addLocalDateTime("time", LocalDateTime.now()).toJobParameters());
        return "";
    }

}
