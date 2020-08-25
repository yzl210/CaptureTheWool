package cn.leomc.capturethewool;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

public class TeamDataType<T> implements PersistentDataType<T, T> {

    private final Class<T> primitiveType;

    public TeamDataType(Class<T> primitiveType) {
        this.primitiveType = primitiveType;
    }

    @Override
    public Class<T> getPrimitiveType() {
        return primitiveType;
    }

    @Override
    public Class<T> getComplexType() {
        return primitiveType;
    }

    @Override
    public T toPrimitive(T complex, PersistentDataAdapterContext context) {
        return complex;
    }

    @Override
    public T fromPrimitive(T primitive, PersistentDataAdapterContext context) {
        return primitive;
    }
}
