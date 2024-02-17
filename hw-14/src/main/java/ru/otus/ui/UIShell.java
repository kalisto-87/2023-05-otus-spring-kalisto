package ru.otus.ui;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDateTime;

@ShellComponent
public class UIShell {

    private final Job jobTransfer;

    private final JobLauncher launcher;

    public UIShell(Job jobTransfer, JobLauncher jobLauncher) {
        this.jobTransfer = jobTransfer;
        this.launcher = jobLauncher;
    }

    @ShellMethod(value = "Start", key = "run")
    public String run() throws Exception {
        launcher.run(
                jobTransfer,
                new JobParametersBuilder().
                        addLocalDateTime("time", LocalDateTime.now()).toJobParameters());
        return "success";
    }

}
