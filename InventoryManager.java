import java.util.ArrayList;

public class InventoryManager {
    private ArrayList<Record> availableRecords;
    private Record[] soldOutRecords;

    publicInventoryManager() {
        availableRecords = new ArrayList<>();
        soldOutRecords = new Record[0];
    }

    public void addRecord(Record record) {
        availableRecord.add(record);
    }

    public void sellRecord(Record record){
