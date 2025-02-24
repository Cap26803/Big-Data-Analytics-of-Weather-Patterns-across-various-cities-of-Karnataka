import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WeatherDataMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String filePath = context.getInputSplit().toString(); // Get input file path
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1); // Extract file name
        String cityName = fileName.replace(".csv", ""); // Extract city name

        // Skip header row
        if (key.get() == 0 && value.toString().startsWith(",date")) {
            return;
        }

        // Split input by comma (CSV format)
        String[] fields = value.toString().split(",");
        if (fields.length >= 7) { // Ensure there are enough fields
            String dateTime = fields[1]; // Extract the date-time
            String[] dateSplit = dateTime.split(" ");
            if (dateSplit.length < 2) {
                return; // Skip if date-time format is invalid
            }
            String date = dateSplit[0]; // Extract the date part

            String temperature = fields[2]; // Temperature
            String humidity = fields[3]; // Relative Humidity
            String precipitation = fields[6]; // Precipitation

            // Emit the city and date as key, and temperature, precipitation, and humidity as values
            context.write(new Text(cityName + "," + date), new Text(temperature + "," + precipitation + "," + humidity));
        }
    }
}

