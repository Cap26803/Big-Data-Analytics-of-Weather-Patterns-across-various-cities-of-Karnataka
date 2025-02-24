import pandas as pd

# Load the data from the HDFS or local CSV
data = pd.read_csv('/home/kushal/weath_op/part-r-00000', delimiter='\t', header=None)

# Display the first few rows to inspect
print(data.head())

# Rename columns for better readability
data.columns = ['Date', 'Temperature', 'Precipitation', 'Humidity']

# Clean missing values - Remove rows with missing data
data = data.dropna()

# Convert 'Date' to datetime
data['Date'] = pd.to_datetime(data['Date'].str.split(',').str[1])

# Handle duplicates
data = data.drop_duplicates()

# Save cleaned data
data.to_csv('/home/kushal/cleaned_weather_data.csv', index=False)

print("Data cleaned and saved successfully!")

