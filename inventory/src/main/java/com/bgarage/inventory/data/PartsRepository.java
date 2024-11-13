package com.bgarage.inventory.data;

import com.bgarage.inventory.domain.Part;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class PartsRepository {
    private static Map<String, com.bgarage.inventory.domain.Part> parts = new HashMap<>();

    public void save(String id, com.bgarage.inventory.domain.Part part) {
        //save it
        parts.put(id, part);
    }

    public boolean isExists(String id){
        return parts.containsKey(id);
    }
    public com.bgarage.inventory.domain.Part get(String id) {
        return parts.get(id);
    }

    public Collection<com.bgarage.inventory.domain.Part> getParts() {
        return parts.values();
    }

    public Part delete(String id) {
        return parts.remove(id);
    }
}
