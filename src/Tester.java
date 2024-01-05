import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;

import java.util.stream.Collectors;

public class Tester {
    ArrayList<Integer> arrayList;
    LinkedList<Integer> linkedList;
    LinkedHashSet<Integer> linkedHashSet;
    TreeSet<Integer> treeSet;
    HashSet<Integer> hashSet;
    HashMap<Integer, String> hashMap;
    LinkedHashMap<Integer, String> linkedHashMap;
    TreeMap<Integer, String> treeMap;

    public int anotherI = 1;

    public LinkedHashMap<String, Long> addingResults;
    public LinkedHashMap<String, Long> filteringResults;
    public LinkedHashMap<String, Long> findingResults;
    public LinkedHashMap<String, Long> containingResults;


  public Tester() {
    this.arrayList = new ArrayList<>();
    this.linkedList = new LinkedList<>();
    this.hashSet = new HashSet<>();
    this.treeSet = new TreeSet<>();
    this.linkedHashSet = new LinkedHashSet<>();
    this.hashMap = new HashMap<>();
    this.linkedHashMap = new LinkedHashMap<>();
    this.treeMap = new TreeMap<>();

    addingResults = new LinkedHashMap<>();
    filteringResults = new LinkedHashMap<>();
    containingResults = new LinkedHashMap<>();
    findingResults = new LinkedHashMap<>();
  }

  public long testAdding(Runnable action, int count) {
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < count; i++) {
      action.run();
      anotherI++;
    }
    long endTime = System.currentTimeMillis();
    anotherI = 1;

    return endTime - startTime;
  }

  public long testActions(Runnable action) {
    long startTime = System.currentTimeMillis();
    action.run();
    long endTime = System.currentTimeMillis();
    return endTime - startTime;
  }

  public void testing (int count) {
    //Adding test
    addingResults.put("ArrayList", testAdding(() -> arrayList.add(anotherI), count));
    addingResults.put("LinkedList", testAdding(() -> linkedList.add(anotherI), count));
    addingResults.put("LinkedHashSet", testAdding(() -> linkedHashSet.add(anotherI), count));
    addingResults.put("TreeSet", testAdding(() -> treeSet.add(anotherI), count));
    addingResults.put("HashSet", testAdding(() -> hashSet.add(anotherI), count));
    addingResults.put("HashMap", testAdding(() -> hashMap.put(anotherI, "da"), count));
    addingResults.put("LinkedHashMap", testAdding(() -> linkedHashMap.put(anotherI, "da"), count));
    addingResults.put("TreeMap", testAdding(() -> treeMap.put(anotherI, "da"), count));

    //Containing test
    containingResults.put("ArrayList", testActions(() -> arrayList.contains(arrayList.size() - 1)));
    containingResults.put("LinkedList", testActions(() -> linkedList.contains(linkedList.size() - 1)));
    containingResults.put("LinkedHashSet", testActions(() -> linkedHashSet.contains(linkedHashSet.size() - 1)));
    containingResults.put("TreeSet", testActions(() -> treeSet.contains(treeSet.size() - 1)));
    containingResults.put("HashSet", testActions(() -> hashSet.contains(hashSet.size() - 1)));
    containingResults.put("HashMap", testActions(() -> hashMap.containsKey(hashMap.size() - 1)));
    containingResults.put("LinkedHashMap", testActions(() -> linkedHashMap.containsKey(linkedHashMap.size() - 1)));
    containingResults.put("TreeMap", testActions(() -> treeMap.containsKey(treeMap.size() - 1)));

    //Filtering test
    filteringResults.put("ArrayList", testActions(() -> arrayList.parallelStream().filter(x -> x % 2 == 0).collect(Collectors.toList())));
    filteringResults.put("LinkedList", testActions(() -> linkedList.parallelStream().filter(x -> x % 2 == 0).collect(Collectors.toList())));
    filteringResults.put("LinkedHashSet", testActions(() -> linkedHashSet.parallelStream().filter(x -> x % 2 == 0).collect(Collectors.toList())));
    filteringResults.put("TreeSet", testActions(() -> treeSet.parallelStream().filter(x -> x % 2 == 0).collect(Collectors.toCollection(TreeSet::new))));
    filteringResults.put("HashSet", testActions(() -> hashSet.parallelStream().filter(x -> x % 2 == 0).collect(Collectors.toSet())));
    filteringResults.put("HashMap", testActions(() -> hashMap.keySet().parallelStream().filter(x -> x % 2 == 0).collect(Collectors.toList())));
    filteringResults.put("LinkedHashMap", testActions(() -> linkedHashMap.keySet().parallelStream().filter(x -> x % 2 == 0).collect(Collectors.toList())));
    filteringResults.put("TreeMap", testActions(() -> treeMap.keySet().parallelStream().filter(x -> x % 2 == 0).collect(Collectors.toList())));

    //Finding test
    findingResults.put("ArrayList", testActions(() -> arrayList.parallelStream().filter(x -> x == arrayList.size() - 1).findAny()));
    findingResults.put("LinkedList", testActions(() -> linkedList.parallelStream().filter(x -> x == linkedList.size() - 1).findAny()));
    findingResults.put("LinkedHashSet", testActions(() -> linkedHashSet.parallelStream().filter(x -> x == linkedHashSet.size() - 1).findAny()));
    findingResults.put("TreeSet", testActions(() -> treeSet.parallelStream().filter(x -> x == treeSet.size() - 1).findAny()));
    findingResults.put("HashSet", testActions(() -> hashSet.parallelStream().filter(x -> x == hashSet.size() - 1).findAny()));
    findingResults.put("HashMap", testActions(() -> hashMap.keySet().parallelStream().filter(x -> x == hashMap.size() - 1).findAny()));
    findingResults.put("LinkedHashMap", testActions(() -> linkedHashMap.keySet().parallelStream().filter(x -> x == linkedHashMap.size() - 1).findAny()));
    findingResults.put("TreeMap", testActions(() -> treeMap.keySet().parallelStream().filter(x -> x == treeMap.size() - 1).findAny()));

    //Show results
    System.out.println("Додавання елементів:");
    System.out.println(resultSorting(addingResults).toString());
    System.out.println("Перевірка наявності елемента:");
    System.out.println(resultSorting(containingResults).toString());
    System.out.println("Фільтрування елементів:");
    System.out.println(resultSorting(filteringResults).toString());
    System.out.println("Пошук елемента:");
    System.out.println(resultSorting(findingResults).toString());
  }

  public LinkedHashMap<String, Long> resultSorting(LinkedHashMap<String, Long> list) {
    LinkedHashMap<String, Long> sortedMap = list.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (e1, e2) -> e1,
            LinkedHashMap::new
        ));
    return sortedMap;
  }
}