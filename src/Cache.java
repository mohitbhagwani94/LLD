import java.util.HashMap;
import java.util.Map;

// Factory design pattern

public class Cache {
    public final Storage storage;
    public final EvictionPolicy evictionPolicy;

    Cache(Storage storage, EvictionPolicy evictionPolicy){
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public void put(String key, String value){
        this.storage.add(key,value);
        this.evictionPolicy.keyAccessed(key);
    }

    public String get(String key){
        String value = this.storage.get(key);
        this.evictionPolicy.keyAccessed(key);
        return value;
    }
}

class Storage{

    Map<String,String> storage; //  = new HashMap<>();
    private final Integer capacity;

    public Storage(Integer capacity){
        this.capacity = capacity;
    }

    public void add(String key, String value){
        if(capacity==storage.size()){
            System.out.println("Capacity is full");
        }
        storage.put(key,value);

    }

    public void remove(String key){
        if(storage.containsKey(key)){
            storage.remove(key);
        }
        return;
    }
    public String get(String key){
        if(storage.containsKey(key)){
            return storage.get(key);
        }
        return null;
    }

}

class DLL{}
class DLLN{}

class EvictionPolicy{
    private DLL dll;
    private Map<String, DLLN> mapper;

    public EvictionPolicy(){
        this.dll = new Dll();
        mapper = new HashMap<>();
    }

    public void keyAccessed(String key){
        detachNode(mapper.get(key));
        addNodeAtEnd(mapper.get(key));
        return;
    }

    public void evictKey(){
        dll.movehead();
        return;
    }
}