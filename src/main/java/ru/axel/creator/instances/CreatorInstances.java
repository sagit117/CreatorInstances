package ru.axel.creator.instances;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.Array;
import java.util.*;

public final class CreatorInstances {
    /**
     * Метод создает инстанс класса.
     * @param clazz класс, инстанс которого нужно создать
     * @return инстанс класса
     */
    @SuppressWarnings("unchecked")
    public static @NotNull <T> T createInstance(
        @NotNull Class<?> clazz
    ) throws InvocationTargetException, InstantiationException, IllegalAccessException, CreateInstanceException {
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getDeclaredConstructors();
        var first= Arrays.stream(constructors).findFirst().orElseThrow();

        try {
            return first.newInstance();
        } catch (IllegalArgumentException ex) {
            for (var constructor: constructors) {
                if (constructor.getGenericParameterTypes().length == 0) {
                    return constructor.newInstance();
                }
            }
        }

        throw new CreateInstanceException("Не удалось найти подходящий конструктор в классе " + clazz.getTypeName());
    }
    @SuppressWarnings("unchecked")
    public static @NotNull <T> T createInstance(
        @NotNull Class<?> clazz,
        Object... args
    ) throws InvocationTargetException, InstantiationException, IllegalAccessException, CreateInstanceException {
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getDeclaredConstructors();
        var first= Arrays.stream(constructors).findFirst().orElseThrow();

        try {
            return first.newInstance(args);
        } catch (IllegalArgumentException ex) {
            Object[] params = Arrays.stream(args).map(Object::getClass).toArray();

            for (var constructor: constructors) {
                Object[] constructorParam = Arrays.stream(constructor.getGenericParameterTypes())
                    .map(type -> switch (type.getTypeName()) {
                        case "int" -> Integer.class;
                        case "float" -> Float.class;
                        case "byte" -> Byte.class;
                        case "long" -> Long.class;
                        case "short" -> Short.class;
                        case "boolean" -> Boolean.class;
                        case "char" -> Character.class;
                        case "double" -> Double.class;
                        default -> type;
                }).toArray();

                if (constructorParam.length == params.length) {
                    int index = 0;
                    boolean isEqual = true;

                    for (var param: params) {
                        if (param != constructorParam[index]) isEqual = false;
                        index++;
                    }

                    if (isEqual) return constructor.newInstance(args);
                }
            }
        }

        throw new CreateInstanceException("Не удалось найти подходящий конструктор в классе " + clazz.getTypeName());
    }
}
