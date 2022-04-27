public class File extends StorageItem{
    private String suffix;
    private String content = "";

    /**
     * Constructor
     * @param name: the file's name
     * @param suffix: the file's suffix
     */
    public File (String name, String suffix) {
        super(name);
        this.suffix = suffix;
    }

    //Get the file's suffix
    public String getSuffix() {return this.suffix;}

    /**
     * @return: the file's name including its suffix
     */
    @Override
    String getName() {
        String fullName = super.getName() + "." + this.suffix;
        return fullName;
    }

    /**
     * Add content to the file
     * @param content: the content to add
     */
    void addContent (String content) {
        String new_content = this.content + content;
        this.content = new_content;
    }

    /**
     * @return: the file's size
     */
    int getSize () {
        int size = content.length();
        return size;
    }

    /**
     * print the file's content by the requested format
     */
    void printContent () {
       System.out.println(this.getName() + " Size: " +
               this.getSize() + "MB Created: " + this.printingDate());
       System.out.println(this.content);
    }

    /**
     * Print the file's name as part of the system's tree
     * @param field: the sorting method
     * @param prefix: the numbers of | printed before the file's name
     */
    void printSorted (SortingField field, String prefix) {
        System.out.println (prefix + this.getName());
    }
}
