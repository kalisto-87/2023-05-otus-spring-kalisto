package ru.otus.service;

import ru.otus.model.ChestBurster;
import ru.otus.model.Xenomorph;

public interface XenomorphTransformer {
    Xenomorph transform(ChestBurster chestBurster);
}
