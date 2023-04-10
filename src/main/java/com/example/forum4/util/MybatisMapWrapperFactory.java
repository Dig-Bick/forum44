package com.example.forum4.util;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.wrapper.ObjectWrapper;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.MapWrapper;

import java.util.Map;

public class MybatisMapWrapperFactory implements ObjectWrapperFactory {

    @Override
    public boolean hasWrapperFor(Object object) {
        return object != null && object instanceof Map;
    }

    @Override
    public ObjectWrapper getWrapperFor(MetaObject metaObject, Object object) {
        return new MapWrapper(metaObject, (Map) object) {
            @Override
            public boolean isCollection() {
                return true;
            }
        };
    }
}
