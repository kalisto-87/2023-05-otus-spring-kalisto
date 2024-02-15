package ru.otus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.model.ChestBurster;
import ru.otus.model.FaceHugger;

@Service
@Slf4j
public class ChestBursterTransformerImpl implements ChestBursterTransformer {
    @Override
    public ChestBurster transform(FaceHugger faceHugger) {
            log.info(faceHugger.getName() + " has been transformed into chestburster");
            return new ChestBurster(faceHugger.getName());
    }
}
