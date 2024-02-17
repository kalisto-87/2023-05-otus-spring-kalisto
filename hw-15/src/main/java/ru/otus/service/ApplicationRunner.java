package ru.otus.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.model.Egg;
import ru.otus.model.Xenomorph;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationRunner {

    private final XenomorphTransformationGateway gateway;

    public void process() {

        List<Egg> eggs = List.of(new Egg("Vasya"),
                new Egg("Gosha"),
                new Egg("Ripley"),
                new Egg("Queen"),
                new Egg("Ridley Scott"));


        Collection<Xenomorph> xenomorphs = gateway.process(eggs);
        log.info("All xenomorphs are ready to kill people! Let's go!");

    }

}
