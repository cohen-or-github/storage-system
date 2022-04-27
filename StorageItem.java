import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.lang.Math;

public abstract class StorageItem {
    private String name;
    private long date ;
    private int size;

    /**
     * A constructor
     * @param name: the item's name
     */
    public StorageItem (String name) {
        this.name = name;
        setDate();
    }

    /**
     * Get the item's name and date
     * @return: name / date
     */
    String getName (){ return this.name;}
    public long getItemDate (){ return this.date;}

    //abstract class to be fulfilled in children's classes
    abstract int getSize();
    abstract void printSorted (SortingField field, String prefix);

    /**
     * Print the system's tree starting from the current item
     * @param field
     */
    void printTree (SortingField field) {
        printSorted(field, "");
    }

    /**
     * Set a random creating date to the item
     */
    void setDate () {
        String firstDate = "2017/01/01 00:00:00";
        String secondDate = "2021/12/31 23:59:59";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = sdf.parse(firstDate);
            long min = date.getTime();
            try {
                Date newDate = sdf.parse(secondDate);
                long max = newDate.getTime();
                //define a range to the date
                long range = max - min;

                //choose a random number then adjust it to the range
                long random = Main.rnd.nextLong();
                this.date = (Math.abs(random) % range) + min;
                System.out.println(max);
                System.out.println(min);
                System.out.println(random);
            } catch (java.text.ParseException e) { }
        }
        catch (java.text.ParseException e) { }

    }

    /**
     * Convert the date's format from milliseconds to string
     * @return: the date as a string
     */
    String printingDate () {
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        Date result = new Date (this.date);
        return (simple.format(result));
    }
}
