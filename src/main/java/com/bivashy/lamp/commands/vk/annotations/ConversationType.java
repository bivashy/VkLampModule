package com.bivashy.lamp.commands.vk.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import revxrsal.commands.annotation.DistributeOnMethods;

@DistributeOnMethods
@Retention(RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ConversationType {

    String[] conversationTypes();

}
