package my.pet.ticket.etcd.util;

import org.springframework.core.convert.TypeDescriptor;

/**
 * TypesConverter класс для преобразования типов данных в соответствии с типом дженерика
 *
 * @author <a href="mailto:baranov.alexalex@gmail.com">abaranov</a>
 */
public class TypesConverter {

    public static <T> T convert(String value, Class<T> clazz) {
        if (Integer.class.equals(TypeDescriptor.valueOf(clazz).getType())) {
            return (T)Integer.valueOf(value);
        } else {
            return (T)value;
        }
        //TODO сделать для других типов по мере необходимости
    }
}
