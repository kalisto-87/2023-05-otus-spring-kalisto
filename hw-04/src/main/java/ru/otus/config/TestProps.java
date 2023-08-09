package ru.otus.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "test")
public class TestProps {

    private final String testName;

    private final String filePath;

    private final char delimiter;

    private final Integer cntAnswersForSuccess;

    @ConstructorBinding
    public TestProps(String name, String filePath, char delimiter, Integer cntForSuccess) {
        this.testName = name;
        this.filePath = filePath;
        this.delimiter = delimiter;
        this.cntAnswersForSuccess = cntForSuccess;
    }
}
