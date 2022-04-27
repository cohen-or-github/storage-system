import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Folder extends StorageItem {
    private ArrayList<StorageItem> itemsList = new ArrayList<>();

    /**
     * The constructor
     * @param name: the folder's name
     */
    public Folder (String name){
        super(name);
        itemsList.clear();
    }

    /**
     * @return the folder's item list
     */
    public ArrayList<StorageItem> getList() {return this.itemsList;}

    /**
     * @return the folder's size
     */
    @Override
    public int getSize() {
        int sum = 0;
        for (StorageItem storageItem : itemsList) {
            sum += storageItem.getSize();
        }
        return sum;
    }

    /**
     * Add item to the folder
     * @param item: the item to add
     * @return: true if the item was added.
     *          false if there's an item with the same name.
     */
    public boolean addItem(StorageItem item){
        if(itemsList.size() == 0){
            itemsList.add(item);
            return true;
        }
        for (StorageItem currentItem : itemsList){
            if (!currentItem.getName().equals(item.getName())) {
                itemsList.add(item);
                return true;
            }
        }
        return false;
    }

    /**
     * Print the tree by sorted order & defined format
     * @param field: the method by which we sort the list
     * @param prefix: the number of | printed before the item's name
     */
    void printSorted (SortingField field, String prefix) {
        System.out.println(prefix + this.getName());
        String new_prefix = "|   " + prefix;

        StorageItem [] sortedList = new StorageItem[this.itemsList.size()];
        StorageItem[] newArray = new StorageItem[this.itemsList.size()];
        this.itemsList.toArray(newArray);

        //Sort the list by the relevant method
        if (field == SortingField.NAME)
            sortedList = sortByName(newArray);
        else if (field == SortingField.SIZE)
            sortedList = sortBySize(newArray);
        else
            sortedList = sortByDate(newArray);

        for (StorageItem item: sortedList) { item.printSorted(field,
                new_prefix);}
    }

    /**
     * Sort the items by their names
     * @param itemsList: the list of items to sort
     * @return: a new sorted list
     */
    StorageItem[] sortByName (StorageItem[] itemsList) {
       Comparator<StorageItem> nameComparator =
               Comparator.comparing(StorageItem::getName);
       Arrays.sort(itemsList, nameComparator);
       return itemsList;
    }

    /**
     * Sort the items by their size
     * if there are few items with the same size, sort namely
     * @param itemsList: the list of items to sort
     * @return: a new sorted list
     */
    StorageItem[] sortBySize (StorageItem[] itemsList) {
        Comparator<StorageItem> sizeComparator =
                Comparator.comparing(StorageItem::getSize);
        Comparator<StorageItem> nameComparator =
                Comparator.comparing(StorageItem:: getName);
        Comparator<StorageItem> comparator =
                sizeComparator.thenComparing(nameComparator);
        Arrays.sort(itemsList, comparator);
        return itemsList;
    }

    /**
     * Sort the items by their creating dates
     * @param itemsList: the list of items to sort
     * @return: a new sorted list
     */
    StorageItem[] sortByDate (StorageItem[] itemsList) {
        Comparator<StorageItem> dateComparator =
                Comparator.comparing(StorageItem::getItemDate);
        Arrays.sort(itemsList, dateComparator);
        return itemsList;
    }

    /**
     * Check if there's a file with the specified path
     * @param path: the path
     * @return: the file with the path
     */
    public File findFile(String path){
        for (StorageItem item : itemsList){
            String tempPath = this.getName();
            if (item instanceof Folder){
                tempPath = tempPath + "/" + item.getName();
                for (StorageItem item1 : ((Folder) item).itemsList){
                    if (path.equals(tempPath + "/" + item1.getName())){
                        return (File) item1;
                    }
                    else if (item1 instanceof Folder){
                        tempPath = tempPath + "/" + item1.getName();
                        for (StorageItem item2 : ((Folder) item1).itemsList){
                            if (path.equals(tempPath + "/" + item2.getName())){
                                return (File) item2;
                            }
                            return null;
                        }
                    }
                    tempPath = "";
                }
            }
            else {
                if (path.equals(tempPath + "/" + item.getName())){
                    return (File) item;
                }
            }
        }
        return null;
    }
}