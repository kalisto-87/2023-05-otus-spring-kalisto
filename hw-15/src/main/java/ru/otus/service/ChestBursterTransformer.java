package ru.otus.service;

import ru.otus.model.ChestBurster;
import ru.otus.model.FaceHugger;

public interface ChestBursterTransformer {
    ChestBurster transform(FaceHugger faceHugger);
}
