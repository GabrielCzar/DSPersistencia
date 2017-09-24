package service;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;


public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        Iterator<IntWritable> valueIt = values.iterator();
        while (valueIt.hasNext())
            sum += valueIt.next().get();
        context.write(key, new IntWritable(sum));
    }

}
