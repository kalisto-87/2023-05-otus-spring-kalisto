package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.PollerSpec;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.service.ChestBursterTransformer;
import ru.otus.service.FaceHuggerTransformer;
import ru.otus.service.XenomorphTransformer;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannelSpec<?, ?> eggChannel() {
        return MessageChannels.queue(5);
    }

    @Bean
    public MessageChannelSpec<?, ?> xChannel() {
        return MessageChannels.publishSubscribe();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerSpec poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2);
    }

    @Bean
    public IntegrationFlow xenomorphFlow(FaceHuggerTransformer faceHuggerTransformer,
            ChestBursterTransformer chestBursterTransformer,
            XenomorphTransformer xenomorphTransformer
                                         ) {
        return IntegrationFlow.from(eggChannel())
                .split()
                .handle(faceHuggerTransformer, "transform")
                .handle(chestBursterTransformer, "transform")
                .handle(xenomorphTransformer, "transform")
                .aggregate()
                .channel(xChannel())
                .get();
    }

}
