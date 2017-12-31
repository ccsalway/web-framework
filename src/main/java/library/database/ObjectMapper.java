package library.database;

import core.annotations.Column;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ObjectMapper<T> {

    private Class clazz;

    public ObjectMapper(Class clazz) {
        this.clazz = clazz;
    }

    public List<T> map(List<Map<String, Object>> rows) {
        List<T> list = new LinkedList<>();

        Map<String, Field> fields = new HashMap<>();
        List<Field> fieldList = Arrays.asList(clazz.getDeclaredFields());
        for (Field field : fieldList) {
            Column col = field.getAnnotation(Column.class);
            if (col != null) {
                field.setAccessible(true);
                fields.put(col.name(), field);
            }
        }

        for (Map<String, Object> row : rows) {
            try {
                T dto = (T) clazz.getConstructor().newInstance();
                for (Map.Entry<String, Object> entity : row.entrySet()) {
                    String column = entity.getKey();
                    Field field = fields.get(column);
                    if (field != null) {
                        field.set(dto, convertInstanceOfObject(entity.getValue()));
                    }
                }
                list.add(dto);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    private T convertInstanceOfObject(Object o) {
        try {
            return (T) o;
        } catch (ClassCastException e) {
            return null;
        }
    }
}
