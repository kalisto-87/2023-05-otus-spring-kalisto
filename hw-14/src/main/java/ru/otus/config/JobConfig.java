package ru.otus.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import ru.otus.domain.h2.Author;
import ru.otus.domain.h2.Book;
import ru.otus.domain.h2.Genre;
import ru.otus.domain.mongo.mAuthor;
import ru.otus.domain.mongo.mBook;
import ru.otus.domain.mongo.mGenre;
import ru.otus.service.TransferService;

import java.util.Map;

@Slf4j
@Configuration
public class JobConfig {

    private static final int CHUNK_SIZE = 3;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager manager;

    public JobConfig(JobRepository jobRepository, PlatformTransactionManager manager) {
        this.jobRepository = jobRepository;
        this.manager = manager;
    }

    @StepScope
    @Bean
    public MongoItemReader<mAuthor> readerAuthor(MongoTemplate template) {
        return new MongoItemReaderBuilder<mAuthor>()
                .name("authorItemReader")
                .targetType(mAuthor.class)
                .jsonQuery("[]")
                .template(template)
                .sorts(Map.of())
                .build();
    }

    @StepScope
    @Bean
    public MongoItemReader<mGenre> readerGenre(MongoTemplate template) {
        return new MongoItemReaderBuilder<mGenre>()
                .name("genreItemReader")
                .targetType(mGenre.class)
                .jsonQuery("[]")
                .template(template)
                .sorts(Map.of())
                .build();
    }

    @StepScope
    @Bean
    public MongoItemReader<mBook> readerBook(MongoTemplate template) {
        return new MongoItemReaderBuilder<mBook>()
                .name("bookItemReader")
                .targetType(mBook.class)
                .jsonQuery("[]")
                .template(template)
                .sorts(Map.of())
                .build();
    }

    @StepScope
    @Bean
    public JpaItemWriter<Author> writerAuthor(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<Author>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @StepScope
    @Bean
    public JpaItemWriter<Genre> writerGenre(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<Genre>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @StepScope
    @Bean
    public JpaItemWriter<Book> writerBook(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<Book>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<mAuthor, Author> processorAuthor(
            TransferService transferService
    ) {
        return transferService::transferAuthor;
    }

    @StepScope
    @Bean
    public ItemProcessor<mGenre, Genre> processorGenre(
            TransferService transferService
    ) {
        return transferService::transferGenre;
    }

    @StepScope
    @Bean
    public ItemProcessor<mBook, Book> processorBook(
            TransferService transferService
    ) {
        return transferService::transferBook;
    }

    @Bean
    public Step transformAuthorStep(
            MongoItemReader<mAuthor> readerCharacter,
            JpaItemWriter<Author> writerCharacter,
            ItemProcessor<mAuthor, Author> processorCharacter
    ) {
        return new StepBuilder("transformAuthorStep", jobRepository)
                .<mAuthor, Author>chunk(CHUNK_SIZE, manager)
                .reader(readerCharacter)
                .processor(processorCharacter)
                .writer(writerCharacter)
                .build();
    }

    @Bean
    public Step transformGenreStep(
            MongoItemReader<mGenre> readerEpisode,
            JpaItemWriter<Genre> writerEpisode,
            ItemProcessor<mGenre, Genre> processorEpisode
    ) {
        return new StepBuilder("transformGenreStep", jobRepository)
                .<mGenre, Genre>chunk(CHUNK_SIZE, manager)
                .reader(readerEpisode)
                .processor(processorEpisode)
                .writer(writerEpisode)
                .build();
    }

    @Bean
    public Step transformBookStep(
            MongoItemReader<mBook> readerCharacter,
            JpaItemWriter<Book> writerCharacter,
            ItemProcessor<mBook, Book> processorCharacter
    ) {
        return new StepBuilder("transformBookStep", jobRepository)
                .<mBook, Book>chunk(CHUNK_SIZE, manager)
                .reader(readerCharacter)
                .processor(processorCharacter)
                .writer(writerCharacter)
                .build();
    }

    @Bean
    public Job transferJob(Step transformAuthorStep, Step transformGenreStep, Step transformBookStep) {
        return new JobBuilder("transferJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(transformAuthorStep)
                .next(transformGenreStep)
                .next(transformBookStep)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        log.info("Start job");
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        log.info("End job");
                    }
                }).build();
    }



}
