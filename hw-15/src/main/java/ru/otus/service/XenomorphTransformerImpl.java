package ru.otus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.model.ChestBurster;
import ru.otus.model.Xenomorph;

@Service
@Slf4j
public class XenomorphTransformerImpl implements XenomorphTransformer {

    @Override
    public Xenomorph transform(ChestBurster chestBurster) {
        log.info(chestBurster.getName() + " has been transformed into xenomorph");
        return new Xenomorph(chestBurster.getName());
    }
}
