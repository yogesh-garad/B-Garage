package com.bgarage.inventory.data;

import com.bgarage.inventory.model.Part;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PartDao {
    private static Map<String, Part> parts = new HashMap<>();
    public void save(Part part) {
        //save it
        parts.put(part.getId(),part);
    }

    public Part get(String id) {
        return parts.get(id);
    }

    public Collection<Part> getParts() {
        return parts.values();
    }
}
