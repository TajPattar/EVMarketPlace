package persistence;

import org.json.JSONObject;

// The following code was taken / made with guidance from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/Writable.java
public interface Writable {

    //EFFECTS: returns this as JSON Object

    JSONObject toJson();
}
