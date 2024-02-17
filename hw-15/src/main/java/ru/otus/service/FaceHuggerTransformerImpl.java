package ru.otus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.model.Egg;
import ru.otus.model.FaceHugger;

@Service
@Slf4j
public class FaceHuggerTransformerImpl implements FaceHuggerTransformer {
    @Override
    public FaceHugger transform(Egg egg) {
        log.info(egg.getName() + " has been transformed into facehugger");
        return new FaceHugger(egg.getName());
    }
}
