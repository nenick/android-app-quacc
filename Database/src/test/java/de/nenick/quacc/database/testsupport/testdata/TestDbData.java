package de.nenick.quacc.database.testsupport.testdata;


import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.nenick.quacc.database.BaseRepository;
import de.nenick.quacc.database.provider.base.AbstractContentValues;
import de.nenick.quacc.database.provider.base.AbstractCursor;

public class TestDbData {

    private long lastEntryId;
    private Map<String, Object> presetValuesMap = new HashMap<>();
    private Map<String, Long> relationsMap = new HashMap<>();

    public static TestDbData iNeed(Class<? extends AbstractContentValues> contentValuesDefinition) {
        return new TestDbData(1, contentValuesDefinition);
    }

    public static TestDbData iNeed(int count, Class<? extends AbstractContentValues> contentValuesDefinition) {
        return new TestDbData(count, contentValuesDefinition);
    }

    public TestDbData with(String key, Object value) {
        if (key.contains("__")) {
            key = key.substring(key.indexOf("__") + 2);
        }
        while (key.contains("_")) {
            int delimiterIndex = key.indexOf("_");
            key = key.substring(0, delimiterIndex) + Character.toUpperCase(key.charAt(delimiterIndex + 1)) + key.substring(delimiterIndex + 2);
        }
        String methodName = "put" + Character.toUpperCase(key.charAt(0)) + key.substring(1);
        presetValuesMap.put(methodName, value);
        return this;
    }

    public TestDbData relatedTo(AbstractCursor... relations) {
        for (AbstractCursor relation : relations) {
            String methodName = "put" + relation.getClass().getSimpleName().replace("Cursor", "Id");
            long relationId = relation.getId();
            relationsMap.put(methodName, relationId);
        }
        return this;
    }

    public <T extends AbstractCursor> List<T> in(BaseRepository repository, Class<? extends AbstractCursor> cursorWrapper) {
        for (int i = 1; i <= count; i++) {
            try {
                createContentValuesObject();
                insertDummyValues();
                storeContentIn(repository);
                readStoredData(repository, cursorWrapper);
            } catch (Exception e) {
                throw new RuntimeException("Failure for " + i + ". entry.", e);
            }
        }
        //noinspection unchecked
        return (List<T>) created;
    }

    private int count;
    private Class<? extends AbstractContentValues> contentValuesDefinition;
    private AbstractContentValues contentValues;
    private List<AbstractCursor> created = new ArrayList<>();

    private TestDbData(int count, Class<? extends AbstractContentValues> contentValuesDefinition) {
        this.count = count;
        this.contentValuesDefinition = contentValuesDefinition;
    }

    private void createContentValuesObject() throws Exception {
        contentValues = contentValuesDefinition.newInstance();
    }

    private void insertDummyValues() throws Exception {
        String methodName;

        HashMap<String, Long> openRelations = new HashMap<>(relationsMap);
        HashMap<String, Object> openPresetValuesMap = new HashMap<>(presetValuesMap);

        for (Method method : contentValuesDefinition.getDeclaredMethods()) {
            methodName = method.getName();

            if (methodName.equals("uri") || methodName.equals("update")) {
                continue;
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 0) {
                continue;
            }

            Object value;
            if (openRelations.containsKey(methodName)) {
                value = openRelations.remove(methodName);
            } else if (openPresetValuesMap.containsKey(methodName) && matchingParamType(parameterTypes[0], openPresetValuesMap.get(methodName) )) {
                value = openPresetValuesMap.remove(methodName);
            } else {
                value = createDummyValueFor(parameterTypes[0]);

                if (existLongAlsoAsDateObject(methodName, value)) continue;
            }

            method.invoke(contentValues, value);
        }

        if (openRelations.size() > 0) {
            throw new IllegalStateException("Not all relations used");
        }
        if (openPresetValuesMap.size() > 0) {
            throw new IllegalStateException("Not all preset values used");
        }
    }

    private boolean matchingParamType(Class<?> parameterType, Object obj) {
        if(parameterType.equals(obj.getClass())) {
            return true;
        }
        else if(parameterType.equals(int.class) && obj.getClass().equals(Integer.class)) {
            return true;
        }
        else if(parameterType.equals(long.class) && obj.getClass().equals(Long.class)) {
            return true;
        }
        return false;
    }

    private boolean existLongAlsoAsDateObject(String methodName, Object value) {
        return value.getClass().equals(Long.class)
                && presetValuesMap.containsKey(methodName)
                && presetValuesMap.get(methodName).getClass().equals(Date.class);
    }

    private Object createDummyValueFor(Class<?> parameterType) {
        for (FakeDataGenerator generator : FakeDataGenerator.values()) {
            if (generator.canHandle(parameterType)) {
                return generator.generateValue();
            }
        }
        throw new RuntimeException("No generator found for " + parameterType);
    }

    private void storeContentIn(BaseRepository repository) throws Exception {
        if (repository == null) throw new IllegalArgumentException("null repository");
        //noinspection unchecked
        lastEntryId = repository.insert(contentValues);
    }

    private void readStoredData(BaseRepository repository, Class<? extends AbstractCursor> cursorWrapper) throws Exception {
        Uri uri = ContentUris.withAppendedId(repository.uri(), lastEntryId);
        Cursor query = repository.getContext().getContentResolver().query(uri, null, null, null, null);
        if(query == null) {
            throw new IllegalStateException("could not query " + uri);
        }
        query.moveToNext();
        created.add(cursorWrapper.getDeclaredConstructor(Cursor.class).newInstance(query));
    }

}
