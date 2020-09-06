package com.spark.anonymizer;

import org.apache.spark.sql.Row;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RowProcessorTest {

    RowProcessor rowProcessor = new RowProcessor();

    @Mock
    private Row row;

    @Test
    public void testProcessRow() {
        when(row.get(0)).thenReturn("Jagdeep");
        when(row.get(1)).thenReturn("Singh");
        when(row.get(2)).thenReturn("1 King Street Melbourne VIC 3000");
        when(row.get(3)).thenReturn("31/12/2000");
        when(row.size()).thenReturn(4);
        String anonymizedRow = rowProcessor.processRow(row);
        assertEquals("*******,*****,********************************,31/12/2000", anonymizedRow);
    }
}
