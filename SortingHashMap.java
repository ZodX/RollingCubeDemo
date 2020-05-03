import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class SortingHashMap {

    public Map<String, Integer> sortMapByValue(Map<String, Integer> map) {

        Map<String, Integer> sortedMap = map.entrySet().stream().sorted(Entry.comparingByValue())
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (o1, o2) -> o1, LinkedHashMap::new));

        return sortedMap;
    }
}