package play.modules.twig;

import static play.modules.twig.TwigDatastoreFactory.getOrInstantiateDatastore;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceConfig;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.appengine.api.datastore.Transaction;
import com.vercer.engine.persist.FindCommand;
import com.vercer.engine.persist.ObjectDatastore;
import com.vercer.engine.persist.StoreCommand;

/**
 * This class maps all methods from thread local implementation of
 * {@link ObjectDatastore}.
 * 
 * For additional information take a look official twig-persist documentation at
 * http://code.google.com/p/twig-persist.
 * 
 * @author Artem Medeu
 * 
 */
public class Twig {
    // fluent style methods
    public static StoreCommand store() {
        return getOrInstantiateDatastore().store();
    }

    public static FindCommand find() {
        return getOrInstantiateDatastore().find();
    }

    // convenience store methods
    public static Key store(Object instance) {
        return getOrInstantiateDatastore().store(instance);
    }

    public static Key store(Object instance, String keyName) {
        return getOrInstantiateDatastore().store(instance, keyName);
    }

    public static Key store(Object instance, String keyName, Object parent) {
        return getOrInstantiateDatastore().store(instance, keyName, parent);
    }

    public static Key store(Object instance, Object parent) {
        return getOrInstantiateDatastore().store(instance, parent);
    }

    public static <T> Map<T, Key> storeAll(Collection<? extends T> instances) {
        return getOrInstantiateDatastore().storeAll(instances);
    }

    public static <T> Map<T, Key> storeAll(Collection<? extends T> instances, Object parent) {
        return getOrInstantiateDatastore().storeAll(instances, parent);
    }

    // convenience load methods
    @SuppressWarnings("unchecked")
    public static <T> T load(Key key) {
        return (T) getOrInstantiateDatastore().load(key);
    }

    public static <T> T load(Class<T> type, Object key) {
        return getOrInstantiateDatastore().load(type, key);
    }

    public static <T> T load(Class<T> type, Object key, Object parent) {
        return getOrInstantiateDatastore().load(type, key, parent);
    }

    // convenience find methods
    public static <T> QueryResultIterator<T> find(Class<T> type) {
        return getOrInstantiateDatastore().find(type);
    }

    public static <T> QueryResultIterator<T> find(Class<T> type, Object ancestor) {
        return getOrInstantiateDatastore().find(type, ancestor);
    }

    // convenience delete methods
    public static void delete(Object instance) {
        getOrInstantiateDatastore().delete(instance);
    }

    public static void deleteAll(Type type) {
        getOrInstantiateDatastore().deleteAll(type);
    }

    public static void deleteAll(Collection<?> instances) {
        getOrInstantiateDatastore().deleteAll(instances);
    }

    // activation
    public static int getActivationDepth() {
        return getOrInstantiateDatastore().getActivationDepth();
    }

    public static void setActivationDepth(int depth) {
        getOrInstantiateDatastore().setActivationDepth(depth);
    }

    public static void refresh(Object instance) {
        getOrInstantiateDatastore().refresh(instance);
    }

    public static void update(Object instance) {
        getOrInstantiateDatastore().update(instance);
    }

    public static void storeOrUpdate(Object instance) {
        getOrInstantiateDatastore().storeOrUpdate(instance);
    }

    public static void storeOrUpdate(Object instance, Object parent) {
        getOrInstantiateDatastore().storeOrUpdate(instance, parent);
    }

    // cache control operations
    public static void associate(Object instance) {
        getOrInstantiateDatastore().associate(instance);
    }

    public static void associate(Object instance, Key key) {
        getOrInstantiateDatastore().associate(instance, key);
    }

    public static void disassociate(Object instance) {
        getOrInstantiateDatastore().disassociate(instance);
    }

    public static void disassociateAll() {
        getOrInstantiateDatastore().disassociateAll();
    }

    public static Key associatedKey(Object instance) {
        return getOrInstantiateDatastore().associatedKey(instance);
    }

    // type-safe to low-level bridge methods
    public static DatastoreService getService() {
        return getOrInstantiateDatastore().getService();
    }

    public static Query query(Type type) {
        return getOrInstantiateDatastore().query(type);
    }

    @SuppressWarnings("unchecked")
    public static <T> T toTypesafe(Entity entity) {
        return (T) getOrInstantiateDatastore().toTypesafe(entity);
    }

    // transactions
    public static Transaction beginTransaction() {
        return getOrInstantiateDatastore().beginTransaction();
    }

    public static Transaction getTransaction() {
        return getOrInstantiateDatastore().getTransaction();
    }

    // configuration used to change underlying datastore options
    public static void setService(DatastoreService service) {
        getOrInstantiateDatastore().setService(service);
    }

    public static void setServiceFromConfig(DatastoreServiceConfig config) {
        getOrInstantiateDatastore().setServiceFromConfig(config);
    }
}
