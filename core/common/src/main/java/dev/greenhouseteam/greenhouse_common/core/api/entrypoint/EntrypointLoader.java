package dev.greenhouseteam.greenhouse_common.core.api.entrypoint;

import dev.greenhouseteam.greenhouse_common.core.api.GreenhouseCommon;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class EntrypointLoader {
    public static <T> List<T> loadEntrypoints(String name, Class<T> clazz) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        var entries = new ArrayList<String>();
        for (var mod : GreenhouseCommon.getHelper().getLoadedMods()) {
            if (mod.getConfig().isEmpty())
                continue;
            var config = mod.getConfig().get();
            for (var entry : config.getEntrypoints(name))
                if (!entries.contains(entry))
                    entries.add(entry);
        }

        var entryClasses = new ArrayList<T>();
        for (var entry : entries)
            entryClasses.add(clazz.cast(Class.forName(entry).getConstructor().newInstance()));

        return entryClasses;
    }
}
