import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WeatherDataReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        double totalTemperature = 0.0;
        double totalPrecipitation = 0.0;
        double totalHumidity = 0.0;
        int count = 0;

        for (Text value : values) {
            String[] fields = value.toString().split(",");
            if (fields.length == 3) { // Ensure valid input format
                try {
                    totalTemperature += Double.parseDouble(fields[0]);
                    totalPrecipitation += Double.parseDouble(fields[1]);
                    totalHumidity += Double.parseDouble(fields[2]);
                    count++;
                } catch (NumberFormatException e) {
                    // Skip malformed data
                    System.err.println("Skipping invalid data: " + value.toString());
                }
            }
        }

        if (count > 0) {
            // Calculate averages
            double avgTemperature = totalTemperature / count;
            double avgHumidity = totalHumidity / count;

            // Emit the city, date, and aggregated values
            context.write(key, new Text(avgTemperature + "\t" + totalPrecipitation + "\t" + avgHumidity));
        }
    }
}

