package org.datavec.spark.transform.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nd4j.linalg.dataset.DataSet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agibsonccc on 1/21/17.
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BatchCSVRecord implements Serializable {
    private List<SingleCSVRecord> records;

    /**
     * Add a record
     * @param record
     */
    public void add(SingleCSVRecord record) {
        if (records == null)
            records = new ArrayList<>();
        records.add(record);
    }


    /**
     * Return a batch record based on a dataset
     * @param dataSet the dataset to get the batch record for
     * @return the batch record
     */
    public static BatchCSVRecord fromDataSet(DataSet dataSet) {
        BatchCSVRecord batchCSVRecord = new BatchCSVRecord();
        for (int i = 0; i < dataSet.numExamples(); i++) {
            batchCSVRecord.add(SingleCSVRecord.fromRow(dataSet.get(i)));
        }

        return batchCSVRecord;
    }

}
