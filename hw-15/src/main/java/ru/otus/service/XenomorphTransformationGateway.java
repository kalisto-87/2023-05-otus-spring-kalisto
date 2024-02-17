package ru.otus.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.model.Egg;
import ru.otus.model.Xenomorph;

import java.util.Collection;

@MessagingGateway
public interface XenomorphTransformationGateway {

    @Gateway(requestChannel = "eggChannel", replyChannel = "xChannel")
    Collection<Xenomorph> process(Collection<Egg> eggs);

}
