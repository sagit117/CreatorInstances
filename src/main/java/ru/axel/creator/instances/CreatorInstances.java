package ru.axel.creator.instances;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public final class CreatorInstances {
    /**
     * Метод создает инстанс класса.
     * @param clazz класс, инстанс которого нужно создать
     * @return инстанс класса
     */
    @SuppressWarnings("unchecked")
    public static @NotNull <T> T createInstance(@NotNull Class<?> clazz) {
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getDeclaredConstructors();
        var first= Arrays.stream(constructors).findFirst();

        try {
            return first.orElseThrow().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    public static @NotNull <T> T createInstance(@NotNull Class<?> clazz, Object... args) {
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getDeclaredConstructors();
        var first= Arrays.stream(constructors).findFirst();

        try {
            return first.orElseThrow().newInstance(args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
