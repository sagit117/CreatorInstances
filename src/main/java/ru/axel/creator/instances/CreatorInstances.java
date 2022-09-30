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
    public static @NotNull <T> T createInstance(@NotNull Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getDeclaredConstructors();
        var first= Arrays.stream(constructors).findFirst().orElseThrow();

        return first.newInstance();
    }
    @SuppressWarnings("unchecked")
    public static @NotNull <T> T createInstance(@NotNull Class<?> clazz, Object... args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getDeclaredConstructors();
        var first= Arrays.stream(constructors).findFirst().orElseThrow();

        return first.newInstance(args);
    }
}
