package org.acme.rest.json;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/fruits")
public class FruitResource {

    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FruitResource() {
        fruits.add(new Fruit("Apple", "R$ 5,00"));
        fruits.add(new Fruit("Pineapple", "R$ 7,00"));
    }

    @GET
    public Set<Fruit> list() {
        return fruits;
    }

    @GET
    @Path("/{name}")
    public Fruit byName(@PathParam("name") String name) {

        for (Fruit fruit : this.fruits) {
            if(fruit.name.equals(name)){
                return fruit;        
            }
        }

        return null;
    }

    @POST
    public Set<Fruit> add(Fruit fruit) {
        fruits.add(fruit);
        return fruits;
    }

    @DELETE
    public Set<Fruit> delete(Fruit fruit) {
        fruits.removeIf(existingFruit -> existingFruit.name.contentEquals(fruit.name));
        return fruits;
    }
}
