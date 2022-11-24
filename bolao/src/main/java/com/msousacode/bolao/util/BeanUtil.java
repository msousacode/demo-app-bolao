package com.msousacode.bolao.util;

import com.msousacode.bolao.exception.ServiceException;
import com.msousacode.bolao.persistence.entity.types.ServiceErrorsType;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class BeanUtil {

    private BeanUtil() {
    }

    public static <T, V> Object convert(T source, V target) {

        Objects.requireNonNull(source);
        Objects.requireNonNull(target);

        try {
            BeanUtils.copyProperties(source, target);
        } catch (Exception ex) {
            throw new ServiceException(ServiceErrorsType.OBJECT_CONVERSION_ERROR);
        }
        return target;
    }
}
